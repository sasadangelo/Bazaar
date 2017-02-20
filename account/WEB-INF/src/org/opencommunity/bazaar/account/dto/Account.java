package org.opencommunity.bazaar.account.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class Account implements Serializable {
	public static String OID_NAME = "ACCOUNT";
	
	private long nID;
	private String txtEmailAddress;
	private String txtPassword;
	private boolean bAdmin;
	private long nActivationCode;
	private boolean bActivationStatus;
	private String txtFirstname;
	private String txtLastname;
	private String txtStreetAddress;
	private String txtZipCode;
	private String txtCity;
	private String txtState;
	private String txtCountry;
	private String txtPhone;
	private Timestamp tsRegTime;
	private Timestamp tsLastChange;

	public Account() {
	}

	public Account(long nID,
			String txtEmailAddress,
			String txtPassword,
			boolean bAdmin,
			long nActivationCode,
			boolean bActivationStatus,
			String txtFirstname,
			String txtLastname,
			String txtStreetAddress,
			String txtZipCode,
			String txtCity,
			String txtState,
			String txtCountry,
			String txtPhone,
			Timestamp tsLastChange,
			Timestamp tsRegTime) {
		
		this.nID = nID;
		this.txtEmailAddress = txtEmailAddress;
		this.txtPassword = txtPassword;
		this.bAdmin = bAdmin;
		this.nActivationCode = nActivationCode;
		this.bActivationStatus = bActivationStatus;
		this.txtFirstname = txtFirstname;
		this.txtLastname = txtLastname;
		this.txtStreetAddress = txtStreetAddress;
		this.txtZipCode = txtZipCode;
		this.txtCity = txtCity;
		this.txtState = txtState;
		this.txtCountry = txtCountry;
		this.txtPhone = txtPhone;
		this.tsLastChange = tsLastChange;
		this.tsRegTime = tsRegTime;
	}
	/**
	 * @return Returns the bAdmin.
	 */
	public boolean isBAdmin() {
		return bAdmin;
	}
	/**
	 * @param admin The bAdmin to set.
	 */
	public void setBAdmin(boolean admin) {
		bAdmin = admin;
	}
	/**
	 * @return Returns the tsRegTime.
	 */
	public Timestamp getTsRegTime() {
		return tsRegTime;
	}
	/**
	 * @param tsRegTime The tsRegTime to set.
	 */
	public void setTsRegTime(Timestamp tsRegTime) {
		this.tsRegTime = tsRegTime;
	}
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
	 * @return Returns the txtPassword.
	 */
	public String getTxtPassword() {
		return txtPassword;
	}
	/**
	 * @param txtPassword The txtPassword to set.
	 */
	public void setTxtPassword(String txtPassword) {
		this.txtPassword = txtPassword;
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
	/**
	 * @return Returns the nID.
	 */
	public long getNID() {
		return nID;
	}
	/**
	 * @param nid The nID to set.
	 */
	public void setNID(long nid) {
		nID = nid;
	}
	/**
	 * @return Returns the tsLastChange.
	 */
	public Timestamp getTsLastChange() {
		return tsLastChange;
	}
	/**
	 * @param tsLastChange The tsLastChange to set.
	 */
	public void setTsLastChange(Timestamp tsLastChange) {
		this.tsLastChange = tsLastChange;
	}
	/**
	 * @return Returns the activationCode.
	 */
	public long getNActivationCode() {
		return nActivationCode;
	}
	/**
	 * @param activationCode The activationCode to set.
	 */
	public void setNActivationCode(long activationCode) {
		this.nActivationCode = activationCode;
	}
	/**
	 * @return Returns the activeState.
	 */
	public boolean isBActivationStatus() {
		return bActivationStatus;
	}
	/**
	 * @param activeState The activeState to set.
	 */
	public void setBActiveState(boolean activationStatus) {
		this.bActivationStatus = activationStatus;
	}
}
