<%@ page import='java.util.*,nbp.jspcart.countries.*,nbp.jspcart.users.*'%>

<jsp:useBean id="website" scope="application" class="nbp.jspcart.WebSite">
	<%website.init(application.getRealPath("../../"));%>
</jsp:useBean>

<%
	String nStart = website.getRequestParameter(request,"nStart");
	String nSize  = website.getRequestParameter(request,"nSize");
	
	String txtMessage = null;
	String action = "admin/countries/Add.jsp";
	Country object = null;
			
	if(request.getMethod().equalsIgnoreCase("post")) {
		object = new Country(request);
		txtMessage = website.countries.insert(object);
		if(txtMessage == null) {
			response.sendRedirect(request.getContextPath() + "/adminListCountries.do?nStart=" + nStart + "&nSize=" + nSize);
			return;
		} else {
			response.sendRedirect(request.getContextPath() + "/adminListCountries.do?nStart=" + nStart + "&nSize=" + nSize + "&txtMessage=" + txtMessage);
			return;
		}
	} else {
		object = Country.defaultObject;
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
