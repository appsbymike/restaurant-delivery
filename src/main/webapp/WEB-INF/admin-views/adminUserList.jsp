<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="context" value="${pageContext.request.contextPath}/"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User List</title>
</head>
<body background="${context}/Images/bg3.jpg">
<%@ include file="resources/AdminHeader.jsp" %>
<%@ include file="resources/NavigationPanel.jsp" %>

<div class="container " style="margin:auto;top:0;left:0;bottom:0;right:0;width:75%; height: 70%;min-width:200px;max-width:1000px; padding: 10px ">
	<div class="jumbotron text-center" >
		<h1 style="line-height: 100px">Regular User's Info</h1>
		<c:forEach items="${users}" var="user">
			<a href="${context}/admin/adminGetUser?id=${user.getId()}">
			${user.getFirstname()} 
			${user.getLastname()} 
			Phone: ${user.getPhone()} 
			Email: ${user.getEmail()}
			</a><br>
		</c:forEach>
    </div>
</div>
<br>
<jsp:include page="resources/AdminFooter.jsp"/>
</body>
</html>