/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.catalog.integration.dao;

import org.opencommunity.bazaar.catalog.integration.dao.mysql.MySQLCategoryProductDAO;

public class CategoryProductDAOFactory {
	private static MySQLCategoryProductDAO categoryProductDAO = new MySQLCategoryProductDAO();
	
    public static ICategoryProductDAO getCategoryProductDAO() {
        return categoryProductDAO;
    }
}
