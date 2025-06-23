import { EnrollmentConstructorArgs } from "./EnrollmentConstructorArgs";

export class Enrollment {

    // ---------- Properties ---------- //
    
    #studentId: string;
    #courseId: string;

    // ---------- Constructor ---------- //

    constructor(args: EnrollmentConstructorArgs) {
        this.#studentId = args.studentId;
        this.#courseId = args.courseId;
    }

    // ---------- Getters and Setters ---------- //

    get studentId(): string {
        return this.#studentId;
    }
    get courseId(): string {
        return this.#courseId;
    }
}

