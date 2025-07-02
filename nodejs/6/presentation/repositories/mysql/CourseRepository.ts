import { CourseRepository } from "../../../core/repositiories/CourseRepository";
import { Course } from "../../../core/entities/Course/Course";
import { Pool } from "mysql2/promise";

export class MySQLCourseRepository implements CourseRepository {

    #db: Pool;

    constructor(db: Pool) {
        this.#db = db;
    }

    async findById(id: string): Promise<Course | null> {
        const [rows] = await this.#db.query(
            "SELECT * FROM courses WHERE id = ?",
            [id]
        );
        const result = (rows as any[])[0];
        if (!result) return null;
        return new Course({
            id: result.id,
            name: result.name,
        });
    }

    async findAll(): Promise<Course[]> {
        const [rows] = await this.#db.query("SELECT * FROM courses");
        return (rows as any[]).map(
            (row) =>
                new Course({
                    id: row.id,
                    name: row.name,
                })
        );
    }

    async save(course: Course): Promise<void> {
        await this.#db.query(
            `INSERT INTO courses (id, name) VALUES (?, ?)
             ON DUPLICATE KEY UPDATE name = VALUES(name)`,
            [course.id, course.name]
        );
    }

    async update(course: Course): Promise<void> {
        await this.#db.query(
            "UPDATE courses SET name = ? WHERE id = ?",
            [course.name, course.id]
        );
    }

    async delete(course: Course): Promise<void> {
        await this.#db.query("DELETE FROM courses WHERE id = ?", [course.id]);
    }
}