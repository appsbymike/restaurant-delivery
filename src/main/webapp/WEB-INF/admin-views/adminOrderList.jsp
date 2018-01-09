<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="context" value="${pageContext.request.contextPath}/"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body background="${context}/Images/bg3.jpg">
<%@ include file="resources/AdminHeader.jsp" %>
<%@ include file="resources/NavigationPanel.jsp" %>

<%-- Check if delete has been attempted --%>
<c:if test="${delete != null}">
	<c:choose>
		<%-- Display whether update has or hasn't succeeded --%>
		<c:when test="${delete == true}">
			<script>alert("Delete Successful!");</script>
		</c:when>
		<c:when test="${delete==false}">
			<script>alert("Delete Unsuccessful!");</script>
		</c:when>
		<%-- If value is somehow something other than true or false, send to error page --%>
		<c:otherwise>
			<c:redirect url="WEB-INF/views/adminError.jsp"/>
		</c:otherwise>
	</c:choose>
</c:if>

<div class="container " style="margin:auto;top:0;left:0;bottom:0;right:0;width:75%; height: 70%;min-width:200px;max-width:1000px; padding: 10px ">
	<div class="jumbotron text-center" style="margin:10px 0">
		<h1 style="line-height: 90px">List of Orders</h1>
		<%-- Start looping through orders --%>
		<c:forEach items="${orders}" var="order">
		<hr style="background-color: gray; color: gray">
		<form method="post" action="${context}/admin/RemoveOrder">
			<font style="font-size: 1.5rem">
				Number of items: ${order.getItemCount()}<br>
				Order Number: ${order.getOrderID()}<br>
				Total: ${order.getOrderTotal()}
				<button name="id" value="${order.getOrderID()}" class="btn btn-danger btn-lg" style="font-size: 1rem; padding:5px,0px" type="submit">Delete</button>
			</font><br>
			
				
			</form>
			
		</c:forEach>
		
	</div>
</div>

<br><br><br>
<jsp:include page="resources/AdminFooter.jsp"/>
</body>
</html>