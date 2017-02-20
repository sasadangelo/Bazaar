<%@page import='org.opencommunity.bazaar.account.dto.Account'%>

<%
	Account headerUser = (Account) session.getAttribute("account");
%>

<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
    <TR>
        <TD>
            <FONT color=blue size=5>Bazaar</FONT>
        </TD>
        <TD>&nbsp;&nbsp; 
            <TABLE cellSpacing=0 cellPadding=0 align=right border=0>
                <TR>
<%
					if (headerUser != null && headerUser.isBAdmin()) {
%>
	                    <TD align=right><A href="home.do">Home</A>&nbsp;&nbsp;</TD>
<%
					} else {
%>
	                    <TD align=right><A href="home.do">Home</A>&nbsp;&nbsp;</TD>
        	            <TD align=right><A href="about.do">Info</A>&nbsp;&nbsp;</TD>
            	        <TD align=right><A href="contact.do">Contatti</A>&nbsp;&nbsp;</TD>
<%
					}
%>
                </TR>
            </TABLE>
        </TD>
    </TR>
    <TR>
    	<TD>
<%
			if (headerUser != null) {
%>
				<B>Benvenuto <%= headerUser.getTxtFirstname() %> <%= headerUser.getTxtLastname() %></B>
<%
				if (headerUser.isBAdmin()) {
%>
					<B>&nbsp;(Amministratore)</B>
<%
				}
			}
%>
		</TD>
		<TD></TD>
    </TR>
</TABLE>
