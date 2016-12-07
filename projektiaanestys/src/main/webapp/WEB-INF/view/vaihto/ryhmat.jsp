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
<script type="text/javascript">
function toggle(source) {
	  checkboxes = document.getElementsByName('valAanestaja');
	  for(var i=0, n=checkboxes.length;i<n;i++) {
	    checkboxes[i].checked = source.checked;
	  }
	}
</script>

<title>Lista ryhmistä</title>
</head>
<body>
	<img id="logo" src="../resources/images/hh_logo.jpg" />
	<hr>
	<div class="container" id="LisaaAanestaja">

		<div class="row">
			<font color="${viestivari}">${alert}</font>
			<div class="nine columns">
				<h1>Lista ryhmistä:</h1>

				<table class="u-full-width">
					<thead>
						<tr class="header">
							<th>Nimi</th>
							<th>Tunnus</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ryhmat}" var="ryhma">

							<tr class="napit">
								<td><c:out value="${ryhma.ryhmaNimi}" /></td>
								<td><c:out value="${ryhma.ryhmaTunnus}" /></td>
							</tr>
							<tr>
								<td><form:form id="envselection" modelAttribute="envBean"
										method="post" action="ryhmapoisto">
										<form:hidden path="env" value="${ryhma.ryhmaID}"
											onclick="submitForm()" />
										<button type="submit">Poista</button>
									</form:form></td>
							</tr>
						</c:forEach>
					</tbody>
					
				</table>
			</div>
		</div>
		<div class="keskitys">
					<hr>
					<a class="button button-red" href="/projektiaanestys/Main/admin">Edellinen</a>
					
					</div>
	</div>
	

</body>
</html>