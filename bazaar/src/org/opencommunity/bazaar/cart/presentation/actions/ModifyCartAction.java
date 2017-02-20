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
import org.opencommunity.bazaar.cart.dto.CartAO;
import org.opencommunity.bazaar.cart.presentation.forms.ModifyCartForm;
import org.opencommunity.bazaar.utils.messages.IErrorCodes;
import org.opencommunity.bazaar.utils.utility.ForwardKeys;

// This class handle a login request. It get the user id and password and validate them.
public final class ModifyCartAction extends Action {
    public ActionForward execute(ActionMapping mapping,
                               ActionForm form, 
                               HttpServletRequest request, 
                               HttpServletResponse response)
    throws IOException, ServletException {

     	ActionMessages errors = new ActionMessages();
    	ModifyCartForm changeForm = (ModifyCartForm) form;

        CartAO cart = (CartAO) request.getSession(true).getAttribute("cart");

        if (changeForm.getAction().equals("Modifica")) {
            if (cart == null) {
            	request.getSession(true).setAttribute("cart", new CartAO());
            } else {
            	if (changeForm.getNumQuantity() <= cart.getProduct().getNumQuantity()) {
	            	cart.getOrder().setNQuantity(changeForm.getNumQuantity());
	            	cart.getOrder().setDblTotal(cart.getProduct().getDblPrice()*changeForm.getNumQuantity());
	            	cart.getOrder().setDblGrandTotal(cart.getProduct().getDblPrice()*changeForm.getNumQuantity());
            	} else {
                    errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(IErrorCodes.ERR_EXCEED_QUANTITY));
                    saveErrors(request, errors);
            	}
            }
        } if (changeForm.getAction().equals("Cancella")) {
            if (cart == null) {
            	request.getSession(true).setAttribute("cart", new CartAO());
            } else {
                cart.setOrder(null);
                cart.setProduct(null);
            }
        }
        
        return mapping.findForward(ForwardKeys.CART_FWD);        
    }
}
