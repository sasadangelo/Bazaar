/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.catalog.presentation.forms;

import org.apache.struts.validator.ValidatorForm;

public final class AddCategoryForm extends ValidatorForm {
    private Long numParent;
    private String txtName;
    private String txtDescription;
	/**
	 * @return Returns the txtDescription.
	 */
	public String getTxtDescription() {
		return txtDescription;
	}
	/**
	 * @param txtDescription The txtDescription to set.
	 */
	public void setTxtDescription(String txtDescription) {
		this.txtDescription = txtDescription;
	}
	/**
	 * @return Returns the txtName.
	 */
	public String getTxtName() {
		return txtName;
	}
	/**
	 * @param txtName The txtName to set.
	 */
	public void setTxtName(String txtName) {
		this.txtName = txtName;
	}
	/**
	 * @return Returns the nParent.
	 */
	public Long getNumParent() {
		return numParent;
	}
	/**
	 * @param parent The nParent to set.
	 */
	public void setNumParent(Long parent) {
		numParent = parent;
	}
}
