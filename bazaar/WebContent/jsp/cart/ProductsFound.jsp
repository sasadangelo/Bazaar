<%@ taglib uri="/WEB-INF/taglibs-image.tld" prefix="img" %>

<%@ page import='org.opencommunity.bazaar.utils.business.IDataListHandler'%>
<%@ page import='org.opencommunity.bazaar.utils.business.IDataListIterator'%>
<%@ page import='org.opencommunity.bazaar.utils.business.IDataListChunk'%>
<%@ page import='org.opencommunity.bazaar.catalog.dto.Product'%>

<%
	IDataListHandler pHandler = (IDataListHandler) request.getSession(true).getAttribute("searchList");
	IDataListChunk dataList = pHandler.getListChunk();
%>

<%
	if (pHandler.size()>0) {
%>
		<TABLE class='Data'  cellspacing=1 width=100%>
		    <TR class='Data'>
		    	<TH>
		    	</TH>
		    	<TH>
					Trovato/ii <%=pHandler.size()%> prodotto/i in Bazaar<BR>
		    	</TH>
		    	
		    </TR>
<%
			for (IDataListIterator itr=dataList.iterator(); itr.hasNext();) {
				Product product = (Product) itr.next();
%>
				<TR>
					<TD nowrap>
						<img src="imageProduct.do?nID=<%= product.getNID() %>&bThumbnail=true" alt="">
					</TD>
					<TD nowrap width=90%>
					    <A href="product.do?nID=<%= product.getNID() %>"><STRONG><%= product.getTxtName() %></STRONG></A><BR>
					    <%= product.getTxtShortDescription() %><BR>
					    <STRONG><%= product.getDblPrice() %> EUR</STRONG>
					</TD>
				</TR>
<%
			}
%>
		</TABLE>
	    <TABLE class='Data' cellspacing=1 >
		    <TR>
<% 
			    if (dataList.hasPrev()) { 
%> 
                    <TD><A href="search.do?page=prev">Prec</A> |</TD>
<%
                } else { 
%>
                    <TD>Prec |</TD>
<%
                }

                if (dataList.hasNext()) {
%>	
                    <TD><A HREF="search.do?page=next">Succ</A></TD>
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
	} else {
%>
		<TABLE class='Data' cellspacing=1 width=100%>
		    <TR class='Data'>
		    	<TH>
					Trovati <%=pHandler.size()%> prodotti in Bazaar<BR>
		    	</TH>
		    	
		    </TR>
			<TR>
                <TD align=center>Nessun Prodotto trovato.</TD>
			</TR>
		</TABLE>
<%
	}
%>
