/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.catalog.business.impl;

public class CategoryManagerServiceLocator {
    public CategoryManagerService getService() {
        return new CategoryManagerService();
    }
}
