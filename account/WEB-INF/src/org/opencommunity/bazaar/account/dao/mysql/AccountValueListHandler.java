/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.account.dao.mysql;

import java.util.List;

import org.opencommunity.bazaar.utils.business.IDataListChunk;
import org.opencommunity.bazaar.utils.business.IDataListHandler;

class AccountValueListHandler implements IDataListHandler {
    //private AccountFetchList accounts;
	List accounts;
    private int startIndex = 0;
    private int pageLength;

    // finders	
    AccountValueListHandler(List accounts, int pageLength) {
        //this.accounts = new AccountFetchList(accounts);
        this.pageLength = pageLength;
    }
    
    public IDataListChunk getListChunk() {
        //return accounts.getListChunk(startIndex, pageLength);
    	return null;
    }

    public boolean elementExists(int index) {
        return accounts.get(index) != null;
    }

    public void moveChunk(int index) {
    	startIndex = (startIndex + index * pageLength) < accounts.size() ? (startIndex + index * pageLength) : startIndex;
    }
    
    public void moveNextChunk() {
    	startIndex = (startIndex + pageLength) < accounts.size() ? (startIndex + pageLength) : startIndex;
    }

    public void movePrevChunk() {
    	startIndex = (startIndex - pageLength) > 0 ? (startIndex - pageLength) : 0;
    }
    
    public void moveFirstChunk() {
    	startIndex = (startIndex + pageLength) < accounts.size() ? (startIndex + pageLength) : startIndex;
    }

    public void moveLastChunk() {
    	startIndex = accounts.size() - (accounts.size() % pageLength);
    }

    public void close() {
        //this.accounts.close();
    }

    public int size() {
        return accounts.size();
    }
}
