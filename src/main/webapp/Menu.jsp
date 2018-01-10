<%@ page language="java" 
	import="java.sql.*, del.res.dao.*, del.res.models.*, del.res.bo.*"
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="context" value="${pageContext.request.contextPath}/"/>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> 
<link rel="stylesheet" href="${context}Bootstrap.css">
<title>Menu</title>
</head>

<body background="${context}Images/bg3.jpg">
<%@ include file="HeaderNormal.jsp" %>

<%-- Check if cart is empty --%>
<c:if test="${param.isempty != null}">
	<script>alert("Please add items to your cart to continue!");</script>
</c:if>
<div class="container ">
	<%-- If item was added to cart successfully --%>
	<c:if test="${param.message != null}" var="message">
		<script>alert("Item added to cart!");</script>
	</c:if>
	
	<%-- Only display proccess order button if user is logged in --%>
	<form method="post" action="${context}Menu">
		<c:if test="${sessionScope.user_id != null}">
			<button class="btn btn-primary btn-lg" style="float: right" type="submit" name="process" value="process">Process Order</button>
		</c:if>
	</form>
	<br><br>
	
	  <div class="jumbotron text-center" >
	      <h1>Menu</h1>
	  </div>
	    
	<%-- Set variable for ArrayList<MenuItem> --%>
	<c:set var="menu" value="${applicationScope.menu}"/>
	
	<%-- Check if menu generated anything. If not, send to Menu servlet to generate --%>
	<c:if test="${menu == null}">
		<c:redirect url="Menu"/>
	</c:if>
	  
	  <%-- Loop through ArrayList for each MenuItem --%>
	  <c:forEach items="${menu}" var="item">
	  	<div class ="container theme-showcase">
		  	<div class="jumbotron">
		  		<div class="row align-items-center" style="position: relative;">
		  			<div class="col-sm-6" style="overflow: auto">
		  				<img src="${context}${item.getImageSrc()}" class="img-responsive img-thumbnail">
		  			</div>
		  			<div class="col-sm-6">
		  				<div class="jumbotron" style="position:absolute;top:50%; transform: translate(0%,-50%); background-color:transparent; text-align: center; width: 100%">
		  					${item.getItemName()}
		  					<br>
		  					${item.getItemPrice()}		  					
		  					<br>
		  					${item.getItemDesc()}
		  					<br><br>
		  					<form method="post" action="${context}Menu">
		  						<%-- Only display add to cart button if user is logged in --%>
			  					<c:if test="${sessionScope.user_id != null}">
			  						<button class="btn btn-primary btn-block" type="submit" name="cart" value="${item.getItemID()}">Add to Cart</button>
			  					</c:if>
			  					<%-- Value of button is the item_id for easy forwarding --%>
			  					<button class="btn btn-success btn-block" type="submit" name="reviews" value="${item.getItemID()}">Reviews</button>
		  					</form>
		  				</div>
		  			</div>
		  		</div>
		  	</div>
		  </div>
	  </c:forEach>
	  		       
	</div>
	<jsp:include page="Footer.html"/>  
</body>
</html>