package com.example.service;

import com.example.dao.ComputerDAO;
import com.example.model.Computer;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ComputerServiceServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ComputerDAO dao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = config.getServletContext();
        String url = context.getInitParameter("jdbc.url");
        String user = context.getInitParameter("jdbc.user");
        String password = context.getInitParameter("jdbc.password");
        if (url == null || url.isEmpty()) {
            throw new ServletException("Database connection URL is not configured. Update web.xml context parameters.");
        }
        dao = new ComputerDAO(url, user, password);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("application/xml;charset=UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            writeError(response, "Missing action parameter. Use action=getAll, add, or find.");
            return;
        }
        try {
            if ("getAll".equalsIgnoreCase(action)) {
                List<Computer> list = dao.getAllComputers();
                writeComputers(response, list);
            } else if ("find".equalsIgnoreCase(action)) {
                String comID = request.getParameter("comID");
                if (comID == null || comID.trim().isEmpty()) {
                    writeError(response, "Parameter comID is required for find.");
                    return;
                }
                Computer computer = dao.findComputerById(comID.trim());
                if (computer == null) {
                    writeError(response, "Computer not found for comID=" + escapeXml(comID));
                } else {
                    writeComputer(response, computer);
                }
            } else if ("add".equalsIgnoreCase(action)) {
                String comID = request.getParameter("comID");
                String comName = request.getParameter("comName");
                String priceValue = request.getParameter("price");
                String manufacturer = request.getParameter("manufacturer");
                if (comID == null || comID.trim().isEmpty() || comName == null || comName.trim().isEmpty()
                        || priceValue == null || priceValue.trim().isEmpty() || manufacturer == null || manufacturer.trim().isEmpty()) {
                    writeError(response, "All fields are required: comID, comName, price, manufacturer.");
                    return;
                }
                double price;
                try {
                    price = Double.parseDouble(priceValue);
                } catch (NumberFormatException e) {
                    writeError(response, "Price must be a numeric value.");
                    return;
                }
                Computer computer = new Computer(comID.trim(), comName.trim(), price, manufacturer.trim());
                boolean created = dao.addComputer(computer);
                if (created) {
                    writeSuccess(response, "Computer was added successfully.");
                } else {
                    writeError(response, "Failed to add computer.");
                }
            } else {
                writeError(response, "Unsupported action: " + escapeXml(action));
            }
        } catch (SQLException e) {
            writeError(response, "Database error: " + escapeXml(e.getMessage()));
        } catch (ClassNotFoundException e) {
            writeError(response, "Driver error: " + escapeXml(e.getMessage()));
        }
    }

    private void writeComputers(HttpServletResponse response, List<Computer> list) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.print("<computers>");
        for (Computer computer : list) {
            writer.print(computer.toXmlString());
        }
        writer.print("</computers>");
    }

    private void writeComputer(HttpServletResponse response, Computer computer) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.print("<computer>");
        writer.print(computer.toXmlString());
        writer.print("</computer>");
    }

    private void writeError(HttpServletResponse response, String message) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.print("<result><success>false</success><message>");
        writer.print(escapeXml(message));
        writer.print("</message></result>");
    }

    private void writeSuccess(HttpServletResponse response, String message) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.print("<result><success>true</success><message>");
        writer.print(escapeXml(message));
        writer.print("</message></result>");
    }

    private String escapeXml(String input) {
        if (input == null) {
            return "";
        }
        return input.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&apos;");
    }
}
