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
	<FORM name="changePasswd" action="changePasswd.do" method="POST">
		<TABLE class=Data width=300 cellspacing=1 >
			<TR>
				<TH colspan=2>Cambia la tua password</TH>
			</TR>
			<TR>
				<TD valign=top><font color=red>*</font>Vecchia Password</TD>
				<TD valign=top><input type=password name='txtOldPassword'></TD>
			</TR>
			<TR>
				<TD valign=top><font color=red>*</font>Nuova Password</TD>
				<TD valign=top><input type=password name='txtNewPassword'></TD>
			</TR>
			<TR>
				<TD valign=top><font color=red>*</font>Conferma Password</TD>
				<TD valign=top><input type=password name='txtConfirmPassword'></TD>
			</TR>
			<TR>
				<TD><input type=submit value='Cambia'></TD>
				<TD></TD>
			</TR>
		</TABLE>
	</FORM>
</CENTER>
