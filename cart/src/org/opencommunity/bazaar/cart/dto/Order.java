/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.cart.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class Order implements Serializable {
	private long nID;
	private long nBuyerID;
	private long nSellerID;
	private long nProductID;
	private int nQuantity;
	private int nOrderState;
	private double dblTotal;
	private double dblShippingCharges;
	private double dblGrandTotal;
	private String txtRemarks;
	private Timestamp tsDate;

	public static String OID_NAME = "ORDER";
	
	public double getDblGrandTotal() {
		return dblGrandTotal;
	}
	public void setDblGrandTotal(double dblGrandTotal) {
		this.dblGrandTotal = dblGrandTotal;
	}
	public double getDblShippingCharges() {
		return dblShippingCharges;
	}
	public void setDblShippingCharges(double dblShippingCharges) {
		this.dblShippingCharges = dblShippingCharges;
	}
	public double getDblTotal() {
		return dblTotal;
	}
	public void setDblTotal(double dblTotal) {
		this.dblTotal = dblTotal;
	}
	public long getNBuyerID() {
		return nBuyerID;
	}
	public void setNBuyerID(long buyerID) {
		nBuyerID = buyerID;
	}
	public long getNID() {
		return nID;
	}
	public void setNID(long nid) {
		nID = nid;
	}
	public int getNOrderState() {
		return nOrderState;
	}
	public void setNOrderState(int orderState) {
		nOrderState = orderState;
	}
	public long getNProductID() {
		return nProductID;
	}
	public void setNProductID(long productID) {
		nProductID = productID;
	}
	public int getNQuantity() {
		return nQuantity;
	}
	public void setNQuantity(int quantity) {
		nQuantity = quantity;
	}
	public long getNSellerID() {
		return nSellerID;
	}
	public void setNSellerID(long sellerID) {
		nSellerID = sellerID;
	}
	public Timestamp getTsDate() {
		return tsDate;
	}
	public void setTsDate(Timestamp tsDate) {
		this.tsDate = tsDate;
	}
	public String getTxtRemarks() {
		return txtRemarks;
	}
	public void setTxtRemarks(String txtRemarks) {
		this.txtRemarks = txtRemarks;
	}

}