<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%@ page import='java.util.List, java.util.ArrayList, java.util.Iterator, org.opencommunity.bazaar.catalog.dto.Category, org.opencommunity.bazaar.catalog.business.impl.CategoryManagerDelegate'%>

<%
    List categoriesByProduct = null;
    List allCategories = new CategoryManagerDelegate().getAllCategories();
    if (request.getParameter("nProductID")!= null) {
        categoriesByProduct = new CategoryManagerDelegate().getCategoriesByProduct(Long.parseLong(request.getParameter("nProductID")));
    } else {
    	categoriesByProduct = new ArrayList();
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
	<FORM name='addProduct' action='addProduct.do' ENCTYPE='multipart/form-data' method=POST >
        <input type=hidden name='txtAryCatIds'>
		<font color=red>*</font>Seleziona Categorie...
		<TABLE border=0 class=Data width=100% cellspacing=1 >
			<TR>
				<TD>
					<select style="width:300px"  size=10 name=listCategories multiple>
	<%
						for (Iterator itr=allCategories.iterator(); itr.hasNext();) {
							Category category = (Category) itr.next();
	%>
							<option value='<%=category.getNID()%>'><%=category.getTxtFullName()%>
	<%
						}
	%>
					</select>
				</TD>
				<TD>
					<input type=button value="&gt;" onclick="addCategory()"><br>
					<input type=button value="&lt;" onclick="removeCategory()">
				</TD>
				<TD>
					<select  style="width:300px" size=10 name=selectedListCategories multiple>
	<%
						for (Iterator itr=categoriesByProduct.iterator(); itr.hasNext();) {
							Category category = (Category) itr.next();
	%>
							<option value='<%=category.getNID()%>'><%=category.getTxtFullName()%>
	<%
						}
	%>
					</select>
				</TD>
			</TR>
		</TABLE>

		<TABLE border=0 class=Data width=100% cellspacing=1 >
			<TR>
				<TH colspan=2>Inserisci i dettagli del Prodotto</TH>
			</TR>
			<TR>
				<TD valign="top"><font color=red>*</font>Nome</TD>
				<TD valign="top"><INPUT TYPE=TEXT name='txtName' value=''></TD>
			</TR>
			<TR>
				<TD valign="top"><font color=red>*</font>Descrizione Breve</TD>
				<TD valign="top"><INPUT TYPE=TEXT size=30 name='txtShortDescription' value=''></TD>
			</TR>
			<TR>
				<TD valign="top">Descrizione Dettagliata</TD>
				<TD valign="top">
  				    <TEXTAREA rows=10 cols=52 name='txtDescription' value=''></TEXTAREA>
				</TD>
			</TR>
			<TR>
				<TD valign="top">URL Immagine</TD>
				<TD valign="top"><INPUT TYPE=file size=70 name='txtImgUrl' value=''></TD>
			</TR>
			<TR>
				<TD valign="top"><font color=red>*</font>Quantita'</TD>
				<TD valign="top"><INPUT TYPE=TEXT name='numQuantity' value='1'></TD>
			</TR>
			<TR>
				<TD valign="top"><font color=red>*</font>Prezzo (IVA inclusa)</TD>
				<TD valign="top"><INPUT TYPE=TEXT name='dblPrice' value='0.00'></TD>
			</TR>
			<TR>
				<TD>Disponibile</TD>
				<TD>
					<SELECT name='txtActive'>
						<OPTION value='1' selected>Si</OPTION>
						<OPTION value='0'>No</OPTION>
					</SELECT>
				</TD>
			</TR>
			<TR>
				<TD colspan=2><INPUT TYPE=Submit name='Submit' Value='Conferma' onclick="catsModified()"></TD>
			</TR>
		</TABLE>
	</FORM>
</CENTER>


<SCRIPT LANGUAGE=javascript>
<!--
	function catsModified() {
		var vals
		var i
		var j
		vals = ""
		var sellist = document.addProduct.selectedListCategories
		
		for(i=0;i<sellist.options.length;i++) {
			vals += sellist.options[i].value + ","
		}
		
		if(vals.length>0) {
			vals = vals.substr(0,vals.length-1)
		}
		
		document.addProduct.txtAryCatIds.value = vals
	}

	function addCategory() {
		var i
		var j
		var val
		var bexists = false
		var list = document.addProduct.listCategories
		var sellist = document.addProduct.selectedListCategories
		for(i=0; i< list.options.length;i++) {
			if(list.options[i].selected) {
				val = list.options[i].value
				bexists = false
				for(j=0;j<sellist.options.length;j++) {
					if(sellist.options[j].value==val) {
						bexists = true
						break
					}
				}
				if(!bexists) {
					j = sellist.options.length++;
					sellist.options[j].text = list.options[i].text
					sellist.options[j].value = list.options[i].value
					catsModified()
				}
			}
		}
	}
	
	function removeCategory() {
		var i
		var j
		var sellist = document.addProduct.selectedListCategories
		for(i=0;i<sellist.options.length;i++) {
			if(sellist.options[i].selected) {
				for(j=i;j<sellist.options.length-1;j++)	{
					sellist.options[j] = sellist.options[j+1]
				}
				sellist.options.length--
				catsModified()
			}
		}
	}
//-->
</SCRIPT>
