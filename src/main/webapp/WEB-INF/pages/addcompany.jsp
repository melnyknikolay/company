<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Add Company Page</title>

    <link rel="stylesheet" href="/resources/css/style.css">
</head>
<body>
<a href="../../index.jsp">Start</a><br/>
<a href="<c:url value="/companies"/>">Main Companies list</a><br/>
<button onclick="window.history.back()">Back</button><br/>
<h1>Add a Company</h1>

<c:url var="addAction" value="/companies/add/"/>


<form:form action="${addAction}" commandName="company">
    <form:form modelAttribute="superparrentid">${superparrentid}</form:form>
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
