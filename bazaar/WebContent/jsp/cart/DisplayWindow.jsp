<%@ taglib uri="/WEB-INF/taglibs-image.tld" prefix="img" %>

<%@page import='nbp.jspcart.*'%>

<jsp:useBean id="website" scope="application" class="nbp.jspcart.WebSite">
    <%website.init(application.getRealPath("../../"));%>
</jsp:useBean>

<%
	nStart=0;
	int nSize=7;
    String txtRedirect=org.nspeech.web.UrlEncoderEx.encode("/adminListProducts.do?nStart=" + nStart);
	Vector prods = website.products.getAllShow(nStart,nSize);
%>

<TABLE class=Data width=100% valign=top>
	<TR>
		<TH align=center>
			<FONT size=1>Offerte</FONT>
		</TH>
	</TR>
    <TR>
        <TD>
<%
			if(prods.size()>0) {
%>
				<TABLE class='Data' align=center>
                    <TR>
<%
		        	    int size = prods.size()==nSize ? nSize-1 : prods.size();
		        	
                        for (int i=0; i<size; ++i) {
                            Product object = (Product) prods.get(i);
%>
							<TD>
							    <TABLE>
							        <TR>
                                        <TD valign=top align=center>
                                            <img:image src="<%=object.txtImgUrl%>" name="<%=object.txtImgUrl%>" dir="thumbnails">
                                                <img:resize height="60"/>
                                            </img:image>
                                        </TD>
							        </TR>
							        <TR>
                                        <TD valign=bottom align=center>
			                                <A href="product.do?nID=<%=object.nID%>"><STRONG><%=object.txtName%></STRONG></A>
                                        </TD>
							        </TR>
							    </TABLE>
                            </TD>
<%
                        }
%>
                    </TR>
				</TABLE>
<%
			} else {
%>
				<TABLE class='Data'  cellspacing=1>
					<TR>
<%
		                if (nStart==0) {
%>
							<TD>Non ci sono prodotti in offerta.</TD>
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

			if (prods.size()==nSize) {
%>
				<TABLE class='Data' cellspacing=1 width=100%>
					<TR>
						<TD align=center>
						    <A HREF="displayWindow.do">Altri Prodotti in Offerta</A>
						</TD>
					</TR>
				</TABLE>
<%
			}
%>
		</TD>
	</TR>
</TABLE>
