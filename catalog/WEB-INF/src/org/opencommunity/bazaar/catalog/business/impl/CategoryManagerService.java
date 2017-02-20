/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.catalog.business.impl;

import java.util.List;

import org.opencommunity.bazaar.catalog.business.ICategoryManager;
import org.opencommunity.bazaar.catalog.dto.Category;
import org.opencommunity.bazaar.catalog.exceptions.CategoryManagerException;
import org.opencommunity.bazaar.catalog.integration.dao.CategoryDAOFactory;
import org.opencommunity.bazaar.catalog.integration.dao.ICategoryDAO;
import org.opencommunity.bazaar.utils.business.IDataListHandler;
import org.opencommunity.bazaar.utils.exceptions.BazaarException;
import org.opencommunity.bazaar.utils.integration.dao.ITransactionManager;
import org.opencommunity.bazaar.utils.integration.dao.TransactionManagerFactory;
import org.opencommunity.bazaar.utils.messages.IErrorCodes;

public class CategoryManagerService implements ICategoryManager {
    public void create(Category category) throws CategoryManagerException {
        // check if a category with the same name already exists
    	if (CategoryCache.getInstance().getByName(category.getTxtName()) != null) {
    		throw new CategoryManagerException(IErrorCodes.ERR_CATEGORY_ALREADY_EXISTS);
        }
        
        // resolve fullname
    	if (category.getNParentID() != null) {
    		Category parentCategory = CategoryCache.getInstance().getCategory(category.getNParentID().longValue());
    		category.setTxtFullName(parentCategory.getTxtFullName() + "\\" + category.getTxtName());
    	}

        // store category into the database
    	ITransactionManager tm = TransactionManagerFactory.getTransactionManager();
        try {
            tm.begin();
            ICategoryDAO dao = CategoryDAOFactory.getCategoryDAO();
		    dao.create(category);
		    tm.commit();
        } catch (BazaarException exception) {
            tm.rollback();
            throw new CategoryManagerException(exception.getErrorCode());
        } catch (RuntimeException exception) {
            tm.rollback();
            throw new CategoryManagerException(IErrorCodes.ERR_INTERNAL);
        } finally {
            tm.end();
        }

        CategoryCache.getInstance().create(category);
    }
    
    public void update(Category category) throws CategoryManagerException {
        // check if we are updating the right category
    	Category categoryCheck = CategoryCache.getInstance().getByName(category.getTxtName());
        if (categoryCheck != null && categoryCheck.getNID()!=category.getNID()) {
    		throw new CategoryManagerException(IErrorCodes.ERR_CATEGORY_ALREADY_EXISTS);
        }

        // resolve fullname
    	if (category.getNParentID() != null) {
    		Category parentCategory = CategoryCache.getInstance().getCategory(category.getNParentID().longValue());
    		category.setTxtFullName(parentCategory.getTxtFullName() + "\\" + category.getTxtName());
    	}
        
        ITransactionManager tm = TransactionManagerFactory.getTransactionManager();
        try {
            tm.begin();
            ICategoryDAO dao = CategoryDAOFactory.getCategoryDAO();
            dao.update(category);
            tm.commit();
        } catch (BazaarException exception) {
            tm.rollback();
            throw new CategoryManagerException(exception.getErrorCode());
        } catch (RuntimeException exception) {
            tm.rollback();
            throw new CategoryManagerException(IErrorCodes.ERR_INTERNAL);
        } finally {
            tm.end();
        }

        CategoryCache.getInstance().update(category);
    }

    public void delete(long key) throws CategoryManagerException {
    	ITransactionManager tm = TransactionManagerFactory.getTransactionManager();

        try {
            tm.begin();
            ICategoryDAO dao = CategoryDAOFactory.getCategoryDAO();
            dao.delete(key);
            tm.commit();
        } catch (BazaarException exception) {
            tm.rollback();
            throw new CategoryManagerException(exception.getErrorCode());
        } catch (RuntimeException exception) {
            tm.rollback();
            throw new CategoryManagerException(IErrorCodes.ERR_INTERNAL);
        } finally {
            tm.end();
        }

        CategoryCache.getInstance().delete(key);
    }

    public List getCategories(Long nParentId) throws CategoryManagerException {
    	return CategoryCache.getInstance().getCategories(nParentId);
    }
    
    public IDataListHandler getAllCategories(Long nParentId) throws CategoryManagerException {
    	return CategoryCache.getInstance().getAllCategories(nParentId);
    }
    
    public List getAllCategories() throws CategoryManagerException {
    	return CategoryCache.getInstance().getAllCategories();
    }

    public List getCategoryHierarchy(Long nParentId) throws CategoryManagerException {
    	return CategoryCache.getInstance().getCategoryHierarchy(nParentId);
    }
    
    public Category getCategory(long key) throws CategoryManagerException {
    	return CategoryCache.getInstance().getCategory(key);
    }
    
    public List getCategoriesByProduct(long nProductID) throws CategoryManagerException {
    	ITransactionManager tm = TransactionManagerFactory.getTransactionManager();
        try {
            tm.begin();
            ICategoryDAO dao = CategoryDAOFactory.getCategoryDAO();
		    List list = dao.findByProduct(nProductID);
		    tm.commit();
		    return list;
        } catch (BazaarException exception) {
            tm.rollback();
            throw new CategoryManagerException(exception.getErrorCode());
        } catch (RuntimeException exception) {
            tm.rollback();
            throw new CategoryManagerException(IErrorCodes.ERR_INTERNAL);
        } finally {
            tm.end();
        }
    }
}
