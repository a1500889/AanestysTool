<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="../resources/styles/tyyli.css">
<link rel="stylesheet" href="../resources/styles/skeleton.css">
<link rel="stylesheet" href="../resources/styles/normalize.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tunnistaudu äänestykseen</title>
<script>

</script>
</head>
<body>

<h2>Anna nimesi:</h2>

${alert}
<form:form method="post" action="tunnistus">
<input type="hidden" name="iidee" value="${iidee}">

<p>Etunimi</p>
<input type=text name="etunimi">
<p>Sukunimi</p>
<input type=text name="sukunimi"><br>
<div class="three columns">
		<button type="submit">Seuraava</button>
		<a class="button" href="/projektiaanestys/Main/aanestys1">Takaisin</a>
	</div>

</form:form>

</body>
</html>