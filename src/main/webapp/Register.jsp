<%@ page language="java"
	import="java.sql.*, del.res.dao.*"
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="context" value="${pageContext.request.contextPath}/"/>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${context}Bootstrap.css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Details</title>
</head>
<body background="${context}Images/bg3.jpg">
<%@ include file="HeaderNormal.jsp" %>

<%-- Check if registration servlet passed any parameters --%>
	
	<%-- If username already exists --%>
	<c:if test="${param.userexists != null }">
		<script>alert("Username already exists");</script>
	</c:if>
	
	<%-- If password doesn't match reentered password --%>
	<c:if test="${param.nomatch != null }">
		<script>alert("Password does not match");</script>
	</c:if>
	
	<%-- If anything in the form is not formatted properly --%>
	<c:if test="${param.invalidformat != null }">
		<script>alert("One of the fields is not formatted correctly.");</script>
	</c:if>
	<div class="container " style="margin:auto; position: absolute; top:0;left:0;bottom:0;right:0;width:75%;height:85%;min-width:200px;max-width:1000px;">
    <div class="jumbotron text-center" >
      <h1 style="line-height: 100px">Restaurant Delivery Registration</h1>
      <form method="post" action="${context}Register">
        <input class="form-control input-lg" type="text" id="username" pattern="[a-zA-Z0-9]{1,20}" style="font-size:2rem; text-align: center" name="username" placeholder="Username" maxlength="20" required title="Please enter a combination of up to 20 Alphanumber Characters" >
        <input class="form-control input-lg" type="password" id="password" pattern="[a-zA-Z0-9]{1,20}" style="font-size:2rem; text-align: center" name="password" placeholder="Password" maxlength="20" required title="Please enter a combination of up to 20 Alphanumber Characters">
        <input class="form-control input-lg" type="password" id="repassword" pattern="[a-zA-Z0-9]{1,20}" style="font-size:2rem; text-align: center" name="repassword" placeholder="Repeat Password" maxlength="20" required title="Please enter a combination of up to 20 Alphanumber Characters">
        <input class="form-control input-lg" type="text" id="firstname" pattern="[a-zA-Z]{1,20}" style="font-size:2rem; text-align: center" name="firstname" placeholder="First Name" maxlength="20" required title="Please enter a combination of up to 20 Alphabetic Characters">
        <input class="form-control input-lg" type="text" id="lastname" pattern="[a-zA-Z]{1,20}" style="font-size:2rem; text-align: center" name="lastname" placeholder="Last Name" maxlength="20" required title="Please enter a combination of up to 20 Alphabetic Characters">
        <select class="form-control input-lg" id="gender" style="font-size:2rem; height: 60px; text-align: center; text-align-last: center" name="gender">
        	<option value="m">Male</option>
        	<option value="f">Female</option>
        </select>
        <input class="form-control input-lg" type="text" id="address" pattern="[a-zA-Z0-9 .]{1,100}" style="font-size:2rem; text-align: center" name="address" placeholder="Address" maxlength="100" required title="Please enter a valid address">
        <input class="form-control input-lg" type="text" id="phone" pattern="[0-9]{10}" style="font-size:2rem; text-align: center" name="phone" placeholder="Phone Number" maxlength="10" required title="Please enter your 10-digit phone number (no dashes)">
        <input class="form-control input-lg" type="email" id="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" style="font-size:2rem; text-align: center" name="email" placeholder="Email Address" maxlength="40" required title="Please enter a valid email">
        <br>
        <button class="btn btn-success btn-block" id="register" style="font-size: 2rem" type="submit" name="register">Register</button>
        <button class="btn btn-danger btn-block" id="cancel" style="font-size: 2rem" type="submit" name="cancel" formnovalidate>Cancel</button>
      </form>
      </div>
      </div>
<jsp:include page="Footer.html"/>
</body>
</html>