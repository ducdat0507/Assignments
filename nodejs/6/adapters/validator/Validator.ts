export interface Validator<T> {
    validate(item: T): void;
}

export class ValidatorResults {
    errors = [];
    
}