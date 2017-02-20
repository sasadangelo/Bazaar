/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.utils.integration.dao.mysql;

import org.opencommunity.bazaar.utils.exceptions.TransactionException;
import org.opencommunity.bazaar.utils.integration.dao.ITransaction;
import org.opencommunity.bazaar.utils.integration.dao.ITransactionManager;
import org.opencommunity.bazaar.utils.messages.IErrorCodes;

public class MySQLTransactionManager implements ITransactionManager {
    private static ThreadLocal local = new ThreadLocal();

    public void begin() throws TransactionException {
        MySQLTransaction transaction = (MySQLTransaction) local.get();
        if (transaction == null) {
            transaction = new MySQLTransaction();
            local.set(transaction);
        } else {
            throw new TransactionException(IErrorCodes.ERR_DATABASE_ACCESS);
        }
    }

    public void commit() throws TransactionException {
    	MySQLTransaction transaction = (MySQLTransaction) local.get();
        if (transaction == null) {
            throw new IllegalStateException("Try to commit a not existing transaction");
        } else {
            transaction.commit();
        }
    }

    public void end() {
    	MySQLTransaction transaction = (MySQLTransaction) local.get();
        if (transaction != null) {
            transaction.releaseConnection();
            local.set(null);
        }
    }

    public void rollback() {
    	MySQLTransaction transaction = (MySQLTransaction) local.get();
        if (transaction != null) {
            transaction.rollback();
        }
    }

    public ITransaction suspend() {
    	MySQLTransaction transaction = (MySQLTransaction) local.get();
        local.set(null);
        return transaction;
    }

    public void resume(ITransaction transaction) {
    	MySQLTransaction tx = (MySQLTransaction) local.get();
        if (tx != null) {
            throw new IllegalStateException("Cannot resume the transaction");
        } else {
            local.set(transaction);
        }
    }

    public ITransaction getTransaction() {
        return (ITransaction) local.get();
    }
}
