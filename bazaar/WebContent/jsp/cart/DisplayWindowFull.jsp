<%@ taglib uri="/WEB-INF/taglibs-image.tld" prefix="img" %>

<%@ page import='java.util.*'%>
<%@ page import='nbp.jspcart.products.*'%>

<jsp:useBean id="website" scope="application" class="nbp.jspcart.WebSite">
    <%website.init(application.getRealPath("./"));%>
</jsp:useBean>

<%
	Vector prods = website.products.getAllShow();
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
				<CENTER>
				<TABLE class='Data'>
<%
					int i = 0;
					int len = (prods.size()/2)*2;

					while(i<len) {
						Product object = (Product) prods.get(i++);
%>
						<TR>
							<TD nowrap valign="top">
							    <TABLE>
							    	<TR>
							    		<TD>
											<img:image src="<%=object.txtImgUrl%>" name="<%=object.txtImgUrl%>" dir="thumbnails">
												<img:resize width="60" />
											</img:image>
							    		</TD>
							    		<TD>
											<A href="product.do?nID=<%=object.nID%>"><STRONG><%=object.txtName%></STRONG></A><BR>
											<%=object.txtDescription%>
							    		</TD>
							    	</TR>
							    </TABLE>
							</TD>
<%
							object = (Product) prods.get(i++);
%>
							<TD nowrap valign="top">
							    <TABLE>
							    	<TR>
							    		<TD>
											<img:image src="<%=object.txtImgUrl%>" name="<%=object.txtImgUrl%>" dir="thumbnails">
												<img:resize width="60" />
											</img:image>
							    		</TD>
							    		<TD>
											<A href="product.do?nID=<%=object.nID%>"><STRONG><%=object.txtName%></STRONG></A><BR>
											<%=object.txtDescription%>
							    		</TD>
							    	</TR>
							    </TABLE>
							</TD>
						</TR>
<%
					}

					if(i<prods.size()) {
						Product object = (Product) prods.get(i++);
%>
						<TR>
							<TD nowrap valign="top">
							    <TABLE>
							    	<TR>
							    		<TD>
											<img:image src="<%=object.txtImgUrl%>" name="<%=object.txtImgUrl%>" dir="thumbnails">
												<img:resize width="60" />
											</img:image>
							    		</TD>
							    		<TD>
											<A href="product.do?nID=<%=object.nID%>"><STRONG><%=object.txtName%></STRONG></A><BR>
											<%=object.txtDescription%>
							    		</TD>
							    	</TR>
							    </TABLE>
							</TD>
							<TD nowrap valign="top">
							</TD>
						</TR>
<%
					}
%>
				</TABLE>
				</CENTER>
<%
			} else {
%>
				<TABLE class='Data'  cellspacing=1>
					<TR>
						<TD>Non ci sono prodotti in vetrina.</TD>
					</TR>
				</TABLE>
<%
			}
%>
		</TD>
	</TR>
</TABLE>
