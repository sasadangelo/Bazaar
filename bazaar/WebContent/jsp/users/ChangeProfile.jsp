<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<jsp:useBean id="account" class="org.opencommunity.bazaar.account.dto.Account" type="org.opencommunity.bazaar.account.dto.Account" scope="session"/>

<CENTER>
    <font color=red>
        <STRONG>
            <html:messages id="error" message="false">
                <li><bean:write name="error"/><br>
            </html:messages>
        </STRONG>
    </font>
	<FORM name="changeProfile" action="changeProfile.do" method=POST>
		<INPUT type=hidden name="txtEmailAddress" value="<%=account.getTxtEmailAddress()%>">
		<TABLE cellSpacing=1 cellPadding=2 border=0 class=Data>
			<TBODY>
				<TR>
					<TH> 
				    	Cambia il tuo profilo - Bazaar
					</TH>
				</TR>
				<TR>
					<TD background=./../../images/tdbg04.gif>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;( Campi Obbligatori <font color=red>*</font> )
						<TABLE cellSpacing=5 cellPadding=2 border=0>
							<TBODY>
								<TR>
									<TD align=right nowrap class=normal><font color=red>*</font> Nome:</TD>
									<TD class=normal><INPUT class=LightBox  size=20 name=txtFirstname value="<%=account.getTxtFirstname()%>"> </TD>
								</TR>
								<TR>
									<TD align=right nowrap class=normal><font color=red>*</font> Cognome:</TD>
									<TD class=normal><INPUT class=LightBox  size=20 name=txtLastname value="<%=account.getTxtLastname()%>"> </TD>
								</TR>
								<TR>
									<TD align=right nowrap class=normal valign=top><font color=red>*</font> Indirizzo:</TD>
									<TD class=normal><TEXTAREA class=LightBox cols=30 rows=3 name=txtStreetAddress><%=account.getTxtStreetAddress()%></TEXTAREA></TD>
								</TR>
								<TR>
									<TD align=right class=normal><font color=red>*</font> Citta':</TD>
									<TD class=normal><INPUT class=LightBox  size=20 name=txtCity value="<%=account.getTxtCity()%>"> </TD>
								</TR>
								<TR>
									<TD align=right class=normal><font color=red>*</font> Codice Postale:</TD>
									<TD class=normal><INPUT class=LightBox  size=20 name=txtZipCode value="<%=account.getTxtZipCode()%>"> </TD>
								</TR>
								<TR>
									<TD align=right class=normal><font color=red>*</font> Stato/Prov.:</TD>
									<TD class=normal>
							    		<INPUT class=LightBox  size=20 name=txtState value="<%=account.getTxtState()%>">
									</TD>
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
									<TD class=normal><INPUT class=LightBox  size=20 name=txtPhone value="<%=account.getTxtPhone()%>"><BR>
								</TR>
								<TR>
									<TD align=right class=normal>&nbsp;</TD>
									<TD class=normal><INPUT class=DarkButton type=submit name=Submit value="Aggiorna"></TD>
								</TR>
							</TBODY>
						</TABLE>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
	</FORM>	
</CENTER>		
