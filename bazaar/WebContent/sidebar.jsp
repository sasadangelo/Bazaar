<%@page import='org.opencommunity.bazaar.account.dto.Account'%>

<%
    String txtSearch = request.getParameter("txtSearch") == null ? "" : request.getParameter("txtSearch");
    Account sidebarUser = (Account) session.getAttribute("account");
%>

<TABLE>
	<TR>
		<TD>
<%

            // if user is not logged
			if (sidebarUser == null) {
%>
				<A href="goLogin.do">Entra</A><BR>
				<A href="goCart.do">Carrello</A><BR>
                <BR>
                <BR>
                <FORM name="search" action="search.do" method="POST">
                    <TABLE class=Data>
                        <TH align=left>
                            Cerca
                        </TH>
                        <TR>
                            <TD>
                                <INPUT type=text size=10 name="txtSearch" value="<%=txtSearch%>">
                            </TD>
                        </TR>
                        <TR>
                            <TD>
                                <INPUT type=submit name="SearchProduct" Value="Cerca" >
                            </TD>
                        </TR>
                    </TABLE>
                </FORM>
				
<%
			} else if (sidebarUser.isBAdmin()) {
%>
				<A href="goProfile.do">Profilo</A><BR>
				<A href="adminListUsers.do">Utenti</A><BR>
				<A href="adminListCategories.do">Categorie</A><BR>
				<A href="listProducts.do">Inserzioni</A><BR>
				<A href="goDefinePayment.do">Pagamenti</A><BR>
<%
			} else {
%>
				<A href="goProfile.do">Profilo</A><BR>
				<A href="goCart.do">Carrello</A><BR>
				<A href="listProducts.do">Inserzioni</A><BR>
				<A href="goDefinePayment.do">Pagamenti</A><BR>
                <BR>
                <BR>
                <FORM name="search" action="search.do" method="POST">
                    <TABLE class=Data>
                        <TH align=left>Cerca</TH>
                        <TR>
                            <TD>
                                <INPUT type=text size=10 name="txtSearch" value="<%=txtSearch%>">
                            </TD>
                        </TR>
                        <TR>
                            <TD>
                                <INPUT type=submit name="SearchProduct" value="Cerca" >
                            </TD>
                        </TR>
                    </TABLE>
                </FORM>
<%
			}
%>
		</TD>
	</TR>
</TABLE>
