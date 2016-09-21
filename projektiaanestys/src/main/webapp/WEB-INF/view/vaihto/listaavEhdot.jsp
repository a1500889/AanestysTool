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




<form>
<c:forEach items="${vaihtoehdot}" var="ehdot">

<input type="radio" name="vaihtoehto" value="${ehdot.vaihtoehtoNimi}" checked>${ehdot.vaihtoehtoNimi} <br>


</c:forEach>

<input type="submit" value="Lähetä">
</form>


</body>
</html>