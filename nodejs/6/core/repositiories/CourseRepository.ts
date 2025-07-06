import { Course } from "../entities/Course/Course";

export interface CourseRepository {
    findById(id: string): Promise<Course | null>;
    findAll(): Promise<Course[]>;

    create(course: Course): Promise<void>;
    update(course: Course): Promise<void>;

    delete(course: Course): Promise<void>;
}
