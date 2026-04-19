package com.example.project_10;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/viewEvents")
public class ViewEventsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        List<Event> events = em.createQuery("SELECT e FROM Event e", Event.class).getResultList();
        em.close();

        request.setAttribute("events", events);
        request.getRequestDispatcher("viewEvents.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if ("delete".equals(action)) {
            Long id = Long.parseLong(request.getParameter("id"));

            EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            Event event = em.find(Event.class, id);
            if (event != null) {
                em.remove(event);
            }
            tx.commit();
            em.close();
        }

        response.sendRedirect("viewEvents?lang=" + request.getParameter("lang"));
    }
}