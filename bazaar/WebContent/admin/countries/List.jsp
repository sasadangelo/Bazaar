<%@ page import='java.util.*,nbp.jspcart.countries.*,nbp.jspcart.users.*'%>

<jsp:useBean id="website" scope="application" class="nbp.jspcart.WebSite">
	<%website.init(application.getRealPath("../../"));%>
</jsp:useBean>

<%
	int nStart = website.getRequestParameter(request,"nStart",0);
	int nSize  = website.getRequestParameter(request,"nSize",11);
	
	Vector items = website.countries.getAll(nStart,nSize);
%>

Gestione Nazioni - <%=website.shopName%><BR>

<%
	if(items.size()>0) {
%>
		<TABLE class='Data' cellspacing=1  width=100%>
			<TR>
				<TH>ID</TH>
				<TH>Nazione</TH>
				<TH>Tariffa Spedizione Standard</TH>
				<TH>Tariffa Spedizione Express</TH>
				<TH>&nbsp;</TH>
				<TH>&nbsp;</TH>
			</TR>
<%
        	int size = items.size()==nSize ? nSize-1 : items.size();
			for (int i=0; i<size; ++i) {
				Country object = (Country) items.get(i);
%>
				<TR>
						<TD><%=object.txtID%></TD>
						<TD><%=object.txtName%></TD>
						<TD><%=object.dblRegisteredPost%></TD>
						<TD><%=object.dblExpressCourier%></TD>
					<TD><A href="adminEditCountries.do?txtID=<%=object.txtID%>&nStart=<%=nStart%>&nSize=<%=nSize%>">Modifica</A></TD>
					<TD><A href="javascript:deleteObject('txtID','<%=object.txtID%>','<%=object.txtID%>')">Cancella</A></TD>
			
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
					<TD>Non ci sono Nazioni registrate nel sistema.</TD>
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
			<TD><A HREF="adminListCountries.do?nStart=<%=nStart-nSize+1%>&nSize=<%=nSize%>">Prec</A> |</TD>
<%
		} else {
%>
			<TD>Prec |</TD>
<%
		}

		if(items.size()==nSize) {
%>
			<TD><A HREF="adminListCountries.do?nStart=<%=nStart+nSize-1%>&nSize=<%=nSize%>">Succ</A></TD>
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
		<TD><A HREF="adminAddCountries.do?nStart=<%=nStart%>&nSize=<%=nSize%>">Aggiungi</A></TD>
	</TR>
</TABLE>

<SCRIPT LANGUAGE=javascript>
<!--
	function deleteObject(id,idval,value) {
		if(confirm("Sei sicuro di voler cancellare "+value)) 	{
			location.href = "admin/countries/Delete.jsp?" + id + "=" + idval + "&nStart=<%=nStart%>&nSize=<%=nSize%>";
		}
	}
//-->
</SCRIPT>
