<%@ page import='java.util.*,nbp.jspcart.emails.*,nbp.jspcart.users.*'%>

<jsp:useBean id="website" scope="application" class="nbp.jspcart.WebSite">
	<%website.init(application.getRealPath("../../"));%>
</jsp:useBean>

<%
	int nStart = website.getRequestParameter(request,"nStart",0);
	int nSize  = website.getRequestParameter(request,"nSize",11);
	
	Vector items = website.emailtemplates.getAll(nStart,nSize);
%>

Gestione Modelli Email - <%=website.shopName%><BR>

<%
	if(items.size()>0) {
%>

		<TABLE class='Data' width=100% cellspacing=1 >
			<TR>
				<TH>Nome</TH>
				<TH>Oggetto</TH>
				<TH>&nbsp;</TH>
				<TH>&nbsp;</TH>
			</TR>

<%
        	int size = items.size()==nSize ? nSize-1 : items.size();
			for (int i=0; i<size; ++i) {
				EmailTemplate object = (EmailTemplate) items.get(i);
%>
				<TR>
					<TD valign=top><%=object.txtName%></TD>
					<TD valign=top><%=object.txtSubject%></TD>
					<TD valign=top><A href="adminEditEmailTemplate.do?txtName=<%=object.txtName%>&nStart=<%=nStart%>&nSize=<%=nSize%>">Modifica</A></TD>
					<TD valign=top><A href="javascript:deleteObject('txtName','<%=object.txtName%>','<%=object.txtName%>')">Cancella</A></TD>
				</TR>
<%
			}
%>
		</TABLE>
		
<%
	} else {
%>
		<TABLE class='Data'  cellspacing=1 >
			<TR>
				<TH>List</TH>
			</TR>
			<TR>
<%
				if (nStart == 0) {
%>			
					<TD>Non ci sono modelli disponibili</TD>
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
			if(nStart>0) {
%>
				<TD><A HREF="adminListEmailTemplates.do?nStart=<%=nStart-nSize+1%>&nSize=<%=nSize%>">Prec</A> |</TD>
<%
			} else {
%>
				<TD>Prec |</TD>
<%
			}

			if(items.size()==nSize) { 
%>
				<TD><A HREF="adminListEmailTemplates.do?nStart=<%=nStart+nSize-1%>&nSize=<%=nSize%>">Succ</A></TD>
<%
			} else { 
%>
				<TD>Succ</TD>
<%
			}
%>
		</TR>
	</TABLE>

<TABLE class='Data' cellspacing=1 >
	<TR>
		<TD><A HREF="adminAddEmailTemplate.do?nStart=<%=nStart%>&nSize=<%=nSize%>">Aggiungi</A></TD>
	</TR>
</TABLE>

<SCRIPT LANGUAGE=javascript>
<!--
	function deleteObject(id, idval, value) {
		if(confirm("Sei sicuro di voler cancellare il modello "+value)) {
			location.href = "admin/emailtemplates/Delete.jsp?" + id + "=" + idval + "&nStart=<%=nStart%>&nSize=<%=nSize%>";
		}
	}
//-->
</SCRIPT>
