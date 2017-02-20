<%@ page import='java.util.*,nbp.jspcart.emails.*,nbp.jspcart.users.*'%>

<jsp:useBean id="website" scope="application" class="nbp.jspcart.WebSite">
	<%website.init(application.getRealPath("../../"));%>
</jsp:useBean>

<%
	String nStart = website.getRequestParameter(request,"nStart");
	String nSize  = website.getRequestParameter(request,"nSize");
	
	String result = null;
	EmailTemplate object = null;
			
	String txtName = request.getParameter("txtName");
	object = website.emailtemplates.get(txtName);
	result = website.emailtemplates.delete(object);
	if(result == null) {
		response.sendRedirect(request.getContextPath() + "/adminListEmailTemplates.do?nStart=" + nStart + "&nSize=" + nSize);
		return;
	}
%>

Error : <%=result%>
