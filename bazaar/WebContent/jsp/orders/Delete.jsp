<%@page import='nbp.jspcart.users.*,nbp.jspcart.*,nbp.jspcart.orders.*,java.util.*'%>

<jsp:useBean id="website" scope="application" class="nbp.jspcart.WebSite"><%website.init(application.getRealPath("../../"));%></jsp:useBean>

<%

	Users	users = website.users;
	Orders	orders = website.orders;
	
	int nOrderID = website.getRequestParameter(request,"nOrderID",0);
	String nStart = website.getRequestParameter(request,"nStart","0");
	String nSize = website.getRequestParameter(request,"nSize","20");

	User user = (User) session.getAttribute("User");
	Order order = orders.get(nOrderID);
	
	if (!user.isAdmin()) {
		if (!order.txtEmailAddress.equals(user.txtUsername)) {
			response.sendError(response.SC_UNAUTHORIZED);
			return;
		}
	}
	
	orders.delete(order);
	response.sendRedirect(request.getContextPath() + "/myorders.do?nStart="+nStart+"&nSize="+nSize);
%>