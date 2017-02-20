/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.cart.dto;

import java.io.Serializable;

public class OrderShippingAddress implements Serializable {
	private long nID;
	private long nOrderID;
	private String txtFirstname;
	private String txtLastname;
	private String txtStreetAddress;
	private String txtZipCode;
	private String txtCity;
	private String txtState;
	private String txtCountry;
	private String txtPhone;
	
	public long getNID() {
		return nID;
	}
	public void setNID(long nid) {
		nID = nid;
	}
	public long getNOrderID() {
		return nOrderID;
	}
	public void setNOrderID(long orderID) {
		nOrderID = orderID;
	}
	public String getTxtCity() {
		return txtCity;
	}
	public void setTxtCity(String txtCity) {
		this.txtCity = txtCity;
	}
	public String getTxtCountry() {
		return txtCountry;
	}
	public void setTxtCountry(String txtCountry) {
		this.txtCountry = txtCountry;
	}
	public String getTxtFirstname() {
		return txtFirstname;
	}
	public void setTxtFirstname(String txtFirstname) {
		this.txtFirstname = txtFirstname;
	}
	public String getTxtLastname() {
		return txtLastname;
	}
	public void setTxtLastname(String txtLastname) {
		this.txtLastname = txtLastname;
	}
	public String getTxtPhone() {
		return txtPhone;
	}
	public void setTxtPhone(String txtPhone) {
		this.txtPhone = txtPhone;
	}
	public String getTxtState() {
		return txtState;
	}
	public void setTxtState(String txtState) {
		this.txtState = txtState;
	}
	public String getTxtStreetAddress() {
		return txtStreetAddress;
	}
	public void setTxtStreetAddress(String txtStreetAddress) {
		this.txtStreetAddress = txtStreetAddress;
	}
	public String getTxtZipCode() {
		return txtZipCode;
	}
	public void setTxtZipCode(String txtZipCode) {
		this.txtZipCode = txtZipCode;
	}

}