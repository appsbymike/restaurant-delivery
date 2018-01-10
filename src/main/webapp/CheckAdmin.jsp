<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="context" value="${pageContext.request.contextPath}/"/>
<c:if test="${sessionScope.isAdmin}">
	<c:redirect url="admin/"/>
</c:if>