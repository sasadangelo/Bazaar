/*
 * Copyleft (C) Open Community author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.account.presentation.actions;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Random;

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
import org.opencommunity.bazaar.account.presentation.forms.SignupForm;
import org.opencommunity.bazaar.utils.dto.EmailMessage;
import org.opencommunity.bazaar.utils.exceptions.BazaarException;
import org.opencommunity.bazaar.utils.exceptions.MailException;
import org.opencommunity.bazaar.utils.integration.email.MailManager;
import org.opencommunity.bazaar.utils.messages.IErrorCodes;
import org.opencommunity.bazaar.utils.messages.IInformationCodes;
import org.opencommunity.bazaar.utils.utility.ForwardKeys;

// This class handle a login request. It get the user id and password and
// validate them.
public final class SignupAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		ActionMessages errors = new ActionMessages();
		SignupForm signupForm = (SignupForm) form;

		// Generate an activation code. When an user register himself on Bazaar
		// he receive an email with activation code. It will be used to activate 
		// the account. In this way we are sure the signup email is valid.
		long activationCode = Math.abs(new Random().nextLong());

		Account account = new Account(0, signupForm.getTxtEmailAddress(),
				signupForm.getTxtPassword(), false, activationCode, false,
				signupForm.getTxtFirstname(), signupForm.getTxtLastname(),
				signupForm.getTxtStreetAddress(), signupForm.getTxtZipCode(),
				signupForm.getTxtCity(), signupForm.getTxtState(), signupForm
						.getTxtCountry(), signupForm.getTxtPhone(),
				new Timestamp(System.currentTimeMillis()), new Timestamp(System
						.currentTimeMillis()));

		try {
			IAccountManager services = new AccountManagerDelegate();
			services.register(account);
		} catch (BazaarException exception) {
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(exception.getErrorCode()));
			saveErrors(request, errors);
			return mapping.findForward(ForwardKeys.SIGNUP_FWD);
		} catch (RuntimeException exception) {
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(IErrorCodes.ERR_INTERNAL));
			saveErrors(request, errors);
			return mapping.findForward(ForwardKeys.SIGNUP_FWD);
		}

		try {
			// registration is successful, send the signup email with activation
			// code
			EmailMessage message = new EmailMessage(signupForm.getTxtEmailAddress(),
					"Registrazione utenza su Bazaar",
					"Gentile Utente,<br><br>La ringraziamo per essersi iscritto a Bazaar, il sistema di compravendita opensource di OpenCommunity.<br>" +
					"Per attivare il suo account clicchi sul link riportato sotto.<br>" +
					"Se il click sul link non apre il suo browser, copi il link e lo incolli nella barra di indirizzi del suo browser (Internet Explorer, Mozilla, ecc.).<br><br>" +
					"<a href='http://localhost:8080/bazaar/activation.do?email=" +
					signupForm.getTxtEmailAddress() + "&code=" + activationCode +
					"'>http://localhost:8080/bazaar/activation.do?email=" +
					signupForm.getTxtEmailAddress() + "&code=" + 
					activationCode + "</a><br>" + "Bazaar Team");
			MailManager.getInstance().send(message);

			ActionMessages messages = new ActionMessages();
			messages.add(IInformationCodes.INFORMATIONS_ID, new ActionMessage(IInformationCodes.USER_REGISTRATION_SUCCESSFUL));
			saveMessages(request, messages);
			return mapping.findForward(ForwardKeys.LOGIN_FWD);
		} catch (MailException exception) {
			ActionMessages messages = new ActionMessages();
			messages.add(IInformationCodes.INFORMATIONS_ID, new ActionMessage(IInformationCodes.USER_REGISTRATION_SUCCESSFUL_BUT_EMAIL_PROBLEM));
			saveMessages(request, messages);
			return mapping.findForward(ForwardKeys.LOGIN_FWD);
		} catch (RuntimeException exception) {
			ActionMessages messages = new ActionMessages();
			messages.add(IInformationCodes.INFORMATIONS_ID, new ActionMessage(IInformationCodes.USER_REGISTRATION_SUCCESSFUL_BUT_EMAIL_PROBLEM));
			saveMessages(request, messages);
			return mapping.findForward(ForwardKeys.LOGIN_FWD);
		}
	}
}
