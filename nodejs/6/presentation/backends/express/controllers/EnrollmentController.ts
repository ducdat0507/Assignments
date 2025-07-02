import { Request, Response } from "express";
import { CreateEnrollmentUseCase, GetEnrollmentsByStudentUseCase, GetEnrollmentsByCourseUseCase, GetAllEnrollmentsUseCase, UpdateEnrollmentUseCase, DeleteEnrollmentUseCase } from "../../../../core/useCases/EnrollmentUseCases";
import { EnrollmentDTO } from "../../../../infrastructure/dtos/EnrollmentDTO";
import { Enrollment } from "../../../../core/entities/Enrollment/Enrollment";
import { Student } from "../../../../core/entities/Student/Student";
import { Course } from "../../../../core/entities/Course/Course";

export class EnrollmentController {
    constructor(
        private createEnrollmentUseCase: CreateEnrollmentUseCase,
        private getEnrollmentsByStudentUseCase: GetEnrollmentsByStudentUseCase,
        private getEnrollmentsByCourseUseCase: GetEnrollmentsByCourseUseCase,
        private getAllEnrollmentsUseCase: GetAllEnrollmentsUseCase,
        private updateEnrollmentUseCase: UpdateEnrollmentUseCase,
        private deleteEnrollmentUseCase: DeleteEnrollmentUseCase
    ) {}

    async create(req: Request, res: Response) {
        try {
            const dto = new EnrollmentDTO(req.body.student_id, req.body.course_id);
            const enrollment = EnrollmentDTO.toEntity(dto);
            await this.createEnrollmentUseCase.execute(enrollment);
            res.status(201).json(dto);
        } catch (err) {
            res.status(400).json({ error: err.message });
        }
    }

    async getByStudent(req: Request, res: Response) {
        try {
            const student = new Student({ id: req.params.studentId, fullName: '', birthdate: new Date() });
            const enrollments = await this.getEnrollmentsByStudentUseCase.execute(student);
            res.json(enrollments.map(EnrollmentDTO.fromEntity));
        } catch (err) {
            res.status(400).json({ error: err.message });
        }
    }

    async getByCourse(req: Request, res: Response) {
        try {
            const course = new Course({ id: req.params.courseId, name: '' });
            const enrollments = await this.getEnrollmentsByCourseUseCase.execute(course);
            res.json(enrollments.map(EnrollmentDTO.fromEntity));
        } catch (err) {
            res.status(400).json({ error: err.message });
        }
    }

    async getAll(req: Request, res: Response) {
        try {
            const enrollments = await this.getAllEnrollmentsUseCase.execute();
            res.json(enrollments.map(EnrollmentDTO.fromEntity));
        } catch (err) {
            res.status(400).json({ error: err.message });
        }
    }

    async update(req: Request, res: Response) {
        try {
            const dto = new EnrollmentDTO(req.body.student_id, req.body.course_id);
            const enrollment = EnrollmentDTO.toEntity(dto);
            await this.updateEnrollmentUseCase.execute(enrollment);
            res.json(dto);
        } catch (err) {
            res.status(400).json({ error: err.message });
        }
    }

    async delete(req: Request, res: Response) {
        try {
            const dto = new EnrollmentDTO(req.body.student_id, req.body.course_id);
            const enrollment = EnrollmentDTO.toEntity(dto);
            await this.deleteEnrollmentUseCase.execute(enrollment);
            res.status(204).send();
        } catch (err) {
            res.status(400).json({ error: err.message });
        }
    }
}
