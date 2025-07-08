import { Logger } from "./Logger";

export class ConsoleLogger implements Logger {
    info(context: string, ...items: any[]) {
        console.log(`[ INFO] [${this.#getCurrentDateString()}] [${context}] :\n\t`, ...items);
    }
    warn(context: string, ...items: any[]) {
        console.warn(`[ WARN] [${this.#getCurrentDateString()}] [${context}] :\n\t`, ...items);
    }
    error(context: string, ...items: any[]) {
        console.error(`[ERROR] [${this.#getCurrentDateString()}] [${context}] :\n\t`, ...items);
    }

    #getCurrentDateString() {
        let date = new Date();
        return date.getUTCFullYear()
            + (date.getUTCMonth()+1+"").padStart(2, "0")
            + (date.getUTCDate()+"").padStart(2, "0")
            + " "
            + (date.getUTCHours()+"").padStart(2, "0")
            + (date.getUTCMinutes()+"").padStart(2, "0")
            + (date.getUTCSeconds()+"").padStart(2, "0");
    }
}