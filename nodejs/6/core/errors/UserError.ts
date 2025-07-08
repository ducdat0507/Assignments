export class UserError extends Error {
    httpStatus: number = 400;
    constructor(message: string) {
        super(message);
        this.name = 'UserError';
    }
}