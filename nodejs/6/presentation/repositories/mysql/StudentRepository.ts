import { StudentRepository } from "../../../core/repositiories/StudentRepository";
import { Student } from "../../../core/entities/Student/Student";
import { StudentConstructorArgs } from "../../../core/entities/Student/StudentConstructorArgs";
import { Pool } from "mysql2/promise";

export class MySQLStudentRepository implements StudentRepository {

    #db: Pool;

    constructor(db: Pool) {
        this.#db = db;
    }

    async findById(id: string): Promise<Student | null> {
        const [rows] = await this.#db.query(
            "SELECT * FROM students WHERE id = ?",
            [id]
        );
        const result = (rows as any[])[0];
        if (!result) return null;
        return new Student({
            id: result.id,
            fullName: result.full_name,
            birthdate: new Date(result.birthdate),
        });
    }

    async findAll(): Promise<Student[]> {
        const [rows] = await this.#db.query("SELECT * FROM students");
        return (rows as any[]).map(
            (row) =>
                new Student({
                    id: row.id,
                    fullName: row.full_name,
                    birthdate: new Date(row.birthdate),
                })
        );
    }

    async save(student: Student): Promise<void> {
        await this.#db.query(
            `INSERT INTO students (id, full_name, birthdate) VALUES (?, ?, ?)
             ON DUPLICATE KEY UPDATE full_name = VALUES(full_name), birthdate = VALUES(birthdate)`,
            [student.id, student.fullName, student.birthdate]
        );
    }

    async update(student: Student): Promise<void> {
        await this.#db.query(
            "UPDATE students SET full_name = ?, birthdate = ? WHERE id = ?",
            [student.fullName, student.birthdate, student.id]
        );
    }

    async delete(student: Student): Promise<void> {
        await this.#db.query("DELETE FROM students WHERE id = ?", [student.id]);
    }
}