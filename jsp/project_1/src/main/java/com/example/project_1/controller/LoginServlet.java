package com.example.project_1.controller;

import java.io.IOException;

import org.json.JSONObject;

import com.example.project_1.dao.UserDAO;
import com.example.project_1.model.User;
import com.example.project_1.utils.JsonUtils;
import org.mindrot.jbcrypt.BCrypt;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/api/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username == null || password == null) {
            resp.setStatus(400);
            JsonUtils.writeToResponse(resp, 
                new JSONObject()
                    .put("success", false)
                    .put("error", "Missing credentials")
            );
            return;
        }

        User user = UserDAO.findByUsername(username);
        if (user != null && BCrypt.checkpw(password, user.getPasswordHash())) {
            // TODO implement access token
            Cookie accessCookie = new Cookie("access-token", "access-token-" + user.getId());
            accessCookie.setSecure(true);
            accessCookie.setPath("/");
            accessCookie.setHttpOnly(true);
            resp.addCookie(accessCookie);
            resp.sendRedirect("../dashboard.jsp");
            return;
        } else {
            resp.setStatus(400);
            JsonUtils.writeToResponse(resp, 
                new JSONObject()
                    .put("success", false)
                    .put("error", "Wrong username or password")
            );
            return;
        }
    }

}
