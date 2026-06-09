package com.example.web;

import com.example.service.ExamService;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/submitExam")
public class SubmitExamServlet extends HttpServlet {

    @EJB
    private ExamService examService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String durationStr = req.getParameter("duration");
        String description = req.getParameter("description");
        int duration = 0;
        try {
            duration = Integer.parseInt(durationStr);
        } catch (NumberFormatException ignored) {}

        examService.saveExam(name, duration, description);

        resp.sendRedirect(req.getContextPath() + "/exams");
    }
}
