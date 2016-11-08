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
<title>Admin</title>
</head>
<body>



	<img id="logo" src="../resources/images/hh_logo.jpg" />
	<hr>
	<div class="container" id="sisalto">

		<div class="row">

			<div class="five columns">


				<h3>Äänestys</h3>

				<a class="button" href="newAanestys">Lisää äänestys</a><br>
				<a class="button" href="aanestys">Listaa äänestykset</a><br>
				<a class="button" href="lisaavaihtoehdot">Lisää vaihtoehtoja</a><br>
				

			</div>
			
			<div class="four columns">


				<h3>Äänestäjät</h3>

				
				<a class="button" href="newAanestaja">Lisää äänestäjä</a><br>
				<a class="button" href="aanestajat">Lista äänestäjistä</a><br>
				<a class="button" href="newExcelAanestaja">Lisää excelistä</a><br>

			</div>
		</div>
	</div>
	<div class="three columns">
		<a class="button" href="/projektiaanestys/">Takaisin</a>
	</div>


</body>
</html>
