<%@page import='nbp.jspcart.users.*,nbp.jspcart.orders.*,nbp.jspcart.products.*,nbp.jspcart.*,nbp.jspcart.orders.*,java.util.*'%>

<jsp:useBean id="website" scope="application" class="nbp.jspcart.WebSite"><%website.init(application.getRealPath("../../"));%></jsp:useBean>

<%
	Products	products	= website.products;
	Orders		orders		= website.orders;
	Users		users		= website.users;

	response.addHeader("expires","0");
	
	int nID = website.getRequestParameter(request,"nID",0);
	
	Order order = orders.get(nID);
	User user = (User) session.getAttribute("User");
	
	if(!user.isAdmin()) {
		if(!order.txtEmailAddress.equals(user.txtUsername))	{
			response.sendError(response.SC_UNAUTHORIZED);
			return;
		}
	}
	
%>

<HTML>
	<HEAD>
	<TITLE><%=website.shopName%> - Free Open Source Shopping Cart by Open Community</TITLE>
	<LINK href="style.css" rel=stylesheet>
	<BODY bgColor=#ffffff leftMargin=0 topMargin=0 marginwidth="0" marginheight="0" onLoad="">
		<TABLE width=100% cellpadding=10>
			<TR>
				<TD>
					<%@include file='incOrderDetails.jsp'%>
				
					<TABLE width=100% border=0 cellspacing=1 class=Data>
						<TR>
							<TH>  Stato Ordine </TH>
						</TR>
						<TR>
							<TD> <%=order.getStatus()%></TD>
						</TR>
<%
						if (order.txtRemarks!=null) {
%>
							<TR>
								<TD> <%=order.txtRemarks%></TD>
							</TR>
<%
						}
%>
					</TABLE>
				</TD>
			</TR>
		</TABLE>
	</BODY>
</HTML>
