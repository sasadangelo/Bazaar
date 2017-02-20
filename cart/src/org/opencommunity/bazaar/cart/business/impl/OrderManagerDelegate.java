/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.cart.business.impl;

import org.opencommunity.bazaar.cart.business.IOrderManager;
import org.opencommunity.bazaar.cart.dto.Order;
import org.opencommunity.bazaar.cart.exceptions.OrderManagerException;

public class OrderManagerDelegate implements IOrderManager {
	private OrderManagerServiceLocator serviceLocator;

    public OrderManagerDelegate() {
        serviceLocator = new OrderManagerServiceLocator();
    }
	
    public void create(Order order) throws OrderManagerException {
    	serviceLocator.getService().create(order);
    }
    
    public void update(Order order) throws OrderManagerException {
    	serviceLocator.getService().update(order);
    }
    
    public void delete(long key) throws OrderManagerException {
    	serviceLocator.getService().delete(key);
    }

    public Order getOrder(long nOrderId) throws OrderManagerException {
    	return serviceLocator.getService().getOrder(nOrderId);
    }
}
