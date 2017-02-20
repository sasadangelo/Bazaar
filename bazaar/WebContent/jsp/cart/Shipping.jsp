<jsp:useBean id="cart" scope="session" class="org.opencommunity.bazaar.cart.dto.CartAO" />
<jsp:useBean id="account" scope="session" class="org.opencommunity.bazaar.account.dto.Account" />

<CENTER>
    <%@ include file='incOrderDetails.jsp'%>
	<FORM name="ShippingForm" action="checkout.do" method="POST">
		<INPUT type=hidden name="nOrderID" value="0">
		<TABLE cellspacing=1 cellpadding=2 class=Data width=80%>
			<TR>
				<TH colspan=2>
				    Indirizzo Destinatario
				</TH>
			</TR>
			<TR>
				<TD colspan=2>
				    Specifica l'indirizzo a cui bisogna inviare la merce.
				    <BR>
				    <BR>
				    <BR>
				</TD>
			</TR>
			<TR>
				<TD align=right nowrap>
				    <font color=red>*</font>
				    Invia a:
				</TD>
				<TD>
				    <INPUT class=LightBox type=text name='txtName' value="<%= account.getTxtFirstname() %> <%= account.getTxtLastname() %>" >
				</TD>
			</TR>
			<TR>
				<TD align=right nowrap valign=top>
				    <font color=red>*</font>
				    Indirizzo:
				</TD>
				<TD>
				    <TEXTAREA class=LightBox cols=30 rows=3 name='txtStreetAddress'><%=account.getTxtStreetAddress()%></TEXTAREA>
				</TD>
			</TR>
			<TR>
				<TD align=right nowrap>
				    <font color=red>*</font>
				    Codice Postale:
				</TD>
				<TD>
				    <INPUT class=LightBox type=text name='txtZipCode' value="<%=account.getTxtZipCode()%>" >
				</TD>
			</TR>
			<TR>
				<TD align=right nowrap>
				    <font color=red>*</font>
				    Citta':
				</TD>
				<TD>
				    <INPUT class=LightBox type=text name='txtCity' value="<%= account.getTxtCity() %>">
				</TD>
			</TR>
			<TR>
				<TD align=right nowrap>
				    <font color=red>*</font>
				    Prov.:
				</TD>
				<TD>
				    <INPUT class=LightBox type=text name='txtState' value="<%= account.getTxtState() %>">
				</TD>
			</TR>
			<TR>
				<TD align=right nowrap>
				    <font color=red>*</font>
				    <A name=Country></A>Nazione:
				</TD>
				<TD>
	                <SELECT name=txtCountry>
	                    <OPTION value="IT">Italy</OPTION>
	                </SELECT>
				</TD>
			</TR>
			<TR>
				<TD align=right nowrap>
				    <font color=red>*</font>
				    Telefono:
				</TD>
				<TD>
				    <INPUT class=LightBox type=text name='txtPhone' value="<%=account.getTxtPhone()%>" >
				</TD>
			</TR>
			<TR>
				<TD></TD>
				<TD>
				    <INPUT type=submit class=SmallDarkButton name='' value='Conferma Ordine'>
				</TD>
			</TR>
		</TABLE>
	</FORM>
</CENTER>