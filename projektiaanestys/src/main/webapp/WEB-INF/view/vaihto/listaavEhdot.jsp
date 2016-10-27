<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>

<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" href="resources/styles/tyyli.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Vaihtoehdot</title>
</head>
<body>

<h1>Äänestyksen vaihtoehdot:</h1>
<%-- Käytetään backup:ina AanestysVaihtoehto beania --%>
	<c:forEach items="${vaihtoehdot}" var="ehdot">
		<form:form modelAttribute="aanestysVaihtoehto" method="post">		
		</form:form>
	</c:forEach>
	<%-- EnvBean jälleen toiminnassa, method=post ohjaa Controllerissa metodia, action=lista ohjaa listaaAanet.jsp:lle --%>
	<form:form id="envselection" modelAttribute="envBean" method="post" action="lista">
	<c:forEach var="optio" items="${vaihtoehdot}">
	<%-- from:radiobutton setti hakee controllerin getView metodista vaihtoehdot --%>
		<form:radiobutton path="env" value="${optio.vaihtoehtoID}" onclick="submitForm()"/>${optio.vaihtoehtoNimi}<br>
	</c:forEach>
    	<button type="submit">Äänestä</button>
</form:form>	

<a href="/projektiaanestys/">Takaisin</a>				



</body>
</html>
