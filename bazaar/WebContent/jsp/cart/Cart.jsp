<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%@ page import='org.opencommunity.bazaar.catalog.dto.Product'%>

<jsp:useBean id="cart" scope="session" class="org.opencommunity.bazaar.cart.dto.CartAO" />

<CENTER>
    <FONT color=red>
        <STRONG>
            <html:messages id="error" message="false">
                <li><bean:write name="error"/><br>
            </html:messages>
        </STRONG>
    </FONT>

<IMG SRC="images/cart.gif" alt="">
<BR>
<BR>
<%
	if (cart.getProduct()==null) 	{
%>
		Il carrello e' vuoto.
<%
	} else {
		Product product = cart.getProduct();
%>
		<FORM name="cartProduct" action="modifyCart.do" method=POST>
  		    <TABLE cellspacing=1 cellpadding=2 class=data width="80%">
				<TR>
					<TH>Prodotto</TH><TH>Prezzo</TH><TH>Quantita'</TH><TH>&nbsp;</TH><TH>&nbsp;</TH>
				</TR>
				<TR>
					<TD valign=top>
					    <STRONG>
					        <%=product.getTxtName()%>
					    </STRONG>
					    <BR>
					    <%=product.getTxtShortDescription()%>
					</TD>
					<TD valign=top>
					    <%=product.getDblPrice()%>EUR 
					    <BR>
					    <STRONG>
					        Totale: <%=cart.getOrder().getNQuantity()*product.getDblPrice()%> EUR
					    </STRONG>
					</TD>
					<TD valign=top>
					    <INPUT type=text size=2 name="numQuantity" value="<%=cart.getOrder().getNQuantity()%>">&nbsp;
					</TD>
					<TD valign=top>
                        <INPUT name=action type=submit class='SmallButton' value='Modifica'>
					</TD>
					<TD valign=top>
                        <INPUT name=action type=submit class='SmallButton' value='Cancella'>
					</TD>
				</TR>
		    </TABLE>
		</FORM>
		<BR>
		<TABLE class=Data cellspacing=1 width="80%">
			<TR>
				<TH colspan=2>Ordine Finale</TH>
			</TR>
			<TR>
				<TD align=right><STRONG>Totale</STRONG></TD>
				<TD align=right><%=cart.getOrder().getDblTotal()%> EUR</TD>
			</TR>
			<TR>
				 <TD align=right><STRONG>Spese Spedizione</STRONG></TD>
				 <TD align=right><%=cart.getOrder().getDblShippingCharges()%> EUR</TD>
			</TR>
			<TR>
				 <TH align=right>Sub Totale</TH>
				 <TH align=right><%=cart.getOrder().getDblGrandTotal()%> EUR</TH>
			</TR>
		</TABLE>
		<BR>
		<FORM name="cartProduct" action="goShipping.do" method=POST>
			<TABLE class=Data width="80%" cellspacing=1 >
				<TR>
					<TH>Sei sicuro di voler acquistare questo prodotto?</TH>
				</TR>
				<TR>
					<TD valign=top>
                        <INPUT name=submit type=submit value='Esegui Ordine'>
					</TD>
				</TR>
			</TABLE>
		</FORM>
<%
	}
%>
</CENTER>