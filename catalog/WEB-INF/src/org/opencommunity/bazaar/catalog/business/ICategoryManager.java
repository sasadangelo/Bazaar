/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.catalog.business;

import java.util.List;

import org.opencommunity.bazaar.catalog.dto.Category;
import org.opencommunity.bazaar.catalog.exceptions.CategoryManagerException;
import org.opencommunity.bazaar.utils.business.IDataListHandler;

public interface ICategoryManager {
    public void create(Category category) throws CategoryManagerException;
    public void update(Category category) throws CategoryManagerException;
    public void delete(long key) throws CategoryManagerException;

    public List getCategories(Long nParentId) throws CategoryManagerException;
    public IDataListHandler getAllCategories(Long nParentId) throws CategoryManagerException;
    public List getAllCategories() throws CategoryManagerException;
    public List getCategoryHierarchy(Long nParentId) throws CategoryManagerException;
    public Category getCategory(long key) throws CategoryManagerException;
    public List getCategoriesByProduct(long nProductID) throws CategoryManagerException;
}
