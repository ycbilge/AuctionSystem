<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Item list - admin</title>
</head>
<body>
<c:choose>
<c:when test="${noItem == false}">
	<c:forEach var="item" items="${itemList}">
		<form name="itemSearchResultAdminForm" action="controller" method="GET" >	
			Item name : <input type="text" name="admin_item_title" value="${item.title}" readonly/><br />
			Item details : <input type="text" name="admin_item_desc" value="${item.description}" readonly/><br />
			Item holder : <input type="text" name="admin_item_username" value="${item.userName}" readonly/><br />
			<input type="submit" name="action" value="banItem" /><br/>
		</form>
		<hr>
	</c:forEach>
</c:when>
<c:when test="${noItem == true}">
    <c:out value="Sorry no search results!!!!"></c:out>
</c:when>
</c:choose>
<br/>
<form name="goBackHomeForm" action="controller" method="GET" >
		go back to home?
		<input type="submit" name="action" value="backToHome" />
	</form>
</body>
</html>