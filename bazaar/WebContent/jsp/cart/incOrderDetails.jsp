<TABLE class=Data  width=80% cellspacing=1 >
    <TR>
        <TH>Dettagli Ordine</TH>
    </TR>
</TABLE>
<TABLE class=data  width=80% cellspacing=1 >
    <TR>
        <TH>Prodotto</TH>
        <TH>Prezzo</TH>
        <TH>Quantita'</TH>
    </TR>
	<TR>
		<TD width=80% valign=top>
		    <B><%= cart.getProduct().getTxtName() %></B>
		</TD>
		<TD width=10% valign=top align=right>
		    <%= cart.getProduct().getDblPrice() %>
		</TD>
		<TD width=10% valign=top align=right>
		    <%= cart.getOrder().getNQuantity() %>
		</TD>
	</TR>
</TABLE>
<BR>
<BR>
<TABLE cellspacing=1 cellpadding=2  width=80% class=Data>
	<TR>
		<TH colspan=2>Ordine Finale</th>
	</TR>
	<TR>
		<td align=right><STRONG>Totale</STRONG></td>
		<td align=right><%=cart.getOrder().getDblTotal()%> EUR</td>
	</TR>
<% 
    if (cart.getOrder().getDblShippingCharges() > 0) {
%>
		<TR>
			<TD align=right><STRONG>Spese Spedizione</STRONG></TD>
			<TD align=right><%=cart.getOrder().getDblShippingCharges()%> EUR </TD>
		</TR>
<% 
    }
%>
	<TR>
		<TD align=right><STRONG>SubTotale</STRONG></TD>
		<TD align=right><%=cart.getOrder().getDblGrandTotal()%> EUR </TD>
	</TR>
</TABLE>
