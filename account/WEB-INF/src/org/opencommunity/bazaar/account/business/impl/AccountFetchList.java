/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.account.business.impl;

import java.util.List;

import org.opencommunity.bazaar.utils.business.IDataList;
import org.opencommunity.bazaar.utils.business.IDataListChunk;
import org.opencommunity.bazaar.utils.business.IDataListIterator;

class AccountFetchList implements IDataList {
	protected List values;

	AccountFetchList(List values) {
		this.values = values;
	}
	
	public IDataListIterator iterator() {
		return new AccountFetchListIterator(this.values.iterator());
	}
	
	public Object get(int index) {
		if (index > values.size()) {
			return null;
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
