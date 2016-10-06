<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>

<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<link rel="stylesheet" href="resources/styles/tyyli.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista äänestäjistä</title>
</head>
<body>

<h1>Lista äänestäjistä:</h1>

<table>

<td>ID:</td>
<td>Etunimi:</td>
<td>Sukunimi:</td>


<c:forEach items="${aanestajat}" var="aanestajat"> 

<tr>   
<td>
  <c:out value="${aanestajat.aanestajaID}"/>
</td>
<td>
   <c:out value="${aanestajat.etunimi}"/>
</td>
<td>
  <c:out value="${aanestajat.sukunimi}"/>
   </td>
</tr>
</c:forEach>
</table>

<a href="/projektiaanestys">Takaisin</a>

</body>
</html>