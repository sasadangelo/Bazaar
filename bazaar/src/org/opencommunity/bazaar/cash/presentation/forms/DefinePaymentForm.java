/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.cash.presentation.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

public final class DefinePaymentForm extends ValidatorForm implements java.io.Serializable {
	private boolean bonifico;
	private boolean bonificoSend;
	private Integer numCc;
	private Integer numAbi;
	private Integer numCab;
	private String txtCin;
	private String paypal;
	private String txtPaypalEmailAddress;
	
	public boolean isBonifico() {
		return bonifico;
	}
	public void setBonifico(boolean bonifico) {
		this.bonifico = bonifico;
	}
	public boolean isBonificoSend() {
		return bonificoSend;
	}
	public void setBonificoSend(boolean bonificoSend) {
		this.bonificoSend = bonificoSend;
	}
	public Integer getNumAbi() {
		return numAbi;
	}
	public void setNumAbi(Integer numAbi) {
		this.numAbi = numAbi;
	}
	public Integer getNumCab() {
		return numCab;
	}
	public void setNumCab(Integer numCab) {
		this.numCab = numCab;
	}
	public String getPaypal() {
		return paypal;
	}
	public void setPaypal(String paypal) {
		this.paypal = paypal;
	}
	public String getTxtCin() {
		return txtCin;
	}
	public void setTxtCin(String txtCin) {
		this.txtCin = txtCin;
	}
	public String getTxtPaypalEmailAddress() {
		return txtPaypalEmailAddress;
	}
	public void setTxtPaypalEmailAddress(String txtPaypalEmailAddress) {
		this.txtPaypalEmailAddress = txtPaypalEmailAddress;
	}
	public Integer getNumCc() {
		return numCc;
	}
	public void setNumCc(Integer numCc) {
		this.numCc = numCc;
	}
}
