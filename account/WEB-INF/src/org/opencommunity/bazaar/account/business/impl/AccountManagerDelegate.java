/*
 * Copyleft (C) Open Community author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.account.business.impl;

import org.opencommunity.bazaar.account.business.IAccountManager;
import org.opencommunity.bazaar.account.dto.Account;
import org.opencommunity.bazaar.account.exceptions.AccountManagerException;
import org.opencommunity.bazaar.utils.business.IDataListHandler;

public class AccountManagerDelegate implements IAccountManager {

	private AccountManagerServiceLocator serviceLocator = null;

	public AccountManagerDelegate() {
		serviceLocator = new AccountManagerServiceLocator();    	
	}

    public Account login(String userName, String password) throws AccountManagerException {
        return serviceLocator.getService().login(userName, password);
    }

    public void register(Account account) throws AccountManagerException {
        serviceLocator.getService().register(account);
    }

    public void modify(Account account) throws AccountManagerException {
        serviceLocator.getService().modify(account);
    }
    
    public void changePasswd(String email, String oldPasswd, String newPasswd) throws AccountManagerException {
        serviceLocator.getService().changePasswd(email, oldPasswd, newPasswd);
    }
    
    public void activate(String email, long activationCode) throws AccountManagerException {
        serviceLocator.getService().activate(email, activationCode);
    }
    
    public Account getAccount(String email) throws AccountManagerException {
    	return serviceLocator.getService().getAccount(email);
    }
    
    public IDataListHandler getAllAccounts() throws AccountManagerException {
    	return serviceLocator.getService().getAllAccounts();
    }

    public void delete(long key) throws AccountManagerException {
    	serviceLocator.getService().delete(key);
    }
}
