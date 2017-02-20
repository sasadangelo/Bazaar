<%@page import='nbp.jspcart.*,nbp.jspcart.users.*,nbp.jspcart.products.*,nbp.jspcart.orders.*,java.util.*,java.text.*,java.sql.*'%>

<%
	response.setContentType("text/text");
	response.addHeader("expires","0");
%>

<jsp:useBean id="website" scope="application" class="nbp.jspcart.WebSite">
    <%
        website.init(application.getRealPath("../../"));
    %>
</jsp:useBean>

<%
	int nStart = website.getRequestParameter(request,"nStart",0);
	int nSize  = website.getRequestParameter(request,"nSize",2000);
	
	int nOrderState = website.getRequestParameter(request,"nOrderState",0);

	int nStart_Day = website.getRequestParameter(request,"nStart_Day",1);
	int nStart_Month = website.getRequestParameter(request,"nStart_Month",1);
	int nStart_Year = website.getRequestParameter(request,"nStart_Year",2003);
	
	int nEnd_Day = website.getRequestParameter(request,"nEnd_Day",1);
	int nEnd_Month = website.getRequestParameter(request,"nEnd_Month",12);
	int nEnd_Year = website.getRequestParameter(request,"nEnd_Year",3000);
	
	String text,startdate,enddate;
	text = String.valueOf(nStart_Year);
	text += "-" + String.valueOf(nStart_Month);
	text += "-" + String.valueOf(nStart_Day);
	startdate = text;
	text += " 00:00:00.00000000";
	Timestamp tsStart = Timestamp.valueOf(text);

	text = String.valueOf(nEnd_Year);
	text += "-" + String.valueOf(nEnd_Month);
	text += "-" + String.valueOf(nEnd_Day);
	enddate = text;
	text += " 00:00:00.00000000";
	Timestamp tsEnd = Timestamp.valueOf(text);

	Vector pageorders = null;
	
	String ids[] = request.getParameterValues("nID");
	if(ids != null)
	{
		pageorders = website.orders.getAll(request.getParameterValues("nID"));
	}
	else
	{
		pageorders = new Vector();
		pageorders.addElement( website.orders.get( website.getRequestParameter(request,"nID",0) ) );
	}

	Enumeration enum = pageorders.elements();
	while(enum.hasMoreElements()) {
		Order order = (Order)enum.nextElement();
		if(order != null && order.nOrderState == order.PAYMENT_PENDING) {
			String txtMessage = website.mailer.sendPaymentPendingMail(order,website.users);
			
			if (txtMessage == null) {
				response.sendRedirect(request.getContextPath() + "/adminListOrders.do?txtMessage=Promemoria%20 inviato%20con%20successo.&" + request.getQueryString() );
				return;
			} else {
				response.sendRedirect(request.getContextPath() + "/adminListOrders.do?txtMessage=" + txtMessage + "&" + request.getQueryString() );
				return;
			}
		}
	}
	
	response.sendRedirect(request.getContextPath() + "/adminListOrders.do?" + request.getQueryString() );
%>
