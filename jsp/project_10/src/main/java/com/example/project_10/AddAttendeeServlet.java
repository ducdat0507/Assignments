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
import java.util.List;
import java.util.Set;

@WebServlet("/addAttendee")
public class AddAttendeeServlet extends HttpServlet {

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
        String email = request.getParameter("email");
        Long eventId = Long.parseLong(request.getParameter("eventId"));

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        Event event = em.find(Event.class, eventId);
        em.close();

        if (event == null) {
            request.setAttribute("error", "Event not found");
            request.getRequestDispatcher("addAttendee.jsp").forward(request, response);
            return;
        }

        Attendee attendee = new Attendee(event, name, email);

        Set<ConstraintViolation<Attendee>> violations = validator.validate(attendee);
        if (!violations.isEmpty()) {
            request.setAttribute("errors", violations);
            request.getRequestDispatcher("addAttendee.jsp").forward(request, response);
            return;
        }

        em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(attendee);
        tx.commit();
        em.close();

        response.sendRedirect("viewAttendees?eventId=" + eventId);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        List<Event> events = em.createQuery("SELECT e FROM Event e", Event.class).getResultList();
        em.close();

        request.setAttribute("events", events);
        request.getRequestDispatcher("addAttendee.jsp").forward(request, response);
    }
}