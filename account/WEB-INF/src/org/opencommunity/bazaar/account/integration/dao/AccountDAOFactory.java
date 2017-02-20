/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.account.integration.dao;

import org.opencommunity.bazaar.account.dao.mysql.MySQLAccountDAO;


public class AccountDAOFactory {
	// the two DAOs are stateless so the factory can return only one instance
	private static MySQLAccountDAO accountDAO = new MySQLAccountDAO();
	
    public static IAccountDAO getAccountDAO() {
        return accountDAO;
    }
}
