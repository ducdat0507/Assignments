import { CreateEnrollmentUseCase, GetEnrollmentByIdUseCase, GetAllEnrollmentsUseCase, UpdateEnrollmentUseCase, DeleteEnrollmentUseCase } from "../../application/useCases/EnrollmentUseCases";
import { EnrollmentDTO } from "../dtos/EnrollmentDTO";
import { RepositoryHolder } from "../../core/repositiories/RepositoryHolder";

export class EnrollmentController {
    #createEnrollmentUseCase: CreateEnrollmentUseCase;
    #getEnrollmentByStudentUseCase: GetEnrollmentByIdUseCase;
    #getAllEnrollmentsUseCase: GetAllEnrollmentsUseCase;
    #updateEnrollmentUseCase: UpdateEnrollmentUseCase;
    #deleteEnrollmentUseCase: DeleteEnrollmentUseCase;

    constructor(repositories: RepositoryHolder) {
        this.#createEnrollmentUseCase = new CreateEnrollmentUseCase(repositories.enrollmentRepository);
        this.#getEnrollmentByStudentUseCase = new GetEnrollmentByStudentIdUseCase(repositories.enrollmentRepository);
        this.#getAllEnrollmentsUseCase = new GetAllEnrollmentsUseCase(repositories.enrollmentRepository);
        this.#updateEnrollmentUseCase = new UpdateEnrollmentUseCase(repositories.enrollmentRepository);
        this.#deleteEnrollmentUseCase = new DeleteEnrollmentUseCase(repositories.enrollmentRepository);
    }

    async create(data: EnrollmentDTO) {
        const enrollment = EnrollmentDTO.toEntity(data);
        await this.#createEnrollmentUseCase.execute(enrollment);
    }

    async getByStudentId(id: string) {
        let enrollment = await this.#getEnrollmentByIdUseCase.execute(id);
        return EnrollmentDTO.fromEntity(enrollment);
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
