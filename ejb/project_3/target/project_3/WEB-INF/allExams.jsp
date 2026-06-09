<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Exam" %>
<%
    List<Exam> exams = (List<Exam>) request.getAttribute("exams");
    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
%>
<html>
<body>
<h1>All Exams</h1>
<p><a href="/">Back home</a> | <a href="newExam.jsp">New Exam</a></p>
<table border="1" cellpadding="4">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Duration</th>
        <th>Description</th>
        <th>Created At</th>
        <th>Updated At</th>
    </tr>
    <% if (exams != null) {
           for (Exam e : exams) { %>
        <tr>
            <td><%= e.getId() %></td>
            <td><%= e.getName() %></td>
            <td><%= e.getDuration() %></td>
            <td><%= e.getDescription() %></td>
            <td><%= e.getCreatedAt() == null ? "" : df.format(e.getCreatedAt()) %></td>
            <td><%= e.getUpdatedAt() == null ? "" : df.format(e.getUpdatedAt()) %></td>
        </tr>
    <%   }
    } %>
</table>
</body>
</html>
