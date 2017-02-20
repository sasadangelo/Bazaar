<%@ page import='java.util.*,nbp.jspcart.categories.*,nbp.jspcart.products.*,nbp.jspcart.users.*'%>

<jsp:useBean id="website" scope="application" class="nbp.jspcart.WebSite">
    <%
        website.init(application.getRealPath("../../"));
    %>
</jsp:useBean>

<%
	int nStart = website.getRequestParameter(request,"nStart",0);
	int nSize  = website.getRequestParameter(request,"nSize",11);

	int nCategoryID = website.getRequestParameter(request,"nCategoryID",0);	
	Category category = website.categories.get(nCategoryID);
	
	String txtSearch = request.getParameter("txtSearch");
	
	if(txtSearch==null) {
		txtSearch = "%";
	}
		
	if(request.getMethod().equalsIgnoreCase("POST")) {
		String ids [] = request.getParameterValues("nID");
		if(ids!=null) {
			for(int i=0;i<ids.length;i++) {
				int nID = Integer.parseInt(ids[i]);
				Product product = website.products.get(nID);
				website.categories.addProduct(category,product);
			}
		}

		response.sendRedirect(request.getContextPath() + "/adminAddProductCategory.do?nCategoryID=" + String.valueOf(nCategoryID));
		return;
	}
	
	Vector items = website.products.getAll(txtSearch, category.getProductIDs(), nStart, nSize);
	
	Enumeration enum2 = items.elements();
%>

Aggiungi Prodotto nella Categoria "<%=category.txtName%>"<BR>
<TABLE class=Data cellspacing=1>
	<TR>
		<TD>
		    <A href="adminListCategories.do">
                <%=website.shopName%>
		    </A> > 
			<%=category.getHierarchy(" > ","","adminListCategories.do")%>
		</TD>
	</TR>
</TABLE>
					
<%
	if(items.size()>0) {
%>
		<form action='admin/categories/AddProduct.jsp' method='POST' name='PList'>
			<input type=hidden name='nCategoryID' value='<%=nCategoryID%>'>
			<input type=hidden name='nStart' value='<%=nStart%>'>
			<input type=hidden name='nSize' value='<%=nSize%>'>
			<input type=hidden name='txtSearch' value='<%=txtSearch%>'>
			<TABLE class='Data'  cellspacing=1 width=100%>
				<TR>
					<TH nowrap>ID</TH>
					<TH nowrap>Prodotto</TH>
					<TH nowrap>Attivo</TH>
					<TH nowrap>Prezzo</TH>
					<TH nowrap>Prezzo (Mercato)</TH>
					<TH nowrap>Spese Spedizione</TH>
				</TR>
<%
	        	int size = items.size()==nSize ? nSize-1 : items.size();
				for (int i=0; i<size; ++i) {
					Product object = (Product) items.get(i);
%>
					<TR>
						<TD nowrap valign=top><input type=checkbox name='nID' value='<%= object.nID%>'></TD>
						<TD nowrap>	<STRONG><%=object.txtName%></STRONG><BR><%=object.txtDescription%><%=(int)object.dblQtyPerPack%></TD>
						<TD nowrap valign=top><%=object.bActive==0 ? "No" : "Si"%></TD>
						<TD nowrap valign=top><%=object.dblPrice%></TD>
						<TD nowrap valign=top><%=object.dblDummyPrice%></TD>
						<TD nowrap valign=top><%=object.dblShippingFactor%></TD>
					</TR>
<%
				}
%>
				<TR>
					<TD colspan=6>
						<input type=submit value='Aggiungi'>
					</TD>
				</TR>
			</TABLE>
		</form> 						

		<TABLE class='Data' cellspacing=1 >
			<TR>
<% 
				if(nStart>0) {
%>
					<TD><A HREF="adminAddProductCategory.do?nCategoryID=<%=nCategoryID%>&nStart=<%=nStart-nSize+1%>">Prec</A> |</TD>
<%
				} else {
%>
					<TD>Prec |</TD>
<%
				}

				if(items.size()==nSize) { 
%>
					<TD><A HREF="adminAddProductCategory.do?nCategoryID=<%=nCategoryID%>&nStart=<%=nStart+nSize-1%>">Succ</A></TD>
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
		<TABLE class='Data'  cellspacing=1 >
			<TR>
				<TH>Elenco Prodotti</TH>
			</TR>
			<TR>
				<TD>Prodotti inesistenti, vai nella sezione Prodotti e aggiungi nuovi Prodotti</TD>
			</TR>
		</TABLE>
<%
	}
%>

<SCRIPT LANGUAGE=javascript>
<!--
	function deleteObject(id,idval,value) {
		if(confirm("Sei sicuro di voler cancellare " + value))	{
			location.href = "admin/categories/Delete.jsp?" + id + "=" + idval;
		}
	}
//-->
</SCRIPT>
