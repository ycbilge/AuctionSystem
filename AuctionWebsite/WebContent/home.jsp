<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body>
<h3>Hello ${usern}</h3>
<form name="homeForm" action="controller" method="GET" >
		Would you like to update your info?<br />  
		<input type="submit" name="action" value="updateInfo" />
		<br />Would you like to search?<br />  
		<input type="submit" name="action" value="searchItem" />
		<br />Would you like to add item?<br />  
		<input type="submit" name="action" value="addItem" />
		<br />Would you like to log out?<br />  
		<input type="submit" name="action" value="logout" />
		<input type="hidden" name="uNname" value="${usern}"/>
</form>
</body>
</html>