<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="resources/styles/tyyli.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lisää äänestäjiä</title>
</head>
<body>

  <div align="center">
        <h1>Lisää äänestäjä</h1>
        <form:form action="saveAanestaja" method="post" modelAttribute="aanestaja">
        <table>
           <form:hidden path="AanestajaID"/>
            <tr>
                <td>Etunimi:</td>
                <td><form:input path="Etunimi" /></td>
            </tr>
            <tr>
                <td>Sukunimi</td>
                <td><form:input path="Sukunimi" /></td>
            </tr>
         
            </tr>
         
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Save"></td>
            </tr>
        </table>
        </form:form>
    </div>

</body>
</html>