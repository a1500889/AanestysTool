<%@ page language="java" contentType="text/html; charset=UTF-8"
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
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Annettujen ‰‰nten tulos</title>
</head>
<body>
	<img id="logo" src="../../resources/images/hh_logo.jpg" />
	<hr>
	
	<div class="container">
	

		<div class="row" align="center">

		


			
			
				<h1>ƒ‰nestyksen tulos:</h1>

<table class="tulostaulu">
<font color="gray" size="2">ƒ‰nestysprosentti:</font>
				<div class="progress">
    <div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width:${aanestysprosentti}%">
      <span>${aanestysprosentti}%</span>
    </div>
  </div>
				<c:forEach items="${tuloslista}" var="tulos">
				<tr>
					<td><c:out value="${tulos.vaihtoehtoNimi}" />: </td>
					<td class="numero"><c:out value="${tulos.aanlkm}" /></td><td><div class="progress">
    <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width:${tulos.aaniosuus}%">
      <span><c:out value="${tulos.aaniosuus}%"/></span>
    </div>
  </div></td>
							</tr>
				</c:forEach>
				</table>
				<hr>
				<a class="button button-red" class="button" href="/projektiaanestys/Main/aanestys1">Takaisin ‰‰nestyksiin</a>
	
		<h1>H‰pe‰paalu</h1>
	<p>Lista omaa vaihtoehtoaan ‰‰nest‰neist‰:</p><br>
	
	<table>
	<tr>
	<th>Nimi</th>
	<th style="padding-left:5%">Ryhm‰</th>
	</tr>
	<c:forEach items="${hapealista}" var="havettava">
	<tr>
		
		<td><c:out value="${havettava.etunimi}" /> <c:out value="${havettava.sukunimi}" /></td>
		
		<td style="padding-left:5%"><c:out value="${havettava.ryhma.ryhmaNimi}"/></td>
	
	</tr>
	</c:forEach>
	</table>
	</div>
	

<!-- 	<h1>H‰pe‰paalu</h1> -->
<!-- 	<p>Lista omaa vaihtoehtoaan ‰‰nest‰neist‰:</p><br> -->
<!-- 	<tr><td>Nimi:</td><td>Ryhm‰:</td></tr> -->
<%-- 	<c:forEach items="${hapealista}" var="havettava"> --%>
<%-- 	<br><tr><td><c:out value="${havettava.etunimi}" /> <c:out value="${havettava.sukunimi}" /></td>, <td><c:out value="${havettava.ryhma.ryhmaNimi}"/></td></tr> --%>
	
<%-- 	</c:forEach> --%>
		
	</div>
<script>
setTimeout(function() {
	  location.reload();
	}, 7500);
</script>

</body>
</html>
