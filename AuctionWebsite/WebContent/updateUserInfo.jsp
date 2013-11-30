<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update User Info</title>
</head>
<body>
<form name="updateUserInfoForm" action="controller" method="GET" >
		User name  : <input type="text" name="uuiuname" value="${unameDB}" readonly/><br />
		Password : <input type="text" name="uuipassword" value="${passDB}"/><br />
		Email  : <input type="text" name="uuiemail" value="${eMailDB}"/><br />
		Nick name  : <input type="text" name="uuinickname" value="${nicnameDB}"/><br />
		First name  : <input type="text" name="uuifirstname" value="${firstnameDB}" /><br />
		Last name  : <input type="text" name="uuilastname" value="${lastnameDB}"/><br />
		Year of Birth : <input type="text" name="uuiyob" value="${yobDB}"/><br />
		Full address : <input type="text" name="uuiaddress" value="${fuladdDB}"/><br />
		Credit card number : <input type="text" name="uuicreditCardNumber" value="${ccnDB}"/><br /> 
		<input type="submit" name="action" value="updateUserInfo" />
	</form>
<h2> Return to <a href="home.jsp">start page</a>?</h2>
</body>
</html>