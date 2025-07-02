import { EnrollmentRepository } from "../repositiories/EnrollmentRepository";
import { Enrollment } from "../entities/Enrollment/Enrollment";
import { Student } from "../entities/Student/Student";
import { Course } from "../entities/Course/Course";

export class CreateEnrollmentUseCase {
    constructor(private enrollmentRepository: EnrollmentRepository) {}

    async execute(enrollment: Enrollment): Promise<void> {
        await this.enrollmentRepository.save(enrollment);
    }
}

export class GetEnrollmentsByStudentUseCase {
    constructor(private enrollmentRepository: EnrollmentRepository) {}

    async execute(student: Student): Promise<Enrollment[]> {
        return this.enrollmentRepository.findByStudent(student);
    }
}

export class GetEnrollmentsByCourseUseCase {
    constructor(private enrollmentRepository: EnrollmentRepository) {}

    async execute(course: Course): Promise<Enrollment[]> {
        return this.enrollmentRepository.findByCourse(course);
    }
}

export class GetAllEnrollmentsUseCase {
    constructor(private enrollmentRepository: EnrollmentRepository) {}

    async execute(): Promise<Enrollment[]> {
        return this.enrollmentRepository.findAll();
    }
}

export class UpdateEnrollmentUseCase {
    constructor(private enrollmentRepository: EnrollmentRepository) {}

    async execute(enrollment: Enrollment): Promise<void> {
        await this.enrollmentRepository.update(enrollment);
    }
}

export class DeleteEnrollmentUseCase {
    constructor(private enrollmentRepository: EnrollmentRepository) {}

    async execute(enrollment: Enrollment): Promise<void> {
        await this.enrollmentRepository.delete(enrollment);
    }
}
