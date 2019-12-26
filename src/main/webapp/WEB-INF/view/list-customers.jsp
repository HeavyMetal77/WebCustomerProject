<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
<head>
    <title>List customers</title>

    <!-- reference our style sheet -->
    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/res/css/style.css" />
</head>
<body>


<div id="wrapper">
    <div id="header">
        <h2>CRM - Customer Relationship Manager</h2>
    </div>
</div>

<div id="container">
    <div id="content">

        <input type="button" value="Add customer" onclick="window.location.href='showFormForAdd'; return false" class="add-button">

        <table>
            <tr>
                <th>First name</th>
                <th>Last name</th>
                <th>Email</th>
            </tr>

            <c:forEach var="tempCustomers" items="${customers}">
                    <tr>
                        <td>${tempCustomers.firstName}</td>
                        <td>${tempCustomers.lastName}</td>
                        <td>${tempCustomers.email}</td>
                    </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>
