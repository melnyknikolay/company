<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<html>
<head>
  <title>CompanyData</title>

  <link rel="stylesheet" href="/resources/css/style.css">
</head>
<body>
<a href="../../index.jsp">Back to main menu</a><br/>
<a href="<c:url value="/companies/0"/>">Main Companies list</a>
<br/>
<br/>
<h1>Company Details</h1>


<table class="tg">
  <tr>
    <th width="80">ID</th>
    <th width="120">Company Name</th>
    <th width="120">Earning</th>
    <th width="120">Parrent</th>
  </tr>
  <tr>
    <td>${company.id}</td>
    <td>${company.companyName}</td>
    <td>${company.earning}</td>
    <td>${Parrent}</td>
  </tr>
</table>

<h1>Company Tree</h1>
${tree}
</body>
</html>