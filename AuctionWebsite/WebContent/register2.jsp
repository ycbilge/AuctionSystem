<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register - #2</title>
</head>
<body>
	<form name="register2Form" action="controller" method="POST" >
		User name  : <input type="text" name="R2username" value="${uName}"/><br />
		<input type="hidden" name="R2password" value="${pass}"/><br />
		Email  : <input type="text" name="R2email" value="${eMail}"/><br />
		Nick name  : <input type="text" name="R2nickname" /><br />
		First name  : <input type="text" name="R2firstname" /><br />
		Last name  : <input type="text" name="R2lastname" /><br />
		Year of Birth : <input type="text" name="R2yob" /><br />
		Full address : <input type="text" name="R2address" /><br />
		Credit card number : <input type="text" name="R2creditCardNumber" /><br /> 
		<input type="submit" name="action" value="register2" />
	</form>
</body>
</html>