import { CourseRepository } from "../repositiories/CourseRepository";
import { Course } from "../entities/Course/Course";

export class CreateCourseUseCase {
    constructor(private courseRepository: CourseRepository) {}

    async execute(course: Course): Promise<void> {
        await this.courseRepository.save(course);
    }
}

export class GetCourseByIdUseCase {
    constructor(private courseRepository: CourseRepository) {}

    async execute(id: string): Promise<Course | null> {
        return this.courseRepository.findById(id);
    }
}

export class GetAllCoursesUseCase {
    constructor(private courseRepository: CourseRepository) {}

    async execute(): Promise<Course[]> {
        return this.courseRepository.findAll();
    }
}

export class UpdateCourseUseCase {
    constructor(private courseRepository: CourseRepository) {}

    async execute(course: Course): Promise<void> {
        await this.courseRepository.update(course);
    }
}

export class DeleteCourseUseCase {
    constructor(private courseRepository: CourseRepository) {}

    async execute(course: Course): Promise<void> {
        await this.courseRepository.delete(course);
    }
}
