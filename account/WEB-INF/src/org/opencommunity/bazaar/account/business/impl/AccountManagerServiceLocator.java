/*
 * Copyleft (C) Open Community author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.account.business.impl;

public class AccountManagerServiceLocator {
    public AccountManagerService getService() {
        return new AccountManagerService();
    }
}
