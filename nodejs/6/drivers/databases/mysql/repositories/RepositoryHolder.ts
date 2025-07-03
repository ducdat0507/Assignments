import { createPool, Pool, PoolOptions } from "mysql2/promise";
import { CourseRepository } from "../../../../core/repositiories/CourseRepository";
import { EnrollmentRepository } from "../../../../core/repositiories/EnrollmentRepository";
import { RepositoryHolder } from "../../../../core/repositiories/RepositoryHolder";
import { StudentRepository } from "../../../../core/repositiories/StudentRepository";
import { MySQLStudentRepository } from "../StudentRepository";
import { MySQLCourseRepository } from "./CourseRepository";
import { MySQLEnrollmentRepository } from "./EnrollmentRepository";

export class MySQLRepositoryHolder implements RepositoryHolder {
    #pool: Pool;
    #studentRepository: StudentRepository;
    #courseRepository: CourseRepository;
    #enrollmentRepository: EnrollmentRepository;

    constructor(options: PoolOptions) {
        this.#pool = createPool(options);
        this.#studentRepository = new MySQLStudentRepository(this.#pool);
        this.#courseRepository = new MySQLCourseRepository(this.#pool);
        this.#enrollmentRepository = new MySQLEnrollmentRepository(this.#pool);
    }

    get studentRepository(): StudentRepository {
        return this.#studentRepository;
    }
    get courseRepository(): CourseRepository {
        return this.#courseRepository;
    }
    get enrollmentRepository(): EnrollmentRepository {
        return this.#enrollmentRepository;
    }
}