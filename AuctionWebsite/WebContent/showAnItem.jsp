<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Item!!</title>
</head>
<body>
<b>Item Title :</b> ${itemToBeShown.title}<br/>
<b>Item Category :</b>${itemToBeShown.category}<br/>
<b>Item Picture :</b>${itemToBeShown.picture}<br/>
<b>Item Description :</b>${itemToBeShown.description}<br/>
<b>Item Postage Detail :</b>${itemToBeShown.postageDetail}<br/>
<b>Item Starting Price :</b>${itemToBeShown.biddingStartPrice}<br/>
<b>Item Bid Increment :</b>${itemToBeShown.biddingIncrement}<br/>
<b>Remaining time to bid :</b>${remainingTimeToBid}<br/>
<c:choose>
<c:when test="${remainingTimeToBid == 0}">
    <c:out value="Time is expired for this item!!!"></c:out>
</c:when>
<c:when test = "${sameUserName == false and reservePriceBeated == false}">
<form name="AddBidToAnItemForm" action="controller" method="GET" >
		New Bid : <input type="text" name="bid-Bid"/><br />
		<input type="hidden" name="itemTitle-Bid" value="${itemToBeShown.title}"/><br />
		<input type="submit" name="action" value="addBidToItem" />
	</form>
	<c:out value="Max value up to now : ${maxBidValue}"></c:out><br />
	<c:out value="Max value belongs to : ${maxBidHolder}"></c:out>
</c:when>
</c:choose>
<h2> Return to <a href="home.jsp">start page</a>?</h2>
</body>
</html>


