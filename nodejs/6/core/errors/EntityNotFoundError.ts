import { UserError } from "./UserError";

export class EntityNotFoundError extends UserError {
    type: string;
    id: string[];
    constructor(type: string, ...id: string[]) {
        super(`${type} with ID ${id.join(",")} not found`);
        this.type = type;
        this.id = id;
        this.httpStatus = 404;
        this.name = 'EntityNotFoundError';
    }
}