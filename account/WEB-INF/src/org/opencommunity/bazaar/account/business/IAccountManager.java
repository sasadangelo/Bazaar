/*
 * Copyleft (C) Open Community author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.account.business;

import org.opencommunity.bazaar.account.dto.Account;
import org.opencommunity.bazaar.account.exceptions.AccountManagerException;
import org.opencommunity.bazaar.utils.business.IDataListHandler;

public interface IAccountManager {
	public static int PAGE_LENGTH = 5;
	
    public Account login(String userName, String password) throws AccountManagerException;
    public void register(Account account) throws AccountManagerException;
    public void modify(Account account) throws AccountManagerException;
    public void changePasswd(String email, String oldPasswd, String newPasswd) throws AccountManagerException;
    public void activate(String email, long activationCode) throws AccountManagerException;
    public Account getAccount(String email) throws AccountManagerException;
    public IDataListHandler getAllAccounts() throws AccountManagerException;
    public void delete(long key) throws AccountManagerException;
}
