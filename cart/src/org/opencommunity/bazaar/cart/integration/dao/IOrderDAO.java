/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.cart.integration.dao;

import org.opencommunity.bazaar.cart.dto.Order;
import org.opencommunity.bazaar.utils.exceptions.DAOException;
import org.opencommunity.bazaar.utils.exceptions.OidManagerException;

public interface IOrderDAO {
    ////////////////////////////////////////////////////////////////////////////
    // CRUD methods
    ////////////////////////////////////////////////////////////////////////////
	
    public void create(Order order) throws DAOException, OidManagerException;
    public void update(Order order) throws DAOException;
    public void delete(long key) throws DAOException;
    
    ////////////////////////////////////////////////////////////////////////////
    // Finder methods
    ////////////////////////////////////////////////////////////////////////////
    public Order findByPrimaryKey(long nOrderID) throws DAOException;
}
