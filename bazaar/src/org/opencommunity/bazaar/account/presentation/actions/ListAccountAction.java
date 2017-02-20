/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.account.presentation.actions;

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
import org.opencommunity.bazaar.account.business.impl.AccountManagerDelegate;
import org.opencommunity.bazaar.utils.business.IDataListHandler;
import org.opencommunity.bazaar.utils.exceptions.BazaarException;
import org.opencommunity.bazaar.utils.messages.IErrorCodes;
import org.opencommunity.bazaar.utils.utility.ForwardKeys;


public final class ListAccountAction extends Action {
    public ActionForward execute(ActionMapping mapping,
                               ActionForm form, 
                               HttpServletRequest request, 
                               HttpServletResponse response)
    throws IOException, ServletException {

    	ActionMessages errors = new ActionMessages();
        
        String action = (String) request.getParameter("page");
        IDataListHandler list = null;
        
        if (action == null || action.equals("")) {
            try {
                list = new AccountManagerDelegate().getAllAccounts();
                request.getSession(true).setAttribute("listAccounts", list);
                return mapping.findForward(ForwardKeys.LIST_ACCOUNTS_FWD);
            } catch(BazaarException exception) {
                errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(exception.getErrorCode()));
                saveErrors(request, errors);
                return mapping.findForward(ForwardKeys.LIST_ACCOUNTS_FWD);
            } catch(RuntimeException exception) {
                errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(IErrorCodes.ERR_INTERNAL));
                saveErrors(request, errors);
                return mapping.findForward(ForwardKeys.LIST_ACCOUNTS_FWD);
            }
        } else if (action.equals("next")) {
           list = (IDataListHandler) request.getSession(true).getAttribute("listAccounts");
           list.moveNextChunk();
           return mapping.findForward(ForwardKeys.LIST_ACCOUNTS_FWD);
        } else if (action.equals("prev")) {
        	list = (IDataListHandler) request.getSession(true).getAttribute("listAccounts");
            list.movePrevChunk();
            return mapping.findForward(ForwardKeys.LIST_ACCOUNTS_FWD);
        } else {
            errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(IErrorCodes.ERR_INVALID_ACCOUNTS_PAGE));
            saveErrors(request, errors);
            return mapping.findForward(ForwardKeys.LIST_ACCOUNTS_FWD);
        }
    }
}
