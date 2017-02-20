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
	<FORM name="signup" action="signup.do" method=POST>
		<TABLE cellSpacing=1 cellPadding=2 border=0 class=Data>
			<TBODY>
				<TR>
					<TH> Registrati - Bazaar</TH>
				</TR>
				<TR>
					<TD background=./../../images/tdbg04.gif>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;( Campi Obbligatori <font color=red>*</font> )
						<TABLE cellSpacing=5 cellPadding=2 border=0>
						<TBODY>
							<TR>
								<TD nowrap class=normal align=right><font color=red>*</font> Indirizzo Email:</TD>
								<TD class=normal><INPUT class=LightBox size=20 name=txtEmailAddress value=""> </TD>
							</TR>
							<TR>
								<TD align=right class=normal><font color=red>*</font> Password:</TD>
								<TD class=normal><INPUT class=LightBox type=password size=20 name=txtPassword> </TD>
							</TR>
							<TR>
								<TD align=right nowrap class=normal><font color=red>*</font> Conferma Password:</TD>
								<TD class=normal><INPUT class=LightBox type=password size=20 name=txtConfirmPassword> </TD>
							</TR>
							<TR>
								<TD align=right nowrap class=normal><font color=red>*</font> Nome:</TD>
								<TD class=normal><INPUT class=LightBox  size=20 name=txtFirstname value=""> </TD>
							</TR>
							<TR>
								<TD align=right nowrap class=normal><font color=red>*</font> Cognome:</TD>
								<TD class=normal><INPUT class=LightBox  size=20 name=txtLastname value=""> </TD>
							</TR>
							<TR>
								<TD align=right nowrap class=normal valign=top><font color=red>*</font> Indirizzo:</TD>
								<TD class=normal><TEXTAREA class=LightBox cols=30 rows=3 name=txtStreetAddress></TEXTAREA></TD>
							</TR>
							<TR>
								<TD align=right class=normal><font color=red>*</font> Citta':</TD>
								<TD class=normal><INPUT class=LightBox  size=20 name=txtCity value=""> </TD>
							</TR>
							<TR>
								<TD align=right class=normal><font color=red>*</font> Codice Postale:</TD>
								<TD class=normal><INPUT class=LightBox  size=20 name=txtZipCode value=""> </TD>
							</TR>
							<TR>
								<TD align=right class=normal><font color=red>*</font> Prov.:</TD>
								<TD class=normal><INPUT class=LightBox  size=20 name=txtState value=""> </TD>
							</TR>
							<TR>
								<TD align=right class=normal><font color=red>*</font> Nazione:</TD>
								<TD class=normal>
								    <SELECT name=txtCountry>
								        <OPTION value="IT">Italy</OPTION>
		                            </SELECT>
		                        </TD>
							</TR>
							<TR>
								<TD align=right class=normal valign=top><font color=red>*</font> Telefono:</TD>
								<TD class=normal><INPUT class=LightBox  size=20 name=txtPhone value=""></TD>
							</TR>
							<TR>
								<TD align=right class=normal>&nbsp;</TD>
								<TD class=normal><INPUT type=checkbox value="0" size=20 name=bAgree> Accetto tutte le <A href="agreement.do">clausole e condizioni</A>.</TD>
							</TR>
							<TR>
								<TD align=right class=normal>&nbsp;</TD>
								<TD class=normal><INPUT class=DarkButton type=submit name=Submit value="Registra"></TD>
							</TR>
						</TBODY>
						</TABLE>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
	</FORM>
</CENTER>

<SCRIPT LANGUAGE=javascript>
<!--

	document.SignupForm.txtCountry.value = "United States of America"

	alphabets = new String("abcdefghijklmnopqrstuvwxyz");
	numbers = new String("0123456789");
	function verifyEmpty(object,name)
	{
		if(object.value=="")
		{
			alert(name + " e' un campo obbligatorio.")
			object.focus();
			return false;
		}
		return true;
	}
	
	function verifyLength(object,n,name)
	{
		Value = new String(object.value)
		if(Value.length<n)
		{
			alert(name + " deve avere almeno  " + n + " caratteri")
			object.focus();
			return false;
		}
		return true;
	}
	
	function exists(chars,ch)
	{
		ch = ch.toLowerCase();
		if(chars.indexOf(ch) != -1)
			return true;
		return false;
	}
	
	function verifyUsername(object)
	{
		if(!verifyEmpty(object,"Username"))
			return false;
		if(!verifyLength(object,5,"Username"))
			return false;
		Value = new String(object.value)
		for(i=0;i<Value.length;i++)
		{
			ch = Value.charAt(i)
			if(exists(alphabets,ch) || exists(numbers,ch) || ch=="_")
			{
			}
			else
			{
				alert("Il campo Username puo' contenere solo caratteri alfabetici, numeri e underscore (_).");
				object.focus();
				return false;
			}
		}
		return true;
	}
	
	function verifyPasswords(pass,pass2)
	{
		if(!verifyEmpty(pass,"Password"))
			return false;
		if(!verifyLength(pass,5,"Password"))
			return false;
		passString = new String(pass.value)
		pass2String = new String(pass2.value)
		if((passString.length == pass2String.length) && (passString.indexOf(pass2String)==0))
			return true;
		alert("Le due password sono diverse. Riprova.")
		pass.value = "";
		pass2.value = "";
		pass.focus();
		return false;
	}
	
	function verifyMail(object)
	{
		if(!verifyEmpty(object,"Indirizzo Email"))
			return false;
		if(!exists(object.value,"@"))
		{
			alert("Inserisci un valido indirizzo email.")
			return false;
		}
		return true;
	}
	
	function verifyForm()
	{
		if(!verifyMail(document.SignupForm.txtEmailAddress))
			return false;
		if(!verifyPasswords(document.SignupForm.txtPassword,document.SignupForm.txtPassword2))
			return false;
		if(!verifyEmpty(document.SignupForm.txtFirstname,"Nome"))
			return false;
		if(!verifyEmpty(document.SignupForm.txtLastname,"Cognome"))
			return false;
		if(!verifyEmpty(document.SignupForm.txtStreetAddress,"Indirizzo"))
			return false;
		if(!verifyEmpty(document.SignupForm.txtCity,"Citta'"))
			return false;
		if(!verifyEmpty(document.SignupForm.txtZipCode,"Codice Postale"))
			return false;
		if(!verifyEmpty(document.SignupForm.txtState,"Stato/Prov."))
			return false;
		if(!verifyEmpty(document.SignupForm.txtPhone,"Telefono"))
			return false;
		if(!document.SignupForm.bAgree.checked)
		{
			alert("Devi accettare tutte le clausole e condizioni.");
			document.SignupForm.bAgree.focus()
			return false;
		}
		return true;
	}
	
//-->
</SCRIPT>
