package com.example.servlet;

import com.example.ejb.VisitorCounterEJB;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    @EJB
    private VisitorCounterEJB visitorCounterEJB;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int visitorNumber = visitorCounterEJB.incrementAndGet();
        resp.setContentType("text/plain; charset=UTF-8");
        resp.getWriter().write("You are visitor number " + visitorNumber + " to this website.");
    }
}
