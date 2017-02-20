/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.catalog.business.impl;

import java.util.List;

import org.opencommunity.bazaar.catalog.business.IProductManager;
import org.opencommunity.bazaar.catalog.dto.Product;
import org.opencommunity.bazaar.catalog.exceptions.ProductManagerException;
import org.opencommunity.bazaar.utils.business.IDataListHandler;

public class ProductManagerDelegate implements IProductManager {
	private ProductManagerServiceLocator serviceLocator;

    public ProductManagerDelegate() {
        serviceLocator = new ProductManagerServiceLocator();
    }
	
    public void create(List categories, Product product) throws ProductManagerException {
    	serviceLocator.getService().create(categories, product);
    }
    
    public void update(List categories, Product product) throws ProductManagerException {
    	serviceLocator.getService().update(categories, product);
    }
    
    public IDataListHandler getProductsByCategory(long nCategoryID) throws ProductManagerException {
    	return serviceLocator.getService().getProductsByCategory(nCategoryID);
    }

    public IDataListHandler getProductsByAccount(long nAccountID) throws ProductManagerException {
    	return serviceLocator.getService().getProductsByAccount(nAccountID);
    }
    
    public Product getProduct(long nID) throws ProductManagerException {
    	return serviceLocator.getService().getProduct(nID);
    }

    public void delete(long accountId, long key) throws ProductManagerException {
    	serviceLocator.getService().delete(accountId, key);
    }
    
    public IDataListHandler search(String txtSearch) throws ProductManagerException {
    	return serviceLocator.getService().search(txtSearch);
    }

    public void rigenerateIndex() throws ProductManagerException {
    	serviceLocator.getService().rigenerateIndex();
    }
}
