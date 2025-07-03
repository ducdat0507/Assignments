import { CreateCourseUseCase, GetCourseByIdUseCase, GetAllCoursesUseCase, UpdateCourseUseCase, DeleteCourseUseCase } from "../../application/useCases/CourseUseCases";
import { CourseDTO } from "../dtos/CourseDTO";
import { RepositoryHolder } from "../../core/repositiories/RepositoryHolder";

export class CourseController {
    
    #createCourseUseCase: CreateCourseUseCase;
    #getCourseByIdUseCase: GetCourseByIdUseCase;
    #getAllCoursesUseCase: GetAllCoursesUseCase;
    #updateCourseUseCase: UpdateCourseUseCase;
    #deleteCourseUseCase: DeleteCourseUseCase;

    constructor(
        repositories: RepositoryHolder
    ) {
        this.#createCourseUseCase = new CreateCourseUseCase(repositories.courseRepository);
        this.#getCourseByIdUseCase = new GetCourseByIdUseCase(repositories.courseRepository);
        this.#getAllCoursesUseCase = new GetAllCoursesUseCase(repositories.courseRepository);
        this.#updateCourseUseCase = new UpdateCourseUseCase(repositories.courseRepository);
        this.#deleteCourseUseCase = new DeleteCourseUseCase(repositories.courseRepository);
    }

    async create(data: CourseDTO) {
        const course = CourseDTO.toEntity(data);
        await this.#createCourseUseCase.execute(course);
    }

    async getById(id: string) {
        let course = await this.#getCourseByIdUseCase.execute(id);
        return CourseDTO.fromEntity(course);
    }

    async getAll() {
        let courses = await this.#getAllCoursesUseCase.execute();
        return courses.map(CourseDTO.fromEntity);
    }

    async update(data: CourseDTO) {
        const course = CourseDTO.toEntity(data);
        await this.#updateCourseUseCase.execute(course);
    }

    async delete(data: CourseDTO) {
        const course = CourseDTO.toEntity(data);
        await this.#deleteCourseUseCase.execute(course);
    }
}
