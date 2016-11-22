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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Lis‰‰ ‰‰nestys</title>
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
                VaihtoehtoNimi.name = "vaihtoehto[" + counts + "].VaihtoehtoNimi";
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

				<h1>Lis‰‰ ‰‰nestys</h1>
				<form:form action="saveAanestys" method="post"
					modelAttribute="aanestys">
					<table>
						<form:hidden path="AanestysID" />
						<tr>
							<td>Nimi:</td>
							<td><form:input path="AanestysNimi" /></td>
						</tr>
						<tr>
							<td>Kuvaus:</td>
							<td><form:input path="Kuvaus" /></td>
						</tr>
						<tr>
							<td>Tunnus:</td>
							<td><form:input path="Tunnus" /></td>
						</tr>

					</table>			
					

					<td colspan="2" align="center"><input type="submit"
								value="Save"></td>
					</tr>
					<div class="three columns">
		<a class="button" href="/projektiaanestys/Main/admin">Takaisin</a>
	</div>
				</form:form>
			</div>
		</div>
	</div>
	
</body>
</html>