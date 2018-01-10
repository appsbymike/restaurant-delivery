<%
	if(session.getAttribute("store_id") == null){
		response.sendRedirect(request.getContextPath() + "/Error.jsp");
	}
%>