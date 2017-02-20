<%
	if (order.size()>0) {
		org.nspeech.util.Date date = null;
		if (order.tsDate!=null)
			date = new org.nspeech.util.Date(order.tsDate);
		else
			date = new org.nspeech.util.Date();
%>

		<TABLE width=100% class=Data cellspacing=1 >
			<TR>
				<th>Dettagli Ordine [<%=date.getLongDate()%>]</th>
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
			while (enum.hasMoreElements()) {
				OrderedItem oi = (OrderedItem) enum.nextElement();
				Product product = products.get(oi.nProductID);
%>
				<TR>
					<TD width=75 valign=top><b><%=product.txtName%></b><br><%=product.txtWeight%></TD>
					<TD valign=top><%=oi.nQuantity%></TD>
					<TD valign=top align=right><%=(int)product.dblQtyPerPack%> unita' per Confezione <BR>
						<STRONG>Totale <%=((int)product.dblQtyPerPack)*oi.nQuantity%> unita'</STRONG>
					</TD>
					<TD valign=top align=right><%=website.format(oi.dblPrice)%> EUR per Confezione <BR>
						<STRONG>Totale : <%=website.format(oi.dblPrice*oi.nQuantity)%> EUR</STRONG>
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
				<TD align=right><%=website.format(order.dblTotal)%> EUR</TD>
			</TR>
<%
			if (order.dblProcessingFees>0) {
%>
				<TR>
					<TD align=right><FONT size=1>(Solo per ordini inferiori a 100 EUR)</FONT> &nbsp;
					<STRONG>Spese Aggiuntive</STRONG></td>
					<TD align=right><%=website.format(order.dblProcessingFees)%> EUR </TD>
				</TR>
<%
			}

			if (order.dblShippingCharges>0) {
%>
				<TR>
					<TD align=right><STRONG>Spese Spedizione</STRONG></TD>
					<TD align=right><%=website.format(order.dblShippingCharges)%> EUR </TD>
				</TR>
<%
			}
%>
			<TR>
				<TH align=right>SubTotale</TH>
				<TH align=right><%=website.format(order.dblGrandTotal) %> EUR</TH>
			</TR>
		</TABLE>
		<BR>
		<BR>
<%
	}
%>
