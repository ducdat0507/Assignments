import { CreateEnrollmentUseCase, GetAllEnrollmentsUseCase, UpdateEnrollmentUseCase, DeleteEnrollmentUseCase, GetEnrollmentsByStudentIdUseCase, GetEnrollmentsByCourseIdUseCase } from "../../application/useCases/EnrollmentUseCases";
import { EnrollmentDTO } from "../dtos/EnrollmentDTO";
import { RepositoryHolder } from "../../core/repositiories/RepositoryHolder";

export class EnrollmentController {
    #createEnrollmentUseCase: CreateEnrollmentUseCase;
    #getEnrollmentsByStudentIdUseCase: GetEnrollmentsByStudentIdUseCase;
    #getEnrollmentsByCourseIdUseCase: GetEnrollmentsByCourseIdUseCase;
    #getAllEnrollmentsUseCase: GetAllEnrollmentsUseCase;
    #updateEnrollmentUseCase: UpdateEnrollmentUseCase;
    #deleteEnrollmentUseCase: DeleteEnrollmentUseCase;

    constructor(repositories: RepositoryHolder) {
        this.#createEnrollmentUseCase = new CreateEnrollmentUseCase(repositories.enrollmentRepository);
        this.#getEnrollmentsByStudentIdUseCase = new GetEnrollmentsByStudentIdUseCase(repositories.enrollmentRepository);
        this.#getEnrollmentsByCourseIdUseCase = new GetEnrollmentsByCourseIdUseCase(repositories.enrollmentRepository);
        this.#getAllEnrollmentsUseCase = new GetAllEnrollmentsUseCase(repositories.enrollmentRepository);
        this.#updateEnrollmentUseCase = new UpdateEnrollmentUseCase(repositories.enrollmentRepository);
        this.#deleteEnrollmentUseCase = new DeleteEnrollmentUseCase(repositories.enrollmentRepository);
    }

    async create(data: EnrollmentDTO) {
        const enrollment = EnrollmentDTO.toEntity(data);
        await this.#createEnrollmentUseCase.execute(enrollment);
    }

    async getByStudentId(id: string) {
        let enrollments = await this.#getEnrollmentsByStudentIdUseCase.execute(id);
        return enrollments.map(EnrollmentDTO.fromEntity);
    }
    async getByCourseId(id: string) {
        let enrollments = await this.#getEnrollmentsByCourseIdUseCase.execute(id);
        return enrollments.map(EnrollmentDTO.fromEntity);
    }

    async getAll() {
        let enrollments = await this.#getAllEnrollmentsUseCase.execute();
        return enrollments.map(EnrollmentDTO.fromEntity);
    }

    async update(data: EnrollmentDTO) {
        const enrollment = EnrollmentDTO.toEntity(data);
        await this.#updateEnrollmentUseCase.execute(enrollment);
    }

    async delete(data: EnrollmentDTO) {
        const enrollment = EnrollmentDTO.toEntity(data);
        await this.#deleteEnrollmentUseCase.execute(enrollment);
    }
}
