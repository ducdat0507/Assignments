import { StudentRepository } from "../../core/repositiories/StudentRepository";
import { Student } from "../../core/entities/Student/Student";

export class CreateStudentUseCase {
    constructor(private studentRepository: StudentRepository) {}

    async execute(student: Student): Promise<void> {
        await this.studentRepository.create(student);
    }
}

export class GetStudentByIdUseCase {
    constructor(private studentRepository: StudentRepository) {}

    async execute(id: string): Promise<Student | null> {
        return this.studentRepository.findById(id);
    }
}

export class GetAllStudentsUseCase {
    constructor(private studentRepository: StudentRepository) {}

    async execute(): Promise<Student[]> {
        return this.studentRepository.findAll();
    }
}

export class UpdateStudentUseCase {
    constructor(private studentRepository: StudentRepository) {}

    async execute(student: Student): Promise<void> {
        await this.studentRepository.update(student);
    }
}

export class DeleteStudentUseCase {
    constructor(private studentRepository: StudentRepository) {}

    async execute(student: Student): Promise<void> {
        await this.studentRepository.delete(student);
    }
}
