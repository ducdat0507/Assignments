package com.example.employee.web;

import com.example.employee.dao.EmployeeDAO;
import com.example.employee.entity.Employee;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ejb.EJB;
import java.io.IOException;
import java.util.List;

public class EmployeeServiceServlet extends HttpServlet {

    @EJB
    private EmployeeDAO employeeDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("delete".equals(action)) {
            String employeeNo = req.getParameter("employeeNo");
            if (employeeNo != null && !employeeNo.trim().isEmpty()) {
                employeeDAO.delete(employeeNo.trim());
            }
            resp.sendRedirect(req.getContextPath() + "/employeeService?action=list");
            return;
        }

        List<Employee> employees = employeeDAO.findAll();
        req.setAttribute("employees", employees);
        req.getRequestDispatcher("/employeeList.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("add".equals(action)) {
            String employeeNo = req.getParameter("employeeNo");
            String fullName = req.getParameter("fullName");
            String placeOfWork = req.getParameter("placeOfWork");
            String phoneNo = req.getParameter("phoneNo");

            if (employeeNo != null && fullName != null && placeOfWork != null && phoneNo != null
                    && !employeeNo.trim().isEmpty()
                    && !fullName.trim().isEmpty()
                    && !placeOfWork.trim().isEmpty()
                    && !phoneNo.trim().isEmpty()) {
                Employee employee = new Employee(employeeNo.trim(), fullName.trim(), placeOfWork.trim(), phoneNo.trim());
                employeeDAO.add(employee);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/employeeService?action=list");
    }
}
