<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Vaihtoehdot</title>
</head>
<body>

<h1>Äänestyksen vaihtoehdot:</h1>


	<tr>
    <th>Numero</th>
    <th>Vaihtoehdon nimi</th>
  </tr>
  
	<c:forEach items="${vaihtoehdot}" var="ehdot"> 
  	
  	
<table style="width:50%">
  
  <tr>
    <td><p><c:out value="${ehdot.vaihtoehtoID}" default="-----"/></p></td>
    <td><p><c:out value="${ehdot.vaihtoehtoNimi}" default="-----"/></p></td>
    
  </tr>
</table>

</c:forEach>


<form>
<input type="radio" name="vaihtoehto" value="vaihtoehto1" checked> Vaihtoehto 1<br>

<input type="radio" name="vaihtoehto" value="vaihtoehto2"> Vaihtoehto 2<br>

<input type="radio" name="vaihtoehto" value="vaihtoehto3"> Vaihtoehto 3<br>

<input type="radio" name="vaihtoehto" value="vaihtoehto4"> Vaihtoehto 5<br>


<input type="submit" value="Lähetä">
</form>


</body>
</html>