package com.example.sports;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/teams")
public class TeamServlet extends HttpServlet {

    @EJB
    private TeamBeanRemote teamBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "addForm":
                req.getRequestDispatcher("/add.jsp").forward(req, resp);
                break;
            case "edit":
                String idStr = req.getParameter("id");
                try {
                    int id = Integer.parseInt(idStr);
                    Team t = teamBean.getTeamById(id);
                    req.setAttribute("team", t);
                    req.getRequestDispatcher("/update.jsp").forward(req, resp);
                } catch (Exception e) {
                    req.setAttribute("error", "Invalid id");
                    resp.sendRedirect("teams");
                }
                break;
            case "delete":
                try {
                    int id = Integer.parseInt(req.getParameter("id"));
                    teamBean.deleteTeam(id);
                } catch (Exception e) {
                    req.setAttribute("error", e.getMessage());
                }
                resp.sendRedirect("teams");
                break;
            default:
                req.setAttribute("teams", teamBean.getAllTeams());
                req.getRequestDispatcher("/list.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("add".equals(action)) {
            String name = req.getParameter("name");
            String city = req.getParameter("city");
            try {
                teamBean.addTeam(name, city);
                resp.sendRedirect("teams");
            } catch (Exception e) {
                req.setAttribute("error", e.getMessage());
                req.getRequestDispatcher("/add.jsp").forward(req, resp);
            }
        } else if ("update".equals(action)) {
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                String name = req.getParameter("name");
                String city = req.getParameter("city");
                teamBean.updateTeam(id, name, city);
                resp.sendRedirect("teams");
            } catch (Exception e) {
                req.setAttribute("error", e.getMessage());
                req.getRequestDispatcher("/update.jsp").forward(req, resp);
            }
        } else {
            resp.sendRedirect("teams");
        }
    }
}
