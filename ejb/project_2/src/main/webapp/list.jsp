<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Teams</title></head>
<body>
<h2>Teams</h2>
<c:if test="${not empty error}"><div style="color:red">${error}</div></c:if>
<p><a href="teams?action=addForm">Add New Team</a></p>
<table border="1">
    <tr><th>ID</th><th>Name</th><th>City</th><th>Actions</th></tr>
    <c:forEach var="t" items="${teams}">
        <tr>
            <td>${t.id}</td>
            <td>${t.name}</td>
            <td>${t.city}</td>
            <td>
                <a href="teams?action=edit&id=${t.id}">Edit</a>
                &nbsp;|
                <a href="teams?action=delete&id=${t.id}" onclick="return confirm('Delete?')">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
