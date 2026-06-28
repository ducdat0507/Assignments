<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add New Employee</title>
</head>
<body>
<h2>Add New Employee</h2>
<form method="post" action="employeeService">
    <input type="hidden" name="action" value="add" />
    <table>
        <tr>
            <td>Employee No:</td>
            <td><input type="text" name="employeeNo" maxlength="20" required /></td>
        </tr>
        <tr>
            <td>Full Name:</td>
            <td><input type="text" name="fullName" maxlength="30" required /></td>
        </tr>
        <tr>
            <td>Place Of Work:</td>
            <td><input type="text" name="placeOfWork" maxlength="30" required /></td>
        </tr>
        <tr>
            <td>Phone No:</td>
            <td><input type="text" name="phoneNo" maxlength="10" required /></td>
        </tr>
        <tr>
            <td colspan="2">
                <button type="submit">Add Employee</button>
                <button type="button" onclick="window.location.href='employeeService?action=list'">Back to List</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
