import { Student } from "../entities/Student/Student";

export interface StudentRepository {
    findById(id: string): Promise<Student>;
    findAll(): Promise<Student[]>;

    create(student: Student): Promise<string>;
    update(student: Student): Promise<void>;

    delete(id: string): Promise<void>;
    
}
