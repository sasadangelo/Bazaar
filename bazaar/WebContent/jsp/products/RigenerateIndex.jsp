<%@ page import='java.util.*, org.opencommunity.bazaar.business.*, org.opencommunity.bazaar.dao.*'%>

<jsp:useBean id="website" scope="application" class="nbp.jspcart.WebSite">
	<%
	    website.init(application.getRealPath("../../"));
	%>
</jsp:useBean>

<%
	int nStart = website.getRequestParameter(request,"nStart",0);

	if(request.getMethod().equalsIgnoreCase("post")) {
	    try {
            new CatalogDelegate(website).rigenerateIndex();
        } catch(DAOException exc) {
            response.sendRedirect(request.getContextPath() + "/adminListProducts.do?nStart=" + nStart + "&txtMessage=Errore durante la generazione dell'indice");
            return;
        }
        response.sendRedirect(request.getContextPath() + "/adminListProducts.do?nStart=" + nStart + "&txtMessage=Indice generato con successo.");
        return;
    }
%>