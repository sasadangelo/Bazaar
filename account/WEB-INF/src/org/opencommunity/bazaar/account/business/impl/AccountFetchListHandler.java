/*
 * Copyleft (C) Open Community author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.account.business.impl;

import java.util.List;

import org.opencommunity.bazaar.account.integration.dao.AccountDAOFactory;
import org.opencommunity.bazaar.account.integration.dao.IAccountDAO;
import org.opencommunity.bazaar.utils.business.IDataListChunk;
import org.opencommunity.bazaar.utils.business.IDataListHandler;
import org.opencommunity.bazaar.utils.exceptions.DAOException;
import org.opencommunity.bazaar.utils.exceptions.DataListException;
import org.opencommunity.bazaar.utils.exceptions.TransactionException;
import org.opencommunity.bazaar.utils.integration.dao.ITransactionManager;
import org.opencommunity.bazaar.utils.integration.dao.TransactionManagerFactory;
import org.opencommunity.bazaar.utils.messages.IErrorCodes;


class AccountFetchListHandler implements IDataListHandler {
	private int startIndex = 0;;
	private int pageLength;
	private int size;

	// finders
	AccountFetchListHandler(int pageLength) {
		this.pageLength = pageLength;
	}

	public IDataListChunk getListChunk() throws DataListException {
    	ITransactionManager tm = TransactionManagerFactory.getTransactionManager();
    	List list;
    	
    	try {
    		tm.begin();
    		IAccountDAO dao = AccountDAOFactory.getAccountDAO();
    		size = dao.countAccounts();
    		list = dao.findAll(startIndex, pageLength);
    		tm.commit();
    		
       		return new AccountFetchListChunk(list, startIndex > 0, (startIndex + pageLength) < size);
    	} catch(TransactionException exception) {
    		tm.rollback();
    		throw new DataListException(IErrorCodes.ERR_DATABASE_ACCESS);
    	} catch(DAOException exception) {
    		tm.rollback();
    		throw new DataListException(IErrorCodes.ERR_DATABASE_ACCESS);
    	} catch(RuntimeException exception) {
    		tm.rollback();
    		throw new DataListException(IErrorCodes.ERR_INTERNAL);
    	} finally {
    		tm.end();
    	}
	}

	public void moveChunk(int index) {
		startIndex = startIndex + index * pageLength;
	}

	public void moveNextChunk() {
		startIndex = startIndex + pageLength;
	}

	public void movePrevChunk() {
		startIndex = (startIndex - pageLength) > 0 ? (startIndex - pageLength) : 0;
	}

	public void moveFirstChunk() {
		startIndex = 0;
	}

	public void moveLastChunk() {
		startIndex = size - (size % pageLength);
	}

	public void close() {
	}

	public int size() {
		return size;
	}
}
