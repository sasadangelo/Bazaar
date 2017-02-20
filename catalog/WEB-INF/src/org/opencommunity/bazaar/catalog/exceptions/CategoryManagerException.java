/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.catalog.exceptions;

import org.opencommunity.bazaar.utils.exceptions.BazaarException;

public class CategoryManagerException extends BazaarException {
    public CategoryManagerException(String errorCode) {
	    super(errorCode);
    }

    public CategoryManagerException(String errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public CategoryManagerException(Throwable cause) {
        super(cause);
    }
}
