<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<h1>Registration</h1>

<form method="POST">

</form>

<% 
    List<String> errors = response.getAttribute("errors");
    if (errors != null && !errors.isEmpty()) {
%>
    <ul>
        <% 
            for (String error : errors) {
        %>
            <li>
                <c:out value="${error}" />
            </li>
        <% 
            }
        %>
    </ul>
<% 
    }
%>