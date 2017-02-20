<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%@ page import='org.opencommunity.bazaar.catalog.dto.*,org.opencommunity.bazaar.catalog.business.impl.*'%>

<%
    Category category = new CategoryManagerDelegate().getCategory(Long.parseLong(request.getParameter("nId")));
%>

<CENTER>
    <font color=red>
        <STRONG>
            <html:messages id="error" message="false">
                <li><bean:write name="error"/><br>
            </html:messages>
        </STRONG>
    </font>
	<FORM name='adminEditCategory' action="adminEditCategory.do" method=POST>
        <INPUT type=hidden name='numId' value="<%=request.getParameter("nId")%>">
<%
        if (request.getParameter("nParentId")!=null) {
%>
            <INPUT type=hidden name='numParent' value="<%=request.getParameter("nParentId")%>">
<%
        }
%>
		<TABLE border=0 class=Data>
			<TR>
				<TH colspan=2>Inserisci i dettagli della Categoria</TH>
			</TR>
			<TR>
				<TD valign="top"><font color=red>*</font> Nome</TD>
				<TD valign="top"><INPUT name='txtName' value="<%=category.getTxtName()%>" size=20></TD>
			</TR>
			<TR>
				<TD valign="top">Descrizione</TD>
				<TD valign="top"><INPUT name='txtDescription' value="<%=category.getTxtDescription()%>" size=30></TD>
			</TR>
			<TR>
				<TD colspan=2><INPUT TYPE=submit name='Submit' Value='Conferma'></TD>
			</TR>
		</TABLE>
	</FORM>
</CENTER>