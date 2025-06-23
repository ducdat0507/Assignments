import { Course } from "../entities/Course/Course";
import { Enrollment } from "../entities/Enrollment/Enrollment";
import { Student } from "../entities/Student/Student";

export interface EnrollmentRepository {
    findByStudent(student: Student): Promise<Enrollment[]>;
    findByCourse(course: Course): Promise<Enrollment[]>;
    findAll(): Promise<Enrollment[]>;

    save(enrollment: Enrollment): Promise<void>;
    update(enrollment: Enrollment): Promise<void>;

    delete(enrollment: Enrollment): Promise<void>;
}
