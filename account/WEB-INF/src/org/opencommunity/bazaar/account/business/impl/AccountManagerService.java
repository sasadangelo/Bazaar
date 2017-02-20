/*
 * Copyleft (C) Open Community author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.account.business.impl;

import org.opencommunity.bazaar.account.business.IAccountManager;
import org.opencommunity.bazaar.account.business.IAccountPage;
import org.opencommunity.bazaar.account.dto.Account;
import org.opencommunity.bazaar.account.exceptions.AccountManagerException;
import org.opencommunity.bazaar.account.integration.dao.AccountDAOFactory;
import org.opencommunity.bazaar.account.integration.dao.IAccountDAO;
import org.opencommunity.bazaar.utils.business.IDataListHandler;
import org.opencommunity.bazaar.utils.exceptions.BazaarException;
import org.opencommunity.bazaar.utils.exceptions.DAOException;
import org.opencommunity.bazaar.utils.exceptions.TransactionException;
import org.opencommunity.bazaar.utils.integration.dao.ITransactionManager;
import org.opencommunity.bazaar.utils.integration.dao.TransactionManagerFactory;
import org.opencommunity.bazaar.utils.messages.IErrorCodes;


public class AccountManagerService implements IAccountManager {

    public Account login(String userName, String password) throws AccountManagerException {
    	Account account = null;
   		ITransactionManager tm = TransactionManagerFactory.getTransactionManager();
    	
    	try {
    		tm.begin();
    		IAccountDAO dao = AccountDAOFactory.getAccountDAO();
    		account = dao.findByEmailAddress(userName);
    		tm.commit();
    	} catch(TransactionException exception) {
    		throw new AccountManagerException(IErrorCodes.ERR_INTERNAL);
    	} catch(DAOException exception) {
    		tm.rollback();
    		throw new AccountManagerException(IErrorCodes.ERR_DATABASE_ACCESS);
    	} catch(RuntimeException exception) {
    		tm.rollback();
    		throw new AccountManagerException(IErrorCodes.ERR_INTERNAL);
    	} finally {
    		tm.end();
    	}

        if (account == null) {
    		throw new AccountManagerException(IErrorCodes.ERR_USER_NOT_FOUND);
        } else if (!account.getTxtPassword().equals(password)) {
            throw new AccountManagerException(IErrorCodes.ERR_INVALID_PASSWORD);
        } else if (!account.isBActivationStatus()) {
            throw new AccountManagerException(IErrorCodes.ERR_USER_NOT_ACTIVE);
        }
        
        return account;
    }

    public void register(Account account) throws AccountManagerException {
    	Account accountCheck = null;
    	ITransactionManager tm = TransactionManagerFactory.getTransactionManager();
    	
    	// check if the user already exists
    	try {
    		tm.begin();
    		IAccountDAO dao = AccountDAOFactory.getAccountDAO();
    		accountCheck = dao.findByEmailAddress(account.getTxtEmailAddress());
    		
    		if (accountCheck == null) {
        		dao.create(account);
        		tm.commit();
    		} else {
        		tm.commit();
        		throw new AccountManagerException(IErrorCodes.ERR_USER_ALREADY_EXISTS);
    		}
    	} catch(BazaarException exception) {
    		tm.rollback();
    		throw new AccountManagerException(exception.getErrorCode());
    	} catch(RuntimeException exception) {
    		tm.rollback();
    		throw new AccountManagerException(IErrorCodes.ERR_INTERNAL);
    	} finally {
    		tm.end();
    	}
    }

    public void modify(Account account) throws AccountManagerException {
    	Account accountCheck = null;
    	ITransactionManager tm = TransactionManagerFactory.getTransactionManager();
    	
    	try {
    		tm.begin();
    		IAccountDAO dao = AccountDAOFactory.getAccountDAO();
        	// check if the user already exists
    		accountCheck = dao.findByEmailAddress(account.getTxtEmailAddress());
    		
    		if (accountCheck != null) {
        		dao.update(account);
        		tm.commit();
    		} else {
        		tm.commit();
        		throw new AccountManagerException(IErrorCodes.ERR_USER_NOT_FOUND);
    		}
    	} catch(TransactionException exception) {
    		tm.rollback();
    		throw new AccountManagerException(IErrorCodes.ERR_DATABASE_ACCESS);
    	} catch(DAOException exception) {
    		tm.rollback();
    		throw new AccountManagerException(IErrorCodes.ERR_DATABASE_ACCESS);
    	} catch(RuntimeException exception) {
    		tm.rollback();
    		throw new AccountManagerException(IErrorCodes.ERR_INTERNAL);
    	} finally {
    		tm.end();
    	}
    }
    
    public void changePasswd(String email, String oldPasswd, String newPasswd) throws AccountManagerException {
    	Account accountCheck = null;
    	ITransactionManager tm = TransactionManagerFactory.getTransactionManager();
    	
    	try {
    		tm.begin();
    		IAccountDAO dao = AccountDAOFactory.getAccountDAO();
        	// check if the user already exists
    		accountCheck = dao.findByEmailAddress(email);
    		
    		if (accountCheck != null) {
     		    if (accountCheck.getTxtPassword().equals(oldPasswd)) {
        		    dao.updatePasswd(email, newPasswd);
        		    tm.commit();
     		    } else {
            		tm.commit();
            		throw new AccountManagerException(IErrorCodes.ERR_WRONG_OLD_PASSWD);
     		    }
    		} else {
        		tm.commit();
        		throw new AccountManagerException(IErrorCodes.ERR_USER_NOT_FOUND);
    		}
    	} catch(TransactionException exception) {
    		tm.rollback();
    		throw new AccountManagerException(IErrorCodes.ERR_DATABASE_ACCESS);
    	} catch(DAOException exception) {
    		tm.rollback();
    		throw new AccountManagerException(IErrorCodes.ERR_DATABASE_ACCESS);
    	} catch(RuntimeException exception) {
    		tm.rollback();
    		throw new AccountManagerException(IErrorCodes.ERR_INTERNAL);
    	} finally {
    		tm.end();
    	}
    }
    
    public void activate(String email, long activationCode) throws AccountManagerException {
    	Account accountCheck = null;
    	ITransactionManager tm = TransactionManagerFactory.getTransactionManager();
    	
    	try {
    		tm.begin();
    		IAccountDAO dao = AccountDAOFactory.getAccountDAO();
        	// check if the user already exists
    		accountCheck = dao.findByEmailAddress(email);
    		
    		if (accountCheck != null) {
     		    if (accountCheck.getNActivationCode()==activationCode) {
        		    dao.updateActivationStatus(email);
        		    tm.commit();
     		    } else {
            		tm.commit();
            		throw new AccountManagerException(IErrorCodes.ERR_WRONG_ACTIVATION_URL);
     		    }
    		} else {
        		tm.commit();
        		throw new AccountManagerException(IErrorCodes.ERR_USER_NOT_FOUND);
    		}
    	} catch(TransactionException exception) {
    		tm.rollback();
    		throw new AccountManagerException(IErrorCodes.ERR_DATABASE_ACCESS);
    	} catch(DAOException exception) {
    		tm.rollback();
    		throw new AccountManagerException(IErrorCodes.ERR_DATABASE_ACCESS);
    	} catch(RuntimeException exception) {
    		tm.rollback();
    		throw new AccountManagerException(IErrorCodes.ERR_INTERNAL);
    	} finally {
    		tm.end();
    	}
    }

    public Account getAccount(String email) throws AccountManagerException {
    	Account accountCheck = null;
    	ITransactionManager tm = TransactionManagerFactory.getTransactionManager();
    	
    	try {
    		tm.begin();
    		IAccountDAO dao = AccountDAOFactory.getAccountDAO();
    		accountCheck = dao.findByEmailAddress(email);
    		tm.commit();
    		
    		if (accountCheck == null) {
        		throw new AccountManagerException(IErrorCodes.ERR_USER_NOT_FOUND);
    		} else {
        		return accountCheck;
    		}
    	} catch(TransactionException exception) {
    		tm.rollback();
    		throw new AccountManagerException(IErrorCodes.ERR_DATABASE_ACCESS);
    	} catch(DAOException exception) {
    		tm.rollback();
    		throw new AccountManagerException(IErrorCodes.ERR_DATABASE_ACCESS);
    	} catch(RuntimeException exception) {
    		tm.rollback();
    		throw new AccountManagerException(IErrorCodes.ERR_INTERNAL);
    	} finally {
    		tm.end();
    	}
    }

    public IDataListHandler getAllAccounts() throws AccountManagerException {
    	return new AccountFetchListHandler(IAccountPage.PAGE_LENGTH);
    }
    
    public void delete(long key) throws AccountManagerException {
    	ITransactionManager tm = TransactionManagerFactory.getTransactionManager();
    	
    	try {
    		tm.begin();
    		IAccountDAO dao = AccountDAOFactory.getAccountDAO();
        	// check if the user already exists
    		dao.delete(key);
       		tm.commit();
    	} catch(TransactionException exception) {
    		tm.rollback();
    		throw new AccountManagerException(IErrorCodes.ERR_DATABASE_ACCESS);
    	} catch(DAOException exception) {
    		tm.rollback();
    		throw new AccountManagerException(IErrorCodes.ERR_DATABASE_ACCESS);
    	} catch(RuntimeException exception) {
    		tm.rollback();
    		throw new AccountManagerException(IErrorCodes.ERR_INTERNAL);
    	} finally {
    		tm.end();
    	}
    }
}
