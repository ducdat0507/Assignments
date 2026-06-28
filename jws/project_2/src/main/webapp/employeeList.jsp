<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.employee.entity.Employee" %>
<html>
<head>
    <title>Employee List</title>
</head>
<body>
<h2>Employee List</h2>
<p><a href="addEmployee.jsp">Add New Employee</a></p>
<table border="1" cellpadding="4" cellspacing="0">
    <tr>
        <th>Employee No</th>
        <th>Full Name</th>
        <th>Place Of Work</th>
        <th>Phone No</th>
        <th>Actions</th>
    </tr>
    <%
        List<Employee> employees = (List<Employee>) request.getAttribute("employees");
        if (employees != null && !employees.isEmpty()) {
            for (Employee employee : employees) {
    %>
    <tr>
        <td><%= employee.getEmployeeNo() %></td>
        <td><%= employee.getFullName() %></td>
        <td><%= employee.getPlaceOfWork() %></td>
        <td><%= employee.getPhoneNo() %></td>
        <td><a href="employeeService?action=delete&employeeNo=<%= employee.getEmployeeNo() %>">Delete</a></td>
    </tr>
    <%      }
        } else {
    %>
    <tr>
        <td colspan="5">No employees found.</td>
    </tr>
    <% } %>
</table>
</body>
</html>
