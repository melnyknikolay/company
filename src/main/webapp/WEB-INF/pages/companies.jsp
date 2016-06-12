<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Companies list</title>

    <link rel="stylesheet" href="/resources/css/style.css">
</head>
<body>
<a href="../../index.jsp">Start</a><br/>
<a href="<c:url value="/companies"/>">Main Companies list</a><br/>
<button onclick="window.history.back()">Back</button><br/>
<h1>${parrentName}'s List</h1>

<c:if test="${!empty listCompanies}">
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="140">COMPANY_NAME</th>
            <th width="120">EARNINGS</th>
            <th width="60">EDIT</th>
            <th width="60">DELETE</th>
            <th width="60">TREE/INFO</th>
        </tr>
        <c:forEach items="${listCompanies}" var="company">
            <tr>
                <td>${company.id}</td>
                <td><a href="/companies/${company.id}">${company.companyName}</a></td>
                <td>${company.earning}K$</td>
                <td><a href="<c:url value='/edit/${company.id}'/>">Edit</a></td>
                <td><a href="<c:url value='/remove/${company.id}'/>">Delete</a></td>
                <td><a href="<c:url value='/companydata/${company.id}'/>">Tree/Info</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>


<h1>Add a Company</h1>

<c:url var="addAction" value="/add/fromlist"/>

<form:form action="${addAction}" commandName="company">
    <table>
        <tr>
            <td>
                <form:label path="companyName">
                    <spring:message text="Name"/>
                </form:label>
            </td>
            <td>
                <form:input path="companyName"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="earning">
                    <spring:message text="Earning"/>
                </form:label>
            </td>
            <td>
                <form:input path="earning"/>
            </td>
        </tr>
        <tr hidden>
            <td>
                <form:label path="parrentId">
                    <spring:message text="parrentId"/>
                </form:label>
            </td>
            <td>
                <form:input path="parrentId"/>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <input type="submit" value="<spring:message text="Add Company"/>"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
