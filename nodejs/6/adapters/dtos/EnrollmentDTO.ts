import { Enrollment } from "../../core/entities/Enrollment/Enrollment";
import { EnrollmentType } from "../../core/entities/Enrollment/EnrollmentType";

export class EnrollmentDTO {
    studentId: string;
    courseId: string;
    enrollmentType: EnrollmentType;
    enrollDate: Date;
    enrollUntil?: Date;

    constructor(studentId: string, courseId: string, enrollmentType: EnrollmentType, enrollDate: Date, enrollUntil: Date | undefined = undefined) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentType = enrollmentType;
        this.enrollDate = enrollDate;
        this.enrollUntil = enrollUntil;
    }

    static fromEntity(entity: Enrollment): EnrollmentDTO {
        return new EnrollmentDTO(entity.studentId, entity.courseId, entity.enrollmentType, entity.enrollDate, entity.enrollUntil);
    }

    static toEntity(dto: EnrollmentDTO): Enrollment {
        return new Enrollment({
            studentId: dto.studentId,
            courseId: dto.courseId,
            enrollmentType: dto.enrollmentType, 
            enrollDate: dto.enrollDate, 
            enrollUntil: dto.enrollUntil, 
        });
    }
}
