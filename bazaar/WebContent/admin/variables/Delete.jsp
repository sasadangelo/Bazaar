<%@ page import='java.util.*,nbp.jspcart.variables.*,nbp.jspcart.users.*'%>

<jsp:useBean id="website" scope="application" class="nbp.jspcart.WebSite">
	<%website.init(application.getRealPath("../../"));%>
</jsp:useBean>

<%
	String nStart = website.getRequestParameter(request,"nStart");
	String nSize  = website.getRequestParameter(request,"nSize");
	
	String result = null;
	Variable object = null;
			
	String txtName = request.getParameter("txtName");
	object = website.variables.get(txtName);
	result = website.variables.delete(object);
	if(result == null) {
		response.sendRedirect(request.getContextPath() + "/adminListVariables.do?nStart="+nStart+"&nSize="+nSize);
		return;
	}
%>

Error : <%=result%>
