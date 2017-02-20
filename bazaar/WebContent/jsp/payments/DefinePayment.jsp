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
	<FORM name="definePayment" action="definePayment.do" method=POST>
	    <TABLE cellspacing=1 cellpadding=2 class=data>
			<TR>
				<TD valign=top align=right>
				    Bonifico:
				</TD>
				<TD valign=top>
				    <INPUT type=checkbox name="bonifico" checked value="" OnClick="Change_Bonifico(this.form);">
				</TD>
				<TD valign=top align=right>
				    <TABLE cellspacing=1 cellpadding=2 class=data>
				        <TR>
							<TD valign=top width=10%>
							</TD>
							<TD valign=top width=90%>
							    Desideri inviare i dati del tuo conto corrente in automatico al cliente che effettua un ordine?
							</TD>
					    </TR>
				        <TR>
							<TD valign=top width=10%>
							</TD>
							<TD valign=top width=90%>
							    <INPUT type=checkbox name="bonificoSend" value="" OnClick="Change_BonificoSend(this.form);">
							</TD>
					    </TR>
				        <TR>
				            <TD valign=top align=right width=13%>
					            <font color=red>*</font>C/C:
					        </TD>
				            <TD valign=top align=left width=87%>
                                <INPUT type=textfield disabled size=16 name="numCc" value="">
					        </TD>
					    </TR>
				        <TR>
				            <TD valign=top align=right width=13%>
					            <font color=red>*</font>ABI:
					        </TD>
				            <TD valign=top align=left width=87%>
                                <INPUT type=textfield disabled size=5 name="numAbi" value="">
					        </TD>
					    </TR>
				        <TR>
				            <TD valign=top align=right width=13%>
					            <font color=red>*</font>CAB:<BR>
					        </TD>
				            <TD valign=top align=left width=87%>
                                <INPUT type=textfield disabled size=5 name="numCab" value="">
					        </TD>
					    </TR>
				        <TR>
				            <TD valign=top align=right width=13%>
					            <font color=red>*</font>CIN:
					        </TD>
				            <TD valign=top align=left width=87%>
                                <INPUT type=textfield disabled size=1 name="txtCin" value="">
					        </TD>
					    </TR>
				    </TABLE>
				</TD>
			</TR>
			<TR>
				<TD valign=top align=right>
				    Paypal:
				</TD>
				<TD valign=top>
				    <INPUT type=checkbox name="paypal" value="" OnClick="Change_Paypal(this.form);">
				</TD>
                <TD>
				    <TABLE cellspacing=1 cellpadding=2 class=data>
				        <TR>
				            <TD valign=top align=right width=16%>
					            <font color=red>*</font>Email:
					        </TD>
				            <TD valign=top align=left width=84%>
                                <INPUT type=textfield disabled size=50 name="txtPaypalEmailAddress" value="">
					        </TD>
					    </TR>
				    </TABLE>
				</TD>
			</TR>
			<TR>
				<TD valign=top align=right>
				</TD>
				<TD valign=top align=right>
				</TD>
                <TD>
				    <TABLE cellspacing=1 cellpadding=2 class=data>
				        <TR>
				            <TD valign=top align=right width=42%>
					        </TD>
							<TD valign=top align=left width=58%>
			                    <INPUT name=submit type=submit value='Conferma'>
							</TD>
					    </TR>
				    </TABLE>
				</TD>
			</TR>
	    </TABLE>
	</FORM>
</CENTER>

<SCRIPT TYPE="text/javascript">
<!--
function Change_Bonifico(form) {
    if (form.bonifico.checked) {
        form.bonificoSend.disabled = false;
        if (form.bonificosend.checked) {
	        form.numCc.disabled = false;
	        form.numAbi.disabled = false;
	        form.numCab.disabled = false;
	        form.txtCin.disabled = false;
        }
    } else {
        form.bonificoSend.disabled = true;
        form.numCc.disabled = true;
        form.numAbi.disabled = true;
        form.numCab.disabled = true;
        form.txtCin.disabled = true;
    }
}

function Change_BonificoSend(form) {
    if (form.bonificoSend.checked) {
        form.numCc.disabled = false;
        form.numAbi.disabled = false;
        form.numCab.disabled = false;
        form.txtCin.disabled = false;
    } else {
        form.numCc.disabled = true;
        form.numAbi.disabled = true;
        form.numCab.disabled = true;
        form.txtCin.disabled = true;
    }
}

function Change_Paypal(form) {
    if (form.paypal.checked) {
        form.txtPaypalEmailAddress.disabled = false;
    } else {
        form.txtPaypalEmailAddress.disabled = true;
    }
}
//-->
</SCRIPT>

