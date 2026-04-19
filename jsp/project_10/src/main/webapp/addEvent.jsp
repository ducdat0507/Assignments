<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="addEvent.title"/></title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        form { max-width: 400px; }
        label { display: block; margin-top: 10px; }
        input { width: 100%; padding: 8px; margin-top: 5px; }
        button { margin-top: 20px; padding: 10px; background-color: #4CAF50; color: white; border: none; border-radius: 5px; cursor: pointer; }
        .error { color: red; }
    </style>
</head>
<body>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <fmt:setBundle basename="messages"/>
    <h1><fmt:message key="addEvent.title"/></h1>
    <form action="addEvent" method="post">
        <input type="hidden" name="lang" value="${param.lang}">
        <label for="name"><fmt:message key="addEvent.name"/>:</label>
        <input type="text" id="name" name="name" required minlength="5">

        <label for="date"><fmt:message key="addEvent.date"/>:</label>
        <input type="date" id="date" name="date" required>

        <label for="venue"><fmt:message key="addEvent.venue"/>:</label>
        <input type="text" id="venue" name="venue" required>

        <label for="seatsAvailable"><fmt:message key="addEvent.seats"/>:</label>
        <input type="number" id="seatsAvailable" name="seatsAvailable" required min="1">

        <button type="submit"><fmt:message key="addEvent.submit"/></button>
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