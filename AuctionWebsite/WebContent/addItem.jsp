<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Item</title>
</head>
<body>
<form name="addItemForm" action="controller" method="GET" >
		Title : <input type="text" name="title-addItemForm" /><br /> 
		Category : <input type="text" name="category-addItemForm" /><br />
		Picture : <input type="text" name="picture-addItemForm" /><br />
		Description : <input type="text" name="description-addItemForm" /><br /> 
		Postage Details : <input type="text" name="postage-addItemForm" /><br /> 
		Reserve Price : <input type="text" name="reservePrice-addItemForm" /><br /> 
		Bidding Start Price : <input type="text" name="biddingStart-addItemForm" /><br /> 
		Bidding Increments : <input type="text" name="biddingIncrement-addItemForm" /><br />
		Time for Bidding to End(minute): <input type="text" name="biddingTime-addItemForm" value="10"/><br />
		<input type="hidden" name="uNNname" value="${userNN}"/> 
		<input type="submit" name="action" value="addItemToDB" />
</form>
<h2> Return to <a href="home.jsp">start page</a>?</h2>
</body>
</html>