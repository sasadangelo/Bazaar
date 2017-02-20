/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.catalog.exceptions;

import org.opencommunity.bazaar.utils.exceptions.BazaarException;

public class ProductManagerException extends BazaarException {
    public ProductManagerException(String errorCode) {
	    super(errorCode);
    }

    public ProductManagerException(String errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public ProductManagerException(Throwable cause) {
        super(cause);
    }
}
