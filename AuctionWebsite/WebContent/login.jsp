<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="userBean" class="edu.unsw.comp9321.bean.UserBean" scope="session"></jsp:useBean>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome!!!</title>
</head>
<body>
	<form name="loginForm" action="controller" method="POST" >
		User name : <input type="text" name="username" /><br /> 
		Password : <input type="password" name="password" /><br /> 
		<input type="submit" name="action" value="login" />
	</form>
	<form name="registerForm" action="controller" method="POST" >
		Would you like to register?<br />  
		<input type="submit" name="action" value="register" />
	</form>
</body>
</html>