<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Update Team</title></head>
<body>
<h2>Update Team</h2>
<c:if test="${not empty error}"><div style="color:red">${error}</div></c:if>
<c:if test="${not empty team}">
    <form action="teams" method="post">
        <input type="hidden" name="action" value="update"/>
        <input type="hidden" name="id" value="${team.id}"/>
        Name: <input type="text" name="name" value="${team.name}" required/><br/>
        City: <input type="text" name="city" value="${team.city}"/><br/>
        <button type="submit">Update</button>
    </form>
</c:if>
<p><a href="teams">Back to list</a></p>
</body>
</html>
