import { ValidationError } from "../errors/ValidationError";


export class ValidatorResults {
    errors: ValidatorErrorItem[] = [];
}

export class Validator<T> {
    validate(item: Partial<T>) {
        let results = new ValidatorResults();
        this.getValidation(item, results);
        if (results.errors.length) throw new ValidationError(results.errors);
    }
    
    getValidation(item: Partial<T>, results: ValidatorResults) {
        throw new Error("Method not implemented.");
    }
}

export type ValidatorErrorItem = {
    field: string,
    error: string,
}