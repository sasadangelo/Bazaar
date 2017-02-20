/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.utils.integration.dao.mysql;

import java.sql.Connection;
import java.sql.SQLException;

import org.opencommunity.bazaar.utils.exceptions.TransactionException;
import org.opencommunity.bazaar.utils.integration.dao.ITransaction;
import org.opencommunity.bazaar.utils.messages.IErrorCodes;

public class MySQLTransaction implements ITransaction {
	private java.sql.Connection connection = null;

    public MySQLTransaction() throws TransactionException {
        connection = MySQLResourceManager.getInstance().getConnection();
    }

    public Connection getConnection() {
		return connection;
    }

    public void releaseConnection() {
	    MySQLResourceManager.getInstance().releaseConnection(connection);
    }

    public void commit() throws TransactionException {
		try {
			connection.commit();
		} catch (SQLException exc) {
			throw new TransactionException(IErrorCodes.ERR_DATABASE_ACCESS);
		}
    }

    public void rollback() {
		try {
			connection.rollback();
		} catch (SQLException exc) {
		}
    }
}
