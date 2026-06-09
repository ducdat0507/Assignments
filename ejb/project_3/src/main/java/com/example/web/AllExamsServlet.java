package com.example.web;

import com.example.model.Exam;
import com.example.service.ExamService;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/exams")
public class AllExamsServlet extends HttpServlet {

    @EJB
    private ExamService examService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Exam> exams = examService.findAllExams();
        req.setAttribute("exams", exams);
        req.getRequestDispatcher("/WEB-INF/allExams.jsp").forward(req, resp);
    }
}
