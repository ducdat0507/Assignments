import { Course } from "../entities/Course/Course";
import { Enrollment } from "../entities/Enrollment/Enrollment";
import { Student } from "../entities/Student/Student";

export interface EnrollmentRepository {
    findByIds(studentId: string, courseId: string): Promise<Enrollment>;
    findByStudentId(id: string): Promise<Enrollment[]>;
    findByCourseId(id: string): Promise<Enrollment[]>;
    findAll(): Promise<Enrollment[]>;

    create(enrollment: Enrollment): Promise<void>;
    update(enrollment: Enrollment): Promise<void>;

    delete(enrollment: Enrollment): Promise<void>;
}
