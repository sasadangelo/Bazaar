<%@ page import='java.util.*,nbp.jspcart.categories.*,nbp.jspcart.products.*,nbp.jspcart.users.*'%>

<jsp:useBean id="website" scope="application" class="nbp.jspcart.WebSite">
    <%
        website.init(application.getRealPath("../../"));
    %>
</jsp:useBean>

<%
	String nStart = request.getParameter("nStart");
	String nSize  = request.getParameter("nSize");
	int nCategoryID = website.getRequestParameter(request,"nCategoryID",0);	

	Category category = website.categories.get(nCategoryID);

	int nProductID = website.getRequestParameter(request,"nProductID",0);
	Product product = website.products.get(nProductID);
	
	website.categories.deleteProduct(category,product);
	
	response.sendRedirect(request.getContextPath() + "/adminListCategories.do?nParentID=" + nCategoryID + "&nStart=" + nStart + "&nSize=" + nSize);
%>
