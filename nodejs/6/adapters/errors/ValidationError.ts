import { UserError } from "../../core/errors/UserError";
import { ValidatorErrorItem } from "../validator/Validator";

export class ValidationError extends UserError {
    errors: ValidatorErrorItem[];
    constructor(errors: ValidatorErrorItem[]) {
        super(`Validation error: ${errors.map(x => x.error).join(", ")}`);
        this.errors = errors;
        this.httpStatus = 400;
        this.name = 'ValidationError';
    }
}