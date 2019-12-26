<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                <th>Action</th>
            </tr>

            <c:forEach var="tempCustomer" items="${customers}">
                <!-- construct an "update" link with customer id -->
                <c:url var="updateLink" value="/customer/showFormForUpdate">
                    <c:param name="customerId" value="${tempCustomer.id}" />
                </c:url>

                <tr>
                    <td>${tempCustomer.firstName}</td>
                    <td>${tempCustomer.lastName}</td>
                    <td>${tempCustomer.email}</td>
                    <td><a href="${updateLink}">Update</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>
