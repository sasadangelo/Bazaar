/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.catalog.business.impl;

import java.util.List;

import org.opencommunity.bazaar.utils.business.IDataListChunk;
import org.opencommunity.bazaar.utils.business.IDataListHandler;
import org.opencommunity.bazaar.utils.exceptions.DataListException;

class ProductPKeyListHandler implements IDataListHandler {
	private ProductPKeyList pkeys;
	private int startIndex = 0;;
	private int pageLength;

	ProductPKeyListHandler(List pkeys, int pageLength) {
		this.pageLength = pageLength;
		this.pkeys = new ProductPKeyList(pkeys);
	}

	public IDataListChunk getListChunk() throws DataListException {
		return pkeys.getListChunk(startIndex, pageLength);
	}

	public void moveChunk(int index) {
		startIndex = startIndex + index * pageLength;
	}

	public void moveNextChunk() {
		startIndex += pageLength;
	}

	public void movePrevChunk() {
		startIndex = (startIndex - pageLength) > 0 ? (startIndex - pageLength) : 0;
	}

	public void moveFirstChunk() {
		startIndex = 0;
	}

	public void moveLastChunk() {
		startIndex = pkeys.size() - (pkeys.size() % pageLength);
	}

	public void close() {
	}

	public int size() {
		return pkeys.size();
	}
}
