/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.catalog.presentation.actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.opencommunity.bazaar.catalog.business.impl.CategoryManagerDelegate;
import org.opencommunity.bazaar.catalog.business.impl.ProductManagerDelegate;
import org.opencommunity.bazaar.catalog.dto.Category;
import org.opencommunity.bazaar.utils.business.IDataListHandler;
import org.opencommunity.bazaar.utils.exceptions.BazaarException;
import org.opencommunity.bazaar.utils.messages.IErrorCodes;
import org.opencommunity.bazaar.utils.utility.ForwardKeys;

public final class ListProductCatalogAction extends Action {
    public ActionForward execute(ActionMapping mapping,
                               ActionForm form, 
                               HttpServletRequest request, 
                               HttpServletResponse response)
    throws IOException, ServletException {

    	List categories = null;
    	List categoryHier = null;
    	Category parent = null;
        IDataListHandler products = null;
        
    	ActionMessages errors = new ActionMessages();
        String action = (String) request.getParameter("page");
        long categoryID = 0;

        try {
	        if (request.getParameter("nParentId")==null) {
	        	categories = new CategoryManagerDelegate().getCategories(null);
	        	categoryHier = new CategoryManagerDelegate().getCategoryHierarchy(null);
	        } else {
	        	categoryID = Long.parseLong(request.getParameter("nParentId")); 
	        	categories = new CategoryManagerDelegate().getCategories(new Long(categoryID));
	            categoryHier = new CategoryManagerDelegate().getCategoryHierarchy(new Long(categoryID));
	            parent = new CategoryManagerDelegate().getCategory(categoryID);
	            
	            if (action == null || action.equals("")) {
                	products = new ProductManagerDelegate().getProductsByCategory(categoryID);
                    request.getSession(true).setAttribute("catalogProducts", products);
	            } else if (action.equals("next")) {
	            	products = (IDataListHandler) request.getSession(true).getAttribute("catalogProducts");
	            	products.moveNextChunk();
	            } else if (action.equals("prev")) {
	            	products = (IDataListHandler) request.getSession(true).getAttribute("catalogProducts");
	            	products.movePrevChunk();
	            } else {
	                errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(IErrorCodes.ERR_INVALID_PRODUCTS_PAGE));
	                saveErrors(request, errors);
	            }
	        }

	        request.getSession(true).setAttribute("categories", categories);
            request.getSession(true).setAttribute("categoryHier", categoryHier);
            request.getSession(true).setAttribute("parentCategory", parent);

            return mapping.findForward(ForwardKeys.HOME_PAGE_FWD);
            /*
            ActionRedirect redirect = new ActionRedirect();
            if (request.getParameter("nParentId")!=null) {
                redirect.addParameter("nParentId", categoryID);
            }
            return redirect;*/
        } catch(BazaarException exception) {
            errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(exception.getErrorCode()));
            saveErrors(request, errors);
            return mapping.findForward(ForwardKeys.HOME_FWD);
        } catch(RuntimeException exception) {
            errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(IErrorCodes.ERR_INTERNAL));
            saveErrors(request, errors);
            return mapping.findForward(ForwardKeys.HOME_FWD);
        }
    }
}
