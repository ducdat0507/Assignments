import { Course } from "../../../../core/entities/Course/Course";

export class CourseAdapter {
    static fromRow(row: any) {
        return new Course({
            id: row.id,
            name: row.name,
        })
    }
}