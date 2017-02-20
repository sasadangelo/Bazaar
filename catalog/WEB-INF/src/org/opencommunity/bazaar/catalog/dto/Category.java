/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.catalog.dto;

public class Category {
	private long nID;
	private Long nParentID;
	private String txtName;
	private String txtFullName;
	private String txtDescription;

	public static String OID_NAME = "CATEGORY";

	public Category() {
	}

	public Category(long nID, Long nParentID, String txtName, String txtFullName, String txtDescription) {
		this.nID = nID;
		this.nParentID = nParentID;
		this.txtName = txtName;
		this.txtDescription = txtDescription;
		this.txtFullName = txtFullName;
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
	 * @return Returns the nParentID.
	 */
	public Long getNParentID() {
		return nParentID;
	}
	/**
	 * @param parentID The nParentID to set.
	 */
	public void setNParentID(Long parentID) {
		nParentID = parentID;
	}
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
	 * @return Returns the txtFullName.
	 */
	public String getTxtFullName() {
		return txtFullName;
	}
	/**
	 * @param txtFullName The txtFullName to set.
	 */
	public void setTxtFullName(String txtFullName) {
		this.txtFullName = txtFullName;
	}
}