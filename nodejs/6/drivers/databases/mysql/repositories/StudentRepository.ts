import { StudentRepository } from "../../../../core/repositiories/StudentRepository";
import { Student } from "../../../../core/entities/Student/Student";
import { StudentConstructorArgs } from "../../../../core/entities/Student/StudentConstructorArgs";
import { Pool } from "mysql2/promise";
import { EntityNotFoundError } from "../../../../core/errors/EntityNotFoundError";
import { StudentAdapter } from "../adapters/StudentAdapter";
import { AlreadyExistsError } from "../../../../core/errors/AlreadyExistsError";

export class MySQLStudentRepository implements StudentRepository {

    #db: Pool;

    constructor(db: Pool) {
        this.#db = db;
    }

    async findById(id: string): Promise<Student> {
        const [rows] = await this.#db.query(
            "SELECT * FROM students WHERE id = ?",
            [id]
        );
        const result = (rows as any[])[0];
        return StudentAdapter.fromRow(result);
    }

    async findAll(): Promise<Student[]> {
        const [rows] = await this.#db.query("SELECT * FROM students");
        return (rows as any[]).map(StudentAdapter.fromRow);
    }

    async create(student: Student): Promise<string> {
        let [result] = await this.#db.query(
            `INSERT INTO students (full_name, birthdate) VALUES (?, ?)`,
            [student.fullName, student.birthdate]
        );
        return result.insertId + "";
    }

    async update(student: Student): Promise<void> {
        let [result] = await this.#db.query(
            "UPDATE students SET full_name = ?, birthdate = ? WHERE id = ?",
            [student.fullName, student.birthdate, student.id]
        );
        if (!result.affectedRows) throw new EntityNotFoundError("Student", student.id!);
    }

    async delete(id: string): Promise<void> {
        let [result] = await this.#db.query("DELETE FROM students WHERE id = ?", [id]);
        if (!result.affectedRows) throw new EntityNotFoundError("Student", id);
    }
}