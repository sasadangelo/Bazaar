/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.utils.business;



public interface IDataListChunk extends IDataList {
    public void clear();// throws Exception;
    public boolean hasNext(); // throws Exception;
    public boolean hasPrev(); // throws Exception;
}


