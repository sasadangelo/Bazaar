<%@ page import='java.util.*,nbp.jspcart.emails.*,nbp.jspcart.users.*'%>

<jsp:useBean id="website" scope="application" class="nbp.jspcart.WebSite">
	<%website.init(application.getRealPath("../../"));%>
</jsp:useBean>

<%
	String nStart = website.getRequestParameter(request,"nStart");
	String nSize  = website.getRequestParameter(request,"nSize");
	
	String txtMessage = null;
	String action = "admin/emailtemplates/Edit.jsp";
	EmailTemplate object = null;
			
	if(request.getMethod().equalsIgnoreCase("post")) {
		object = new EmailTemplate(request);
		txtMessage = website.emailtemplates.edit(object);
		if (txtMessage == null) {
			response.sendRedirect(request.getContextPath() + "/adminListEmailTemplates.do?nStart=" + nStart + "&nSize=" + nSize);
			return;
		} else {
			response.sendRedirect(request.getContextPath() + "/adminListEmailTemplates.do?nStart=" + nStart + "&nSize=" + nSize + "&txtMessage=" + txtMessage);
			return;
		}
	} else {
		String txtName = request.getParameter("txtName");
		object = website.emailtemplates.get(txtName);
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
