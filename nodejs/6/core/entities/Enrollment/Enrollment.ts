import { EnrollmentConstructorArgs } from "./EnrollmentConstructorArgs";

export class Enrollment {

    // ---------- Properties ---------- //
    
    #studentId: string;
    #courseId: string;
    #enrollDate: Date;

    // ---------- Constructor ---------- //

    constructor(args: EnrollmentConstructorArgs) {
        this.#studentId = args.studentId;
        this.#courseId = args.courseId;
        this.#enrollDate = args.enrollDate;
    }

    // ---------- Getters and Setters ---------- //

    get studentId(): string {
        return this.#studentId;
    }
    get courseId(): string {
        return this.#courseId;
    }
    get enrollDate(): Date {
        return this.#enrollDate;
    }
    set enrollDate(value: Date) {
        this.#enrollDate = value;
    }
}

