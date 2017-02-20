/*
 * Created on Apr 19, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.opencommunity.bazaar.utils.exceptions;

public class TransactionException extends BazaarException {
    public TransactionException(String errorCode) {
	    super(errorCode);
    }

    public TransactionException(String errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public TransactionException(Throwable cause) {
        super(cause);
    }
}
