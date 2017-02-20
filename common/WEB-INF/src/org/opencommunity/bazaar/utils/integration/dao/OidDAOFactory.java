/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.utils.integration.dao;

import org.opencommunity.bazaar.utils.integration.dao.mysql.MySQLOidDAO;

public class OidDAOFactory {

    private OidDAOFactory() {
    }

    public static IOidDAO getOidDAO() {
        return new MySQLOidDAO();
    }
}
