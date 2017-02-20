<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%@ page import='org.opencommunity.bazaar.account.dto.*,org.opencommunity.bazaar.account.business.impl.*'%>

<%
    Account account = new AccountManagerDelegate().getAccount(request.getParameter("txtEmailAddress"));
%>

<CENTER>
    <font color=red>
        <STRONG>
            <html:messages id="error" message="false">
                <li><bean:write name="error"/><br>
            </html:messages>
        </STRONG>
    </font>
	<FORM name='adminEditUser' action='adminEditUser.do' method=POST>
		<INPUT type=hidden name='txtEmailAddress' value='<%=account.getTxtEmailAddress()%>'>
		<TABLE border=0 class=Data cellspacing=1 >
			<TR>
				<TH colspan=2>Modifica i dati dell'utente</TH>
			</TR>
			<TR>
				<TD align=right>Nome <font color=red>*</font>:</TD>
				<TD><INPUT TYPE=TEXT name='txtFirstname' value="<%=account.getTxtFirstname()%>"></TD>
			</TR>
			<TR>
				<TD align=right>Cognome <font color=red>*</font>:</TD>
				<TD><INPUT TYPE=TEXT name='txtLastname' value="<%=account.getTxtLastname()%>"></TD>
			</TR>
			<TR>
				<TD align=right>Indirizzo <font color=red>*</font>:</TD>
				<TD><INPUT TYPE=TEXT name='txtStreetAddress' value="<%=account.getTxtStreetAddress()%>"></TD>
			</TR>
			<TR>
				<TD align=right>Codice Postale <font color=red>*</font>:</TD>
				<TD><INPUT TYPE=TEXT name='txtZipCode' value="<%=account.getTxtZipCode()%>"></TD>
			</TR>
			<TR>
				<TD align=right>Citta' <font color=red>*</font>:</TD>
				<TD><INPUT TYPE=TEXT name='txtCity' value="<%=account.getTxtCity()%>"></TD>
			</TR>
			<TR>
				<TD align=right>Stato/Prov. <font color=red>*</font>:</TD>
				<TD><INPUT TYPE=TEXT name='txtState' value="<%=account.getTxtState()%>"></TD>
			</TR>
			<TR>
				<TD align=right class=normal><font color=red>*</font> Nazione:</TD>
				<TD class=normal>
				    <SELECT name=txtCountry>
				        <OPTION value="IT">Italy</OPTION>
                    </SELECT>
                </TD>
			</TR>
			<TR>
				<TD align=right>Telefono <font color=red>*</font>:</TD>
				<TD><INPUT TYPE=TEXT name='txtPhone' value="<%=account.getTxtPhone()%>"></TD>
			</TR>
			<TR>
				<TD colspan=2><INPUT TYPE=Submit name='Submit' Value='Conferma'></TD>
			</TR>
		</TABLE>
	</FORM>
</CENTER>
