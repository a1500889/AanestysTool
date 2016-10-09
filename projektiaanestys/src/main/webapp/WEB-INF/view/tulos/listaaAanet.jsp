<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="resources/styles/tyyli.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Annettujen ��nten tulos</title>
</head>
<body>

<h1>��nestyksen tulos:</h1>




<c:forEach items="${tuloslista}" var="tulos">
  <li><c:out value="${tulos.vaihtoehtoNimi}" />:<c:out value="${tulos.aanlkm}" /></li>
</c:forEach>
<a href="/projektiaanestys/">Takaisin</a>


</body>
</html>