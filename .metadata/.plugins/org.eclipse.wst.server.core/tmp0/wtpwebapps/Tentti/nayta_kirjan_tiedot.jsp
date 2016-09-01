<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="model.Kirja" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3>Kirjan tiedot:</h3>
<%Kirja k = (Kirja) request.getAttribute("Kirja"); %>
Nimi:
<%=k.getNimi()%>
Tekijä:
<%=k.getTekijatiedot()%>
ISBN:
<%=k.getISBN()%>

</body>
</html>