/*
 * Created on Apr 19, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.opencommunity.bazaar.utils.exceptions;

public class MailException extends BazaarException {
    public MailException(String errorCode) {
	    super(errorCode);
    }

    public MailException(String errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public MailException(Throwable cause) {
        super(cause);
    }
}
