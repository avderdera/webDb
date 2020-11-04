
<%@page import="webDb.ArrayAtr"%> 
<%@page import="java.util.ArrayList"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<!DOCTYPE html> 
<html> 
  <head> 
   	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
   	<title>Product List</title> 
  	</head>
  	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<style>
	html,body,h1,h2,h3,h4,h5,h6 {font-family: "Roboto", sans-serif 
	}
	
	p, h1{
	color: rgb(255, 255, 255);
  	font-size: 1.3em;
	}
	h2 {
		color: rgb(255, 0, 0);
	}
	body {
  		background-image: url('https://images.idgesg.net/images/article/2017/09/networking-100735059-large.jpg');
  		background-size: cover;
	}
	table {
  		font-family: arial, sans-serif;
  		border-collapse: collapse;
  		width: 100%;
  		color: rgb(255, 255, 255);
	}

	td, th {
  		border: 1px solid #dddddd;
  		text-align: left;
  		padding: 8px;
	}

	
	</style> 
 
  	<body> 
      <h1>Displaying Product List</h1>
      <%--Getting an error description if that barcode already exits--%>
      <h2><%=request.getAttribute("Error") %></h2>
      <%--Setting up the table --%> 
      <table border ="1" width="500" align="center"> 
         <tr bgcolor="#154c63"> 
          <th><b>Barcode</b></th> 
          <th><b>Name</b></th> 
          <th><b>Color</b></th> 
          <th><b>Description</b></th> 
         </tr> 
        <%-- Fetching the attributes of the request object 
             which was previously set by the servlet  
              "InserAtr.java" 
        --%>  
        <%ArrayList<ArrayAtr> prod =  
            (ArrayList<ArrayAtr>)request.getAttribute("data"); 
        for(ArrayAtr s:prod){%> 
        <%-- Arranging data in tabular form 
        --%> 
            <tr> 
                <td><%=s.getBarcode()%></td> 
                <td><%=s.getName()%></td> 
                <td><%=s.getColor()%></td> 
                <td><%=s.getDescription()%></td>
            </tr> 
            <%}%> 
        </table>  
        <hr/> 
    </body> 
</html> 
