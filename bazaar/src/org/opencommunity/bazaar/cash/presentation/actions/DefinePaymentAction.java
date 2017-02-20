/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.cash.presentation.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.opencommunity.bazaar.cash.presentation.forms.DefinePaymentForm;
import org.opencommunity.bazaar.utils.utility.ForwardKeys;

// This class handle a login request. It get the user id and password and validate them.
public final class DefinePaymentAction extends Action {
    public ActionForward execute(ActionMapping mapping,
                               ActionForm form, 
                               HttpServletRequest request, 
                               HttpServletResponse response)
    throws IOException, ServletException {

    	ActionMessages errors = new ActionMessages();
    	DefinePaymentForm paymentForm = (DefinePaymentForm) form;
        return mapping.findForward(ForwardKeys.PROFILE_FWD);
    }
}
