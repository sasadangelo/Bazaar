<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%@ page import='org.opencommunity.bazaar.utils.business.*,org.opencommunity.bazaar.account.dto.*'%>

<%
    IDataListHandler list = (IDataListHandler) request.getSession(true).getAttribute("listAccounts");
    IDataListChunk chunk = list.getListChunk();
%>

<CENTER>
    <font color=red>
        <STRONG>
            <html:messages id="error" message="false">
                <li><bean:write name="error"/><br>
            </html:messages>
        </STRONG>
    </font>
</CENTER>

Gestione Utenti - Bazaar

<%
	if (list.size() > 0) {
%>
		<TABLE class='Data' width=100% cellspacing=1 >
			<TR>
				<TH>Indirizzo Email</TH>
				<TH>Dettagli</TH>
				<TH>Telefono</TH>
				<TH>Data e Ora di Registrazione</TH>
				<TH>&nbsp;</TH>
				<TH>&nbsp;</TH>
			</TR>
<%
				for (IDataListIterator itr = chunk.iterator(); itr.hasNext(); ) {
					Account account = (Account) itr.next();
%>
					<TR>
						<TD valign=top>
						    <STRONG>
						        <%=account.getTxtEmailAddress()%>
						    </STRONG>
						    <BR>
							Ruolo: <%= account.isBAdmin() ? "Aministratore" : "Utente"%>
						</TD>
						<TD valign=top>
						    <STRONG>
						        <%=account.getTxtFirstname()%> <%=account.getTxtLastname()%>
						    </STRONG>
						    <BR>
							<%=account.getTxtStreetAddress()%>
							<BR>
							<%=account.getTxtZipCode()%>,<%=account.getTxtCity()%>
							<BR>
							<%=account.getTxtState()%>,<%=account.getTxtCountry()%>
						</TD>
						<TD valign=top><%=account.getTxtPhone()%></TD>
						<TD valign=top><%=account.getTsRegTime()%></TD>
						<TD valign=top><A href="goAdminEditUser.do?txtEmailAddress=<%=account.getTxtEmailAddress()%>">Modifica</A></TD>
						<TD valign=top><A href="javascript:deleteObject('<%=account.getTxtEmailAddress()%>','<%=account.getNID()%>')">Cancella</A></TD>
					</TR>
			<%
				}
			%>
		</TABLE>
<%
	} else {
%>
		<TABLE class='Data' cellspacing=1 >
			<TR>
				<TH>Dettagli</TH>
			</TR>
			<TR>
				<TD>Non ci sono utenti registrati nel sistema.</TD>
			</TR>
		</TABLE>
<%
	}
%>

<TABLE class='Data' cellspacing=1 >
	<TR>
		<TD>
<% 
			if (chunk.hasPrev()) {
%>
				<a href="adminListUsers.do?page=prev">Prec</a> |
<%
            } else {
%>
				Prec |
<%
            }
%>
		</TD>
		<TD>
<%
			if (chunk.hasNext()) {
%>
				<a href="adminListUsers.do?page=next">Succ</a>
<%
			} else {
%>
				Succ
<%
			}
%>
		</TD>
	</TR>
</TABLE>

<SCRIPT LANGUAGE=javascript>
<!--
	function deleteObject(txtEmailAddress, idValue) {
		if(confirm("Sei sicuro di voler cancellare l'utente " + txtEmailAddress)) 	{
			location.href = "adminDeleteUser.do?id=" + idValue;
		}
	}
//-->
</SCRIPT>
