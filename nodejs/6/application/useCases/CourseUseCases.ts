import { CourseRepository } from "../../core/repositiories/CourseRepository";
import { Course } from "../../core/entities/Course/Course";

export class CreateCourseUseCase {
    constructor(private courseRepository: CourseRepository) {}

    async execute(course: Course): Promise<void> {
        await this.courseRepository.create(course);
    }
}

export class GetCourseByIdUseCase {
    constructor(private courseRepository: CourseRepository) {}

    async execute(id: string): Promise<Course> {
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
