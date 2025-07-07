export class AlreadyEnrolledError extends Error {
    courseId: string;
    studentId: string;
    constructor(courseId: string, studentId: string) {
        super(`Student with ID ${courseId} already enrolled on Course with ID ${studentId}`);
        this.courseId = courseId;
        this.studentId = studentId;
        this.name = 'AlreadyEnrolledError';
    }
}