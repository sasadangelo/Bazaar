/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.cart.business.impl;

public class OrderManagerServiceLocator {
    public OrderManagerService getService() {
        return new OrderManagerService();
    }
}
