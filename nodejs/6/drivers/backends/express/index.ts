import { ControllerHolder } from "../../../adapters/controllers/ControllerHolder";
import express from "express";
import { Backend } from "../Backend";

export class ExpressBackend implements Backend {
    setup(controllers: ControllerHolder) {
        const app = express();

        app.listen(process.env.BACKEND_PORT, (err) => {
            if (err) console.error(err);
        })
    }
}