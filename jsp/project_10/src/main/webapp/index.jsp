<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="app.title"/></title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        a { margin: 10px; padding: 10px; text-decoration: none; background-color: #4CAF50; color: white; border-radius: 5px; }
    </style>
</head>
<body>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <fmt:setBundle basename="messages"/>
    <h1><fmt:message key="app.title"/></h1>
    <a href="addEvent.jsp?lang=${param.lang}"><fmt:message key="nav.addEvent"/></a>
    <a href="viewEvents?lang=${param.lang}"><fmt:message key="nav.viewEvents"/></a>
    <a href="addAttendee?lang=${param.lang}"><fmt:message key="nav.addAttendee"/></a>
    <br><br>
    <a href="?lang=vi">Tiếng Việt</a> | <a href="?lang=en">English</a>
</body>
</html>
