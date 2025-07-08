import { RepositoryHolder } from "../../core/repositiories/RepositoryHolder";
import { CourseController } from "./CourseController";
import { EnrollmentController } from "./EnrollmentController";
import { StudentController } from "./StudentController";

export class ControllerHolder {
    #studentController: StudentController;
    #courseController: CourseController;
    #enrollmentController: EnrollmentController;

    constructor (repositories: RepositoryHolder) {
        this.#studentController = new StudentController(repositories);
        this.#courseController = new CourseController(repositories);
        this.#enrollmentController = new EnrollmentController(repositories);
    }

    get studentController() { return this.#studentController; }
    get courseController() { return this.#courseController; }
    get enrollmentController() { return this.#enrollmentController; }
}