<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>

<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<link rel="stylesheet" href="../resources/styles/tyyli.css">
<link rel="stylesheet" href="../resources/styles/skeleton.css">
<link rel="stylesheet" href="../resources/styles/normalize.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Lista äänestäjistä</title>
</head>
<body>
	<img id="logo" src="../resources/images/hh_logo.jpg" />
	<hr>
	<div class="container" id="LisaaAanestaja">

		<div class="row">
			<font color="${viestivari}">${alert}</font>
			<form:form method="POST" id="oikeusForm" commandName="lisaaOikeudet" action="lisaaOikeudet">
			<c:forEach items="${aanestykset}" var="aanestys">
			<td><input type="checkbox" name="valAanestykset" value="${aanestys.aanestysID}"/> </td>
			<td><c:out value="${aanestys.aanestysNimi}" /></td>
			</c:forEach>
			<button name="lahetysnappi" value="oikeus" type="submit">Lisaa oikeudet</button><br>
			<c:forEach items="${ryhmat}" var="ryhma">
			<td><input type="radio" name="valRyhma" value="${ryhma.ryhmaID}"/> </td>
			<td><c:out value="${ryhma.ryhmaNimi}" /></td>
			</c:forEach>
			<button name="lahetysnappi" value="ryhma" type="submit">Lisaa ryhmään</button>
			<div class="nine columns">
				<h1>Lista äänestäjistä:</h1>

				<table class="u-full-width">
					<thead>
						<tr class="header">
							<th>ID</th>
							<th>Etunimi</th>
							<th>Sukunimi</th>
							<th>Ryhmä</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${aanestajat}" var="aanestaja">

							<tr class="napit">
								<td><input type="checkbox" name="valAanestaja" value="${aanestaja.aanestajaID}"/> </td>
								<td><c:out value="${aanestaja.aanestajaID}" /></td>
								<td><c:out value="${aanestaja.etunimi}" /></td>
								<td><c:out value="${aanestaja.sukunimi}" /></td>
								<td><c:out value="${aanestaja.ryhma.ryhmaTunnus}" /></td>
							</tr>
							<tr>
								<td><form:form id="envselection" modelAttribute="envBean"
										method="post" action="aanestajapoisto">
										<form:hidden path="env" value="${aanestaja.aanestajaID}"
											onclick="submitForm()" />
										<button type="submit">Poista</button>
									</form:form></td>
							</tr>
						</c:forEach>
					</tbody>
					
				</table>
			</div>
			</form:form>
		</div>
		<div class="keskitys">
					<hr>
					<a class="button button-red" href="/projektiaanestys/Main/admin">Edellinen</a>
					
					</div>
	</div>
	

</body>
</html>