import { ControllerHolder } from "../../adapters/controllers/ControllerHolder";

export interface Backend {
    setup(controllers: ControllerHolder): void;
}