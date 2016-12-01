<%@ page language="java" contentType="text/html; charset=UTF-8"
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
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Tunnistaudu äänestykseen</title>
<script>

</script>
</head>
<body>
<div class="container keskitys">
<h2 style="margin-top:15%;">Anna nimesi:</h2>
<font color="red">${alert}</font>
<form:form method="post" action="tunnistus">
<input type="hidden" name="iidee" value="${iidee}">

<label>Etunimi</label>
<input class="u-full-width" type=text name="etunimi">
<label>Sukunimi</label>
<input class="u-full-width" type=text name="sukunimi"><br>
<label>Ryhmän nimi (PAKOLLINEN jos kuulut johonkin ryhmään)</label>
<input class="u-full-width" type=text name="rTun"><br>
<!-- <div class="keskitys"> -->
		<a class="button button-red" href="/projektiaanestys/Main/aanestys1">Edellinen</a>
		<button class="button button-primary" type="submit">Seuraava</button>
		
<!-- 	</div> -->

</form:form>
</div>
</body>
</html>