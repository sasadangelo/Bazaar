<%@ page import='java.util.*,nbp.jspcart.variables.*,nbp.jspcart.users.*'%>

<jsp:useBean id="website" scope="application" class="nbp.jspcart.WebSite">
	<%website.init(application.getRealPath("../../"));%>
</jsp:useBean>

<%
	int nStart = website.getRequestParameter(request,"nStart",0);
	int nSize  = website.getRequestParameter(request,"nSize",11);
	
	Vector items = website.variables.getAll(nStart,nSize);
%>

Gestione Variabili - <%=website.shopName%><BR>

<%
	if(items.size()>0) {
%>
		<TABLE class='Data' width=100% cellspacing=1 >
			<TR>
				<TH>Nome</TH>
				<TH>Valore</TH>
				<TH>&nbsp;</TH>
				<TH>&nbsp;</TH>
		
			</TR>
<%
        	int size = items.size()==nSize ? nSize-1 : items.size();
			for (int i=0; i<size; ++i) {
				Variable object = (Variable) items.get(i);
%>
				<TR>
						<TD><%=object.txtName%></TD>
						<TD><%=object.txtValue%></TD>
					<TD><A href="adminEditVariables.do?txtName=<%=object.txtName%>&nStart=<%=nStart%>&nSize=<%=nSize%>">Modifica</A></TD>
					<TD><A href="javascript:deleteObject('txtName','<%=object.txtName%>','<%=object.txtName%>')">Cancella</A></TD>
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
				<TH>Dettagli</TH>
			</TR>
			<TR>
<%
				if (nStart == 0) {
%>			
					<TD>Non ci sono variabili definite nel sistema</TD>
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
				<TD><A HREF="adminListVariables.do?nStart=<%=nStart-nSize+1%>&nSize=<%=nSize%>">Prec</A> |</TD>
<%
			} else {
%>
				<TD>Prec |</TD>
<%
			}

			if(items.size()==nSize) { 
%>
				<TD><A HREF="adminListVariables.do?nStart=<%=nStart+nSize-1%>&nSize=<%=nSize%>">Succ</A></TD>
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
		<TD><A HREF="adminAddVariables.do?nStart=<%=nStart%>&nSize=<%=nSize%>">Aggiungi</A></TD>
	</TR>
</TABLE>

<SCRIPT LANGUAGE=javascript>
<!--
	function deleteObject(id,idval,value) {
		if(confirm("Sei sicuro di voler cancellare "+value)) {
			location.href = "admin/variables/Delete.jsp?" + id + "=" + idval + "&nStart=<%=nStart%>&nSize=<%=nSize%>";
		}
	}
//-->
</SCRIPT>
