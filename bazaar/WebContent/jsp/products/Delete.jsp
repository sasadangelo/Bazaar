<%@ page import='java.util.*,nbp.jspcart.categories.*,nbp.jspcart.products.*,nbp.jspcart.users.*'%>

<jsp:useBean id="website" scope="application" class="nbp.jspcart.WebSite">
	<%
	    website.init(application.getRealPath("../../"));
	%>
</jsp:useBean>

<%
	String result = null;
	Product object = null;

	int nStart = website.getRequestParameter(request,"nStart",0);
	int nID = Integer.parseInt(request.getParameter("nID"));
	object = website.products.get(nID);
	result = website.products.delete(object);
	if(result == null) {
		response.sendRedirect(request.getContextPath() + "/adminListProducts.do?nStart=" + nStart);
		return;
	}
%>

Error : <%=result%>
