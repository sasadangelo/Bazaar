/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.catalog.dto;

import java.awt.image.BufferedImage;

public class Product {
	// Members
	private long nID;
	private String txtName;
	private String txtShortDescription;
	private String txtDescription;
	private BufferedImage imgPhoto;
	private BufferedImage imgThumbnailPhoto;
	private int numQuantity;
	private double dblPrice;
	private boolean bActive;
	private long nAccountID;

	public static String OID_NAME = "PRODUCT";

	public Product() {
	}
	
	/**
	 * @return Returns the bActive.
	 */
	public boolean isBActive() {
		return bActive;
	}
	/**
	 * @param active The bActive to set.
	 */
	public void setBActive(boolean active) {
		bActive = active;
	}
	/**
	 * @return Returns the dblPrice.
	 */
	public double getDblPrice() {
		return dblPrice;
	}
	/**
	 * @param dblPrice The dblPrice to set.
	 */
	public void setDblPrice(double dblPrice) {
		this.dblPrice = dblPrice;
	}
	/**
	 * @return Returns the dblQuantity.
	 */
	public int getNumQuantity() {
		return numQuantity;
	}
	/**
	 * @param dblQuantity The dblQuantity to set.
	 */
	public void setNumQuantity(int numQuantity) {
		this.numQuantity = numQuantity;
	}
	/**
	 * @return Returns the nAccountID.
	 */
	public long getNAccountID() {
		return nAccountID;
	}
	/**
	 * @param accountID The nAccountID to set.
	 */
	public void setNAccountID(long accountID) {
		nAccountID = accountID;
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
	 * @return Returns the txtShortDescription.
	 */
	public String getTxtShortDescription() {
		return txtShortDescription;
	}
	/**
	 * @param txtShortDescription The txtShortDescription to set.
	 */
	public void setTxtShortDescription(String txtShortDescription) {
		this.txtShortDescription = txtShortDescription;
	}

	public BufferedImage getImgPhoto() {
		return imgPhoto;
	}

	public void setImgPhoto(BufferedImage imgPhoto) {
		this.imgPhoto = imgPhoto;
	}

	public BufferedImage getImgThumbnailPhoto() {
		return imgThumbnailPhoto;
	}

	public void setImgThumbnailPhoto(BufferedImage imgThumbnailPhoto) {
		this.imgThumbnailPhoto = imgThumbnailPhoto;
	}
}