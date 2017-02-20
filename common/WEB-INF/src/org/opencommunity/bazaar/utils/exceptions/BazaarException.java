/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.utils.exceptions;

public class BazaarException extends Exception {
	private String errorCode;
	
    public BazaarException(String errorCode) {
	    super();
	    this.errorCode = errorCode;
    }

    public BazaarException(String errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    public BazaarException(Throwable cause) {
        super(cause);
    }

    public String getErrorCode() {
		return errorCode;
	}
}