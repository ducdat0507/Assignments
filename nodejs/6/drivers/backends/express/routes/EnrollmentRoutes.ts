import { EnrollmentController } from "../../../../adapters/controllers/EnrollmentController";
import express from "express";
import { UserError } from "../../../../core/errors/UserError";
import { NewEnrollmentValidator } from "../../../../adapters/validator/EnrollmentValidator";

export function getRoutes(controller: EnrollmentController) {
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
    router.get("/:courseId/:studentId", async (req, res) => {
        try {
            let result = await controller.getByIds(req.params.courseId, req.params.studentId);
            res.json({ result });
        } catch (e) {
            if (e instanceof UserError) res.status(e.httpStatus).json({ error: e.message });
            else res.status(500).json({ error: "Internal server error" });
        }
    });
    router.get("/course/:id", async (req, res) => {
        try {
            let result = await controller.getByCourseId(req.params.id);
            res.json({ result });
        } catch (e) {
            if (e instanceof UserError) res.status(e.httpStatus).json({ error: e.message });
            else res.status(500).json({ error: "Internal server error" });
        }
    });
    router.post("/", async (req, res) => {
        try {
            let obj = new NewEnrollmentValidator().validate(req.body);
            let result = await controller.create(obj);
            res.json({ result });
        } catch (e) {
            if (e instanceof UserError) res.status(e.httpStatus).json({ error: e.message });
            else res.status(500).json({ error: "Internal server error" });
        }
    });
    router.put("/", async (req, res) => {
        try {
            let obj = new NewEnrollmentValidator().validate(req.body);
            await controller.update(obj);
            res.json({ ok: 1 });
        } catch (e) {
            if (e instanceof UserError) res.status(e.httpStatus).json({ error: e.message });
            else res.status(500).json({ error: "Internal server error" });
        }
    });
    router.delete("/", async (req, res) => {
        try {
            const { studentId, courseId } = req.body;
            await controller.delete(studentId, courseId);
            res.json({ ok: 1 });
        } catch (e) {
            if (e instanceof UserError) res.status(e.httpStatus).json({ error: e.message });
            else res.status(500).json({ error: "Internal server error" });
        }
    });
    return router;
}
