/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.utils.integration.dao;

import org.opencommunity.bazaar.utils.exceptions.TransactionException;

public interface ITransactionManager {
    public void begin() throws TransactionException;
    public void commit() throws TransactionException;
    public void end();
    public void rollback();
    public ITransaction suspend();
    public void resume(ITransaction transaction);
    public ITransaction getTransaction();
}
