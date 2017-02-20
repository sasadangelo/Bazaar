/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.account.presentation.forms;

import org.apache.struts.validator.ValidatorForm;


public final class ForgotForm extends ValidatorForm {
    private String txtEmailAddress;

	/**
	 * @return Returns the txtEmailAddress.
	 */
	public String getTxtEmailAddress() {
		return txtEmailAddress;
	}
	/**
	 * @param txtEmailAddress The txtEmailAddress to set.
	 */
	public void setTxtEmailAddress(String txtEmailAddress) {
		this.txtEmailAddress = txtEmailAddress;
	}
}
