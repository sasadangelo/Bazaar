/*
 * Copyleft (C) Open Community author Salvatore D'Angelo (koala.gnu@tiscali.it)
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
import org.opencommunity.bazaar.account.presentation.forms.EditUserForm;
import org.opencommunity.bazaar.utils.exceptions.BazaarException;
import org.opencommunity.bazaar.utils.messages.IErrorCodes;
import org.opencommunity.bazaar.utils.utility.ForwardKeys;

public final class EditUserAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		ActionMessages errors = new ActionMessages();
		EditUserForm editUserForm = (EditUserForm) form;

		Account account = new Account(0, editUserForm.getTxtEmailAddress(),
				null, false, 0, false,
				editUserForm.getTxtFirstname(), editUserForm.getTxtLastname(),
				editUserForm.getTxtStreetAddress(), editUserForm.getTxtZipCode(),
				editUserForm.getTxtCity(), editUserForm.getTxtState(), editUserForm.getTxtCountry(), 
				editUserForm.getTxtPhone(),
				new Timestamp(System.currentTimeMillis()), null);

		try {
			IAccountManager services = new AccountManagerDelegate();
			services.modify(account);
			return mapping.findForward(ForwardKeys.LIST_ACCOUNTS_FWD);
		} catch (BazaarException exception) {
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(exception.getErrorCode()));
			saveErrors(request, errors);
			return mapping.findForward(ForwardKeys.EDIT_ACCOUNT_FWD);
		} catch (RuntimeException exception) {
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(IErrorCodes.ERR_INTERNAL));
			saveErrors(request, errors);
			return mapping.findForward(ForwardKeys.EDIT_ACCOUNT_FWD);
		}
	}
}
