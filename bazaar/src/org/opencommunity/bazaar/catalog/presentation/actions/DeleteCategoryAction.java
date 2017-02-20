/*
 * Copyleft (C) Open Community author Salvatore D'Angelo (koala.gnu@tiscali.it)
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
import org.opencommunity.bazaar.catalog.business.ICategoryManager;
import org.opencommunity.bazaar.catalog.business.impl.CategoryManagerDelegate;
import org.opencommunity.bazaar.utils.exceptions.BazaarException;
import org.opencommunity.bazaar.utils.messages.IErrorCodes;
import org.opencommunity.bazaar.utils.utility.ForwardKeys;

public final class DeleteCategoryAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

    	long key;
    	ActionMessages errors = new ActionMessages();
        
        String txtKey = (String) request.getParameter("id");
        String txtParentId = (String) request.getParameter("nParentId");

        if (txtKey == null) {
            errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(IErrorCodes.ERR_DELETE_CATEGORY_WRONG_URL));
            saveErrors(request, errors);
            return mapping.findForward(ForwardKeys.LIST_CATEGORIES_FWD);
        }

        try {
        	key = Long.parseLong(txtKey); 
        } catch(NumberFormatException exception) {
            errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(IErrorCodes.ERR_DELETE_USER_WRONG_URL));
            saveErrors(request, errors);
            return mapping.findForward(ForwardKeys.LIST_ACCOUNTS_FWD);
        }

		try {
			ICategoryManager services = new CategoryManagerDelegate();
			services.delete(key);
		} catch (BazaarException exception) {
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(exception.getErrorCode()));
			saveErrors(request, errors);
		} catch (RuntimeException exception) {
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(IErrorCodes.ERR_INTERNAL));
			saveErrors(request, errors);
		}

		if (txtParentId==null) {
			return mapping.findForward(ForwardKeys.LIST_CATEGORIES_FWD);
		} else {
			return new ActionForward("/adminListCategories.do?nParentId=" + txtParentId);
		}
	}
}
