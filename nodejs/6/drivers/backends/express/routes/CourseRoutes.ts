import { CourseController } from "../../../../adapters/controllers/CourseController";
import express from "express";
import { UserError } from "../../../../core/errors/UserError";
import { NewCourseValidator } from "../../../../adapters/validator/CourseValidator";
import { EnrollmentController } from "../../../../adapters/controllers/EnrollmentController";

export function getRoutes(controller: CourseController, enrollments: EnrollmentController) {
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
            let obj = new NewCourseValidator().validate(req.body);
            let result = controller.create(obj);
            res.json({result});
        } catch (e) {
            if (e instanceof UserError) res.json(e.httpStatus).json({error: e.message});
        }
    });
    router.get("/:id", (req, res) => {
        try {
            let result = controller.getById(req.params.id);
            res.json({result});
        } catch (e) {
            if (e instanceof UserError) res.json(e.httpStatus).json({error: e.message});
        }
    });
    router.get("/:id/enrollments", async (req, res) => {
        try {
            let result = await enrollments.getByCourseId(req.params.id);
            res.json({ result });
        } catch (e) {
            if (e instanceof UserError) res.status(e.httpStatus).json({ error: e.message });
            else res.status(500).json({ error: "Internal server error" });
        }
    });
    router.put("/:id", (req, res) => {
        try {
            let obj = new NewCourseValidator().validate(req.body);
            obj.id = req.params.id;
            controller.update(obj);
            res.json({ ok: 1 });
        } catch (e) {
            if (e instanceof UserError) res.json(e.httpStatus).json({error: e.message});
        }
    });
    router.delete("/:id", (req, res) => {
        try {
            let result = controller.delete(req.params.id);
            res.json({ ok: 1 });
        } catch (e) {
            if (e instanceof UserError) res.json(e.httpStatus).json({error: e.message});
        }
    });
    return router;
}