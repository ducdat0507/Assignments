import { Request, Response } from "express";
import { CreateCourseUseCase, GetCourseByIdUseCase, GetAllCoursesUseCase, UpdateCourseUseCase, DeleteCourseUseCase } from "../../../../core/useCases/CourseUseCases";
import { CourseDTO } from "../../../../infrastructure/dtos/CourseDTO";
import { Course } from "../../../../core/entities/Course/Course";

export class CourseController {
    constructor(
        private createCourseUseCase: CreateCourseUseCase,
        private getCourseByIdUseCase: GetCourseByIdUseCase,
        private getAllCoursesUseCase: GetAllCoursesUseCase,
        private updateCourseUseCase: UpdateCourseUseCase,
        private deleteCourseUseCase: DeleteCourseUseCase
    ) {}

    async create(req: Request, res: Response) {
        try {
            const dto = new CourseDTO(req.body.id, req.body.name);
            const course = CourseDTO.toEntity(dto);
            await this.createCourseUseCase.execute(course);
            res.status(201).json(dto);
        } catch (err) {
            res.status(400).json({ error: err.message });
        }
    }

    async getById(req: Request, res: Response) {
        try {
            const course = await this.getCourseByIdUseCase.execute(req.params.id);
            if (!course) return res.status(404).json({ error: "Course not found" });
            res.json(CourseDTO.fromEntity(course));
        } catch (err) {
            res.status(400).json({ error: err.message });
        }
    }

    async getAll(req: Request, res: Response) {
        try {
            const courses = await this.getAllCoursesUseCase.execute();
            res.json(courses.map(CourseDTO.fromEntity));
        } catch (err) {
            res.status(400).json({ error: err.message });
        }
    }

    async update(req: Request, res: Response) {
        try {
            const dto = new CourseDTO(req.body.id, req.body.name);
            const course = CourseDTO.toEntity(dto);
            await this.updateCourseUseCase.execute(course);
            res.json(dto);
        } catch (err) {
            res.status(400).json({ error: err.message });
        }
    }

    async delete(req: Request, res: Response) {
        try {
            const course = await this.getCourseByIdUseCase.execute(req.params.id);
            if (!course) return res.status(404).json({ error: "Course not found" });
            await this.deleteCourseUseCase.execute(course);
            res.status(204).send();
        } catch (err) {
            res.status(400).json({ error: err.message });
        }
    }
}
