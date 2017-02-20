/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.utils.integration.dao;

import java.util.Collection;

import org.opencommunity.bazaar.utils.exceptions.DAOException;

public interface IOidDAO {
    public long findNextBlockOid(String name, int blockSize) throws DAOException;
    public Collection findAll() throws DAOException;
}
