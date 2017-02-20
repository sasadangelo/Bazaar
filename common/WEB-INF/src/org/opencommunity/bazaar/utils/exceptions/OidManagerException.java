/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.utils.exceptions;

public class OidManagerException extends BazaarException {
    public OidManagerException(String errorCode) {
	    super(errorCode);
    }

    public OidManagerException(String errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public OidManagerException(Throwable cause) {
        super(cause);
    }
}
