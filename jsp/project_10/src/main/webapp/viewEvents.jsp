<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="viewEvents.title"/></title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        button { background-color: #f44336; color: white; border: none; padding: 5px 10px; cursor: pointer; }
    </style>
</head>
<body>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <fmt:setBundle basename="messages"/>
    <h1><fmt:message key="viewEvents.title"/></h1>
    <table>
        <tr>
            <th><fmt:message key="viewEvents.id"/></th>
            <th><fmt:message key="viewEvents.name"/></th>
            <th><fmt:message key="viewEvents.date"/></th>
            <th><fmt:message key="viewEvents.venue"/></th>
            <th><fmt:message key="viewEvents.seats"/></th>
            <th><fmt:message key="viewEvents.actions"/></th>
        </tr>
        <%@ page import="java.util.List, com.example.project_10.Event" %>
        <% List<Event> events = (List<Event>) request.getAttribute("events"); %>
        <% if (events != null) { %>
            <% for (Event event : events) { %>
                <tr>
                    <td><%= event.getId() %></td>
                    <td><%= event.getName() %></td>
                    <td><%= event.getDate() %></td>
                    <td><%= event.getVenue() %></td>
                    <td><%= event.getSeatsAvailable() %></td>
                    <td>
                        <form action="viewEvents" method="post" style="display:inline;">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="id" value="<%= event.getId() %>">
                            <input type="hidden" name="lang" value="${param.lang}">
                            <button type="submit"><fmt:message key="viewEvents.delete"/></button>
                        </form>
                        <a href="viewAttendees?eventId=<%= event.getId() %>&lang=${param.lang}"><fmt:message key="viewEvents.viewAttendees"/></a>
                    </td>
                </tr>
            <% } %>
        <% } %>
    </table>
    <br><a href="index.jsp?lang=${param.lang}"><fmt:message key="backHome"/></a>
</body>
</html>