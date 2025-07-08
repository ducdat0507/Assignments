import { Validator, ValidatorResults } from "./Validator";
import { CourseDTO, MaybeCourseDTO } from "../dtos/CourseDTO";
import { ValidationError } from "../errors/ValidationError";

export class NewCourseValidator extends Validator<CourseDTO> {
    validate(item: MaybeCourseDTO) {
        let results = new ValidatorResults();
        this.getValidation(item, results);
        if (results.errors.length) throw new ValidationError(results.errors);
        return new CourseDTO(item.id, item.name!);
    }

    getValidation(item: Partial<CourseDTO>, results: ValidatorResults): void {
        if (item.name === undefined) {
            results.errors.push({
                field: "name",
                error: "Name is undefined",
            });
        }
    }
}