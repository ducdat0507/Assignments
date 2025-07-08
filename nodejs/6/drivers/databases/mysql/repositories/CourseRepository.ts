import { CourseRepository } from "../../../../core/repositiories/CourseRepository";
import { Course } from "../../../../core/entities/Course/Course";
import { Pool } from "mysql2/promise";
import { EntityNotFoundError } from "../../../../core/errors/EntityNotFoundError";
import { CourseAdapter } from "../adapters/CourseAdapter";

export class MySQLCourseRepository implements CourseRepository {

    #db: Pool;

    constructor(db: Pool) {
        this.#db = db;
    }

    async findById(id: string): Promise<Course> {
        const [rows] = await this.#db.query(
            "SELECT * FROM courses WHERE id = ?",
            [id]
        );
        const result = (rows as any[])[0];
        if (!result) throw new EntityNotFoundError("Course", id);
        return CourseAdapter.fromRow(result);
    }

    async findAll(): Promise<Course[]> {
        const [rows] = await this.#db.query("SELECT * FROM courses");
        return (rows as any[]).map(CourseAdapter.fromRow);
    }

    async create(course: Course): Promise<string> {
        let [result] = await this.#db.query(
            `INSERT INTO courses (name) VALUES (?)`,
            [course.name]
        );
        return result.insertId;
    }

    async update(course: Course): Promise<void> {
        let [result] = await this.#db.execute(
            "UPDATE courses SET name = ? WHERE id = ?",
            [course.name, course.id]
        );
        if (!result.affectedRows) throw new EntityNotFoundError("Course", course.id!);
    }

    async delete(id: string): Promise<void> {
        let [result] = await this.#db.query("DELETE FROM courses WHERE id = ?", [id]);
        if (!result.affectedRows) throw new EntityNotFoundError("Course", id);
    }
}