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
 
<!--  <td> -->
<%--  <c:url value="/lista/${tykset.aanestysID}" var="aanUrl"/> --%>
<%--  <form action="<c:url value="/lista/${tykset.aanestysID}"/>"> --%>
<!--  <button type="submit">paina</button> -->
<%--  </form> --%>
<%--  <button class="btn btn-info" onclick="location.href='${aanUrl}'">LIIKU</button> --%>
<!--  </td> -->
<td>
 <form:form id="envselection" modelAttribute="envBean" method="get" action="lista">
 <form:hidden path="env" value="${tykset.aanestysID}" onclick="submitForm()"/>
 <button type="submit">Valitse</button>
 </form:form>
</td>
<td>
 <form:form id="envselection" modelAttribute="envBean" method="get" action="aanestyspoisto">
 <form:hidden path="env" value="${tykset.aanestysID}" onclick="submitForm()"/>
 <button type="submit">Poista</button>
 </form:form>
</td>
</tr>
 
</form>
</c:forEach>


</table>
<a href="/projektiaanestys/">Takaisin</a>
</body>
</html>