import { CourseController } from "../../../../adapters/controllers/CourseController";
import express from "express";
import { UserError } from "../../../../errors/UserError";

export function getRoutes(controller: CourseController) {
    let router = express.Router();
    router.get("/", (req, res) => {
        try {
            let result = controller.getAll();
            res.json({result});
        } catch (e) {
            if (e instanceof UserError) res.json(e.httpStatus).json({error: e.message});
        }
    });
    router.post("/", (req, res) => {
        try {
            let result = controller.post();
            res.json({result});
        } catch (e) {
            if (e instanceof UserError) res.json(e.httpStatus).json({error: e.message});
        }
    });
    return router;
}