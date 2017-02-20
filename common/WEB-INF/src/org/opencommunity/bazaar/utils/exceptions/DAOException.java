/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.utils.exceptions;

import org.opencommunity.bazaar.utils.exceptions.BazaarException;

public class DAOException extends BazaarException {
    public DAOException(String errorCode) {
	    super(errorCode);
    }

    public DAOException(String errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }
}
