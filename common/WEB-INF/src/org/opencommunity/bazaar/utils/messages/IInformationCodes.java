/*
 * Copyleft (C) Open Community author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.utils.messages;


public interface IInformationCodes {
	// message categories
	public static String INFORMATIONS_ID = "information";
	
	// account management messages
    public static String USER_REGISTRATION_SUCCESSFUL = "user.registration.successful";
    public static String USER_REGISTRATION_SUCCESSFUL_BUT_EMAIL_PROBLEM = "user.registration.successful.but.email.problem";
    public static String USER_CHANGED_SUCCESSFUL = "user.changed.successful";
    public static String USER_PASSWORD_CHANGED_SUCCESSFUL = "user.password.changed.successful";
    public static String USER_ACTIVATION_SUCCESSFUL = "user.activation.successful";
    public static String PASSWORD_SENT_SUCCESSFUL = "password.sent.successful";
    public static String USER_DELETE_SUCCESSFUL = "user.delete.successful";
}
