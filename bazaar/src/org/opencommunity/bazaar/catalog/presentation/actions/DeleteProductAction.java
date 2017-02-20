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
import org.opencommunity.bazaar.account.dto.Account;
import org.opencommunity.bazaar.catalog.business.IProductManager;
import org.opencommunity.bazaar.catalog.business.impl.ProductManagerDelegate;
import org.opencommunity.bazaar.utils.exceptions.BazaarException;
import org.opencommunity.bazaar.utils.messages.IErrorCodes;
import org.opencommunity.bazaar.utils.utility.ForwardKeys;

public final class DeleteProductAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		ActionMessages errors = new ActionMessages();

        Account account = (Account) request.getSession(true).getAttribute("account"); 

		try {
			long nID = Long.parseLong(request.getParameter("nID"));

			IProductManager services = new ProductManagerDelegate();
			services.delete(account.getNID(), nID);
			
			return mapping.findForward(ForwardKeys.LIST_PRODUCTS_FWD);
		} catch (BazaarException exception) {
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(exception.getErrorCode()));
			saveErrors(request, errors);
			return mapping.findForward(ForwardKeys.EDIT_PRODUCT_FWD);
		} catch (RuntimeException exception) {
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(IErrorCodes.ERR_INTERNAL));
			saveErrors(request, errors);
			return mapping.findForward(ForwardKeys.EDIT_PRODUCT_FWD);
		}
	}
}
