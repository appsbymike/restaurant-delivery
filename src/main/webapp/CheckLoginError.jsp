<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="context" value="${pageContext.request.contextPath}/"/>
<c:if test="${sessionScope.user_id==null}">
	<c:redirect url="Error.jsp"/>
</c:if>