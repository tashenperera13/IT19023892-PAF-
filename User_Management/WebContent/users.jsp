<%@ page import="com.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management</title>

<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/users.js"></script>
<link rel="stylesheet" href="Views/bootstrap.min.css">

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col">
			
				<h1>User Management</h1>
				
				<form id="formUser" name="formUser">
 						User Type:
 						<input id="user_Type" name="user_Type" type="text" class="form-control form-control-sm">
 						<br> 
 						
 						User Name:
 						<input id="user_Name" name="user_Name" type="text" class="form-control form-control-sm">
 						<br> 
 						
 						User Password:
 						<input id="password" name="password" type="text" class="form-control form-control-sm">
 						<br> 
 						
 						User Email:
 						<input id="user_Email" name="user_Email" type="text" class="form-control form-control-sm">
 						<br>
 						
 						User Phone:
 						<input id="user_Phone" name="user_Phone" type="text" class="form-control form-control-sm">
 						<br> 
 						
 						User Address:
 						<input id="user_Address" name="user_Address" type="text" class="form-control form-control-sm">
 						<br>
 						
 						<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
 						<input type="hidden" id="hidUserIDSave" name="hidUserIDSave" value="">
				</form>

				<br>

				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>

				<br>
				<div id="divUsersGrid">
					<%
							User userObjRead = new User();
							out.print(userObjRead.readUser());
					%>
				</div>

			</div>
		</div>
	</div>
</body>
</html>