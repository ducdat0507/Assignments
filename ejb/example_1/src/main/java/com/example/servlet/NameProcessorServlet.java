package com.example.servlet;

import com.example.ejb.StringProcessorEJB;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/processName")
public class NameProcessorServlet extends HttpServlet {

    @EJB
    private StringProcessorEJB stringProcessorEJB;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String result = stringProcessorEJB.processFullName(name);
        resp.setContentType("text/plain; charset=UTF-8");
        resp.getWriter().write(result);
    }
}
