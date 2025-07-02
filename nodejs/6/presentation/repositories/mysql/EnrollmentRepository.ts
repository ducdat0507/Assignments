import { EnrollmentRepository } from "../../../core/repositiories/EnrollmentRepository";
import { Enrollment } from "../../../core/entities/Enrollment/Enrollment";
import { Student } from "../../../core/entities/Student/Student";
import { Course } from "../../../core/entities/Course/Course";
import { Pool } from "mysql2/promise";

export class MySQLEnrollmentRepository implements EnrollmentRepository {

    #db: Pool;

    constructor(db: Pool) {
        this.#db = db;
    }

    async findByStudent(student: Student): Promise<Enrollment[]> {
        const [rows] = await this.#db.query(
            "SELECT * FROM enrollments WHERE student_id = ?",
            [student.id]
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

    async findByCourse(course: Course): Promise<Enrollment[]> {
        const [rows] = await this.#db.query(
            "SELECT * FROM enrollments WHERE course_id = ?",
            [course.id]
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

    async save(enrollment: Enrollment): Promise<void> {
        await this.#db.query(
            `INSERT INTO enrollments (student_id, course_id, enroll_date) VALUES (?, ?, ?)
             ON DUPLICATE KEY UPDATE enroll_date = VALUES(enroll_date)`,
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
