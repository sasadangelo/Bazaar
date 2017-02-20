<%@ page import='java.util.*,nbp.jspcart.countries.*,nbp.jspcart.users.*'%>

<jsp:useBean id="website" scope="application" class="nbp.jspcart.WebSite">
	<%website.init(application.getRealPath("../../"));%>
</jsp:useBean>

<%
	String nStart = website.getRequestParameter(request,"nStart");
	String nSize  = website.getRequestParameter(request,"nSize");
	
	String result = null;
	Country object = null;
			
	object = website.countries.get(request.getParameter("txtID"));
	result = website.countries.delete(object);
	if(result == null) {
		response.sendRedirect(request.getContextPath() + "/adminListCountries.do?nStart=" + nStart + "&nSize=" + nSize);
		return;
	}
%>

Error : <%=result%>
