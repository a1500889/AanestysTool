<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="resources/styles/tyyli.css">
<title>��nestys</title>
</head>
<body>
<div id="sisalto">
<div id="sivuraidat">
	<img id="raidat" src="resources/images/raidat.png" />
</div>

<h1>��nestys</h1>

	<div id="contentbox">
		<h3>T�lt� sivulta l�yd�t ��nestyksen</h3>
		
		
		<h3>Linkki ��nestykseen:</h3>
		
			<a href="Main/lista">��nestys</a>
		
	</div>

<a href="Main/newAanestys">Muokkaa/lis�� ��nestys</a>
<a href="Main/newAanestaja">Lis�� ��nest�j�</a>

<h2>��nestyslista</h2>
<a href="Main/aanestys">��nestykset</a>

<h2>��nest�j�lista</h2>
<a href="Main/aanestajat">��nest�j�t</a>
</div>

    
</body>
</html> 
