<!DOCTYPE html>
<html>
<head>
    <title>Add New Event</title>
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
    <h1>Add New Event</h1>
    <form action="addEvent" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required minlength="5">

        <label for="date">Date:</label>
        <input type="date" id="date" name="date" required>

        <label for="venue">Venue:</label>
        <input type="text" id="venue" name="venue" required>

        <label for="seatsAvailable">Seats Available:</label>
        <input type="number" id="seatsAvailable" name="seatsAvailable" required min="1">

        <button type="submit">Add Event</button>
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
    <br><a href="index.jsp">Back to Home</a>
</body>
</html>