<%@ page import='java.util.*,nbp.jspcart.orders.*,nbp.jspcart.users.*'%>

<jsp:useBean id="website" scope="application" class="nbp.jspcart.WebSite">
	<%
		website.init(application.getRealPath("../../"));
	%>
</jsp:useBean>

<%
	String nStart = website.getRequestParameter(request,"nStart");
	String nSize  = website.getRequestParameter(request,"nSize");
	
	String result = null;
	Order object = null;

	String ids [] = request.getParameterValues("nID");

	if (ids != null) {
        for (int i=0; i<ids.length; ++i) {
            object = website.orders.get(Integer.parseInt(ids[i]));
            result = website.orders.delete(object);
            if(result != null) break;
        }
        
        if (result == null) {
            response.sendRedirect(request.getContextPath() + "/adminListOrders.do?nStart=" + nStart + "&nSize=" + nSize);
            return;
        }
	} else {
        response.sendRedirect(request.getContextPath() + "/adminListOrders.do?txtMessage=Nessun%20ordine%20selezionato.&nStart=" + nStart + "&nSize=" + nSize);
        return;
	}

%>

Error : <%=result%>
