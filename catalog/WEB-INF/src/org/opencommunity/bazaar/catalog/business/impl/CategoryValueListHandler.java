/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.catalog.business.impl;

import java.util.List;

import org.opencommunity.bazaar.catalog.exceptions.CategoryManagerException;
import org.opencommunity.bazaar.utils.business.IDataListChunk;
import org.opencommunity.bazaar.utils.business.IDataListHandler;
import org.opencommunity.bazaar.utils.exceptions.DataListException;


class CategoryValueListHandler implements IDataListHandler {
	private int startIndex = 0;;
	private int pageLength;
    private CategoryValueList categories;
    
	// finders
	CategoryValueListHandler(List categories, int pageLength) throws CategoryManagerException {
		this.pageLength = pageLength;
		this.categories = new CategoryValueList(categories);
	}

	public IDataListChunk getListChunk() throws DataListException {
		return categories.getListChunk(startIndex, pageLength);
	}

	public void moveChunk(int index) {
		startIndex = (((startIndex + index * pageLength) < categories.size()) && ((startIndex + index * pageLength) >= 0)) ? (startIndex + index * pageLength) : startIndex ;
	}

	public void moveNextChunk() {
		startIndex = (startIndex + pageLength) < categories.size() ? (startIndex + pageLength) : startIndex;
	}

	public void movePrevChunk() {
		startIndex = (startIndex - pageLength) > 0 ? (startIndex - pageLength) : 0;
	}

	public void moveFirstChunk() {
		startIndex = 0;
	}

	public void moveLastChunk() {
		startIndex = categories.size() - (categories.size() % pageLength);
	}

	public void close() {
		categories.close();
	}

	public int size() {
		return categories.size();
	}
}
