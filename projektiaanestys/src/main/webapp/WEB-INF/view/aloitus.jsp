<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="resources/styles/tyyli.css">
<link rel="stylesheet" href="resources/styles/skeleton.css">
<link rel="stylesheet" href="resources/styles/normalize.css">
<title>Äänestys</title>
</head>
<body>



	<img id="logo" src="resources/images/hh_logo.jpg" />
	<div class="container" id="sisalto">

		<div class="row">

			<div class="six columns">
				<h3>Äänestäjä</h3>



				<a class="button" href="Main/lista">Äänestys</a>

				<!-- 				<a href="Main/lista">Äänestys</a> -->
			</div>

			<div class="six columns">

				<h3>Admin</h3>

				<a class="button" href="Main/newAanestys">Lisää/muokkaa äänestys</a><br>
				<a class="button" href="Main/newAanestaja">Lisää äänestäjä</a><br>
				<a class="button" href="Main/aanestys">Listaa äänestykset</a><br>
				<a class="button" href="Main/aanestajat">Lista äänestäjistä</a><br>
				<a class="button" href="Main/lisaavaihtoehdot">Lisää vaihtoehtoja</a><br>

			</div>
		</div>
	</div>



</body>
</html>
