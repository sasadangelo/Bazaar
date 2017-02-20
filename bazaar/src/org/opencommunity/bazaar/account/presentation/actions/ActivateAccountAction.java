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
public final class ActivateAccountAction extends Action {
    public ActionForward execute(ActionMapping mapping,
                               ActionForm form, 
                               HttpServletRequest request, 
                               HttpServletResponse response)
    throws IOException, ServletException {

    	long activationCode;
    	ActionMessages errors = new ActionMessages();
        
        String email = (String) request.getParameter("email");
        String code = (String) request.getParameter("code");

        if (email == null || code == null) {
            errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(IErrorCodes.ERR_WRONG_ACTIVATION_URL));
            saveErrors(request, errors);
            return mapping.findForward(ForwardKeys.LOGIN_FWD);
        }
        
        try {
        	activationCode = Long.parseLong(code); 
        } catch(NumberFormatException exception) {
            errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(IErrorCodes.ERR_WRONG_ACTIVATION_URL));
            saveErrors(request, errors);
            return mapping.findForward(ForwardKeys.LOGIN_FWD);
        }
        
        try {
            IAccountManager services = new AccountManagerDelegate();
            services.activate(email, activationCode);
            
            ActionMessages messages = new ActionMessages();
            messages.add(IInformationCodes.INFORMATIONS_ID, new ActionMessage(IInformationCodes.USER_ACTIVATION_SUCCESSFUL));
            saveMessages(request, messages);
            return mapping.findForward(ForwardKeys.LOGIN_FWD);
        } catch(BazaarException exception) {
            errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(exception.getErrorCode()));
            saveErrors(request, errors);
            return mapping.findForward(ForwardKeys.LOGIN_FWD);
        } catch(RuntimeException exception) {
            errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(IErrorCodes.ERR_INTERNAL));
            saveErrors(request, errors);
            return mapping.findForward(ForwardKeys.LOGIN_FWD);
        }
    }
}
