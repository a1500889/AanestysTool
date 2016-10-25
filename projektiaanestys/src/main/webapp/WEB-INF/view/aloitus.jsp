<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="resources/styles/tyyli.css">
<link rel="stylesheet" href="resources/styles/skeleton.css">
<link rel="stylesheet" href="resources/styles/normalize.css">
<title>Äänestys</title>
</head>
<body>
<div id="sisalto">
<div id="sivuraidat">
	<img id="raidat" src="resources/images/raidat.png" />
</div>

<h1>Admin</h1>

	<div id="contentbox">
		<h3>Tältä sivulta löydät äänestyksen</h3>
		
		
		<h2>Linkki vaihtoehtoihin:</h2>
		
			<a href="Main/lista">Äänestys</a>
		
	</div>

<h2>Äänestyksen muokkaukset</h2>
<a href="Main/newAanestys">Muokkaa/lisää äänestys</a><br>
<a href="Main/newAanestaja">Lisää äänestäjä</a>

<h2>Äänestyslista</h2>
<a href="Main/aanestys">Äänestykset</a>

<h2>Äänestäjälista</h2>
<a href="Main/aanestajat">Äänestäjät</a>
</div>

    
</body>
</html> 
