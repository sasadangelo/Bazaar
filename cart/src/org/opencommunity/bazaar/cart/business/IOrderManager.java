/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.cart.business;

import org.opencommunity.bazaar.cart.dto.Order;
import org.opencommunity.bazaar.cart.exceptions.OrderManagerException;

public interface IOrderManager {
    public void create(Order order) throws OrderManagerException;
    public void update(Order order) throws OrderManagerException;
    public void delete(long key) throws OrderManagerException;

    public Order getOrder(long nOrderId) throws OrderManagerException;
}
