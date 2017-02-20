/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.catalog.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.opencommunity.bazaar.catalog.integration.dao.IProductDAO;
import org.opencommunity.bazaar.catalog.integration.dao.ProductDAOFactory;
import org.opencommunity.bazaar.utils.business.IDataListChunk;
import org.opencommunity.bazaar.utils.exceptions.BazaarException;
import org.opencommunity.bazaar.utils.exceptions.DataListException;
import org.opencommunity.bazaar.utils.integration.dao.ITransactionManager;
import org.opencommunity.bazaar.utils.integration.dao.TransactionManagerFactory;
import org.opencommunity.bazaar.utils.messages.IErrorCodes;

class ProductPKeyListChunk extends ProductPKeyList implements IDataListChunk {
    private boolean hasNext;
    private boolean hasPrev;

    ProductPKeyListChunk(List pkeys, int startIndex, int count) throws DataListException {
    	products = new ArrayList();
    	
    	ITransactionManager tm = TransactionManagerFactory.getTransactionManager();
            	
    	try {
    		tm.begin();
    		IProductDAO dao = ProductDAOFactory.getProductDAO();
    		int size = startIndex+count > pkeys.size() ? pkeys.size() : startIndex+count; 
    		for (int i=startIndex; i<size; ++i) {
    			Long pkey = (Long) pkeys.get(i);
                products.add(dao.findByPrimaryKey(pkey.longValue()));
    		}
    		tm.commit();
    	} catch(BazaarException exception) {
    		tm.rollback();
    		throw new DataListException(IErrorCodes.ERR_DATABASE_ACCESS);
    	} catch(RuntimeException exception) {
    		tm.rollback();
    		throw new DataListException(IErrorCodes.ERR_INTERNAL);
    	} finally {
    		tm.end();
    	}
        this.hasNext = ((startIndex + count) < pkeys.size());
        this.hasPrev = startIndex > 0;
    }

    public void clear() {
        this.products.clear();
    }

    public boolean hasNext() {
        return hasNext;
    }

    public boolean hasPrev() {
        return hasPrev;
    }
}
