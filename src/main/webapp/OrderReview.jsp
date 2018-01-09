<%@ page language="java" 
	import="java.sql.*,del.res.dao.*, java.util.*, del.res.models.*, del.res.bo.*"
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="context" value="${pageContext.request.contextPath}/"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Review Order</title>
<link rel="stylesheet" href="${context}Bootstrap.css">
</head>

<body background="${context}Images/bg3.jpg">

<%-- Check if user is logged in and selected a store --%>
<%@ include file="CheckLoginError.jsp" %>
<%@ include  file="CheckStoreError.jsp" %>
<%@ include file="HeaderNormal.jsp" %>


	<div class="container">

	<%-- Only show process and cancel buttons if user is logged in --%>
	<c:if test="${sessionScope.user_id != null}">
		<form method="post" action="${context}ReviewOrder">
			<div class="btn-group" style="float: right">
				<button class="btn btn-success btn-lg" type="submit" name="process" value="process">Process Order</button>
				<button class="btn btn-danger btn-lg" type="submit" name="cancel" value="cancel">Cancel Order</button>
			</div>
		</form>
	</c:if>
	<br><br>

	    
	    <div class="jumbotron text-center" >
	      <h1>Review your order</h1>
	    </div>
    	
    	<%-- Get cart (ArrayList<ReviewItem>) from the sessionScope --%>
	    <c:set var="cart" value="${sessionScope.cart}"/>
	    
	    <%-- Loop through each item in the cart --%>
		<c:forEach items="${cart}" var="item">
			<div class ="container theme-showcase">
			  	<div class="jumbotron">
			  		<div class="row align-items-center" style="position: relative;">
			  			<div class="col-sm-6" style="overflow: auto">
			  				<img src="${context}${item.getImageSrc()}" class="img-responsive img-thumbnail">
			  			</div>
			  			<div class="col-sm-6">
			  				<div class="jumbotron" style="position:absolute;top:50%; transform: translate(0%,-50%); background-color:transparent; text-align: center; width: 100%">
			  					<h2>${item.getItemName()}</h2>
			  					<br>
			  					<h3>${item.getItemPrice()}</h3>
			  					<br><br>
			  					<form method="post" action="${context}ReviewOrder">
				  					<button class="btn btn-danger btn-block" type="submit" name="remove" value="${item.getItemID()}">Remove</button>
			  					</form>
			  				</div>
			  			</div>
			  		</div>
			  	</div>
			</div>
		</c:forEach>
		
		<%-- Get the posttax_total from the sessionScope --%>
		<div class="jumbotron text-center">
			<h1>Total cost after tax: $${sessionScope.posttax_total}</h1>
		</div>
		
</div>
<jsp:include page="Footer.html"/>
</body>
</html>