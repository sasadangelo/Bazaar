<%@ page import='java.util.*,java.text.*,nbp.jspcart.*,nbp.jspcart.orders.*,nbp.jspcart.products.*,nbp.jspcart.users.*'%>

<%
	response.addHeader("expires","0");
%>

<jsp:useBean id="website" scope="application" class="nbp.jspcart.WebSite">
    <%
        website.init(application.getRealPath("../../"));
    %>
</jsp:useBean>

<%
	String nStart = request.getParameter("nStart");
	String nSize  = request.getParameter("nSize");
	String nOrderState = request.getParameter("nOrderState");
	String nEnd_Day = request.getParameter("nEnd_Day");
	String nEnd_Month = request.getParameter("nEnd_Month");
	String nEnd_Year = request.getParameter("nEnd_Year");
	String nStart_Day = request.getParameter("nStart_Day");
	String nStart_Month = request.getParameter("nStart_Month");
	String nStart_Year = request.getParameter("nStart_Year");
	
	String result = null;
	
	Vector pageorders = new Vector();
	
	if(request.getMethod().equalsIgnoreCase("post")) {
		String states [] = request.getParameterValues("Order_nOrderState");
		String remarks [] = request.getParameterValues("Order_txtRemarks");
		String tracking [] = request.getParameterValues("Order_txtTracking");
		String ids [] = request.getParameterValues("Order_nID");
			
		if (ids != null) {
		    for(int i=0;i<ids.length;i++) {
			    int nID = Integer.parseInt(ids[i]);
			
			    Order order = website.orders.getCompleteOrder(nID);
			    order.nOrderState = Integer.parseInt(states[i]);
			    order.txtRemarks = remarks[i];
			    order.txtTracking = tracking[i];
			
			    result = website.orders.edit(order,website.users);
			    if(result != null) {
				    break;
				}
		    }
			
		    if(result==null) {
			    String url = "/adminListOrders.do?nStart=" + nStart 
			    	+ "&nSize=" + nSize 
			    	+ "&nOrderState=" + nOrderState 
			    	+ "&nEnd_Day=" + nEnd_Day 
			    	+ "&nEnd_Month=" + nEnd_Month 
			    	+ "&nEnd_Year=" + nEnd_Year
			        + "&nStart_Day=" + nStart_Day
			        + "&nStart_Month=" + nStart_Month
			    	+ "&nStart_Year=" + nStart_Year;

			    response.sendRedirect(request.getContextPath() + url);
			    return;
		    }
		} else {
		    String url = "/adminListOrders.do?txtMessage=Nessun%20ordine%20selezionato."
		    	+ "&nStart=" + nStart 
		    	+ "&nSize=" + nSize 
		    	+ "&nOrderState=" + nOrderState 
		    	+ "&nEnd_Day=" + nEnd_Day 
		    	+ "&nEnd_Month=" + nEnd_Month 
		    	+ "&nEnd_Year=" + nEnd_Year
		        + "&nStart_Day=" + nStart_Day
		        + "&nStart_Month=" + nStart_Month
		    	+ "&nStart_Year=" + nStart_Year;

		    response.sendRedirect(request.getContextPath() + url);
		    return;
		}
	} else {
		String ids2[] = request.getParameterValues("nID");
		if(ids2 != null) {
			pageorders = website.orders.getAll(request.getParameterValues("nID"));
		} else {
			pageorders.addElement( website.orders.get( website.getRequestParameter(request,"nID",0) ) );
		}
	}
/*	
	} else {
	    String url = "/adminListOrders.do?txtMessage=Nessun%20ordine%20selezionato."
	    	+ "&nStart=" + nStart 
	    	+ "&nSize=" + nSize 
	    	+ "&nOrderState=" + nOrderState 
	    	+ "&nEnd_Day=" + nEnd_Day 
	    	+ "&nEnd_Month=" + nEnd_Month 
			+ "&nEnd_Year=" + nEnd_Year
	        + "&nStart_Day=" + nStart_Day
			+ "&nStart_Month=" + nStart_Month
	    	+ "&nStart_Year=" + nStart_Year;

		response.sendRedirect(request.getContextPath() + url);
       	return;
	}
*/
%>

<%@include file='EditForm.htm'%>