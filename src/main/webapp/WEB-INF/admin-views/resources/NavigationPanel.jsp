<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<div class="container " style="margin:auto;top:0;left:0;bottom:0;right:0;width:75%; height: 70%;min-width:200px;max-width:1000px; padding: 10px;">
    <div class="jumbotron" style="margin:0">
    	<ul style="text-align: center; font-size: 1.5rem">
    		<li style="display: inline-block; float: none;"><a href="${context}/admin/adminUserList" style="display: inline-block;">User List</a></li>
    		<li style="display: inline-block; float: none;"><a href="${context}/admin/adminItemList" style="display: inline-block;">Item List</a></li>
    		<li style="display: inline-block; float: none;"><a href="${context}/admin/adminOrderList" style="display: inline-block;">Order List</a></li>
    		<li style="display: inline-block; float: none;"><a href="${context}/admin/adminLocationList" style="display: inline-block;">Location List</a></li>
    	</ul>
    </div>
</div>