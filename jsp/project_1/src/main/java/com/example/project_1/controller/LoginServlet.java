package com.example.project_1.controller;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.stream.Stream;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/api/login")
public class LoginServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // TODO remove placeholder credentials
        if (username.equals("admin") && password.equals("1234567")) {
            // TODO implement access token
            Cookie accessCookie = new Cookie("access-token", "acess-token-goes-here-1234567890");
            accessCookie.setSecure(true);
            accessCookie.setPath("/");
            accessCookie.setHttpOnly(true);
            resp.addCookie(accessCookie);
            resp.sendRedirect("../dashboard.jsp");
        } else {
            resp.getWriter().write("<h2>Wrong username or password</h2>");
        }
    }

}
