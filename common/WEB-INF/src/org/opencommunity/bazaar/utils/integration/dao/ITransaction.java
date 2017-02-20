/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.utils.integration.dao;

import org.opencommunity.bazaar.utils.exceptions.TransactionException;

public interface ITransaction {
    public void commit() throws TransactionException;
    public void rollback();
}
