import { Validator, ValidatorResults } from "./Validator";
import { StudentDTO, MaybeStudentDTO } from "../dtos/StudentDTO";
import { ValidationError } from "../errors/ValidationError";

export class NewStudentValidator extends Validator<StudentDTO> {
    validate(item: MaybeStudentDTO) {
        let results = new ValidatorResults();
        this.getValidation(item, results);
        if (results.errors.length) throw new ValidationError(results.errors);
        return new StudentDTO(item.id, item.fullName!, item.birthdate!);
    }

    getValidation(item: MaybeStudentDTO, results: ValidatorResults): void {
        if (item.fullName === undefined) {
            results.errors.push({
                field: "fullName",
                error: "Full name is required",
            });
        }
        if (item.birthdate === undefined) {
            results.errors.push({
                field: "birthdate",
                error: "Birthdate is required",
            });
        }
    }
}

export class ExistingStudentValidator extends NewStudentValidator {
    getValidation(item: MaybeStudentDTO, results: ValidatorResults): void {
        if (item.id === undefined) {
            results.errors.push({
                field: "id",
                error: "ID is required",
            });
        }
        super.getValidation(item, results);
    }
}