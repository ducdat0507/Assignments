<!DOCTYPE html>
<html>
<head>
    <title>Attendees List</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
    </style>
</head>
<body>
    <h1>Attendees for Event: <%= ((com.example.project_10.Event) request.getAttribute("event")).getName() %></h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
        </tr>
        <%@ page import="java.util.List, com.example.project_10.Attendee" %>
        <% List<Attendee> attendees = (List<Attendee>) request.getAttribute("attendees"); %>
        <% if (attendees != null && !attendees.isEmpty()) { %>
            <% for (Attendee attendee : attendees) { %>
                <tr>
                    <td><%= attendee.getId() %></td>
                    <td><%= attendee.getName() %></td>
                    <td><%= attendee.getEmail() %></td>
                </tr>
            <% } %>
        <% } else { %>
            <tr>
                <td colspan="3">No attendees found for this event.</td>
            </tr>
        <% } %>
    </table>
    <br><a href="index.jsp">Back to Home</a>
</body>
</html>