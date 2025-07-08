import { StudentController } from "../../../../adapters/controllers/StudentController";
import express from "express";
import { UserError } from "../../../../core/errors/UserError";
import { NewStudentValidator } from "../../../../adapters/validator/StudentValidator";
import { EnrollmentController } from "../../../../adapters/controllers/EnrollmentController";

export function getRoutes(controller: StudentController, enrollments: EnrollmentController) {
    let router = express.Router();
    router.get("/", async (req, res) => {
        try {
            let result = await controller.getAll();
            res.json({ result });
        } catch (e) {
            if (e instanceof UserError) res.status(e.httpStatus).json({ error: e.message });
            else res.status(500).json({ error: "Internal server error" });
        }
    });
    router.post("/", async (req, res) => {
        try {
            let obj = new NewStudentValidator().validate(req.body);
            let result = await controller.create(obj);
            res.json({ result });
        } catch (e) {
            if (e instanceof UserError) res.status(e.httpStatus).json({ error: e.message });
            else res.status(500).json({ error: "Internal server error" });
        }
    });
    router.get("/:id/enrollments", async (req, res) => {
        try {
            let result = await enrollments.getByStudentId(req.params.id);
            res.json({ result });
        } catch (e) {
            if (e instanceof UserError) res.status(e.httpStatus).json({ error: e.message });
            else res.status(500).json({ error: "Internal server error" });
        }
    });
    router.get("/:id", async (req, res) => {
        try {
            let result = await controller.getById(req.params.id);
            res.json({ result });
        } catch (e) {
            if (e instanceof UserError) res.status(e.httpStatus).json({ error: e.message });
            else res.status(500).json({ error: "Internal server error" });
        }
    });
    router.put("/:id", async (req, res) => {
        try {
            let obj = new NewStudentValidator().validate(req.body);
            obj.id = req.params.id;
            await controller.update(obj);
            res.json({ ok: 1 });
        } catch (e) {
            if (e instanceof UserError) res.status(e.httpStatus).json({ error: e.message });
            else res.status(500).json({ error: "Internal server error" });
        }
    });
    router.delete("/:id", async (req, res) => {
        try {
            await controller.delete(req.params.id);
            res.json({ ok: 1 });
        } catch (e) {
            if (e instanceof UserError) res.status(e.httpStatus).json({ error: e.message });
            else res.status(500).json({ error: "Internal server error" });
        }
    });
    return router;
}
