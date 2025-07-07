import { Student } from "../../../../core/entities/Student/Student";

export class StudentAdapter {
    static fromRow(row: any) {
        return new Student({
            id: row.id,
            fullName: row.full_name,
            birthdate: new Date(row.birthdate),
        });
    }
}
