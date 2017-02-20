/*
 * Created on Apr 19, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.opencommunity.bazaar.utils.exceptions;

public class DataListException extends BazaarException {
    public DataListException(String errorCode) {
	    super(errorCode);
    }

    public DataListException(String errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public DataListException(Throwable cause) {
        super(cause);
    }
}
