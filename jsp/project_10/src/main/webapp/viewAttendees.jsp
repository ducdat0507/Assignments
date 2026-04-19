<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="viewAttendees.title"/>: <%= ((com.example.project_10.Event) request.getAttribute("event")).getName() %></title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
    </style>
</head>
<body>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <fmt:setBundle basename="messages"/>
    <h1><fmt:message key="viewAttendees.title"/>: <%= ((com.example.project_10.Event) request.getAttribute("event")).getName() %></h1>
    <table>
        <tr>
            <th><fmt:message key="viewAttendees.id"/></th>
            <th><fmt:message key="viewAttendees.name"/></th>
            <th><fmt:message key="viewAttendees.email"/></th>
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
                <td colspan="3"><fmt:message key="viewAttendees.noAttendees"/></td>
            </tr>
        <% } %>
    </table>
    <br><a href="index.jsp?lang=${param.lang}"><fmt:message key="backHome"/></a>
</body>
</html>