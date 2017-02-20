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
import org.opencommunity.bazaar.account.business.IAccountManager;
import org.opencommunity.bazaar.account.business.impl.AccountManagerDelegate;
import org.opencommunity.bazaar.utils.exceptions.BazaarException;
import org.opencommunity.bazaar.utils.messages.IErrorCodes;
import org.opencommunity.bazaar.utils.messages.IInformationCodes;
import org.opencommunity.bazaar.utils.utility.ForwardKeys;

// This class handle a login request. It get the user id and password and validate them.
public final class DeleteAccountAction extends Action {
    public ActionForward execute(ActionMapping mapping,
                               ActionForm form, 
                               HttpServletRequest request, 
                               HttpServletResponse response)
    throws IOException, ServletException {

    	long key;
    	ActionMessages errors = new ActionMessages();
        
        String keyString = (String) request.getParameter("id");

        if (keyString == null) {
            errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(IErrorCodes.ERR_DELETE_USER_WRONG_URL));
            saveErrors(request, errors);
            return mapping.findForward(ForwardKeys.LIST_ACCOUNTS_FWD);
        }
        
        try {
        	key = Long.parseLong(keyString); 
        } catch(NumberFormatException exception) {
            errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(IErrorCodes.ERR_DELETE_USER_WRONG_URL));
            saveErrors(request, errors);
            return mapping.findForward(ForwardKeys.LIST_ACCOUNTS_FWD);
        }
        
        try {
            IAccountManager services = new AccountManagerDelegate();
            services.delete(key);
            
            ActionMessages messages = new ActionMessages();
            messages.add(IInformationCodes.INFORMATIONS_ID, new ActionMessage(IInformationCodes.USER_DELETE_SUCCESSFUL));
            saveMessages(request, messages);
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
    }
}
