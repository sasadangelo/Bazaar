/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.catalog.presentation.actions;

import java.io.IOException;

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
import org.opencommunity.bazaar.utils.business.IDataListHandler;
import org.opencommunity.bazaar.utils.exceptions.BazaarException;
import org.opencommunity.bazaar.utils.messages.IErrorCodes;
import org.opencommunity.bazaar.utils.utility.ForwardKeys;

public final class ListCategoryAction extends Action {
    public ActionForward execute(ActionMapping mapping,
                               ActionForm form, 
                               HttpServletRequest request, 
                               HttpServletResponse response)
    throws IOException, ServletException {
    	
    	ActionMessages errors = new ActionMessages();
        
        String action = (String) request.getParameter("page");
        String txtParentId = (String) request.getParameter("nParentId");
    	IDataListHandler categories = null;
    	
        if (action == null || action.equals("")) {
            try {
                if (txtParentId == null) {
            	    categories = new CategoryManagerDelegate().getAllCategories(null);
                } else {
           	        categories = new CategoryManagerDelegate().getAllCategories(new Long(Long.parseLong(txtParentId)));
                }
                request.getSession(true).setAttribute("listCategories", categories);
                return mapping.findForward(ForwardKeys.LIST_CATEGORIES_FWD);
            } catch(BazaarException exception) {
                errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(exception.getErrorCode()));
                saveErrors(request, errors);
                return mapping.findForward(ForwardKeys.LIST_CATEGORIES_FWD);
            } catch(RuntimeException exception) {
                errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(IErrorCodes.ERR_INTERNAL));
                saveErrors(request, errors);
                return mapping.findForward(ForwardKeys.LIST_CATEGORIES_FWD);
            }
        } else if (action.equals("next")) {
            categories = (IDataListHandler) request.getSession(true).getAttribute("listCategories");
        	categories.moveNextChunk();
            return mapping.findForward(ForwardKeys.LIST_CATEGORIES_FWD);
        } else if (action.equals("prev")) {
        	categories = (IDataListHandler) request.getSession(true).getAttribute("listCategories");
        	categories.movePrevChunk();
            return mapping.findForward(ForwardKeys.LIST_CATEGORIES_FWD);
        } else {
            errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(IErrorCodes.ERR_INVALID_CATEGORIES_PAGE));
            saveErrors(request, errors);
            return mapping.findForward(ForwardKeys.LIST_CATEGORIES_FWD);
        }
    }
}
