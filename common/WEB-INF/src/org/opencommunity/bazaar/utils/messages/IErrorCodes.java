/*
 * Copyleft (C) Open Community author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.utils.messages;

public interface IErrorCodes {
    // account management errors
	public static String ERR_USER_NOT_FOUND = "error.user.not.found";
    public static String ERR_INVALID_PASSWORD = "error.invalid.password";
    public static String ERR_USER_ALREADY_EXISTS  = "error.user.already.exists";
    public static String ERR_OLD_NEW_PASSWD_EQUALS  = "error.old.new.passwd.equals";
    public static String ERR_WRONG_OLD_PASSWD  = "error.wrong.old.passwd";
	public static String ERR_UNABLE_SEND_EMAIL = "error.unable.send.email";
	public static String ERR_USER_NOT_ACTIVE = "error.user.not.active";
	public static String ERR_WRONG_ACTIVATION_URL = "error.wrong.activation.url";
	public static String ERR_INVALID_ACCOUNTS_PAGE = "error.invalid.accounts.page";
	public static String ERR_INVALID_CATEGORIES_PAGE = "error.invalid.categories.page";
	public static String ERR_DELETE_USER_WRONG_URL = "error.delete.user.wrong.url";
    public static String ERR_CATEGORY_ALREADY_EXISTS  = "error.category.already.exists";
    public static String ERR_DELETE_CATEGORY_WRONG_URL = "error.delete.category.wrong.url";
    public static String ERR_FUNCTION_NOT_IMPLEMENTED = "error.function.not.implemented";
    public static String ERR_INVALID_PRODUCTS_PAGE = "error.invalid.products.page";
    public static String ERR_UNAUTHORIZED_USER = "error.unauthorized.user";
    public static String ERR_LUCENE_QUERY = "error.lucene.query";
    public static String ERR_LUCENE_MODIFY = "error.lucene.modify";
    public static String ERR_EXCEED_QUANTITY = "error.exceed.quantity";
    public static String ERR_SHIPPING_EMPTY_CART = "error.shipping.empty.cart";
	
	// generic error codes
    public static String ERR_JNDI_ACCESS = "error.jndi.access";
    public static String ERR_DATABASE_ACCESS = "error.database.access";
    public static String ERR_INTERNAL = "error.internal";
    
}
