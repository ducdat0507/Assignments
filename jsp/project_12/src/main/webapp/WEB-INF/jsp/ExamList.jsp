<%-- 
    Document   : ExamList
    Created on : 22 thg 4, 2026, 22:32:48
    Author     : ducdat0507
--%>

<%@page import="com.example.project_12.dao.ExamDao"%>
<%@page import="com.example.project_12.model.Exam"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% List<Exam> exams = ExamDao.getAll(); %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>View Exams</h1>
        <table> 
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Doration</th>
                </tr>
            </thead>
            <tbody>
                <% for (Exam exam : exams) { %>
                    <tr>
                        <td><%= exam.getId() %></td>
                        <th><%= exam.getName() %></th>
                        <th><%= exam.getDuration() %></th>
                    </tr>
                <% } %>
            </tbody>
    </body>
</html>
