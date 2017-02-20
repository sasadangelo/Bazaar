/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.utils.integration.dao.mysql;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.opencommunity.bazaar.utils.exceptions.TransactionException;
import org.opencommunity.bazaar.utils.integration.utils.ServiceLocator;
import org.opencommunity.bazaar.utils.messages.IErrorCodes;

public class MySQLResourceManager {
    private static String JNDI_NAME = "jdbc/BazaarDB";

    public static MySQLResourceManager getInstance() {
        if (instance == null) {
            synchronized (MySQLResourceManager.class) {
                if (instance == null) {
                    instance = new MySQLResourceManager();
                }
            }
        }
        return instance;
    }

    public synchronized Connection getConnection() throws TransactionException {
        Connection conn = null;

        try {
            DataSource ds = (DataSource) ServiceLocator.getInstance().getService(JNDI_NAME);
            conn = ds.getConnection();
            conn.setAutoCommit(false);
            return conn;
        } catch (SQLException exception) {
            throw new TransactionException(IErrorCodes.ERR_DATABASE_ACCESS);
        } catch (NamingException exception) {
            throw new TransactionException(IErrorCodes.ERR_JNDI_ACCESS);
        }
    }

    public synchronized void releaseConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException sqle) {
        }
    }

    private MySQLResourceManager() {
    }

    private static MySQLResourceManager instance = null;
}
