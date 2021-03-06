/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.catalog.business.impl;

import java.util.List;

import org.opencommunity.bazaar.utils.business.IDataList;
import org.opencommunity.bazaar.utils.business.IDataListChunk;
import org.opencommunity.bazaar.utils.business.IDataListIterator;

class ProductFetchList implements IDataList {
	protected List values;

	ProductFetchList(List values) {
		this.values = values;
	}
	
	public IDataListIterator iterator() {
		return new ProductFetchListIterator(this.values.iterator());
	}
	
	public Object get(int index) {
		if (index > values.size()) {
			throw new java.lang.ArrayIndexOutOfBoundsException();
		}
	    return values.toArray()[index];
	}
	
	public void close() {
		this.values.clear();
	}
	
	public boolean isEmpty() {
		return this.values.isEmpty();
	}
	
	public int size() {
		return values.size();
	}
	
	public IDataListChunk getListChunk(int startIndex, int count) {
		// not implemented
		return null;
	}
}
