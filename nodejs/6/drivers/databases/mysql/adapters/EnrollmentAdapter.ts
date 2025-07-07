import { Enrollment } from "../../../../core/entities/Enrollment/Enrollment";
import { EnrollmentType } from "../../../../core/entities/Enrollment/EnrollmentType";

export class EnrollmentAdapter {
    static fromRow(row: any) {
        return new Enrollment({
            studentId: row.student_id,
            courseId: row.course_id,
            enrollmentType: row.enrollment_type as EnrollmentType,
            enrollDate: new Date(row.enroll_date),
            enrollUntil: row.enroll_until ? new Date(row.enroll_until) : undefined
        });
    }
}
