<%@page import='nbp.jspcart.users.*,nbp.jspcart.*,nbp.jspcart.orders.*,java.util.*'%>

<jsp:useBean id="website" scope="application" class="nbp.jspcart.WebSite">
    <%
        website.init(application.getRealPath("../../"));
    %>
</jsp:useBean>

<%
	Users	users = website.users;
	Orders	orders = website.orders;
	int nStart = website.getRequestParameter(request,"nStart",0);
	int nSize  = website.getRequestParameter(request,"nSize",20);

	User user = (User) request.getSession().getAttribute("User");
	
	Vector items = orders.getAll(nStart,nSize,user.txtUsername);
	
	if (items.size() == 0) {
%>
		<BR><CENTER>Nessun ordine disponibile</CENTER>
<%
	} else {
%>
		<TABLE width=80% cellpadding=2 cellspacing=1 class=Data>
			<TR>
				<TH align=left>CODICE Ordine</TH>
				<TH align=left>Data</TH>
				<TH align=right>Spesa</TH>
				<TH colspan=2>Stato</TH>
				<TH>Annulla</TH>
			</TR>
<%
			Enumeration enum2 = items.elements();
			while(enum2.hasMoreElements()) {
				Order order = (Order)enum2.nextElement();
				org.nspeech.util.Date date = new org.nspeech.util.Date(order.tsDate);
%>
				<TR>
					<TD align=left><%=order.nID%>&nbsp;&nbsp;<A href="javascript:orderDetail(<%=order.nID%>)">Dettagli</A></TD>
					<TD align=left><%=date.getShortDate()%></TD>
					<TD align=right><%=website.format(order.dblGrandTotal)%> EUR</TD>
					<TD>&nbsp;&nbsp;&nbsp;</TD>
<%
					if (order.nOrderState==order.PAYMENT_PENDING) {
%>
						<TD align=left><%=order.getStatus()%><BR>
				<!--							<A href="../../jsp/cart/Checkout2.jsp?nOrderID=<%=order.nID%>">Paga Subito!</A> -->
						</TD>
						<TD align=right><a href="javascript:cancelOrder('<%=order.nID%>')">Annulla</A></TD>
<%
					} else {
%>
						<TD align=left><%=order.getStatus()%></TD>
						<TD align=right>N/A</TD>
<%
					}
%>
				</TR>
<%
			}
%>
		</TABLE>
		
		Gli ordini non ancora spediti possono essere annullati.
<%
	}
%>
	
<SCRIPT LANGUAGE=javascript>
<!--
	function orderDetail(nID) {
		window.open("orderdetails.do?nID="+nID,"OrderDetail"+nID,"top=50,left=50,width=500,height=400")
	}
	
	function cancelOrder(nID) {
		if(confirm("Sei sicuro di voler annullare l'ordine "+nID)) {
			location.href = "jsp/orders/Delete.jsp?nOrderID=" + nID + "&nStart=<%=nStart%>&nSize=<%=nSize%>"
		}
	}
//-->
</SCRIPT>