export let logger: Logger | null = null;

export function setLogger(lg: Logger) {
    logger = lg;
}

export interface Logger {
    info(context: string, ...items: any[]): void;
    warn(context: string, ...items: any[]): void;
    error(context: string, ...items: any[]): void;
}