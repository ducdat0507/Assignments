<!DOCTYPE html>
<html>
<head>
    <title>Event List</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        button { background-color: #f44336; color: white; border: none; padding: 5px 10px; cursor: pointer; }
    </style>
</head>
<body>
    <h1>Event List</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Date</th>
            <th>Venue</th>
            <th>Seats Available</th>
            <th>Actions</th>
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
                            <button type="submit">Delete</button>
                        </form>
                        <a href="viewAttendees?eventId=<%= event.getId() %>">View Attendees</a>
                    </td>
                </tr>
            <% } %>
        <% } %>
    </table>
    <br><a href="index.jsp">Back to Home</a>
</body>
</html>