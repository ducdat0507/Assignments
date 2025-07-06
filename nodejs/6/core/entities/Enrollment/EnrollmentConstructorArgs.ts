import { EnrollmentType } from "./EnrollmentType";

export type EnrollmentConstructorArgs = {
    studentId: string;
    courseId: string;
    enrollmentType: EnrollmentType;
    enrollDate: Date;
    enrollUntil?: Date;
};
