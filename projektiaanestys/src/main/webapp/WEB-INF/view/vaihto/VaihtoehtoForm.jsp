<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="../resources/styles/tyyli.css">
<link rel="stylesheet" href="../resources/styles/skeleton.css">
<link rel="stylesheet" href="../resources/styles/normalize.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Lisää vaihtoehto</title>
<SCRIPT lang="javascript">
        function addRow(tableID) 
        {
                var table = document.getElementById(tableID);

                var rowCount = table.rows.length;
                var row = table.insertRow(rowCount);
                var counts = rowCount - 1;

                var cell1 = row.insertCell(0);
                var VaihtoehtoNimi = document.createElement("input");
                VaihtoehtoNimi.type = "text";
                VaihtoehtoNimi.name = "vaihtoehtoNimet";
                cell1.appendChild(VaihtoehtoNimi);

        }
        
        function deleteRow(tableID)
        {
        	if(document.getElementById(tableID).rows.length>2){
            var table = document.getElementById(tableID)
            var rowCount = table.rows.length;
            table.deleteRow(rowCount - 1);
        	}
        }
</SCRIPT>
</head>
<body>
	<img id="logo" src="../resources/images/hh_logo.jpg" />
	<hr>
	<div class="container" id="LisaaAanestaja">

		<div class="row">
		
		
			<div class="nine columns">
			<font color="${viestivari}">${alert}</font>
			<form:form action="lisaavaihtoehdot" method="post"
					modelAttribute="vaihtoehto">
				<form:hidden path="VaihtoehtoID" />
				<c:forEach items="${aanestykset}" var="aanestys">
				<form:radiobutton path="AanestysID" value="${aanestys.aanestysID}" onclick="submitForm()" />${aanestys.aanestysNimi}
				</c:forEach>
				
      				<!-- 	<TR>

              				<TD>Vaihtoehdon nimi:</TD>

        				</TR>
      					<TR>
						<form:hidden path="VaihtoehtoID" value="0"/>
						<form:hidden path="aanlkm" value="0"/>
                		<TD><INPUT type="text" name="vaihtoehtoNimet" /></TD><TD><INPUT type="text" name="RyhmaTunnus" /></TD>

                		
        				</TR>
					</TABLE>
					<INPUT type="button" value="Add More" onclick="addRow('vaihtoehtoTaulu')" />
					<td><input type="button" id="delPOIbutton" value="deleteRow" onclick="deleteRow('vaihtoehtoTaulu')"/></td>
					<tr>
					
					-->
				<table id="vaihtoehtoTaulu">
					<tr><td>Nimi:</td><td>RyhmäTunnus:</td></tr>
					<tr><td><form:input path="VaihtoehtoNimi" /></td><form:hidden path="aanlkm"/><td><c:forEach items="${ryhmat}" var="ryhma">
				<form:radiobutton path="RyhmaTunnus" value="${ryhma.ryhmaID}" onclick="submitForm()" />${ryhma.ryhmaTunnus}</c:forEach></td></tr>
					
					
				</table>
					<button type="submit">Tallenna</button>
				</form:form>
			</div>
		</div>
		<div class="keskitys">
					<hr>
					<a class="button button-red" href="/projektiaanestys/Main/admin">Takaisin</a>
					
					</div>
	</div>
	
</body>
</html>