<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="addAttendee.title"/></title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        form { max-width: 400px; }
        label { display: block; margin-top: 10px; }
        input, select { width: 100%; padding: 8px; margin-top: 5px; }
        button { margin-top: 20px; padding: 10px; background-color: #4CAF50; color: white; border: none; border-radius: 5px; cursor: pointer; }
        .error { color: red; }
    </style>
</head>
<body>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <fmt:setBundle basename="messages"/>
    <h1><fmt:message key="addAttendee.title"/></h1>
    <form action="addAttendee" method="post">
        <input type="hidden" name="lang" value="${param.lang}">
        <label for="name"><fmt:message key="addAttendee.name"/>:</label>
        <input type="text" id="name" name="name" required minlength="3">

        <label for="email"><fmt:message key="addAttendee.email"/>:</label>
        <input type="email" id="email" name="email" required>

        <label for="eventId"><fmt:message key="addAttendee.event"/>:</label>
        <select id="eventId" name="eventId" required>
            <%@ page import="java.util.List, com.example.project_10.Event" %>
            <% List<Event> events = (List<Event>) request.getAttribute("events"); %>
            <% if (events != null) { %>
                <% for (Event event : events) { %>
                    <option value="<%= event.getId() %>"><%= event.getName() %> (<%= event.getDate() %>)</option>
                <% } %>
            <% } %>
        </select>

        <button type="submit"><fmt:message key="addAttendee.submit"/></button>
    </form>
    <% if (request.getAttribute("errors") != null) { %>
        <div class="error">
            <ul>
                <% for (javax.validation.ConstraintViolation violation : (java.util.Set<javax.validation.ConstraintViolation>) request.getAttribute("errors")) { %>
                    <li><%= violation.getMessage() %></li>
                <% } %>
            </ul>
        </div>
    <% } %>
    <% if (request.getAttribute("error") != null) { %>
        <div class="error"><%= request.getAttribute("error") %></div>
    <% } %>
    <br><a href="index.jsp?lang=${param.lang}"><fmt:message key="backHome"/></a>
</body>
</html>