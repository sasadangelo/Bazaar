/*
 * Copyleft (C) Open Community author Salvatore D'Angelo (koala.gnu@tiscali.it)
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
import org.opencommunity.bazaar.account.dto.Account;
import org.opencommunity.bazaar.account.presentation.forms.ForgotForm;
import org.opencommunity.bazaar.utils.dto.EmailMessage;
import org.opencommunity.bazaar.utils.exceptions.BazaarException;
import org.opencommunity.bazaar.utils.integration.email.MailManager;
import org.opencommunity.bazaar.utils.messages.IErrorCodes;
import org.opencommunity.bazaar.utils.messages.IInformationCodes;
import org.opencommunity.bazaar.utils.utility.ForwardKeys;

public final class ForgotPasswordAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		ActionMessages errors = new ActionMessages();
		ForgotForm forgotForm = (ForgotForm) form;

		try {
			IAccountManager services = new AccountManagerDelegate();
			Account account = services.getAccount(forgotForm.getTxtEmailAddress());
			
			EmailMessage message = new EmailMessage(forgotForm.getTxtEmailAddress(),
					"Registrazione utenza su Bazaar",
					"Gentile Utente,<br><br>La sua password e': " + account.getTxtPassword() + "<br>Bazaar Team");
			MailManager.getInstance().send(message);
			
			ActionMessages messages = new ActionMessages();
			messages.add(IInformationCodes.INFORMATIONS_ID, new ActionMessage(IInformationCodes.PASSWORD_SENT_SUCCESSFUL));
			saveMessages(request, messages);
			return mapping.findForward(ForwardKeys.LOGIN_FWD);
		} catch (BazaarException exception) {
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(exception.getErrorCode()));
			saveErrors(request, errors);
			return mapping.findForward(ForwardKeys.LOGIN_FWD);
		} catch (RuntimeException exception) {
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(IErrorCodes.ERR_INTERNAL));
			saveErrors(request, errors);
			return mapping.findForward(ForwardKeys.LOGIN_FWD);
		}
	}
}
