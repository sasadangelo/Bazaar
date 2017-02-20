/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.catalog.dto;

public class CategoryProduct {
	// Members
	private long nProductId;
	private long nCategoryId;
	
	public long getNCategoryId() {
		return nCategoryId;
	}
	public void setNCategoryId(long categoryId) {
		nCategoryId = categoryId;
	}
	public long getNProductId() {
		return nProductId;
	}
	public void setNProductId(long productId) {
		nProductId = productId;
	}
}