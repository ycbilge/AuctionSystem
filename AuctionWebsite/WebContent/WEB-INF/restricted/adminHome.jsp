<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello Admin!!!</title>
</head>
<body>
	<form name="adminHomeSearchUserForm" action="controller" method="GET" >
		Search User : <input type="text" name="userName_adminHome" /> 
		<input type="submit" name="action" value="adminHomeSearchUser" />
	</form>
	<br /> 
	<form name="adminHomeSearchItemForm" action="controller" method="GET" >
		Search Item : <input type="text" name="itemName_adminHome" /> 
		<input type="submit" name="action" value="adminHomeSearchItem" />
	</form>
	<br /> 
</body>
</html>