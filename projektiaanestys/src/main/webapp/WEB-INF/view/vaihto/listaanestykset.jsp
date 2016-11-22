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
<title>Äänestykset</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<img id="logo" src="../resources/images/hh_logo.jpg" />

	<div class="container" id="LisaaAanestaja">

		<div class="row">

<!-- isoille näytöille. ei näy mobiilissa -->
			<div class="eleven columns desktop">


				<h1>Tarjolla olevat äänestykset:</h1>

				<table id="miyazaki">
					<thead>
						<tr class="header">
							<th>Nimi</th>
							<th>Tunnus</th>
							<th>Kuvaus</th>
							<th>
							<th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${aanestykset}" var="tykset">
							
							<tr>
								<td><c:out value="${tykset.aanestysNimi}" /></td>
								<%-- 							<td><c:out value="${tykset.aanestysID}" /></td> --%>
								<td><c:out value="${tykset.tunnus}" /></td>

								<td><c:out value="${tykset.kuvaus}" /></td>
								<!-- 						</tr> -->

								<!-- 						<tr> -->
								<td><form:form id="envselection" modelAttribute="envBean"
										method="get" action="tunnistus">
										<form:hidden path="env" value="${tykset.aanestysID}"
											onclick="submitForm()" />
										<button type="submit">Valitse</button>
									</form:form></td>
								<!-- 						</tr> -->
								<!-- 						<tr class="napit"> -->
								<td>
									<button onclick="location.href='listaa/${tykset.aanestysID}'">Tulos</button>
								</td>
							</tr>
						</c:forEach>
				</table>
			</div>
			
<!-- mobiiliversio. katoaa isoilla näytöillä -->
			<div class="twelve columns mobiili">
			<h3>Valitse äänestys</h3>
			<c:forEach items="${aanestykset}" var="tykset">

					<tr>

						<td><form:form id="envselection" modelAttribute="envBean"
								method="get" action="tunnistus">
								<form:hidden path="env" value="${tykset.aanestysID}"
									onclick="submitForm()" />
								<button class="button-primary" type="submit">
									<c:out value="${tykset.aanestysNimi}" />
								</button>
							</form:form></td>
<!-- 					</tr> -->
<!-- 					<tr class="napit"> -->
<!-- 						<td> -->
<%-- 							<button onclick="location.href='listaa/${tykset.aanestysID}'">Tulos</button> --%>
<!-- 						</td> -->
					</tr>
				</c:forEach>
			
			</div>
		</div>
	</div>
	<div class="three columns">
		<a class="button" href="/projektiaanestys/">Takaisin</a>
	</div>
	<script>
		var headertext = [], headers = document
				.querySelectorAll("#miyazaki th"), tablerows = document
				.querySelectorAll("#miyazaki th"), tablebody = document
				.querySelector("#miyazaki tbody");

		for (var i = 0; i < headers.length; i++) {
			var current = headers[i];
			headertext.push(current.textContent.replace(/\r?\n|\r/, ""));
		}
		for (var i = 0, row; row = tablebody.rows[i]; i++) {
			for (var j = 0, col; col = row.cells[j]; j++) {
				col.setAttribute("data-th", headertext[j]);
			}
		}
	</script>
</body>
</html>