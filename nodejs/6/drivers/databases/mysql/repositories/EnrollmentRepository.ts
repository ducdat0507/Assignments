import { EnrollmentRepository } from "../../../../core/repositiories/EnrollmentRepository";
import { Enrollment } from "../../../../core/entities/Enrollment/Enrollment";
import { Student } from "../../../../core/entities/Student/Student";
import { Course } from "../../../../core/entities/Course/Course";
import { Pool } from "mysql2/promise";
import { EntityNotFoundError } from "../../../../core/errors/EntityNotFoundError";
import { EnrollmentAdapter } from "../adapters/EnrollmentAdapter";
import { AlreadyEnrolledError } from "../../../../core/errors/AlreadyEnrolledError";
import { QueryResult } from "../QueryResult";

export class MySQLEnrollmentRepository implements EnrollmentRepository {

    #db: Pool;

    constructor(db: Pool) {
        this.#db = db;
    }

    async findByIds(studentId: string, courseId: string): Promise<Enrollment> {
        const [rows] = await this.#db.query(
            "SELECT * FROM enrollments WHERE student_id = ? AND course_id = ?",
            [studentId, courseId]
        );
        const result = (rows as any[])[0];
        if (!result) throw new EntityNotFoundError("Enrollment", studentId, courseId);
        return EnrollmentAdapter.fromRow(result);
    }

    async findByStudentId(id: string): Promise<Enrollment[]> {
        const [rows] = await this.#db.query(
            "SELECT * FROM enrollments WHERE student_id = ?",
            [id]
        );
        return (rows as any[]).map(EnrollmentAdapter.fromRow);
    }

    async findByCourseId(id: string): Promise<Enrollment[]> {
        const [rows] = await this.#db.query(
            "SELECT * FROM enrollments WHERE course_id = ?",
            [id]
        );
        return (rows as any[]).map(EnrollmentAdapter.fromRow);
    }

    async findAll(): Promise<Enrollment[]> {
        const [rows] = await this.#db.query("SELECT * FROM enrollments");
        return (rows as any[]).map(EnrollmentAdapter.fromRow);
    }

    async create(enrollment: Enrollment): Promise<void> {
        try {
            await this.#db.query(
                `INSERT INTO enrollments (student_id, course_id, enrollment_type, enroll_date, enroll_until) VALUES (?, ?, ?, ?, ?)`,
                [
                    enrollment.studentId,
                    enrollment.courseId,
                    enrollment.enrollmentType,
                    enrollment.enrollDate,
                    enrollment.enrollUntil ?? null
                ]
            );
        } catch (e) {
            if (e.code == "ER_DUP_ENTRY") {
                throw new AlreadyEnrolledError(enrollment.courseId, enrollment.studentId);
            }
        }
    }

    async update(enrollment: Enrollment): Promise<void> {
        let [result] = await this.#db.query(
            "UPDATE enrollments SET enrollment_type = ?, enroll_date = ?, enroll_until = ? WHERE student_id = ? AND course_id = ?",
            [
                enrollment.enrollmentType,
                enrollment.enrollDate,
                enrollment.enrollUntil ?? null,
                enrollment.studentId,
                enrollment.courseId
            ]
        ) as unknown as [QueryResult];
        if (!result.affectedRows) throw new EntityNotFoundError("Enrollment", enrollment.studentId, enrollment.courseId);
    }

    async delete(studentId: string, courseId: string): Promise<void> {
        let [result] = await this.#db.query(
            "DELETE FROM enrollments WHERE student_id = ? AND course_id = ?",
            [studentId, courseId]
        ) as unknown as [QueryResult];
        if (!result.affectedRows) throw new EntityNotFoundError("Enrollment", studentId, courseId);
    }
}
