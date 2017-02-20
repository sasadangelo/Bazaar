/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.catalog.integration.dao;

import org.opencommunity.bazaar.catalog.dto.CategoryProduct;
import org.opencommunity.bazaar.utils.exceptions.DAOException;

public interface ICategoryProductDAO {
    ////////////////////////////////////////////////////////////////////////////
    // CRUD methods
    ////////////////////////////////////////////////////////////////////////////
	
    public void create(CategoryProduct catProd) throws DAOException;
    public void delete(long nProductId) throws DAOException;
}
