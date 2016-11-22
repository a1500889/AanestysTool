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
</head>
<body>
	<img id="logo" src="../resources/images/hh_logo.jpg" />
	<hr>
	<div class="container" id="LisaaAanestaja">

		<div class="row">


			<div class="nine columns">


				<h1>Lisää äänestäjiä</h1>
				<h3>Tarkista äänestäjät!</h3>

				<table class="u-full-width">
					<thead>
						<tr class="header">
							<c:forEach items="${Lista}" var="L">
								<tr>
								<td><c:out value="${L.etunimi}" /></td>
								<td><c:out value="${L.sukunimi}" /></td>
								
								
							</tr>
							
							
							</c:forEach>
						</tr>
					</thead>
					<tbody>

						
				
				</tbody>


				</table>
			</div>
		</div>
	</div>
	<div class="three columns">
		<a class="button" href="/projektiaanestys/">Takaisin</a>
		<a class="button" href="saveExcelAanestaja">Tallenna</a>
	</div>
</body>
</html>