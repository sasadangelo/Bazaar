<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='tiles' %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<CENTER>
    <font color=blue>
        <STRONG>
            <html:messages id="message" property="<%=org.opencommunity.bazaar.utils.messages.IInformationCodes.INFORMATIONS_ID%>" message="true">
                <bean:write name="message"/><br>
            </html:messages>
        </STRONG>
    </font>
    <font color=red>
        <STRONG>
            <html:messages id="error" message="false">
                <li><bean:write name="error"/><br>
            </html:messages>
        </STRONG>
    </font>

	<FORM name="login" action="login.do" method="POST" >
    	<TABLE class=Data cellspacing=1 >
        	<TR>
            	<TH colspan=2>
                	Entra in Bazaar
            	</TH>
        	</TR>
        	<TR>
            	<TD>Indirizzo Email</TD>
            	<TD><input type=text name="userName" value=""></TD>
        	</TR>
        	<TR>
            	<TD>Password</TD>
            	<TD><input type=password name="password"></TD>
        	</TR>
        	<TR>
            	<TD colspan=2><input type=submit value="Entra" id=submit1 name=submit1></TD>
        	</TR>
		</TABLE>
	</FORM>
	<BR>
   	<FORM name="forgot" action="forgot.do" method="POST">
		<TABLE class=Data cellspacing=1 >
			<TR>
				<TH colspan=2>Hai dimenticato la Password?</TH>
			</TR>
			<TR>
				<TD>Indirizzo Email</TD>
				<TD><input type=text name='txtEmailAddress' value=""></TD>
			</TR>
			<TR>
				<TD colspan=2><input type=submit value="Invia Password" id=submit2 name=submit2></TD>
			</TR>
	    </TABLE>
	</FORM>
	<BR>
	<BR>
	<TABLE class=Data cellspacing=1 >
		<TR>
			<TH>Non ti sei ancora registrato?</TH>
		</TR>
		<TR>
			<TD><A href="goSignup.do">Clicca qui</a> per registrarti con Bazaar</TD>
		</TR>
	</TABLE>
</CENTER>
