<%@ page language="java"
	import="java.sql.*, del.res.dao.*, javax.servlet.jsp.*"
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="context" value="${pageContext.request.contextPath}/"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="${context}Bootstrap.css">

<title>Restaurant Delivery</title>
</head>

<body background="${context}Images/bg3.jpg">

	<div class="container " style="margin:auto; position: absolute; top:0;left:0;bottom:0;right:0;width:75%;height:50%;min-width:200px;max-width:1000px;">
    
    <div class="jumbotron text-center" >
      <h1 style="line-height: 100px">Restaurant Delivery Login</h1>
      <%-- Check if servlet passed invalid parameter --%>
		<c:if test="${param.invalid != null}">
			<h4 style="color: red" id="invalid">Invalid Username or Password</h4>
		</c:if>
      <form method="post" action="${context}Login">
        <input class="form-control input-lg" type="text" pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$" style="font-size:2rem; text-align: center" name="username" placeholder="Username" id="username" required title="Please enter a combination of up to 20 Alphanumeric Characters">

        <input class="form-control input-lg" type="password" pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$" style="font-size:2rem; text-align: center" name="password" placeholder="Password" id="password" required title="Please enter a combination of up to 20 Alphanumeric Characters">
        <br>
        <button class="btn btn-primary btn-block" style="font-size: 2rem" type="submit" name="login" id="login">Log In</button>
        <button class="btn btn-success btn-block" style="font-size: 2rem" type="submit" name="register" id="register" formnovalidate>Register</button>
      </form>
      </div>
      </div>
<%@ include file="HeaderFixed.jsp" %>
<jsp:include page="Footer.html"/>
</body>
</html>