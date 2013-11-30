<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search Item</title>
</head>
<body>
<form name="SearchItemForm" action="controller" method="GET" >
		Item title : <br />
		<input type="text" name="itemTitle" />  
		<input type="submit" name="action" value="searchItemInDB" />
</form>
<h2> Return to <a href="home.jsp">start page</a>?</h2>
</body>
</html>