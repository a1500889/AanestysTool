<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="../resources/styles/tyyli.css">
<link rel="stylesheet" href="../resources/styles/skeleton.css">
<link rel="stylesheet" href="../resources/styles/normalize.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Admin</title>
</head>
<body>



	<img id="logo" src="../resources/images/hh_logo.jpg" />
	<hr>
	<div class="container" id="sisalto">

		<div class="row">

			<div class="five columns">


				<h3>��nestys</h3>

				<a class="button" href="newAanestys">Lis�� ��nestys</a><br>
				<a class="button" href="aanestys">Listaa ��nestykset</a><br>
				<a class="button" href="lisaavaihtoehdot">Lis�� vaihtoehtoja</a><br>
				

			</div>
			
			<div class="four columns">


				<h3>��nest�j�t</h3>

				
				<a class="button" href="newAanestaja">Lis�� ��nest�j�</a><br>
				<a class="button" href="aanestajat">Lista ��nest�jist�</a><br>
				<a class="button" href="newExcelAanestaja">Lis�� excelist�</a><br>
				<a class="button" href="newRyhma">Lis�� Ryhm�</a><br>

			</div>
		</div>
	</div>
	<div class="three columns">
		<a class="button" href="../j_spring_security_logout">Kirjaudu ulos</a>
	</div>


</body>
</html>
