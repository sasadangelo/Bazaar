<jsp:useBean id="cart" scope="session" class="org.opencommunity.bazaar.cart.dto.CartAO" />

<%@ include file='incOrderDetails.jsp'%>

<TABLE class=Data width=80% cellspacing=1 >
	<TR><TH>Il CODICE del tuo ordine e' <%=cart.getOrder().getNID()%></TH></TR>
	<TR>
		<TD>
		    Questo codice ti verra' richiesto ogni volta che desideri conoscere lo stato del tuo ordine.
		    L'ordine verra' pagato secondo le modalita' riportate nella sezione <a href="agreement.do">Condizioni generali di Vendita</a>.
		    Il cliente potra' annullare il seguente ordine nella sezione "Profilo" a patto che esso sia nello stato di
		    "In Attesa di Spedizione/Pagamento".<BR>
		</TD>
	</TD>
</TABLE>
<BR>

<%
	// Free up the shopping cart....
	cart.setOrder(null);
	cart.setProduct(null);
%>
