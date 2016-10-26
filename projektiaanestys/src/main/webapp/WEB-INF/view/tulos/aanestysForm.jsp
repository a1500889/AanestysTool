<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="../resources/styles/tyyli.css">
<link rel="stylesheet" href="../resources/styles/skeleton.css">
<link rel="stylesheet" href="../resources/styles/normalize.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>muokkaa/lis‰‰ ‰‰nestys</title>
</head>
<body>
	<img id="logo" src="../resources/images/hh_logo.jpg" />

	<div class="container" id="LisaaAanestaja">

		<div class="row">
			<div class="nine columns">

				<h1>muokkaa/lis‰‰ ‰‰nestys</h1>
				<form:form action="saveAanestys" method="post"
					modelAttribute="aanestys">
					<table>
						<form:hidden path="AanestysID" />
						<tr>
							<td>nimi:</td>
							<td><form:input path="AanestysNimi" /></td>
						</tr>
						<tr>
							<td>Kuvaus:</td>
							<td><form:input path="Kuvaus" /></td>
						</tr>
						<tr>
							<td>Tunnus:</td>
							<td><form:input path="Tunnus" /></td>
						</tr>

						<tr>
							<td colspan="2" align="center"><input type="submit"
								value="Save"></td>
						</tr>
					</table>
				</form:form>
			</div>
		</div>
	</div>
	<div class="three columns">
		<a class="button" href="/projektiaanestys/">Takaisin</a>
	</div>
</body>
</html>