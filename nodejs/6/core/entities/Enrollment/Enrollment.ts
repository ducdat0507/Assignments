import { EnrollmentConstructorArgs } from "./EnrollmentConstructorArgs";
import { EnrollmentType } from "./EnrollmentType";

export class Enrollment {

    // ---------- Properties ---------- //
    
    #studentId: string;
    #courseId: string;
    #enrollmentType: EnrollmentType;
    #enrollDate: Date;
    #enrollUntil?: Date;

    // ---------- Constructor ---------- //

    constructor(args: EnrollmentConstructorArgs) {
        this.#studentId = args.studentId;
        this.#courseId = args.courseId;
        this.#enrollmentType = args.enrollmentType;
        this.#enrollDate = args.enrollDate;
        this.#enrollUntil = args.enrollUntil;
    }

    // ---------- Getters and Setters ---------- //

    get studentId(): string {
        return this.#studentId;
    }
    get courseId(): string {
        return this.#courseId;
    }
    get enrollmentType(): EnrollmentType {
        return this.#enrollmentType;
    }
    set enrollmentType(value: EnrollmentType) {
        this.#enrollmentType = value;
    }
    get enrollDate(): Date {
        return this.#enrollDate;
    }
    set enrollDate(value: Date) {
        this.#enrollDate = value;
    }
    get enrollUntil(): Date | undefined {
        return this.#enrollUntil;
    }
    set enrollUntil(value: Date | undefined) {
        this.#enrollUntil = value;
    }
}

