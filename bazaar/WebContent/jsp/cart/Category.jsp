<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%@ page import='java.util.List'%>
<%@ page import='java.util.Iterator'%>
<%@ page import='org.opencommunity.bazaar.utils.business.*'%>
<%@ page import='org.opencommunity.bazaar.catalog.dto.Category'%>
<%@ page import='org.opencommunity.bazaar.catalog.dto.Product'%>

<%
    List categories = (List) request.getSession(true).getAttribute("categories");
    List categoryHier = (List) request.getSession(true).getAttribute("categoryHier");;
    Category parent = (Category) request.getSession(true).getAttribute("parentCategory");;
    IDataListHandler products = (IDataListHandler) request.getSession(true).getAttribute("catalogProducts");
    IDataListChunk chunk = null;    
    
    if (products != null) {
        chunk = products.getListChunk();    
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

<TABLE class=Data width=100%>
	<TR>
		<TH align=left>
			<FONT size=1>Naviga fra le categorie</FONT>
		</TH>
	</TR>
    <TR>
        <TD>
			<TABLE class=Data cellspacing=1 width=100%>
				<TR>
					<TD align=left><A href="home.do">Bazaar</A>
<%
                    if (categoryHier != null) {
                        for (Iterator itr=categoryHier.iterator(); itr.hasNext();) {
                            Category category = (Category) itr.next();
%>
                            > <A href="home.do?nParentId=<%=category.getNID() %>"><%=category.getTxtName() %></A>
<%
                        }
                    }
%>
					</TD>
				</TR>
			</TABLE>
			<BR>

			<TABLE class='Data' width=100%>
				<TR>
					<TH valign="top" colspan=2>Categorie in  <%=((parent==null) ? ("Bazaar") : (parent.getTxtName())) %></TH>
				</TR>
<%
			    if (categories != null && categories.size()>0) {
%>
<%
					int i = 0;
					int len = (categories.size()/2)*2;
				
					while (i<len) {
						Category category = (Category) categories.get(i++);
%>
						<TR>
							<TD width=50% valign="top"><A href="home.do?nParentId=<%=category.getNID()%>"><%=category.getTxtName()%></A></TD>
<%
                            category = (Category) categories.get(i++);
%>
							<TD width=50% valign="top"><A href="home.do?nParentId=<%=category.getNID()%>"><%=category.getTxtName()%></A></TD>
						</TR>
<%
					}
				
					if (i<categories.size()) {
						Category category = (Category) categories.get(i);
%>
						<TR>
							<TD colspan=2 valign="top"><A href="home.do?nParentId=<%=category.getNID()%>"><%=category.getTxtName()%></A></TD>
						</TR>
<%
					}
			    } else {
%>
					<TR>
						<TD colspan=2 valign="top">
                            Non ci sono categorie in <%=((parent==null) ? ("Bazaar") : (parent.getTxtName())) %>
						</TD>
					</TR>
<%
			    }
%>
			</TABLE>
			<BR>						
			<BR>
<%
			if (parent != null) {
%>
				<TABLE cellspacing=1 cellpadding=2 class=Data width=100%>
					<TR>
						<TH> Prodotto </TH>
						<TH> Descrizione </TH>
						<TH> Prezzo </TH>
					</TR>
<%
				    if (products.size() > 0) {
						for (IDataListIterator itr = chunk.iterator(); itr.hasNext();) {
							Product product = (Product) itr.next();
%>
							<TR>
								<TD valign=top><a href="product.do?nID=<%= product.getNID() %>&nCategoryID=<%= parent.getNID() %>"><STRONG><%= product.getTxtName() %></STRONG></A><BR><%= product.getTxtDescription() %></TD>
								<TD valign=top align=right><%= product.getTxtShortDescription() %></TD>
								<TD valign=top align=right><STRONG><%= product.getDblPrice() %> EUR</STRONG></TD>
							</TR>
<%
						 }
                     } else {
%>
							<TR>
								<TD valign=top>
								    Non ci sono prodotti in <%=((parent==null) ? ("Bazaar") : (parent.getTxtName())) %>
								</TD>
							</TR>
<%
                     }
%>
				</TABLE>
<%
                if (products.size() > 0) {
%>
				    <BR>
                    <TABLE class='Data' cellspacing=1 >
		                <TR>
<% 
		                    if (chunk.hasPrev()) { 
%>
			                    <TD><A HREF="home.do?nParentId=<%= parent.getNID() %>&page=prev">Prec</A> |</TD>
<%
                            } else { 
%>
                                <TD>Prec |</TD>
<%
		                    }
                            if (chunk.hasNext()) {
%>
			                    <TD><A HREF="home.do?nParentId=<%= parent.getNID() %>&page=next">Succ</A></TD>
<%
		                    } else { 
%>
			                    <TD>Succ</TD>
<%
		                    }
%>
		                </TR>
                    </TABLE>
<%
                }
            }
%>
			<BR>						
			<BR>
		</TD>
	</TR>
</TABLE>
