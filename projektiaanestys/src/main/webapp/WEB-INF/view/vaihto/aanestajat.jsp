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
<title>Lista äänestäjistä</title>
</head>
<body>
	<img id="logo" src="../resources/images/hh_logo.jpg" />
	<hr>
	<div class="container" id="LisaaAanestaja">

		<div class="row">


			<div class="nine columns">
				<h1>Lista äänestäjistä:</h1>

				<table class="u-full-width">
					<thead>
						<tr class="header">
							<th>ID</th>
							<th>Etunimi</th>
							<th>Sukunimi</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach items="${aanestajat}" var="aanestajat">

							<tr class="napit">
								<td><c:out value="${aanestajat.aanestajaID}" /></td>
								<td><c:out value="${aanestajat.etunimi}" /></td>
								<td><c:out value="${aanestajat.sukunimi}" /></td>
							</tr>
							<tr>
								<td><form:form id="envselection" modelAttribute="envBean"
										method="post" action="aanestajapoisto">
										<form:hidden path="env" value="${aanestajat.aanestajaID}"
											onclick="submitForm()" />
										<button type="submit">Poista</button>
									</form:form></td>
							</tr>
						</c:forEach>
					</tbody>
					
				</table>
			</div>
		</div>
	</div>
	<div class="three columns">
		<a class="button" href="/projektiaanestys/">Takaisin</a>
	</div>

</body>
</html>