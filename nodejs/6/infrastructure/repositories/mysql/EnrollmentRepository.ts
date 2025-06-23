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
                })
        );
    }

    async save(enrollment: Enrollment): Promise<void> {
        await this.#db.query(
            "INSERT INTO enrollments (student_id, course_id) VALUES (?, ?)",
            [enrollment.studentId, enrollment.courseId]
        );
    }

    async update(enrollment: Enrollment): Promise<void> {
        // Assuming you want to update to a new course for a student
        // You may need a composite key or unique id for enrollments
        // This is a placeholder and may need adjustment
        await this.#db.query(
            "UPDATE enrollments SET course_id = ? WHERE student_id = ? AND course_id = ?",
            [enrollment.courseId, enrollment.studentId, enrollment.courseId]
        );
    }

    async delete(enrollment: Enrollment): Promise<void> {
        await this.#db.query(
            "DELETE FROM enrollments WHERE student_id = ? AND course_id = ?",
            [enrollment.studentId, enrollment.courseId]
        );
    }
}