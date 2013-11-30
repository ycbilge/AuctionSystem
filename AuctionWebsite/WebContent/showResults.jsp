<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search Results</title>
</head>
<body>
<c:forEach var="item" items="${showItems}">
<h2><a href="http://localhost:8080/AuctionWebsite/controller?action=selectedItem&itemId=${item.title}"><c:out value="${item.title}"></c:out></a><br/></h2>
</c:forEach> 
<h2> Return to <a href="home.jsp">start page</a>?</h2>
</body>
</html>