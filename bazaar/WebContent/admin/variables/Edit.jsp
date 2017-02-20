<%@ page import='java.util.*,nbp.jspcart.variables.*,nbp.jspcart.users.*'%>

<jsp:useBean id="website" scope="application" class="nbp.jspcart.WebSite">
	<%website.init(application.getRealPath("../../"));%>
</jsp:useBean>

<%
	String nStart = website.getRequestParameter(request,"nStart");
	String nSize  = website.getRequestParameter(request,"nSize");
	
	String txtMessage = null;
	String action = "admin/variables/Edit.jsp";
	Variable object = null;
			
	if(request.getMethod().equalsIgnoreCase("post")) {
		object = new Variable(request);
		txtMessage = website.variables.edit(object);
		if (txtMessage == null) {
			response.sendRedirect(request.getContextPath() + "/adminListVariables.do?nStart=" + nStart + "&nSize=" + nSize);
			return;
		} else {
			response.sendRedirect(request.getContextPath() + "/adminListVariables.do?nStart=" + nStart + "&nSize=" + nSize + "&txtMessage=" + txtMessage);
			return;
		}
	} else {
		String txtName = request.getParameter("txtName");
		object = website.variables.get(txtName);
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
