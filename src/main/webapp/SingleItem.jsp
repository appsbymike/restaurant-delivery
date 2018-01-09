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
<title>View Single Item</title>
</head>

<body background="${context}Images/bg3.jpg">
<%@ include file="HeaderNormal.jsp" %>
<br>

<%-- Check if review was submitted correctly --%>
<c:if test="${param.submit != null}">
	<script>alert("Review submitted!");</script>
</c:if>

<%-- Check if review was submitted in invalid format --%>
<c:if test="${param.invalidformat != null }">
	<script>alert("Review is not formatted correctly.");</script>
</c:if>

<%-- Check if request-scope parameters item and reviews haven't been filled yet --%>
<c:if test="${item==null || reviews == null}">
	<c:redirect url="SingleItem"/>
</c:if>

	<div class="container " style="margin:auto; top:0;left:0;bottom:0;right:0;width:100%;height:85%;min-width:200px;max-width:1000px;">
	
		<div class ="container theme-showcase">
			<%-- Fill in item info from request-scope parameter item --%>
			<div class="jumbotron text-center">
		      <h1>${item.getItemName()}</h1>
		    </div>
		    
			<div class="jumbotron">
				<div class="row align-items-center" style="position: relative;">
					<div class="col-sm-6" style="overflow: auto">
						<img src="${context}${item.getImageSrc()}" class="img-responsive img-thumbnail">
					</div>
					<div class="col-sm-6">
						<div class="jumbotron" style="position:absolute;top:50%; transform: translate(0%,-50%); background-color:transparent; text-align: center; width: 100%">
							<h5>${item.getItemDesc()}</h5>
							<br>
							<h4>${item.getItemPrice()}</h4>
							<br><br>
							<form method="post" action="${context}Menu">
								<c:if test="${sessionScope.user_id != null}">
			  						<button class="btn btn-primary btn-block" type="submit" name="cart" value="${param.item_id}">Add to Cart</button>
			  					</c:if>
							</form>	
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="container theme-showcase">
			<div class="row align-item-center" style="position: relative">
	  			<div class="col-sm-6">
	  				<c:if test="${sessionScope.user_id != null}">
		  				<%-- Review box goes here --%>
		  				<div class="jumbotron" style="text-align: center; padding: 30px">
		  					<form method="post" action="${context}SingleItem">
		  						<h2 class="display-4">Write a review!</h2>
		  						<textarea rows=5 style="width: 100%;" name="reviewText" required maxlength="500"></textarea>
		  						<br>
		  						<button class="btn btn-success btn-block" style="font-size: 1.5rem" type="submit" name="review" value="${param.item_id}">Submit Review</button>
		  					</form>
		  				</div>
	  				</c:if>
	  			</div>
	  			<div class="col-sm-6">
	  				<%-- Past reviews go here --%>
	  				<div class="jumbotron text-center" style="padding: 10px; margin-bottom: 1rem">
	     				 <h2>See what others think!</h2>
	    			</div>
	  				
	  				<%-- Start looping through reviews --%>
	  				<c:forEach var="review" items="${reviews}">
	  					<%-- Individual review starts here --%>
		  				<div class="jumbotron" style="text-align: left; padding: 15px; margin-bottom: 1rem">
		  					<h4>${review.getUsername()} says:</h4>
		  					<p>${review.getReviewText()}</p>
		  				</div>
		  			</c:forEach>
  				</div>
		  	</div>       
		</div>
	</div>
<jsp:include page="Footer.html"/>    
</body>
</html>