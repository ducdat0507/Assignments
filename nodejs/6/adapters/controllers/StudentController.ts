import { CreateStudentUseCase, GetStudentByIdUseCase, GetAllStudentsUseCase, UpdateStudentUseCase, DeleteStudentUseCase } from "../../application/useCases/StudentUseCases";
import { StudentDTO } from "../dtos/StudentDTO";
import { RepositoryHolder } from "../../core/repositiories/RepositoryHolder";

export class StudentController {
    #createStudentUseCase: CreateStudentUseCase;
    #getStudentByIdUseCase: GetStudentByIdUseCase;
    #getAllStudentsUseCase: GetAllStudentsUseCase;
    #updateStudentUseCase: UpdateStudentUseCase;
    #deleteStudentUseCase: DeleteStudentUseCase;

    constructor(repositories: RepositoryHolder) {
        this.#createStudentUseCase = new CreateStudentUseCase(repositories.studentRepository);
        this.#getStudentByIdUseCase = new GetStudentByIdUseCase(repositories.studentRepository);
        this.#getAllStudentsUseCase = new GetAllStudentsUseCase(repositories.studentRepository);
        this.#updateStudentUseCase = new UpdateStudentUseCase(repositories.studentRepository);
        this.#deleteStudentUseCase = new DeleteStudentUseCase(repositories.studentRepository);
    }

    async create(data: StudentDTO) {
        const student = StudentDTO.toEntity(data);
        await this.#createStudentUseCase.execute(student);
    }

    async getById(id: string) {
        let student = await this.#getStudentByIdUseCase.execute(id);
        return StudentDTO.fromEntity(student);
    }

    async getAll() {
        let students = await this.#getAllStudentsUseCase.execute();
        return students.map(StudentDTO.fromEntity);
    }

    async update(data: StudentDTO) {
        const student = StudentDTO.toEntity(data);
        await this.#updateStudentUseCase.execute(student);
    }

    async delete(data: StudentDTO) {
        const student = StudentDTO.toEntity(data);
        await this.#deleteStudentUseCase.execute(student);
    }
}
