<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="../resources/styles/tyyli.css">
<link rel="stylesheet" href="../resources/styles/skeleton.css">
<link rel="stylesheet" href="../resources/styles/normalize.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Lisää äänestäjiä</title>
</head>
<body>
	<img id="logo" src="../resources/images/hh_logo.jpg" />
	<hr>
	<div class="container" id="LisaaAanestaja">

		<div class="row">
		
		
			<div class="nine columns">

				<h1>Lisää äänestäjä</h1>
				<form:form action="saveAanestaja" method="post"
					modelAttribute="aanestaja">
					<table>
						<form:hidden path="AanestajaID" />
						<tr>
							<td>Etunimi: </td>
							<td><form:input path="Etunimi" /></td>
						</tr>
						<tr>
							<td>Sukunimi: </td>
							<td><form:input path="Sukunimi" /></td>
						</tr>


						<tr>
							<td colspan="2" align="center"><input type="submit"
								value="Save"></td>
						</tr>
					</table>
				</form:form>
			</div>
		</div>
		<div class="keskitys">
					<hr>
					<a class="button button-red" href="/projektiaanestys/Main/admin">Edellinen</a>
					
					</div>
	</div>
	
</body>
</html>