/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.catalog.integration.dao;

import java.util.HashMap;
import java.util.List;

import org.opencommunity.bazaar.catalog.dto.Product;
import org.opencommunity.bazaar.utils.exceptions.DAOException;
import org.opencommunity.bazaar.utils.exceptions.OidManagerException;

public interface IProductDAO {
    ////////////////////////////////////////////////////////////////////////////
    // CRUD methods
    ////////////////////////////////////////////////////////////////////////////
	
    public void create(Product product) throws DAOException, OidManagerException;
    public void update(Product product) throws DAOException;
    public void delete(long nID) throws DAOException;
    public void delete() throws DAOException;
    
    ////////////////////////////////////////////////////////////////////////////
    // Finder methods
    ////////////////////////////////////////////////////////////////////////////

    public Product findByPrimaryKey(long key) throws DAOException;
    public HashMap findAll() throws DAOException;
    public List findByAccount(long nAccountID, int startIndex, int pageLength) throws DAOException;
    public List findByCategory(long nAccountID, int startIndex, int pageLength) throws DAOException;
    public List findByText(String text) throws DAOException;

    ////////////////////////////////////////////////////////////////////////////
    // User defined methods
    ////////////////////////////////////////////////////////////////////////////
    public int countProductsByAccount(long nAccountID) throws DAOException;
    public int countProductsByCategory(long nCategoryID) throws DAOException;
}
