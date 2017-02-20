/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.cart.integration.dao;

import order.opencommunity.bazaar.cart.integration.dao.db2.MySQLOrderDAO;

public class OrderDAOFactory {
	private static MySQLOrderDAO orderDAO = new MySQLOrderDAO();
	
    public static IOrderDAO getOrderDAO() {
        return orderDAO;
    }
}
