<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@page import="dto.FoodItems"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit</title>
</head>
<body>
<%FoodItems item=(FoodItems)request.getAttribute("item"); %>

<form action="" method="post" enctype="multipart/form-data">
	<input type="text" name="id" value="<%=item.getItem_id()%>" hidden=""><br><br>
	Name:<input type="text" name="name" value="<%=item.getItem_name()%>"><br><br>
	Price:<input type="text" name="price" value="<%=item.getItem_price()%>"><br><br>
	Food Type: 
	<%if(item.getFood_type().equals("veg")){ %>
	<input type="radio" name="type" value="veg" checked="checked" >Veg
	<input type="radio" name="type" value="non-veg">Non-Veg<br>
	<%}else{ %>
	<input type="radio" name="type" value="veg" >Veg
	<input type="radio" name="type" value="non-veg" checked="checked" >Non-Veg
	<%} %>
	<br><br>
	Quantity:<input type="text" name="quantity" value="<%=item.getQuantity()%>"><br><br>
	Picture:
	<%String base64 = Base64.encodeBase64String(item.getPicture());%> 
			<img height="50px" width="50px" alt="unknown"
						src="data:image/jpeg;base64,<%=base64%>">
	<input type="file" name="pic"><br><br>
	<button>Update</button>
	<button type="reset">Cancel</button>
</form>


</body>
</html>