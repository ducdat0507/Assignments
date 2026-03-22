
<%@ page import="java.util.stream.Stream" %>
<% 

    Cookie[] cookies = request.getCookies();
    Stream.of(cookies).forEach(c -> System.out.println(c.getName()));
    if (cookies != null && Stream.of(cookies).anyMatch((cookie) -> cookie.getName().equals("access-token"))) {
        // wow
    } else {
        response.sendRedirect("login.jsp");
    }
%>

<html>
<body>
    <h2>Dashboard page</h2>
    <form method="post" action="api/logout"
        <div>
            <input type="submit" value="Logout">
        </div>
    </form>
</body>
</html>