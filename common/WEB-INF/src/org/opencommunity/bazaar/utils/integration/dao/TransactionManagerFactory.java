/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.utils.integration.dao;

import org.opencommunity.bazaar.utils.integration.dao.mysql.MySQLTransactionManager;

public class TransactionManagerFactory {
	private static final ITransactionManager tm = new MySQLTransactionManager();

	public static ITransactionManager getTransactionManager() {
        return tm;
    }
}
