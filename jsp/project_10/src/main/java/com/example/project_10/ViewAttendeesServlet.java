package com.example.project_10;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/viewAttendees")
public class ViewAttendeesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long eventId = Long.parseLong(request.getParameter("eventId"));

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        Event event = em.find(Event.class, eventId);
        List<Attendee> attendees = em.createQuery("SELECT a FROM Attendee a WHERE a.event.id = :eventId", Attendee.class)
                .setParameter("eventId", eventId)
                .getResultList();
        em.close();

        request.setAttribute("event", event);
        request.setAttribute("attendees", attendees);
        request.getRequestDispatcher("viewAttendees.jsp").forward(request, response);
    }
}