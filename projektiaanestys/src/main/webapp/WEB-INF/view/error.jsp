<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>

<% response.setStatus(404); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
 "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
  	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>404 error</title>
  </head>
  <body>
  <center><H1>Ups, nyt kävi köpelösti!</H1>
  
   <p> Sivua ei löydy tai se on vanhentunut.</p> 
    <button onclick="goBack()">Palaa edelliselle sivulle</button>
</center>
<script>
function goBack() {
    window.history.back();
}
</script>
  </body>
</html>