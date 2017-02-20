<%@ page import='java.util.*,nbp.jspcart.orders.*,nbp.jspcart.products.*,nbp.jspcart.users.*,java.sql.*'%>

<%
	response.addHeader("expires","0");
%>

<jsp:useBean id="website" scope="application" class="nbp.jspcart.WebSite">
    <%
        website.init(application.getRealPath("../../"));
    %>
</jsp:useBean>

<%
	int nID = website.getRequestParameter(request,"nID",0);
	
	Order order = website.orders.get(nID);
	
	String Message = null;
	
	if(request.getMethod().equalsIgnoreCase("post")) {
		order.nOrderState = website.getRequestParameter(request,"nOrderState",0);
		order.txtRemarks = request.getParameter("txtRemarks");
		order.txtTracking = request.getParameter("txtTracking");
		Message = website.orders.edit(order,website.users);
		if(Message==null) {
			Message = "Stato dell' Ordine modificato con successo";
		}
	}
%>

<HTML>
	<HEAD>
	<TITLE><%=website.shopName%> - Free Open Source Shopping Cart by Open Community</TITLE>
	<META http-equiv=Content-Type content="text/html; charset=windows-1252">
	<LINK href="../../style.css" rel=stylesheet>
	<META content="MSHTML 5.50.4134.100" name=GENERATOR></HEAD>
	<BODY bgColor=#ffffff leftMargin=0 topMargin=0 marginwidth="0" marginheight="0" onLoad="">
		<TABLE width=100% cellpadding=10>
			<TR>
				<TD>
<%
					if(order.size()>0) {
						org.nspeech.util.Date date = null;
						if(order.tsDate!=null)
							date = new org.nspeech.util.Date(order.tsDate);
						else
							date = new org.nspeech.util.Date();
%>
						<TABLE width=100% class=Data cellspacing=1 >
<%
							if (Message!=null) { 
%>
								<TR>
									<TD><FONT color=red><STRONG><%=Message%></STRONG></FONT></TD>
								</TR>
<%
							}
%>
							<TR>
								<TH>Dettagli ordine di <A href="mailto:<%=order.txtEmailAddress%>"><%=order.txtEmailAddress%></a> [<%=date.getLongDate()%>]</TH>
							</TR>
						</TABLE>
						<TABLE class=data width=100% cellspacing=1 >
							<TR>
								<TH>Prodotto</TH>
								<TH>Confezione/i</TH>
								<TH>Unita' x Confezione</TH>
								<TH>Prezzo</TH>
							</TR>
<%
							Enumeration enum = order.orderedItems.elements();
							while(enum.hasMoreElements()) {
								OrderedItem oi = (OrderedItem) enum.nextElement();
								Product product = website.products.get(oi.nProductID);
%>
								<TR>
									<TD width=75 valign=top><b><%=product.txtName%></b><br><%=product.txtWeight%></TD>
									<TD valign=top><%=oi.nQuantity%></TD>
									<TD valign=top align=right><%=(int)product.dblQtyPerPack%> Unita' per Confezione <BR>
										<STRONG>Totale <%=((int)product.dblQtyPerPack)*oi.nQuantity%> unita'</STRONG>
									</TD>
									<TD valign=top align=right><%=website.format(oi.dblPrice)%> EUR per Confezione <BR>
										<STRONG>Totale : <%=website.format(oi.dblPrice*oi.nQuantity)%> EUR</STRONG>
									</TD>
								</TR>
								<TR>
									<TD colspan=4>
<%
                                        if (oi.txtReason != null) {
%>									
										    <STRONG><U>Commento:</U></STRONG><BR>
										    <%=oi.txtReason%>
										    <hr size=1>
<%
                                        }
%>										
									</TD>
								</TR>
<%
							}
%>
						</TABLE>
						<BR>
						<BR>
						<TABLE cellspacing=1 cellpadding=2 width=100% class=Data>
							<TR>
								<TH colspan=2>Ordine Finale</TH>
							</TR>
							<TR>
								<TD align=right><STRONG>Totale</STRONG></TD>
								<TD width="130" align=right><%=website.format(order.dblTotal)%> EUR</TD>
							</TR>
<%
							if (order.dblProcessingFees>0) {
%>
								<TR>
									<TD align=right><font size=1>(Solo per ordini inferiori a 100 EUR)</font> &nbsp;
										<STRONG>Spese Aggiuntive</STRONG>
									</TD>
									<TD align=right><%=website.format(order.dblProcessingFees)%> EUR </TD>
								</TR>
<%
							}
%>
							<TR>
								<TD align=right><STRONG>Spese Spedizione</STRONG></TD>
								<TD align=right><%=website.format(order.dblShippingCharges)%> EUR </TD>
							</TR>
							<TR>
								<TH align=right>SubTotale</TH>
								<TH align=right><%=website.format(order.dblGrandTotal) %> EUR</TH>
							</TR>
						</TABLE>
						<BR>
						<BR>
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
						<TABLE WIDTH=100% BORDER=0 CELLSPACING=1 CELLPADDING=1 class=data>
							<FORM action="OrderDetail.jsp" method=post name=OrdersForm>
								<INPUT type=hidden name=nID value='<%=order.nID%>'>
								<TR>
									<TH>Modifica Stato dell'Ordine</TH>
								</TR>
								<TR>
									<TD>
										<SELECT name='nOrderState'>
											<OPTION Value='0'>In Attesa di Spedizione/Pagamento</OPTION>
											<!--										
											<OPTION Value='1'>In Attesa di convalida Carta di Credito</OPTION>
											<OPTION Value='2'>Autorizzazione Carta di Credito fallita</OPTION>
											<OPTION Value='3'>Ordine Pagato ma non Eseguito ancora</OPTION>
											-->										
											<OPTION Value='4'>Ordine Eseguito e Spedito</OPTION>
											<!--										
											<OPTION Value='7'>Ordine Eseguito & Aggiornamento Tracking</OPTION>
											-->										
											<OPTION Value='5'>Ordine Rifiutato</OPTION>
										</SELECT>
									</TD>
								</TR>
								<TR>
									<TD>
										<TEXTAREA name=txtRemarks cols=33 rows=4><%=order.txtRemarks==null?"":order.txtRemarks%></TEXTAREA>
									</TD>
								</TR>
								<TR>
									<TD><input type=submit value="Modifica" id=submit1 name=submit1></TD>
								</TR>
							</FORM>
						</TABLE>
						<SCRIPT LANGUAGE=javascript>
						<!--
							document.OrdersForm.nOrderState.value = <%=order.nOrderState%>
						//-->
						</SCRIPT>
<%
					}
%>
				</TD>
			</TR>
		</TABLE>
	</BODY>
</HTML>
