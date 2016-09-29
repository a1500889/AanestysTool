<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>

<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Vaihtoehdot</title>
</head>
<body>

<h1>Äänestyksen vaihtoehdot:</h1>
<%-- Käytetään backup:ina AanestysVaihtoehto beania --%>
	<c:forEach items="${vaihtoehdot}" var="ehdot">
		<form:form modelAttribute="aanestysVaihtoehto" method="post">
	
			
				
				<br/><p></p>		

		</form:form>
	</c:forEach>
	<%-- EnvBean jälleen toiminnassa, method=post ohjaa Controllerissa metodia, action=lista ohjaa listaaAanet.jsp:lle --%>
	<form:form id="envselection" modelAttribute="envBean" method="post" action="lista">
	<c:forEach var="optio" items="${vaihtoehdot}">
	<%-- from:radiobutton setti hakee controllerin getView metodista vaihtoehdot --%>
		<form:radiobutton path="env" value="${optio.vaihtoehtoID}" onclick="submitForm()"/>${optio.vaihtoehtoNimi}
	</c:forEach>
	
	
<%--     <form:radiobutton path="env" value="QA 71" onclick="submitForm()"/>QA 71 --%>
<%--     <form:radiobutton path="env" value="QA 72" onclick="submitForm()"/>QA 72 --%>
<%--     <form:radiobutton path="env" value="QA 73" onclick="submitForm()"/>QA 73 --%>
<%--     <form:radiobutton path="env" value="QA 74" onclick="submitForm()"/>QA 74 --%>
    	<button type="submit">Äänestä</button>
</form:form>		
				



</body>
</html>