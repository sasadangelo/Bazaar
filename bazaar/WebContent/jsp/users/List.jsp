<%@ page import='java.util.*,nbp.jspcart.users.*,nbp.jspcart.users.*'%>

<%
	int nStart = website.getRequestParameter(request,"nStart",0);
	int nSize  = website.getRequestParameter(request,"nSize",11);
	
	Vector items = website.users.getAll(nStart,nSize);
%>

Gestione Utenti - Bazaar

<%
	if(items.size()>0) {
%>
		<TABLE class='Data' width=100% cellspacing=1 >
			<TR>
				<TH>Indirizzo Email</TH>
				<TH>Dettagli</TH>
				<TH>Telefono</TH>
				<TH>Riferimento</TH>
				<TH>Segnalato da</TH>
				<TH>Data e Ora di Registrazione</TH>
				<TH>&nbsp;</TH>
				<TH>&nbsp;</TH>
			</TR>
<%
	        	int size = items.size()==nSize ? nSize-1 : items.size();
				for (int i=0; i<size; ++i) {
					UserProfile object = (UserProfile) items.get(i);
%>
					<TR>
						<TD valign=top><STRONG><%=object.txtEmailAddress%></STRONG><BR>
							Ruolo: <%= object.isAdmin() ? "Aministratore" : "Cliente"%>
						</TD>
						<TD valign=top><STRONG><%=object.txtFirstname%> <%=object.txtLastname%></STRONG><BR>
							<%=object.txtStreetAddress%><BR>
							<%=object.txtZipCode%>,<%=object.txtCity%><BR>
							<%=object.txtState%>,<%=object.txtCountry%></TD>
						<TD valign=top><%=object.txtPhone%></TD>
						<TD valign=top><%=object.txtReference%></TD>
						<TD valign=top><%=object.txtReferredBy%></TD>
						<TD valign=top><%=object.tsRegTime%></TD>
						<TD valign=top><A href="adminEditUsers.do?txtEmailAddress=<%=object.txtEmailAddress%>&nStart=<%=nStart%>&nSize=<%=nSize%>">Modifica</A></TD>
						<TD valign=top><A href="javascript:deleteObject('txtEmailAddress','<%=object.txtEmailAddress%>','<%=object.txtEmailAddress%>')">Cancella</A></TD>
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
<%
				if (nStart == 0) {
%>			
					<TD>Non ci sono utenti registrati nel sistema.</TD>
<%
				} else {
%>
					<TD>Consulta le pagine precedenti.</TD>
<%
				}
%>
			</TR>
		</TABLE>
<%
	}
%>

<TABLE class='Data' cellspacing=1 >
	<TR>
<% 
		if (nStart>0) {
%>
			<TD><A HREF="adminListUsers.do?nStart=<%=nStart-nSize+1%>">Prec</A> |</TD>
<%
		} else {
%>
			<TD>Prec |</TD>
<%
		}

		if (items.size()==nSize) {
%>
			<TD><A HREF="adminListUsers.do?nStart=<%=nStart+nSize-1%>">Succ</A></TD>
<%
		} else {
%>
			<TD>Succ</TD>
<%
		}
%>
	</TR>
</TABLE>

<SCRIPT LANGUAGE=javascript>
<!--
	function deleteObject(id, idval, value) {
		if(confirm("Sei sicuro di voler cancellare "+value)) 	{
			location.href = "admin/users/Delete.jsp?" + id + "=" + idval + "&nStart=<%=nStart%>&nSize=<%=nSize%>";
		}
	}
//-->
</SCRIPT>
