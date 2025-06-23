import { CourseConstructorArgs } from "./CourseConstructorArgs";

export class Course {

    // ---------- Properties ---------- //
    
    #id: string;
    #name: string;

    // ---------- Constructor ---------- //

    constructor(args: CourseConstructorArgs) {
        this.#id = args.id;
        this.#name = args.name;
    }

    // ---------- Getters and Setters ---------- //

    get id(): string {
        return this.#id;
    }
    get name(): string {
        return this.#name;
    }
    set name(value: string) {
        this.#name = value;
    }
}


