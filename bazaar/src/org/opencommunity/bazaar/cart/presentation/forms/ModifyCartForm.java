/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.cart.presentation.forms;

import org.apache.struts.validator.ValidatorForm;

public final class ModifyCartForm extends ValidatorForm {
	private int numQuantity;
	private String action;

	public int getNumQuantity() {
		return numQuantity;
	}

	public void setNumQuantity(int quantity) {
		numQuantity = quantity;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
}
