<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%@ page import='java.util.*,org.opencommunity.bazaar.utils.business.*,org.opencommunity.bazaar.catalog.dto.*,org.opencommunity.bazaar.catalog.business.impl.*'%>

<%

    List categoryHier = null;
    Category parentCategory = null;

    IDataListHandler list = (IDataListHandler) request.getSession(true).getAttribute("listCategories");
    IDataListChunk chunk = list.getListChunk();

    if (request.getParameter("nParentId")==null) {
        categoryHier = new CategoryManagerDelegate().getCategoryHierarchy(null);
    } else {
        categoryHier = new CategoryManagerDelegate().getCategoryHierarchy(new Long(Long.parseLong(request.getParameter("nParentId"))));
    }
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

Gestione Categorie - Bazaar
						
<TABLE class=Data cellspacing=1>
	<TR>
		<TD>
		    <A href="adminListCategories.do">Bazaar</A>
<%
            for (Iterator itr=categoryHier.iterator(); itr.hasNext();) {
                Category category = (Category) itr.next();
                parentCategory = category; 
%>
                > <A href="adminListCategories.do?nParentId=<%=category.getNID() %>"><%=category.getTxtName() %></A>
                
<%
            }
%>
		</TD>
	</TR>
</TABLE>
					
<%
	if (list.size() > 0) {
%>
		<TABLE class='Data' width=100%>
			<TR>
				<TH valign="top" colspan=5>Categorie in <%=(parentCategory==null) ? "+" : parentCategory.getTxtName()%></TH>
			</TR>
			<TR>
				<TH valign="top">Nome</TH>
				<TH valign="top">&nbsp;</TH>
				<TH valign="top">&nbsp;</TH>
			</TR>
<%
			for (IDataListIterator itr = chunk.iterator(); itr.hasNext(); ) {
				Category category = (Category) itr.next();
%>
			    <TR>
				    <TD valign="top"><A href="adminListCategories.do?nParentId=<%=category.getNID()%>"><%=category.getTxtName()%></A></TD>
				    <TD valign="top"><A href="goAdminEditCategory.do?nId=<%=category.getNID()%><%=request.getParameter("nParentId")==null ? "" : "&nParentId=" + request.getParameter("nParentId") %>">Modifica</A></TD>
				    <TD valign="top"><A href="javascript:deleteObject('<%=category.getNID()%>','<%=request.getParameter("nParentId")%>', '<%=category.getTxtName()%>')">Cancella</A></TD>
			    </TR>
<%
			}
%>
		</TABLE>
<%
	} else {
%>
		<TABLE class='Data'  width=100%>
			<TR>
				<TH valign="top" colspan=5>Categorie in <%=(parentCategory==null) ? "+" : parentCategory.getTxtName()%></TH>
			</TR>
			<TR>
				<TD>Non ci sono Categorie all'interno di questa Categoria</TD>
			</TR>
		</TABLE>
<%
	}
%>
	<TABLE class='Data'>
		<TR>
<% 
			if (chunk.hasPrev()) {
%>
				<TD><A HREF="adminListCategories.do?page=prev">Prec</A> |</TD>
<%
			} else { 
%>
				<TD>Prec |</TD>
<%
			}

 			if (chunk.hasNext()) {
%>
				<TD><A HREF="adminListCategories.do?page=next">Succ</A></TD>
<%
			} else {
%>
				<TD>Succ</TD>
<%
			}
%>
		</TR>
	</TABLE>
						
	<TABLE class='Data'>
		<TR>
            <TD><A HREF="goAdminAddCategory.do<%=request.getParameter("nParentId")==null ? "" : "?nParentId=" + request.getParameter("nParentId") %>">Aggiungi nuova Categoria</A></TD>
		</TR>
	</TABLE>
						
<SCRIPT LANGUAGE=javascript>
<!--
	function deleteObject(nCategoryID, nParentId, txtCategoryName) {
		if(confirm("Sei sicuro di voler cancellare la Categoria " + txtCategoryName + "?")) {
		    if(nParentId==null || nParentId=="null") {
		        location.href = "adminDeleteCategory.do?id="+nCategoryID;
		    } else {
			    location.href = "adminDeleteCategory.do?id="+nCategoryID+"&nParentId="+nParentId;
		    }
		}
	}
	
//-->
</SCRIPT>
