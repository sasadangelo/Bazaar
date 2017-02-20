/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.cart.presentation.actions;

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
import org.opencommunity.bazaar.cart.dto.CartAO;
import org.opencommunity.bazaar.utils.messages.IErrorCodes;
import org.opencommunity.bazaar.utils.utility.ForwardKeys;

// This class handle a login request. It get the user id and password and validate them.
public final class GoShippingCartAction extends Action {
    public ActionForward execute(ActionMapping mapping,
                               ActionForm form, 
                               HttpServletRequest request, 
                               HttpServletResponse response)
    throws IOException, ServletException {

    	ActionMessages errors = new ActionMessages();
        CartAO cart = (CartAO) request.getSession(true).getAttribute("cart");
        Account account = (Account) request.getSession(true).getAttribute("account");
        
        // if the cart is available then go to the shipping page, else send an error to the user 
        if (cart != null && cart.getProduct() != null && cart.getOrder() != null) {
        	// if no user is logged then go to the login page, but save the forward
        	if (account == null) {
        		// save the current forward
        		
                return mapping.findForward(ForwardKeys.LOGIN_FWD);
        	} else {
                return mapping.findForward(ForwardKeys.SHIPPING_CART_FWD);
        	}
        } else {
            errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(IErrorCodes.ERR_SHIPPING_EMPTY_CART));
            saveErrors(request, errors);
            
            // if the user is logged the go to the profile page else go the login page
            if (account == null) {
                return mapping.findForward(ForwardKeys.LOGIN_FWD);
            } else {
                return mapping.findForward(ForwardKeys.PROFILE_FWD);
            }
        }
    }
}
