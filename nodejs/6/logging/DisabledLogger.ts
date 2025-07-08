import { Logger } from "./Logger";

export class DisabledLogger implements Logger {
    info(context: string, ...items: any[]) {}
    warn(context: string, ...items: any[]) {}
    error(context: string, ...items: any[]) {}
}