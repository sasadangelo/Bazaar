/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.account.presentation.forms;

import org.apache.struts.validator.ValidatorForm;

public final class ChangePasswdForm extends ValidatorForm {
	private String txtNewPassword;
	private String txtOldPassword;
	private String txtConfirmPassword;

	/**
	 * @return Returns the txtOldPassword.
	 */
	public String getTxtOldPassword() {
		return txtOldPassword;
	}
	/**
	 * @param txtOldPassword The txtOldPassword to set.
	 */
	public void setTxtOldPassword(String txtOldPassword) {
		this.txtOldPassword = txtOldPassword;
	}
	/**
	 * @return Returns the txtConfirmPassword.
	 */
	public String getTxtConfirmPassword() {
		return txtConfirmPassword;
	}
	/**
	 * @param txtConfirmPassword The txtConfirmPassword to set.
	 */
	public void setTxtConfirmPassword(String txtConfirmPassword) {
		this.txtConfirmPassword = txtConfirmPassword;
	}
	/**
	 * @return Returns the txtNewPassword.
	 */
	public String getTxtNewPassword() {
		return txtNewPassword;
	}
	/**
	 * @param txtNewPassword The txtNewPassword to set.
	 */
	public void setTxtNewPassword(String txtNewPassword) {
		this.txtNewPassword = txtNewPassword;
	}
}
