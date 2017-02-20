<%@ page import='java.util.*,nbp.jspcart.shipping.*,nbp.jspcart.users.*'%>

<jsp:useBean id="website" scope="application" class="nbp.jspcart.WebSite">
	<%website.init(application.getRealPath("../../"));%>
</jsp:useBean>

<%
	String nStart = website.getRequestParameter(request,"nStart");
	String nSize  = website.getRequestParameter(request,"nSize");
	
	String result = null;
	ShippingCompany object = null;
			
	int nID = Integer.parseInt(request.getParameter("nID"));
	object = website.shippingcompanies.get(nID);
	result = website.shippingcompanies.delete(object);
	if(result == null) {
		response.sendRedirect(request.getContextPath() + "/adminListShipping.do?nStart="+nStart+"&nSize="+nSize);
		return;
	}
%>

Error : <%=result%>
