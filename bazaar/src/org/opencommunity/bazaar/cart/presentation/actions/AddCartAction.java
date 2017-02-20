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
import org.opencommunity.bazaar.cart.dto.Order;
import org.opencommunity.bazaar.cart.presentation.forms.AddCartForm;
import org.opencommunity.bazaar.catalog.business.IProductManager;
import org.opencommunity.bazaar.catalog.business.impl.ProductManagerDelegate;
import org.opencommunity.bazaar.catalog.dto.Product;
import org.opencommunity.bazaar.utils.exceptions.BazaarException;
import org.opencommunity.bazaar.utils.messages.IErrorCodes;
import org.opencommunity.bazaar.utils.utility.ForwardKeys;

// This class handle a login request. It get the user id and password and validate them.
public final class AddCartAction extends Action {
    public ActionForward execute(ActionMapping mapping,
                               ActionForm form, 
                               HttpServletRequest request, 
                               HttpServletResponse response)
    throws IOException, ServletException {

    	ActionMessages errors = new ActionMessages();
    	AddCartForm cartForm = (AddCartForm) form;

        try {
            IProductManager services = new ProductManagerDelegate();
            Product product = services.getProduct(cartForm.getNumID());
            Order order = new Order();
            order.setNSellerID(product.getNAccountID());
            order.setNQuantity(1);
            order.setDblTotal(product.getDblPrice());
            order.setDblGrandTotal(product.getDblPrice());
            order.setNProductID(product.getNID());
            CartAO cart = new CartAO();
            cart.setProduct(product);
            cart.setOrder(order);
            
            request.getSession(true).setAttribute("cart", cart);
            
            return mapping.findForward(ForwardKeys.CART_FWD);
        } catch(BazaarException exception) {
            errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(exception.getErrorCode()));
            saveErrors(request, errors);
            return mapping.findForward(ForwardKeys.CART_FWD);
        } catch(RuntimeException exception) {
            errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(IErrorCodes.ERR_INTERNAL));
            saveErrors(request, errors);
            return mapping.findForward(ForwardKeys.CART_FWD);
        }
    }
}
