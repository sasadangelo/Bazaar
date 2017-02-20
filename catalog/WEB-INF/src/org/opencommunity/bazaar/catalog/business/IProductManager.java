/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.catalog.business;

import java.util.List;

import org.opencommunity.bazaar.catalog.dto.Product;
import org.opencommunity.bazaar.catalog.exceptions.ProductManagerException;
import org.opencommunity.bazaar.utils.business.IDataListHandler;

public interface IProductManager {
    public void create(List categories, Product product) throws ProductManagerException;
    public void update(List categories, Product product) throws ProductManagerException;
    public void delete(long accountId, long key) throws ProductManagerException;
    
    public IDataListHandler getProductsByAccount(long nAccountID) throws ProductManagerException;
    public IDataListHandler getProductsByCategory(long nCategoryID) throws ProductManagerException;
    public Product getProduct(long nID) throws ProductManagerException;
    
    public IDataListHandler search(String txtSearch) throws ProductManagerException;
    public void rigenerateIndex() throws ProductManagerException;
}
