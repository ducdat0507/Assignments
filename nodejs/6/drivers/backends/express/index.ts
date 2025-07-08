import { ControllerHolder } from "../../../adapters/controllers/ControllerHolder";
import express from "express";
import { Backend } from "../Backend";
import { logger } from "../../../logging/Logger";

export class ExpressBackend implements Backend {
    port: number;

    constructor(port: number) {
        this.port = port;
    }

    setup(controllers: ControllerHolder) {
        const app = express();

        app.listen(this.port, (err) => {
            if (err) logger?.error("backend", err);
            else logger?.info("backend", `Backend is running at http://127.0.0.1:${this.port}`);
        })
    }
}