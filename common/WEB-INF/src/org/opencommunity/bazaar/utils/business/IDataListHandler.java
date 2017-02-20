/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.utils.business;

import org.opencommunity.bazaar.utils.exceptions.DataListException;

public interface IDataListHandler {
    public IDataListChunk getListChunk() throws DataListException;
    //public boolean elementExists(int index);
    public void moveChunk(int index);
    public void moveNextChunk();
    public void movePrevChunk();
    public void close();
    public int size();
}
