<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Add Team</title></head>
<body>
<h2>Add Team</h2>
<c:if test="${not empty error}"><div style="color:red">${error}</div></c:if>
<form action="teams" method="post">
    <input type="hidden" name="action" value="add"/>
    Name: <input type="text" name="name" required/><br/>
    City: <input type="text" name="city"/><br/>
    <button type="submit">Add</button>
</form>
<p><a href="teams">Back to list</a></p>
</body>
</html>
