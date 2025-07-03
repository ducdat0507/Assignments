import { EnrollmentRepository } from "../../../../core/repositiories/EnrollmentRepository";
import { Enrollment } from "../../../../core/entities/Enrollment/Enrollment";
import { Student } from "../../../../core/entities/Student/Student";
import { Course } from "../../../../core/entities/Course/Course";
import { Pool } from "mysql2/promise";

export class MySQLEnrollmentRepository implements EnrollmentRepository {

    #db: Pool;

    constructor(db: Pool) {
        this.#db = db;
    }

    async findByStudentId(id: string): Promise<Enrollment[]> {
        const [rows] = await this.#db.query(
            "SELECT * FROM enrollments WHERE student_id = ?",
            [id]
        );
        return (rows as any[]).map(
            (row) =>
                new Enrollment({
                    studentId: row.student_id,
                    courseId: row.course_id,
                    enrollDate: row.enroll_date
                })
        );
    }

    async findByCourseId(id: string): Promise<Enrollment[]> {
        const [rows] = await this.#db.query(
            "SELECT * FROM enrollments WHERE course_id = ?",
            [id]
        );
        return (rows as any[]).map(
            (row) =>
                new Enrollment({
                    studentId: row.student_id,
                    courseId: row.course_id,
                    enrollDate: row.enroll_date
                })
        );
    }

    async findAll(): Promise<Enrollment[]> {
        const [rows] = await this.#db.query("SELECT * FROM enrollments");
        return (rows as any[]).map(
            (row) =>
                new Enrollment({
                    studentId: row.student_id,
                    courseId: row.course_id,
                    enrollDate: row.enroll_date
                })
        );
    }

    async create(enrollment: Enrollment): Promise<void> {
        await this.#db.query(
            `INSERT INTO enrollments (student_id, course_id, enroll_date) VALUES (?, ?, ?)`,
            [enrollment.studentId, enrollment.courseId, enrollment.enrollDate]
        );
    }

    async update(enrollment: Enrollment): Promise<void> {
        await this.#db.query(
            "UPDATE enrollments SET enroll_date = ? WHERE student_id = ? AND course_id = ?",
            [enrollment.enrollDate, enrollment.studentId, enrollment.courseId]
        );
    }

    async delete(enrollment: Enrollment): Promise<void> {
        await this.#db.query(
            "DELETE FROM enrollments WHERE student_id = ? AND course_id = ?",
            [enrollment.studentId, enrollment.courseId]
        );
    }
}
