/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.account.integration.dao;

import java.util.List;

import org.opencommunity.bazaar.account.dto.Account;
import org.opencommunity.bazaar.utils.exceptions.DAOException;
import org.opencommunity.bazaar.utils.exceptions.OidManagerException;

public interface IAccountDAO {
    ////////////////////////////////////////////////////////////////////////////
    // CRUD methods
    ////////////////////////////////////////////////////////////////////////////
	
    public void create(Account account) throws DAOException, OidManagerException;
    public void update(Account account) throws DAOException;
    public void delete(long nID) throws DAOException;
    
    ////////////////////////////////////////////////////////////////////////////
    // Finder methods
    ////////////////////////////////////////////////////////////////////////////

    public Account findByEmailAddress(String emailAddress) throws DAOException;
    public List findAll(int startIndex, int length) throws DAOException;
    
    ////////////////////////////////////////////////////////////////////////////
    // User defined methods
    ////////////////////////////////////////////////////////////////////////////

    public int countAccounts() throws DAOException;
    public void updatePasswd(String email, String passwd) throws DAOException;
    public void updateActivationStatus(String email) throws DAOException;
}
