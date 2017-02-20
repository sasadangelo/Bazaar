/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.utils.business;

import org.opencommunity.bazaar.utils.exceptions.DataListException;

public interface IDataList {
	public IDataListIterator iterator();
	public Object get(int index);
	public void close();
	public boolean isEmpty();
	public int size();
    public IDataListChunk getListChunk(int startIndex, int count) throws DataListException ;
}

