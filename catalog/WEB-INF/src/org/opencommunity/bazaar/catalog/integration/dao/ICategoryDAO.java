/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.catalog.integration.dao;

import java.util.List;

import org.opencommunity.bazaar.catalog.dto.Category;
import org.opencommunity.bazaar.utils.exceptions.DAOException;
import org.opencommunity.bazaar.utils.exceptions.OidManagerException;

public interface ICategoryDAO {
    ////////////////////////////////////////////////////////////////////////////
    // CRUD methods
    ////////////////////////////////////////////////////////////////////////////
	
    public void create(Category category) throws DAOException, OidManagerException;
    public void update(Category category) throws DAOException;
    public void delete(long key) throws DAOException;
    
    ////////////////////////////////////////////////////////////////////////////
    // Finder methods
    ////////////////////////////////////////////////////////////////////////////
    //public List findByParentId(Long nParentId, int startIndex, int length) throws DAOException;
    public List findAll() throws DAOException;
    public List findByProduct(long nProductID) throws DAOException;
    
    ////////////////////////////////////////////////////////////////////////////
    // User defined methods
    ////////////////////////////////////////////////////////////////////////////
    //public int countCategoriesByParentId(Long nParentId) throws DAOException;
}
