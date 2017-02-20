/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.catalog.integration.dao;

import org.opencommunity.bazaar.catalog.integration.dao.lucene.LUCENEProductDAO;
import org.opencommunity.bazaar.catalog.integration.dao.mysql.MySQLProductDAO;

public class ProductDAOFactory {
	// the two DAOs are stateless so the factory can return only one instance
	private static MySQLProductDAO productDAO = new MySQLProductDAO();
	private static LUCENEProductDAO luceneDAO = new LUCENEProductDAO();
	
    public static IProductDAO getProductDAO() {
        return productDAO;
    }

    public static IProductDAO getLuceneProductDAO() {
        return luceneDAO;
    }
}
