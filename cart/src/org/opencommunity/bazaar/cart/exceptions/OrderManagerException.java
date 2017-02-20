/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.cart.exceptions;

import org.opencommunity.bazaar.utils.exceptions.BazaarException;

public class OrderManagerException extends BazaarException {
    public OrderManagerException(String errorCode) {
	    super(errorCode);
    }

    public OrderManagerException(String errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public OrderManagerException(Throwable cause) {
        super(cause);
    }
}
