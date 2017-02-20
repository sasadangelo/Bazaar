/*
 * Copyleft (C) Open Community author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.account.exceptions;

import org.opencommunity.bazaar.utils.exceptions.BazaarException;

public class AccountManagerException extends BazaarException {
    public AccountManagerException(String errorCode) {
	    super(errorCode);
    }

    public AccountManagerException(String errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public AccountManagerException(Throwable cause) {
        super(cause);
    }
}
