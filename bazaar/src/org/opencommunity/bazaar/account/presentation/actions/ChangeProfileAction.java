/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.account.presentation.actions;

import java.io.IOException;
import java.sql.Timestamp;

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
import org.opencommunity.bazaar.account.dto.Account;
import org.opencommunity.bazaar.account.presentation.forms.ChangeProfileForm;
import org.opencommunity.bazaar.utils.exceptions.BazaarException;
import org.opencommunity.bazaar.utils.messages.IErrorCodes;
import org.opencommunity.bazaar.utils.messages.IInformationCodes;
import org.opencommunity.bazaar.utils.utility.ForwardKeys;

// This class handle a login request. It get the user id and password and validate them.
public final class ChangeProfileAction extends Action {
    public ActionForward execute(ActionMapping mapping,
                               ActionForm form, 
                               HttpServletRequest request, 
                               HttpServletResponse response)
    throws IOException, ServletException {

        ActionMessages errors = new ActionMessages();
        ChangeProfileForm signupForm = (ChangeProfileForm) form;
        
        Account account = new Account(0, 
        		signupForm.getTxtEmailAddress(),
				null,
				false, // not used
				0,     // activation code not used
				true,  // the account is already active
				signupForm.getTxtFirstname(),
				signupForm.getTxtLastname(),
				signupForm.getTxtStreetAddress(),
				signupForm.getTxtZipCode(),
				signupForm.getTxtCity(),
				signupForm.getTxtState(),
				signupForm.getTxtCountry(),
				signupForm.getTxtPhone(),
				new Timestamp(System.currentTimeMillis()),
				// not used
				null);

        try {
            IAccountManager services = new AccountManagerDelegate();
            services.modify(account);
 
            // changed is successful, update the account data in session
            request.getSession(true).setAttribute("account", account);

            // goto the welcome page
            ActionMessages messages = new ActionMessages();
            messages.add(IInformationCodes.INFORMATIONS_ID, new ActionMessage(IInformationCodes.USER_CHANGED_SUCCESSFUL));
            saveMessages(request, messages);
            return mapping.findForward(ForwardKeys.PROFILE_FWD);
        } catch(BazaarException exception) {
            errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(exception.getErrorCode()));
            saveErrors(request, errors);
            // user id and password not validated, display the error page.
            return mapping.findForward(ForwardKeys.CHANGE_PROFILE_FWD);
        } catch(RuntimeException exception) {
            errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(IErrorCodes.ERR_INTERNAL));
            saveErrors(request, errors);
            // user id and password not validated, display the error page.
            return mapping.findForward(ForwardKeys.CHANGE_PROFILE_FWD);
        }
    }
}
