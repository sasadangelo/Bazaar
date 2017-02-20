/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.catalog.business.impl;

import java.util.List;

import org.opencommunity.bazaar.utils.business.IDataList;
import org.opencommunity.bazaar.utils.business.IDataListChunk;
import org.opencommunity.bazaar.utils.business.IDataListIterator;
import org.opencommunity.bazaar.utils.exceptions.DataListException;

class ProductPKeyList implements IDataList {
	protected List products;

	ProductPKeyList() {
	}

	ProductPKeyList(List products) {
		this.products = products;
	}
	
	public IDataListIterator iterator() {
		return new ProductFetchListIterator(this.products.iterator());
	}
	
	public Object get(int index) {
		if (index > products.size()) {
			throw new java.lang.ArrayIndexOutOfBoundsException();
		}
	    return products.toArray()[index];
	}
	
	public void close() {
		this.products.clear();
	}
	
	public boolean isEmpty() {
		return this.products.isEmpty();
	}
	
	public int size() {
		return products.size();
	}
	
	public IDataListChunk getListChunk(int startIndex, int count) throws DataListException {
		return new ProductPKeyListChunk(products, startIndex, count);
	}
}
