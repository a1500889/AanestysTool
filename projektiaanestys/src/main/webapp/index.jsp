<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="styles/tyyli.css">
<title>Äänestys</title>
</head>
<body>
<div id="sisalto">
<div id="sivuraidat">
	<img id="raidat" src="images/raidat.png" />
</div>
	



<div id="lomake">
	<img src="images/hh_logo.jpg" alt="Haaga-Helia" />
	<h1>Testiäänestys</h1>
	
	<form action="" method="post">
	  <input type="radio" name="testi" value="mango" checked> Mango<br>
	  <input type="radio" name="testi" value="kettu"> Kettu<br>
	  <input type="radio" name="testi" value="kettu2"> Kettu2<br>
	<input type="button" onclick="alert('Kiitos äänestäsi!')" value="Lähetä">

</form>
</div>
</div>
</body>
</html> 