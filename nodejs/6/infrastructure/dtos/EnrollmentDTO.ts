import { Enrollment } from "../../core/entities/Enrollment/Enrollment";

export class EnrollmentDTO {
    studentId: string;
    courseId: string;

    constructor(studentId: string, courseId: string) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    static fromEntity(entity: Enrollment): EnrollmentDTO {
        return new EnrollmentDTO(entity.studentId, entity.courseId);
    }

    static toEntity(dto: EnrollmentDTO): Enrollment {
        return new Enrollment({
            studentId: dto.studentId,
            courseId: dto.courseId,
            enrollDate: new Date(), // Default or adjust as needed
        });
    }
}
