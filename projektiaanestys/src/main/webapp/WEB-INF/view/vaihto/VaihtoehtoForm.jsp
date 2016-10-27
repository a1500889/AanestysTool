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
<title>Lis�� vaihtoehto</title>
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

	<div class="container" id="LisaaAanestaja">

		<div class="row">
		
		
			<div class="nine columns">
			<form:form action="lisaavaihtoehdot" method="post"
					modelAttribute="arrayList<Vaihtoehto>">
				<TABLE id="vaihtoehtoTaulu">
      					<TR>

              				<TD>Vaihtoehdon nimi:</TD>

        				</TR>
      					<TR>

                		<TD><INPUT type="text" name="vaihtoehto[0].VaihtoehtoNimi" /></TD>
        				</TR>
					</TABLE>
					<INPUT type="button" value="Add More" onclick="addRow('vaihtoehtoTaulu')" />
					<td><input type="button" id="delPOIbutton" value="deleteRow" onclick="deleteRow('vaihtoehtoTaulu')"/></td>
					<tr>
					
					</table>
				</form:form>
			</div>
		</div>
	</div>
	<div class="three columns">
		<a class="button" href="/projektiaanestys/">Takaisin</a>
	</div>
</body>
</html>