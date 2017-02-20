<!--
 Copyleft (C) Open Community
 author Salvatore D''Angelo (koala.gnu@tiscali.it)
-->

<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='tiles' %>

<HTML>
    <HEAD>
        <TITLE>Bazaar - Free Open Source Shopping Cart by Open Community</TITLE>
    </HEAD>
    
    <link rel=stylesheet href="style.css" type="text/css">
    
    <BODY>
        <center>
	        <TABLE cellSpacing=1 cellPadding=1 bgColor=#00000ff border=0>
	            <TR>
	                <TD bgColor=#ffffff colSpan=2>
	                    <tiles:insert attribute='header'/>
	                </TD>
	            </TR>
	            <TR>
	                <TD bgColor=#ffffff width=75 vAlign=top>
	                    <tiles:insert attribute='sidebar'/>
	                </TD>
	                <TD bgColor=#ffffff width=600 valign=top>
	                    <tiles:insert attribute='content'/>
	                </TD>
	            </TR>
	            <TR>
	                <TD align=middle bgColor=#ffffff colSpan=2>
	                    <tiles:insert attribute='footer'/>
	                </TD>
	            </TR>
	        </TABLE>
        </center>
    </BODY>
</HTML>
