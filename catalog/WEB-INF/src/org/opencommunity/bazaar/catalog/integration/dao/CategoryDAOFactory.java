/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.catalog.integration.dao;

import org.opencommunity.bazaar.catalog.integration.dao.mysql.MySQLCategoryDAO;

public class CategoryDAOFactory {
	private static MySQLCategoryDAO categoryDAO = new MySQLCategoryDAO();
	
    public static ICategoryDAO getCategoryDAO() {
        return categoryDAO;
    }
}
