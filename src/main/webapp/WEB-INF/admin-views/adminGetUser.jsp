<%@ page language="java"
	import="java.sql.*, del.res.dao.*"
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${context}/Bootstrap.css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Details</title>
</head>
<body background="${context}/Images/bg3.jpg">
<%@ include file="resources/AdminHeader.jsp" %>
<%@ include file="resources/NavigationPanel.jsp" %>
	
	<%-- Check if user entered invalid info --%>
	<c:if test="${nomatch != null }">
		<script>alert("Password does not match");</script>
	</c:if>
	
	<c:if test="${invalidformat != null }">
		<script>alert("One of the fields is not formatted correctly.");</script>
	</c:if>
	
	<c:if test="${deleted != null}">
		<script>alert("Successfully deleted review!");</script>
	</c:if>
	
	<%-- Check if the user changed their info successfully --%>
	<c:if test="${success != null}">
		<script>alert("Information Successfully Updated!");</script>
	</c:if>
	
	
	<%-- Fill in all fields with contents of user_info --%>
	<div class="container " style="margin:auto;top:0;left:0;bottom:0;right:0;width:75%; height: 70%;max-width:1000px; padding: 10px ">
	    <div class="row">
	    	<div class="col-sm-8">
		    	<div class="jumbotron text-center" style="margin:10px 0">
		    		<h1 style="line-height: 100px">Normal User Account Details</h1>
					<form:form method="post" action="${context}/admin/adminGetUser">
					    <form:input path="firstname" class="form-control input-lg" type="text" pattern="[a-zA-Z]{1,20}" style="font-size:2rem; text-align: center" placeholder="First Name" maxlength="20" required="required" title="Please enter a combination of up to 20 Alphabetic Characters" id="firstname"/>
					    <form:input path="lastname" class="form-control input-lg" type="text" pattern="[a-zA-Z]{1,20}" style="font-size:2rem; text-align: center" placeholder="Last Name" maxlength="20" required="required" title="Please enter a combination of up to 20 Alphabetic Characters" id="lastname"/>
					    <form:input path="password" class="form-control input-lg" type="password" pattern="[a-zA-Z0-9]{1,20}" style="font-size:2rem; text-align: center" placeholder="Password" maxlength="20" required="required" title="Please enter a combination of up to 20 Alphanumber Characters" id="password"/>
					    <form:input path="repassword" class="form-control input-lg" type="password" pattern="[a-zA-Z0-9]{1,20}" style="font-size:2rem; text-align: center" placeholder="Re-enter Password" maxlength="20" required="required" title="Please enter a combination of up to 20 Alphanumber Characters" id="repassword"/>
					    <form:input path="address" class="form-control input-lg" type="text" pattern="[a-zA-Z0-9 .]{1,100}" style="font-size:2rem; text-align: center" placeholder="Address" maxlength="100" required="required" title="Please enter a valid address" id="address"/>
					    <form:input path="phone" class="form-control input-lg" type="text" pattern="[0-9]{10}" style="font-size:2rem; text-align: center" placeholder="Phone Number" maxlength="10" required="required" title="Please enter your 10-digit phone number (no dashes)" id="phone"/>
					    <form:input path="email" class="form-control input-lg" type="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" style="font-size:2rem; text-align: center" placeholder="Email Address" maxlength="40" required="required" title="Please enter a valid email" id="email"/>
					    <form:input path="id" hidden="true"/>
					    <br>
					    <button class="btn btn-success btn-block" style="font-size: 2rem" type="submit" name="update">Update</button>
				 	</form:form>
				</div>
		    </div>
		    <div class="col-sm-4">
		    	<c:forEach items="${reviews}" var="review">
			    	<div class="jumbotron" style="margin:10px 0">
			    		<form:form method="post" action="${context}/admin/adminGetUser?review_id=${review.getId()}" id="reviews">
			    			<h4>${review.getUsername()} says:</h4>
			    			<hr>
			  				<p>${review.getReviewText()}</p>
			  				<form:input path="id" hidden="true"/>
			  				<button class="btn btn-danger btn-block" style="font-size: 2rem" type="submit" name="delete">Delete Review</button>
			    		</form:form>
			    	</div>
		    	</c:forEach>
		    </div>			     
	      </div>
      </div>
<br>
<br>
<jsp:include page="resources/AdminFooter.jsp"/>
</body>
</html>