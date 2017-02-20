/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.catalog.business.impl;

import java.util.List;

import org.opencommunity.bazaar.catalog.business.ICategoryManager;
import org.opencommunity.bazaar.catalog.dto.Category;
import org.opencommunity.bazaar.catalog.exceptions.CategoryManagerException;
import org.opencommunity.bazaar.utils.business.IDataListHandler;

public class CategoryManagerDelegate implements ICategoryManager {
	private CategoryManagerServiceLocator serviceLocator;

    public CategoryManagerDelegate() {
        serviceLocator = new CategoryManagerServiceLocator();
    }
	
    public void create(Category category) throws CategoryManagerException {
    	serviceLocator.getService().create(category);
    }
    
    public void update(Category category) throws CategoryManagerException {
    	serviceLocator.getService().update(category);
    }
    
    public void delete(long key) throws CategoryManagerException {
    	serviceLocator.getService().delete(key);
    }

    public List getCategories(Long nParentId) throws CategoryManagerException {
    	return serviceLocator.getService().getCategories(nParentId);
    }

    public IDataListHandler getAllCategories(Long nParentId) throws CategoryManagerException {
    	return serviceLocator.getService().getAllCategories(nParentId);
    }
    
    public List getCategoryHierarchy(Long nParentId) throws CategoryManagerException {
    	return serviceLocator.getService().getCategoryHierarchy(nParentId);
    }
    
    public Category getCategory(long key) throws CategoryManagerException {
    	return serviceLocator.getService().getCategory(key);
    }
    
    public List getAllCategories() throws CategoryManagerException {
    	return serviceLocator.getService().getAllCategories();
    }
    
    public List getCategoriesByProduct(long nProductID) throws CategoryManagerException {
    	return serviceLocator.getService().getCategoriesByProduct(nProductID);
    }
}
