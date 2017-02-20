<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<CENTER>
    <font color=red>
        <STRONG>
            <html:messages id="error" message="false">
                <li><bean:write name="error"/><br>
            </html:messages>
        </STRONG>
    </font>
    <font color=blue>
        <STRONG>
            <html:messages id="message" property="<%=org.opencommunity.bazaar.utils.messages.IInformationCodes.INFORMATIONS_ID%>" message="true">
                <bean:write name="message"/><br>
            </html:messages>
        </STRONG>
    </font>
	<TABLE class=Data width=300 cellspacing=1 >
		<TR>
			<TH colspan=2>
		  		Profilo - Bazaar
			</TH>
		</TR>
		<TR>
			<TD><A href="goChangePasswd.do">Cambia Password</A></TD>
			<TD><A href="goChangeProfile.do">Cambia Profilo</A></TD>
		</TR>
		<TR>
			<TD><A href="listProducts.do">Inserzioni</A></TD>
			<TD><A href="logout.do">Esci</A></TD>
		</TR>
	</TABLE>
</CENTER>
