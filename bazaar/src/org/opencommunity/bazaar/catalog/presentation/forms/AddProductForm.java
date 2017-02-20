/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.catalog.presentation.forms;

import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorForm;

public final class AddProductForm extends ValidatorForm {
    private String txtName;
    private String txtShortDescription;
    private String txtDescription;
    private FormFile txtImgUrl;
    private int numQuantity;
    private double dblPrice;
    private String txtActive;
    private String txtAryCatIds;
    
	public String getTxtActive() {
		return txtActive;
	}
	public void setTxtActive(String active) {
		txtActive = active;
	}
	public double getDblPrice() {
		return dblPrice;
	}
	public void setDblPrice(double dblPrice) {
		this.dblPrice = dblPrice;
	}
	public int getNumQuantity() {
		return numQuantity;
	}
	public void setNumQuantity(int numQuantity) {
		this.numQuantity = numQuantity;
	}
	public String getTxtDescription() {
		return txtDescription;
	}
	public void setTxtDescription(String txtDescription) {
		this.txtDescription = txtDescription;
	}
	public FormFile getTxtImgUrl() {
		return txtImgUrl;
	}
	public void setTxtImgUrl(FormFile txtImgUrl) {
		this.txtImgUrl = txtImgUrl;
	}
	public String getTxtName() {
		return txtName;
	}
	public void setTxtName(String txtName) {
		this.txtName = txtName;
	}
	public String getTxtShortDescription() {
		return txtShortDescription;
	}
	public void setTxtShortDescription(String txtShortDescription) {
		this.txtShortDescription = txtShortDescription;
	}
	public String getTxtAryCatIds() {
		return txtAryCatIds;
	}
	public void setTxtAryCatIds(String txtAryCatIds) {
		this.txtAryCatIds = txtAryCatIds;
	}
}
