package com.example.project_6.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.project_6.model.User;
import com.example.project_6.utils.QuickValidation;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/Registration.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = Optional.of(req.getParameter("username")).orElse("");
        String email = Optional.of(req.getParameter("email")).orElse("");
        String password = Optional.of(req.getParameter("password")).orElse("");
        String password2 = Optional.of(req.getParameter("password2")).orElse("");

        List<String> errors = new ArrayList<>();

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password2);

        errors.addAll(QuickValidation.validate(user));
        if (password2.isBlank()) {
            errors.add("confirm password cannot be blank");
        }
        if (password != password2) {
            errors.add("password and confirm password does not match");
        }

        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/Registration.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
