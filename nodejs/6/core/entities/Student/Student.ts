import { StudentConstructorArgs } from "./StudentConstructorArgs";

export class Student {

    // ---------- Properties ---------- //
    
    #id: string;
    #fullName: string;
    #birthdate: Date;

    // ---------- Constructor ---------- //

    constructor(args: StudentConstructorArgs) {
        this.#id = args.id;
        this.#fullName = args.fullName;
        this.#birthdate = args.birthdate;
    }

    // ---------- Getters and Setters ---------- //

    get id(): string {
        return this.#id;
    }
    get fullName(): string {
        return this.#fullName;
    }
    set fullName(value: string) {
        this.#fullName = value;
    }
    get birthdate(): Date {
        return this.#birthdate;
    }
    set birthdate(value: Date) {
        this.#birthdate = value;
    }
}

