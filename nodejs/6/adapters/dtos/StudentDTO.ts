import { Student } from "../../core/entities/Student/Student";

export class StudentDTO {
    id?: string;
    fullName: string;
    birthdate: Date;

    constructor(id: string | undefined, fullName: string, birthdate: Date) {
        this.id = id;
        this.fullName = fullName;
        this.birthdate = birthdate;
    }

    static fromEntity(entity: Student): StudentDTO {
        return new StudentDTO(entity.id, entity.fullName, entity.birthdate);
    }

    static toEntity(dto: StudentDTO): Student {
        return new Student({
            id: dto.id,
            fullName: dto.fullName,
            birthdate: dto.birthdate,
        });
    }
}

export type MaybeStudentDTO = Partial<StudentDTO>
