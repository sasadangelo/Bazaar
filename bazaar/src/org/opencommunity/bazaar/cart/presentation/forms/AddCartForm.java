/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.cart.presentation.forms;

import org.apache.struts.validator.ValidatorForm;

public final class AddCartForm extends ValidatorForm {
	private long numID;

	public long getNumID() {
		return numID;
	}

	public void setNumID(long nid) {
		numID = nid;
	}
}
