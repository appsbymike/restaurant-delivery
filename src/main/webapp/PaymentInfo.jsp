<%@ page language="java" 
	import="java.sql.*, del.res.dao.*, del.res.models.*, java.util.Enumeration, del.res.utilities.*"
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="context" value="${pageContext.request.contextPath}/"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Payment Information</title>
</head>
<body background="${context}Images/bg3.jpg">

<%-- Check if user is logged in and selected a store --%>
<jsp:directive.include file="CheckLoginError.jsp"/>

<%-- Check if user previously submitted with an invalid format --%>
<c:if test="${param.invalidformat != null }">
	<script>alert("One of the fields is not formatted correctly.");</script>
</c:if>

<%@ include file="HeaderFixed.jsp" %>
	<div class="container " style="margin:auto; position: absolute; top:0;left:0;bottom:0;right:0;width:75%; height: 50%;min-width:200px;max-width:1000px; padding: 10px ">
	    <div class="jumbotron text-center" >
	      <h1 style="line-height: 100px">Payment Information</h1>
	
	      	<form method="post" action="${context}PaymentInfo">
		        <input class="form-control input-lg" type="text" pattern="[0-9]{1,20}" style="font-size:2rem; text-align: center" name="ccnumber" placeholder="Credit Card Number" required title="Please enter a valid Credit Card Number">
		        <input class="form-control input-lg" type="password" pattern="[0-9]{3,}" style="font-size:2rem; text-align: center" name="seccode" placeholder="Security Code" required title="Please enter a valid Security Code">
		        <input class="form-control input-lg" type="text" pattern="[0-9]{5,}" style="font-size:2rem; text-align: center" name="zipcode" placeholder="Zipcode" required title="Please enter a valid Zipcode">
				<br>
				<button class="btn btn-success btn-block" style="font-size: 2rem" type="submit" name="process">Process Order</button>
			</form>
		</div>
	</div>
<jsp:include page="Footer.html"/>
</body>
</html>