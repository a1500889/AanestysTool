<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="../../resources/styles/tyyli.css">
<link rel="stylesheet" href="../../resources/styles/skeleton.css">
<link rel="stylesheet" href="../../resources/styles/normalize.css">
<c:url var="cssUrl" value="/tyyli.css" />
<link rel="stylesheet" type="text/css" href="${cssUrl}" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Annettujen äänten tulos</title>
</head>
<body>
	<img id="logo" src="../../resources/images/hh_logo.jpg" />
	<hr>
	
	<div class="container" id="LisaaAanestaja" align ="center" >

		<div style=" text-align:left;" class="row">


			
			
				<h1>Äänestyksen tulos:</h1>




				<c:forEach items="${tuloslista}" var="tulos">
					<li><c:out value="${tulos.vaihtoehtoNimi}" />:<c:out
							value="${tulos.aanlkm}" /></li>
				</c:forEach>
				<a class="button button-red" class="button" href="/projektiaanestys/Main/aanestys1">Takaisin</a>
	
		
	</div>
	
		
	</div>
<script>
setTimeout(function() {
	  location.reload();
	}, 7500);
</script>

</body>
</html>
