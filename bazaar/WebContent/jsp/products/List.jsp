<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%@ page import='org.opencommunity.bazaar.utils.business.*,org.opencommunity.bazaar.catalog.dto.*,org.opencommunity.bazaar.account.dto.*'%>

<%
    IDataListHandler list = (IDataListHandler) request.getSession(true).getAttribute("listProducts");
    IDataListChunk chunk = list.getListChunk();
    Account user = (Account) session.getAttribute("account");
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

Gestione Prodotti - Bazaar
<%
	if (list.size() > 0) {
%>
		<TABLE class='Data'  cellspacing=1 width=100%>
			<TR>
				<TH nowrap>Codice</TH>
				<TH nowrap>Nome Prodotto</TH>
				<TH nowrap>Breve Descrizione</TH>
				<TH nowrap>Disponibile</TH>
				<TH nowrap>Quantita'</TH>
				<TH nowrap>Prezzo</TH>
				<TH nowrap>&nbsp;</TH>
				<TH nowrap>&nbsp;</TH>
			</TR>
<%
			for (IDataListIterator itr = chunk.iterator(); itr.hasNext(); ) {
				Product product = (Product) itr.next();
%>
				<TR>
					<TD nowrap><%=product.getNID()%></TD>
					<TD nowrap><STRONG><%=product.getTxtName()%></STRONG></TD>
					<TD><%=product.getTxtShortDescription()%></TD>
					<TD nowrap valign=top><%=product.isBActive() ? "Si" : "No"%></TD>
					<TD nowrap valign=top><%=product.getNumQuantity()%></TD>
					<TD nowrap valign=top><%=product.getDblPrice()%></TD>
					<TD><A href="goEditProduct.do?nID=<%=product.getNID()%>">Modifica</A></TD>
					<TD><A href="javascript:deleteObject('<%=product.getNID()%>','<%=product.getTxtName()%>')">Cancella</A></TD>
				</TR>
<%
			}
%>
		</TABLE>
        <TABLE class='Data' cellspacing=1 >
           <TR>
<% 
           if (chunk.hasPrev()) { 
%>
               <TD><A HREF="listProducts.do?page=prev">Prec</A> |</TD>
<%
           } else { 
%>
               <TD>Prec |</TD>
<%
           }

           if (chunk.hasNext()) {
%>
               <TD><A HREF="listProducts.do?page=next">Succ</A></TD>
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
		<TABLE class='Data'  cellspacing=1 width=100%>
			<TR>
				<TH>Elenco Prodotti</TH>
			</TR>
			<TR>
				<TD>Non ci sono prodotti disponibili.</TD>
			</TR>
		</TABLE>
<%
	}
%>

	
	<TABLE class='Data' cellspacing=1 >
		<TR>
			<TD><A HREF="goAddProduct.do">Aggiungi nuovo Prodotto</A></TD>
		</TR>
	</TABLE>
<%
    if (user != null && user.isBAdmin()) {
%>
		<BR>
		<BR>
	    <FORM action='admin/products/RigenerateIndex.jsp' method=post name='RigenerateIndex'>
	        <TABLE class='Data'  cellspacing=1 width=100%>
	            <TR>
	                <TH nowrap>Rigenera Indice Prodotti</TH>
	            </TR>
	            <TR>
	                <TD nowrap>
	                    Se si riscontrano problemi nella ricerca dei prodotti, rigenerare gli indici.
	                </TD>
	            </TR>
	            <TR>
	                <TD nowrap align=center>
	                    <INPUT TYPE=Submit name='Rigenera' Value='Rigenera'>
	                </TD>
	            </TR>
	        </TABLE>
	    </FORM>
<%
    }
%>

<SCRIPT LANGUAGE=javascript>
<!--
	function deleteObject(idval, value) {
		if(confirm("Sei sicuro di voler cancellare " + value)) {
			location.href = "deleteProduct.do?nID=" + idval;
		}
	}
//-->
</SCRIPT>
