<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>

<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" href="../resources/styles/tyyli.css">
<link rel="stylesheet" href="../resources/styles/skeleton.css">
<link rel="stylesheet" href="../resources/styles/normalize.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Vaihtoehdot</title>
</head>
<body>
	<img id="logo" src="../resources/images/hh_logo.jpg" />
	<hr>
	<div class="container" id="LisaaAanestaja">

		<div class="row">


			<div class="nine columns">
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
						<label for="${optio.vaihtoehtoID}">
						<form:radiobutton path="env" id="${optio.vaihtoehtoID}" value="${optio.vaihtoehtoID}"
							onclick="submitForm()" /> ${optio.vaihtoehtoNimi}
							</label>
					</c:forEach>
					<input type="hidden" name="KID" value="${KID}"/>
					
					<div class="keskitys">
					<hr>
					<a class="button button-red" href="/projektiaanestys/Main/aanestys1">Takaisin</a>
					<button class="button button-primary" type="submit">Äänestä</button>
					</div>
					
		
				</form:form>
				

			</div>
		</div>
	</div>

</body>
</html>
