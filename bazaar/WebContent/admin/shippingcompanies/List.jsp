<%@ page import='java.util.*,nbp.jspcart.shipping.*,nbp.jspcart.users.*'%>

<jsp:useBean id="website" scope="application" class="nbp.jspcart.WebSite">
	<%website.init(application.getRealPath("../../"));%>
</jsp:useBean>

<%
	int nStart = website.getRequestParameter(request,"nStart",0);
	int nSize  = website.getRequestParameter(request,"nSize",11);
	
	Vector items = website.shippingcompanies.getAll(nStart,nSize);
%>

Gestione Compagnie Postali - <%=website.shopName%><BR>

<%
	if(items.size()>0) {
%>

		<TABLE class='Data' width=100% cellspacing=1 >
			<TR>
				<TH>ID</TH>
				<TH>Nome</TH>
				<TH>URL</TH>
				<TH>Tracking</TH>
				<TH>&nbsp;</TH>
				<TH>&nbsp;</TH>
		
			</TR>
<%
        	int size = items.size()==nSize ? nSize-1 : items.size();
			for (int i=0; i<size; ++i) {
				ShippingCompany object = (ShippingCompany) items.get(i);
%>
			<TR>
					<TD><%=object.nID%></TD>
					<TD><%=object.txtName%></TD>
					<TD><a href="<%=object.txtURL%>" target="_blank"><%=object.txtURL%></a></TD>
					<TD><a href="<%=object.txtTrackingURL%>" target="_blank"><%=object.txtTrackingURL%></a></TD>
					<TD><A href="adminEditShipping.do?nID=<%=object.nID%>&nStart=<%=nStart%>&nSize=<%=nSize%>">Modifica</A></TD>
					<TD><A href="javascript:deleteObject('nID','<%=object.nID%>','<%=object.nID%>')">Cancella</A></TD>
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
				<TH>Compagnie Postali</TH>
			</TR>
			<TR>
<%
				if (nStart == 0) {
%>			
					<TD>Non ci sono compagnie postali disponibili</TD>
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
				<TD><A HREF="adminListShipping.do?nStart=<%=nStart-nSize+1%>&nSize=<%=nSize%>">Prec</A> |</TD>
<%
			} else {
%>
				<TD>Prec |</TD>
<%
			}

			if(items.size()==nSize) {
%>
				<TD><A HREF="adminListShipping.do?nStart=<%=nStart+nSize-1%>&nSize=<%=nSize%>">Succ</A></TD>
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
			<TD><A HREF="adminAddShipping.do?nStart=<%=nStart%>&nSize=<%=nSize%>">Aggiungi</A></TD>
		</TR>
	</TABLE>

<SCRIPT LANGUAGE=javascript>
<!--
	function deleteObject(id,idval,value) {
		if(confirm("Are you sure you want to delete "+value)) {
			location.href = "admin/shippingcompanies/Delete.jsp?" + id + "=" + idval + "&nStart=<%=nStart%>&nSize=<%=nSize%>";
		}
	}
//-->
</SCRIPT>
