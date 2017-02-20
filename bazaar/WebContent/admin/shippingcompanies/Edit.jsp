<%@ page import='java.util.*,nbp.jspcart.shipping.*,nbp.jspcart.users.*'%>

<jsp:useBean id="website" scope="application" class="nbp.jspcart.WebSite">
	<%website.init(application.getRealPath("../../"));%>
</jsp:useBean>

<%
	String nStart = website.getRequestParameter(request,"nStart");
	String nSize  = website.getRequestParameter(request,"nSize");
	
	String txtMessage = null;
	String action = "admin/shippingcompanies/Edit.jsp";
	ShippingCompany object = null;
			
	if(request.getMethod().equalsIgnoreCase("post")) {
		object = new ShippingCompany(request);
		txtMessage = website.shippingcompanies.edit(object);
		if(txtMessage == null) {
			response.sendRedirect(request.getContextPath() + "/adminListShipping.do?nStart=" + nStart + "&nSize=" + nSize);
			return;
		} else {
			response.sendRedirect(request.getContextPath() + "/adminListShipping.do?nStart=" + nStart + "&nSize=" + nSize + "&txtMessage=" + txtMessage);
			return;
		}
	} else {
		int nID = Integer.parseInt(request.getParameter("nID"));
		object = website.shippingcompanies.get(nID);
	}
%>

<%
	if(txtMessage != null) {
%>
		<P><font color=red size=2><b><%=txtMessage%></b></font></P>
<%
	}
%>

<%@ include file='Form.htm'%>
