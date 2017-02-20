/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.cart.business.impl;

import java.util.List;

import org.opencommunity.bazaar.cart.business.IOrderManager;
import org.opencommunity.bazaar.cart.dto.Order;
import org.opencommunity.bazaar.cart.exceptions.OrderManagerException;
import org.opencommunity.bazaar.cart.integration.dao.IOrderDAO;
import org.opencommunity.bazaar.cart.integration.dao.OrderDAOFactory;
import org.opencommunity.bazaar.utils.business.IDataListHandler;
import org.opencommunity.bazaar.utils.exceptions.BazaarException;
import org.opencommunity.bazaar.utils.integration.dao.ITransactionManager;
import org.opencommunity.bazaar.utils.integration.dao.TransactionManagerFactory;
import org.opencommunity.bazaar.utils.messages.IErrorCodes;

public class OrderManagerService implements IOrderManager {
    public void create(Order order) throws OrderManagerException {
        // store category into the database
    	ITransactionManager tm = TransactionManagerFactory.getTransactionManager();
        try {
            tm.begin();
            IOrderDAO dao = OrderDAOFactory.getOrderDAO();
		    dao.create(order);
		    tm.commit();
        } catch (BazaarException exception) {
            tm.rollback();
            throw new OrderManagerException(exception.getErrorCode());
        } catch (RuntimeException exception) {
            tm.rollback();
            throw new OrderManagerException(IErrorCodes.ERR_INTERNAL);
        } finally {
            tm.end();
        }
    }
    
    public void update(Order order) throws OrderManagerException {
        ITransactionManager tm = TransactionManagerFactory.getTransactionManager();
        try {
            tm.begin();
            IOrderDAO dao = OrderDAOFactory.getOrderDAO();
            dao.update(order);
            tm.commit();
        } catch (BazaarException exception) {
            tm.rollback();
            throw new OrderManagerException(exception.getErrorCode());
        } catch (RuntimeException exception) {
            tm.rollback();
            throw new OrderManagerException(IErrorCodes.ERR_INTERNAL);
        } finally {
            tm.end();
        }
    }

    public void delete(long key) throws OrderManagerException {
    	ITransactionManager tm = TransactionManagerFactory.getTransactionManager();

        try {
            tm.begin();
            IOrderDAO dao = OrderDAOFactory.getOrderDAO();
            dao.delete(key);
            tm.commit();
        } catch (BazaarException exception) {
            tm.rollback();
            throw new OrderManagerException(exception.getErrorCode());
        } catch (RuntimeException exception) {
            tm.rollback();
            throw new OrderManagerException(IErrorCodes.ERR_INTERNAL);
        } finally {
            tm.end();
        }
    }
    
    public Order getOrder(long nOrderID) throws OrderManagerException {
    	ITransactionManager tm = TransactionManagerFactory.getTransactionManager();
        try {
            tm.begin();
            IOrderDAO dao = OrderDAOFactory.getOrderDAO();
		    Order order = dao.findByPrimaryKey(nOrderID);
		    tm.commit();
		    return order;
        } catch (BazaarException exception) {
            tm.rollback();
            throw new OrderManagerException(exception.getErrorCode());
        } catch (RuntimeException exception) {
            tm.rollback();
            throw new OrderManagerException(IErrorCodes.ERR_INTERNAL);
        } finally {
            tm.end();
        }
    }
}
