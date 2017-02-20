<%@ page import='java.util.*,nbp.jspcart.shipping.*,nbp.jspcart.users.*'%>

<jsp:useBean id="website" scope="application" class="nbp.jspcart.WebSite">
	<%website.init(application.getRealPath("../../"));%>
</jsp:useBean>

<%
	String nStart = website.getRequestParameter(request,"nStart");
	String nSize  = website.getRequestParameter(request,"nSize");
	
	String txtMessage = null;
	String action = "admin/shippingcompanies/Add.jsp";
	ShippingCompany object = null;
			
	if(request.getMethod().equalsIgnoreCase("post")) {
		object = new ShippingCompany(request);
		txtMessage = website.shippingcompanies.insert(object);
		if(txtMessage == null) {
			response.sendRedirect(request.getContextPath() + "/adminListShipping.do?nStart=" + nStart + "&nSize=" + nSize);
			return;
		} else {
			response.sendRedirect(request.getContextPath() + "/adminListShipping.do?nStart=" + nStart + "&nSize=" + nSize + "&txtMessage=" + txtMessage);
			return;
		}
	} else {
		object = ShippingCompany.defaultObject;
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
