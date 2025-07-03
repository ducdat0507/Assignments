export class EntityNotFoundError extends Error {
    type: string;
    id: string;
    constructor(type: string, id: string) {
        super(`${type} with ID ${type} not found`);
        this.type = type;
        this.id = id;
        this.name = 'EntityNotFoundError';
    }
}