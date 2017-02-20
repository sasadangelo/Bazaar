<%@ page import='java.util.*,nbp.jspcart.orders.*,nbp.jspcart.users.*,java.sql.*'%>

<%!
	String getDateBox(String prefix,int nDay,int nMonth,int nYear) {
		int i;
		String html = "";
		
		html += "<SELECT name='" + prefix + "_Day'>\r\n";
		for(i=1;i<=31;i++) {
			String num = String.valueOf(i);
			if(i==nDay)
				html += "<OPTION SELECTED Value='" + num + "'>" + num + "</OPTION>\r\n";
			else
				html += "<OPTION Value='" + num + "'>" + num + "</OPTION>\r\n";
		}
		html += "</SELECT>\r\n";
		
		html += "<SELECT name='" + prefix + "_Month'>\r\n";
		for(i=1;i<=12;i++) {
			String num = String.valueOf(i);
			if(i==nMonth)
				html += "<OPTION SELECTED Value='" + num + "'>" + org.nspeech.util.Date.getMonthName(i) + "</OPTION>\r\n";
			else
				html += "<OPTION Value='" + num + "'>" + org.nspeech.util.Date.getMonthName(i) + "</OPTION>\r\n";
		}
		html += "</SELECT>\r\n";
		
		html += "<SELECT name='" + prefix + "_Year'>\r\n";
		for(i=2003;i<2050;i++) {
			String num = String.valueOf(i);
			if(i==nYear)
				html += "<OPTION SELECTED Value='" + num + "'>" + num + "</OPTION>\r\n";
			else
				html += "<OPTION Value='" + num + "'>" + num + "</OPTION>\r\n";
		}
		html += "</SELECT>\r\n";
	
		return html;
	}
%>

<jsp:useBean id="website" scope="application" class="nbp.jspcart.WebSite"><%website.init(application.getRealPath("../../"));%></jsp:useBean>

<%
	response.addHeader("expires","0");

	long now = System.currentTimeMillis();
	org.nspeech.util.Date dt = new org.nspeech.util.Date(now+86400000);
	
	int nStart = website.getRequestParameter(request,"nStart",0);
	int nSize  = website.getRequestParameter(request,"nSize",2000);
	String txtMessage = website.getRequestParameter(request,"txtMessage");
	
	int nOrderState = website.getRequestParameter(request,"nOrderState",3);

	int nEnd_Day = website.getRequestParameter(request,"nEnd_Day",dt.getDay());
	int nEnd_Month = website.getRequestParameter(request,"nEnd_Month",dt.getMonth());
	int nEnd_Year = website.getRequestParameter(request,"nEnd_Year",dt.getYear());
	
	dt = new org.nspeech.util.Date(now-86400000);
	int nStart_Day = website.getRequestParameter(request,"nStart_Day",dt.getDay());
	int nStart_Month = website.getRequestParameter(request,"nStart_Month",dt.getMonth());
	int nStart_Year = website.getRequestParameter(request,"nStart_Year",dt.getYear());
	
	String text;
	text = String.valueOf(nStart_Year);
	text += "-" + String.valueOf(nStart_Month);
	text += "-" + String.valueOf(nStart_Day);
	text += " 00:00:00.00000000";
	Timestamp tsStart = Timestamp.valueOf(text);

	text = String.valueOf(nEnd_Year);
	text += "-" + String.valueOf(nEnd_Month);
	text += "-" + String.valueOf(nEnd_Day);
	text += " 00:00:00.00000000";
	Timestamp tsEnd = Timestamp.valueOf(text);
	
	Vector items = website.orders.getAll(tsStart,tsEnd,nOrderState);
%>

<%
	if (txtMessage != null) {
%>
		<P><font color=red size=2><b><%=txtMessage%></b></font></P>
<%
	}
%>

<FORM action="adminListOrders.do" name="OrdersForm">
	<TABLE width=100% cellpadding=2 cellspacing=1 class=Data>
		<input type=hidden name=nStart value='<%=nStart%>'>
		<input type=hidden name=nSize value='<%=nSize%>'>
		<TR>
			<TD colspan=5>
				Mostra 
				<SELECT name='nOrderState'>
					<OPTION Value='-1'>Tutti</OPTION>
					<OPTION Value='0'>In Attesa di Spedizione/Pagamento</OPTION>
					<!--										
					<OPTION Value='1'>In Attesa di convalida Carta di Credito</OPTION>
					<OPTION Value='2'>Autorizzazione Carta di Credito fallita</OPTION>
					<OPTION Value='3'>Ordini Pagati ma non Eseguiti ancora</OPTION>
					-->										
					<OPTION Value='4'>Ordini Eseguiti e Spediti</OPTION>
					<!--										
					<OPTION Value='7'>Ordini Eseguiti & Aggiornamento Tracking</OPTION>
					-->										
					<OPTION Value='5'>Ordini Rifiutati</OPTION>
				</SELECT>
			</TD>
		</TR>
		<TR>
			<TD>
				Da
			</TD>
			<TD>
				<%=getDateBox("nStart",nStart_Day,nStart_Month,nStart_Year)%>
			</TD>
			<TD>
				A
			</TD>
			<TD>
				<%=getDateBox("nEnd",nEnd_Day,nEnd_Month,nEnd_Year)%>
			</TD>
			<TD>
				<input type=submit class=darksmallbutton value=Mostra>
			</TD>
		</TR>
	</TABLE>
	<TABLE width=100% cellpadding=2 cellspacing=1 class=Data>
		<TR>
			<TH align=left><input type=checkbox name='nAllID' value='' onclick='doCheck()'></TH>
			<TH align=left>CODICE Ordine</TH>
			<TH align=left>Utente</TH>
			<TH align=left>Data</TH>
			<TH align=right>Totale</TH>
			<TH colspan=2>Stato</TH>
			<TH>Nota</TH>
		</TR>
<%
		Enumeration enum = items.elements();
		while(enum.hasMoreElements()) {
			Order order = (Order)enum.nextElement();
			org.nspeech.util.Date date = new org.nspeech.util.Date(order.tsDate);
%>
			<TR>
				<TD align=left><input type=checkbox name='nID' value='<%=order.nID%>'></TD>
				<TD align=left><%=order.nID%>&nbsp;&nbsp;<A href="javascript:orderDetail(<%=order.nID%>)">Dettagli</A></TD>
				<TD align=left><a href="mailto:<%=order.txtEmailAddress%>"><%=order.txtEmailAddress%></A></TD>
				<TD align=left><%=date.getShortDate()%></TD>
				<TD align=right><%=website.format(order.dblGrandTotal)%> EUR</TD>
				<TD>&nbsp;&nbsp;&nbsp;</TD>
				<TD align=left><%=order.getStatus()%> &nbsp;&nbsp;&nbsp;
				<TD align=right><%=order.txtRemarks==null?"":order.txtRemarks%></TD>
			</TR>
<%
		}
%>
	</TABLE>
</FORM>						
		
<TABLE class='Data' cellspacing=1 >
	<TR>
		<TD><A href="javascript:editSelected()">Modifica</A>&nbsp;&nbsp;</TD>
		<TD><A href="javascript:printCSV()">Scarica tutti i CSV (Uso Ufficio)</A></TD>
	</TR>
	<TR>
		<TD><A href="javascript:deleteSelected()">Cancella</A>&nbsp;&nbsp;</TD>
		<TD><A href="javascript:remindPayment()">Invia Promemoria Pagamento</A></TD>
	</TR>
	
	<TR>
		<TD><A href="javascript:editTracking()">Tracking</A>&nbsp;&nbsp;</TD>
		<TD><A href="javascript:print()">Stampa Tutto (Uso Ufficio)</A></TD>
	</TR>
</TABLE>
						
<SCRIPT LANGUAGE=javascript>
<!--
	document.OrdersForm.nOrderState.value = <%=nOrderState%>

	function doCheck() {
		l=document.OrdersForm.nID.length
		for(i=0;i<l;i++) {
			document.OrdersForm.nID[i].checked = document.OrdersForm.nAllID.checked
		}
	}
	
	function print() {
		document.OrdersForm.action = "admin/orders/PrintList.jsp";
		document.OrdersForm.submit();
	}
	
	function editSelected() {
		document.OrdersForm.action = "adminEditOrders.do";
		document.OrdersForm.submit();
	}
	
	function editTracking() {
		document.OrdersForm.action = "adminEditTrackOrders.do";
		document.OrdersForm.submit();
	}
	
	function deleteSelected() {
		if(confirm("Sei sicuro di voler cancellare gli ordini selezionati?")) {
			document.OrdersForm.action = "admin/orders/Delete.jsp";
			document.OrdersForm.submit();
		}
	}
	
	function printListInfo() {
		document.OrdersForm.action = "admin/orders/PrintListInfo.jsp";
		document.OrdersForm.submit();
	}
	
	function printCSV() {
		document.OrdersForm.action = "admin/orders/PrintCSV.jsp";
		document.OrdersForm.submit();
	}
	
	function remindPayment() {
		document.OrdersForm.action = "admin/orders/RemindPayment.jsp";
		document.OrdersForm.submit();
	}
	
	function show(start,size) {
		document.OrdersForm.action = "./List.jsp";
		document.OrdersForm.nStart.value = start;
		document.OrdersForm.nSize.value = size;
		document.OrdersForm.submit();
	}
//-->
</SCRIPT>
<SCRIPT LANGUAGE=javascript>
	function orderDetail(nID) {
		window.open("admin/orders/OrderDetail.jsp?nID="+nID,"OrderDetail"+nID,"top=50,left=50,width=500,height=400,scrollbars=1,resize=1")
	}
	
	function cancelOrder(nID) {
		if(confirm("Sei sicuro di voler annullare l'ordine "+nID+"?")) {
			location.href = "./Delete.jsp?nOrderID=" + nID
		}
	}
</SCRIPT>
