export class AlreadyExistsError extends Error {
    type: string;
    id: string;
    constructor(type: string, id: string) {
        super(`${type} with ID ${type} already exists`);
        this.type = type;
        this.id = id;
        this.name = 'AlreadyExistsError';
    }
}