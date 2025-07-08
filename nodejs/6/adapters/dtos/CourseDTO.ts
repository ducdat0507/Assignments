import { Course } from "../../core/entities/Course/Course";

export class CourseDTO {
    id?: string;
    name: string;

    constructor(id: string | undefined, name: string) {
        this.id = id;
        this.name = name;
    }

    static fromEntity(entity: Course): CourseDTO {
        return new CourseDTO(entity.id, entity.name);
    }

    static toEntity(dto: CourseDTO): Course {
        return new Course({
            id: dto.id,
            name: dto.name,
        });
    }
}

export type MaybeCourseDTO = Partial<CourseDTO>