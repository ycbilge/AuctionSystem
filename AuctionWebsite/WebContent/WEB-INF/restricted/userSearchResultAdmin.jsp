<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SearchResults-Admin Page</title>
</head>
<body>
<c:choose>
<c:when test="${noUser == false}">
	<form name="userSearchResultAdminForm" action="controller" method="GET" >
	    User name : <input type="text" name="admin_user_name" value="${uname_admin}" readonly/><br />
	    User first-name : <input type="text" name="admin_user_fname" value="${ufname_admin}" readonly/><br/>	    
	    User last-name  : <input type="text" name="admin_user_lname" value="${ulname_admin}" readonly/><br/>
	    User nick-name  : <input type="text" name="admin_user_nname" value="${unname_admin}" readonly/><br/>
	    <input type="submit" name="action" value="banUser" />
    </form>
</c:when>
<c:when test="${noUser == true}">
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