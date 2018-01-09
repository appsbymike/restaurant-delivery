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
<%@ include file="HeaderFixed.jsp" %>
	
	<%-- Ensure user is logged in --%>
	<c:if test="${sessionScope.user_id == null}">
		<c:redirect url="Error.jsp"/>
	</c:if>
	
	<%-- Ensure request-scope parameter user_info has been filled --%>
	<c:if test="${user_info == null}">
		<c:redirect url="UpdateAccount"/>
	</c:if>
	
	<%-- Check if user entered invalid info --%>
	<c:if test="${param.nomatch != null }">
		<script>alert("Password does not match");</script>
	</c:if>
	
	<c:if test="${param.invalidformat != null }">
		<script>alert("One of the fields is not formatted correctly.");</script>
	</c:if>
	
	<%-- Check if the user changed their info successfully --%>
	<c:if test="${param.success != null}">
		<script>alert("Information Successfully Updated!");</script>
	</c:if>
	
	
	<%-- Fill in all fields with contents of user_info --%>
	<c:set var="info" value="${user_info}"/>
	<div class="container " style="margin:auto; position: absolute; top:0;left:0;bottom:0;right:0;width:75%; height: 70%;min-width:200px;max-width:1000px; padding: 10px ">
    <div class="jumbotron text-center" >
      <h1 style="line-height: 100px">User Account Details</h1>

      <form method="post" action="${context}UpdateAccount">
        <input class="form-control input-lg" type="text" pattern="[a-zA-Z]{1,20}" style="font-size:2rem; text-align: center" name="firstname" placeholder="First Name" value="${info.getFirstname()}" maxlength="20" required title="Please enter a combination of up to 20 Alphabetic Characters">
        <input class="form-control input-lg" type="text" pattern="[a-zA-Z]{1,20}" style="font-size:2rem; text-align: center" name="lastname" placeholder="Last Name" value="${info.getLastname()}" maxlength="20" required title="Please enter a combination of up to 20 Alphabetic Characters">
        <input class="form-control input-lg" type="password" pattern="[a-zA-Z0-9]{1,20}" style="font-size:2rem; text-align: center" name="password" placeholder="Password" value="${info.getPassword()}" maxlength="20" required title="Please enter a combination of up to 20 Alphanumber Characters">
        <input class="form-control input-lg" type="password" pattern="[a-zA-Z0-9]{1,20}" style="font-size:2rem; text-align: center" name="repassword" placeholder="Re-enter Password" value="${info.getPassword()}" maxlength="20" required title="Please enter a combination of up to 20 Alphanumber Characters">
        <input class="form-control input-lg" type="text" pattern="[a-zA-Z0-9 .]{1,100}" style="font-size:2rem; text-align: center" name="address" placeholder="Address" value="${info.getAddress()}" maxlength="100" required title="Please enter a valid address">
        <input class="form-control input-lg" type="text" pattern="[0-9]{10}" style="font-size:2rem; text-align: center" name="phone" placeholder="Phone Number" value="${info.getPhone()}" maxlength="10" required title="Please enter your 10-digit phone number (no dashes)">
        <input class="form-control input-lg" type="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" style="font-size:2rem; text-align: center" name="email" placeholder="Email Address" value="${info.getEmail()}" maxlength="40" required title="Please enter a valid email">
        <br>
        <button class="btn btn-success btn-block" style="font-size: 2rem" type="submit" name="update">Update</button>
      </form>
      </div>
      </div>
<jsp:include page="Footer.html"/>
</body>
</html>