/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.account.presentation.forms;

import org.apache.struts.validator.ValidatorForm;

public final class ChangeProfileForm extends ValidatorForm {
	private String txtEmailAddress;
	private String txtFirstname;
	private String txtLastname;
	private String txtStreetAddress;
	private String txtZipCode;
	private String txtCity;
	private String txtState;
	private String txtCountry;
	private String txtPhone;

	/**
	 * @return Returns the txtCity.
	 */
	public String getTxtCity() {
		return txtCity;
	}
	/**
	 * @param txtCity The txtCity to set.
	 */
	public void setTxtCity(String txtCity) {
		this.txtCity = txtCity;
	}
	/**
	 * @return Returns the txtCountry.
	 */
	public String getTxtCountry() {
		return txtCountry;
	}
	/**
	 * @param txtCountry The txtCountry to set.
	 */
	public void setTxtCountry(String txtCountry) {
		this.txtCountry = txtCountry;
	}
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
	/**
	 * @return Returns the txtFirstname.
	 */
	public String getTxtFirstname() {
		return txtFirstname;
	}
	/**
	 * @param txtFirstname The txtFirstname to set.
	 */
	public void setTxtFirstname(String txtFirstname) {
		this.txtFirstname = txtFirstname;
	}
	/**
	 * @return Returns the txtLastname.
	 */
	public String getTxtLastname() {
		return txtLastname;
	}
	/**
	 * @param txtLastname The txtLastname to set.
	 */
	public void setTxtLastname(String txtLastname) {
		this.txtLastname = txtLastname;
	}
	/**
	 * @return Returns the txtPhone.
	 */
	public String getTxtPhone() {
		return txtPhone;
	}
	/**
	 * @param txtPhone The txtPhone to set.
	 */
	public void setTxtPhone(String txtPhone) {
		this.txtPhone = txtPhone;
	}
	/**
	 * @return Returns the txtState.
	 */
	public String getTxtState() {
		return txtState;
	}
	/**
	 * @param txtState The txtState to set.
	 */
	public void setTxtState(String txtState) {
		this.txtState = txtState;
	}
	/**
	 * @return Returns the txtStreetAddress.
	 */
	public String getTxtStreetAddress() {
		return txtStreetAddress;
	}
	/**
	 * @param txtStreetAddress The txtStreetAddress to set.
	 */
	public void setTxtStreetAddress(String txtStreetAddress) {
		this.txtStreetAddress = txtStreetAddress;
	}
	/**
	 * @return Returns the txtZipCode.
	 */
	public String getTxtZipCode() {
		return txtZipCode;
	}
	/**
	 * @param txtZipCode The txtZipCode to set.
	 */
	public void setTxtZipCode(String txtZipCode) {
		this.txtZipCode = txtZipCode;
	}
}
