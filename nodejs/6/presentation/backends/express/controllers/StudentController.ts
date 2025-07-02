import { Request, Response } from "express";
import { CreateStudentUseCase, GetStudentByIdUseCase, GetAllStudentsUseCase, UpdateStudentUseCase, DeleteStudentUseCase } from "../../../../core/useCases/StudentUseCases";
import { StudentDTO } from "../../../../infrastructure/dtos/StudentDTO";
import { Student } from "../../../../core/entities/Student/Student";

export class StudentController {
    constructor(
        private createStudentUseCase: CreateStudentUseCase,
        private getStudentByIdUseCase: GetStudentByIdUseCase,
        private getAllStudentsUseCase: GetAllStudentsUseCase,
        private updateStudentUseCase: UpdateStudentUseCase,
        private deleteStudentUseCase: DeleteStudentUseCase
    ) {}

    async create(req: Request, res: Response) {
        try {
            const dto = new StudentDTO(req.body.id, req.body.full_name, new Date(req.body.birthdate));
            const student = StudentDTO.toEntity(dto);
            await this.createStudentUseCase.execute(student);
            res.status(201).json(dto);
        } catch (err) {
            res.status(400).json({ error: err.message });
        }
    }

    async getById(req: Request, res: Response) {
        try {
            const student = await this.getStudentByIdUseCase.execute(req.params.id);
            if (!student) return res.status(404).json({ error: "Student not found" });
            res.json(StudentDTO.fromEntity(student));
        } catch (err) {
            res.status(400).json({ error: err.message });
        }
    }

    async getAll(req: Request, res: Response) {
        try {
            const students = await this.getAllStudentsUseCase.execute();
            res.json(students.map(StudentDTO.fromEntity));
        } catch (err) {
            res.status(400).json({ error: err.message });
        }
    }

    async update(req: Request, res: Response) {
        try {
            const dto = new StudentDTO(req.body.id, req.body.full_name, new Date(req.body.birthdate));
            const student = StudentDTO.toEntity(dto);
            await this.updateStudentUseCase.execute(student);
            res.json(dto);
        } catch (err) {
            res.status(400).json({ error: err.message });
        }
    }

    async delete(req: Request, res: Response) {
        try {
            const student = await this.getStudentByIdUseCase.execute(req.params.id);
            if (!student) return res.status(404).json({ error: "Student not found" });
            await this.deleteStudentUseCase.execute(student);
            res.status(204).send();
        } catch (err) {
            res.status(400).json({ error: err.message });
        }
    }
}
