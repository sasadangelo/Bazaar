<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%@page import='java.util.List'%>
<%@page import='java.util.Iterator'%>
<%@page import='org.opencommunity.bazaar.catalog.dto.Category'%>
<%@page import='org.opencommunity.bazaar.catalog.dto.Product'%>
<%@page import='org.opencommunity.bazaar.catalog.business.impl.CategoryManagerDelegate'%>
<%@page import='org.opencommunity.bazaar.catalog.business.impl.ProductManagerDelegate'%>

<%
    List categoryHier = null;
    if (request.getParameter("nCategoryID") != null) {
        categoryHier = new CategoryManagerDelegate().getCategoryHierarchy(new Long(request.getParameter("nCategoryID")));
    }
    Product product = new ProductManagerDelegate().getProduct(Long.parseLong(request.getParameter("nID")));
%>

<TABLE class=Data cellspacing=1 width=100%>
	<TR>
        <TD align=left><A href="home.do">Bazaar</A>
<%
            if (categoryHier != null) {
                for (Iterator itr=categoryHier.iterator(); itr.hasNext();) {
                    Category cat = (Category) itr.next();
%>
                    > <A href="home.do?nParentId=<%=cat.getNID() %>"><%=cat.getTxtName() %></A>
<%
                }
            }
%>
        </TD>
	</TR>
</TABLE>
<BR>

<FORM name='cartProduct' action='addCart.do' method=POST >
    <input name=numID type=hidden value='<%= request.getParameter("nID") %>'>
	<TABLE BORDER=0 CELLSPACING=1 CELLPADDING=0 class=Data width=100%>
		<TR>
			<TD valign=top>
				<STRONG><%= product.getTxtName() %></STRONG>
				<BR>
				<%= product.getTxtShortDescription() %>
				<BR>
				<%= product.getTxtDescription() %>
				<BR>
			    <IMG src="imageProduct.do?nID=<%= product.getNID() %>&bThumbnail=false" alt="">
			</TD>
			<TD valign=top>
				Prezzo <STRONG><%= product.getDblPrice() %></STRONG> EUR<BR>
				<BR>
				<input name=submit type=submit class='SmallButton' value='Compra'>
			</TD>
		</TR>
	</TABLE>
</FORM>