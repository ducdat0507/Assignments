import { EnrollmentRepository } from "../../core/repositiories/EnrollmentRepository";
import { Enrollment } from "../../core/entities/Enrollment/Enrollment";
import { Student } from "../../core/entities/Student/Student";
import { Course } from "../../core/entities/Course/Course";

export class CreateEnrollmentUseCase {
    constructor(private enrollmentRepository: EnrollmentRepository) {}

    async execute(enrollment: Enrollment): Promise<void> {
        await this.enrollmentRepository.create(enrollment);
    }
}

export class GetEnrollmentsByIdsUseCase {
    constructor(private enrollmentRepository: EnrollmentRepository) {}

    async execute(studentId: string, courseId: string): Promise<Enrollment> {
        return this.enrollmentRepository.findByIds(studentId, courseId);
    }
}

export class GetEnrollmentsByStudentIdUseCase {
    constructor(private enrollmentRepository: EnrollmentRepository) {}

    async execute(id: string): Promise<Enrollment[]> {
        return this.enrollmentRepository.findByStudentId(id);
    }
}

export class GetEnrollmentsByCourseIdUseCase {
    constructor(private enrollmentRepository: EnrollmentRepository) {}

    async execute(id: string): Promise<Enrollment[]> {
        return this.enrollmentRepository.findByCourseId(id);
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

    async execute(studentId: string, courseId: string): Promise<void> {
        await this.enrollmentRepository.delete(studentId, courseId);
    }
}
