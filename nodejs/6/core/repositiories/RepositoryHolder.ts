import { CourseRepository } from "./CourseRepository";
import { EnrollmentRepository } from "./EnrollmentRepository";
import { StudentRepository } from "./StudentRepository";

export interface RepositoryHolder {
    get studentRepository(): StudentRepository;
    get courseRepository(): CourseRepository;
    get enrollmentRepository(): EnrollmentRepository;
}