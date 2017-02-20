/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.exceptions;

public class BazaarException extends Exception {
    // layer affected by the exception
    public final static int DAO_LAYER = 0;
    public final static int BUSINESS_LAYER = 1;

    // error category
    public final static int INFO = 0;
    public final static int WARNING = 1;
    public final static int ERROR = 2;
    public final static int FATAL = 3;

    private int layer;
    private int category;
    private String errorCode;

    public BazaarException(int layer, int category, String errorCode) {
        super();
        this.layer = layer;
        this.category = category;
        this.errorCode = errorCode;
    }

    public BazaarException(int layer, 
                           int category, 
                           String errorCode, 
                           String msg) {
        super(msg);
        this.layer = layer;
        this.category = category;
        this.errorCode = errorCode;
    }

    public int getLayer() {
        return layer;
    }

    public int getCategory() {
        return category;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
