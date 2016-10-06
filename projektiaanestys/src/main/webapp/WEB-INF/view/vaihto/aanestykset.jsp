<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>

<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<link rel="stylesheet" href="resources/styles/tyyli.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Äänestykset</title>
</head>
<body>

<h1>Tarjolla olevat äänestykset:</h1>

<table>

<td>Nimi:</td>
<td>ID:</td>
<td>Tunnus:</td>
<td>Kuvaus:</td>

<form action="/aanestyspoisto/${aanestys.aanestysID}" method="post">

<c:forEach items="${aanestykset}" var="tykset"> 
<tr>   
<td>
   <c:out value="${tykset.aanestysNimi}"/>
</td>
<td>
  <c:out value="${tykset.aanestysID}"/>
   </td>
   <td>
  <c:out value="${tykset.tunnus}"/>
</td>
 
 <td>
 <c:out value="${tykset.kuvaus}"/>
 </td>
 
 <form:form id="envselection" modelAttribute="envBean" method="get" action="aanestyspoisto">
 <form:radiobutton path="env" value="${tykset.aanestysID}" onclick="submitForm()"/>
 <button type="submit">Testi</button>
 </form:form>

</tr>
 
</form>
</c:forEach>


</table>

</body>
</html>