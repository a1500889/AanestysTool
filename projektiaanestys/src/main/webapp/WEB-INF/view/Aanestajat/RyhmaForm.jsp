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
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Lis�� ryhm�</title>

</head>
<body>
	<img id="logo" src="../resources/images/hh_logo.jpg" />
	<hr>
	<div class="container" id="LisaaRyhma">

		<div class="row">
			<div class="nine columns">
				<font color="${viestivari}">${alert}</font>
				<h1>Lis�� ryhm�</h1>
				<form:form action="saveRyhma" method="post"
					modelAttribute="Ryhma">
					<table>
						<form:hidden path="RyhmaTunnus" />
						<tr>
							<td>Nimi:</td>
							<td><form:input path="RyhmaNimi" /></td>
						</tr>
						<tr>
							<td>Tunnus:</td>
							<td><form:input path="RyhmaID" /></td>
						</tr>

					</table>			
					

					<td colspan="2" align="center"><input type="submit"
								value="Save"></td>
					
					<div class="three columns">
				
		<a class="button" href="/projektiaanestys/Main/admin">Takaisin</a>
	</div>
				</form:form>
			</div>
		</div>
	</div>
	
</body>
</html>