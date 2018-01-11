<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="${context}/Bootstrap.css">
<style>
ul {
	width:100%;
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    background-color: #333;
}

li {
    float: left;
}

li a {
    display: block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}

li a:hover:not(.active) {
    background-color: #111;
}

.active {
    background-color: #4CAF50;
}

.hide{
	display: none;
}
</style>
<body>

<c:if test="${sessionScope.isAdmin == null}">
	<c:redirect url="/Error.jsp"/>
</c:if>
	
	<ul>
		<li><h1><font color="white">Restaurant Delivery Admin Panel</font></h1></li>
		<li style="float:right" id="hdLogout"><a href="${context}/Logout">Log Out</a></li>
		<c:if test="${fn:endsWith(pageContext.request.requestURI, 'adminAccountDetails.jsp')}">
			<li style="float:right" id="hdNavigate"><a href="${context}/admin/adminNavigate">Navigate Application</a></li>
		</c:if>
		<li style="float:right" id="hdHome"><a href="${context}/admin/">Home</a></li>
	</ul>
<br>
</body>