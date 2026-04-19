package com.example.project_10;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@WebServlet("/addEvent")
public class AddEventServlet extends HttpServlet {

    private Validator validator;

    @Override
    public void init() throws ServletException {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String dateStr = request.getParameter("date");
        String venue = request.getParameter("venue");
        String seatsStr = request.getParameter("seatsAvailable");

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(dateStr);
            Integer seatsAvailable = Integer.parseInt(seatsStr);

            Event event = new Event(name, date, venue, seatsAvailable);

            Set<ConstraintViolation<Event>> violations = validator.validate(event);
            if (!violations.isEmpty()) {
                request.setAttribute("errors", violations);
                request.getRequestDispatcher("addEvent.jsp").forward(request, response);
                return;
            }

            EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist(event);
            tx.commit();
            em.close();

            response.sendRedirect("viewEvents?lang=" + request.getParameter("lang"));

        } catch (ParseException | NumberFormatException e) {
            request.setAttribute("error", "Invalid input format");
            request.getRequestDispatcher("addEvent.jsp").forward(request, response);
        }
    }
}