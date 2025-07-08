import { Validator, ValidatorResults } from "./Validator";
import { EnrollmentDTO, MaybeEnrollmentDTO } from "../dtos/EnrollmentDTO";
import { ValidationError } from "../errors/ValidationError";

export class NewEnrollmentValidator extends Validator<EnrollmentDTO> {
    validate(item: MaybeEnrollmentDTO) {
        let results = new ValidatorResults();
        this.getValidation(item, results);
        if (results.errors.length) throw new ValidationError(results.errors);
        return new EnrollmentDTO(
            item.studentId!,
            item.courseId!,
            item.enrollmentType!,
            item.enrollDate!,
            item.enrollUntil
        );
    }

    getValidation(item: Partial<EnrollmentDTO>, results: ValidatorResults): void {
        if (item.studentId === undefined) {
            results.errors.push({
                field: "studentId",
                error: "Student ID is required",
            });
        }
        if (item.courseId === undefined) {
            results.errors.push({
                field: "courseId",
                error: "Course ID is required",
            });
        }
        if (item.enrollmentType === undefined) {
            results.errors.push({
                field: "enrollmentType",
                error: "Enrollment type is required",
            });
        }
        if (item.enrollDate === undefined) {
            results.errors.push({
                field: "enrollDate",
                error: "Enroll date is required",
            });
        }
    }
}

export class ExistingEnrollmentValidator extends NewEnrollmentValidator {
    getValidation(item: MaybeEnrollmentDTO, results: ValidatorResults): void {
        // No extra validation needed
        super.getValidation(item, results);
    }
}