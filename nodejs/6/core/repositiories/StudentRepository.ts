import { Student } from "../entities/Student/Student";

export interface StudentRepository {
    findById(id: string): Promise<Student>;
    findAll(): Promise<Student[]>;

    create(student: Student): Promise<void>;
    update(student: Student): Promise<void>;

    delete(student: Student): Promise<void>;
    
}
