/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.utils.oid;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import org.opencommunity.bazaar.utils.dto.Oid;
import org.opencommunity.bazaar.utils.exceptions.DAOException;
import org.opencommunity.bazaar.utils.exceptions.OidManagerException;
import org.opencommunity.bazaar.utils.exceptions.TransactionException;
import org.opencommunity.bazaar.utils.integration.dao.IOidDAO;
import org.opencommunity.bazaar.utils.integration.dao.ITransaction;
import org.opencommunity.bazaar.utils.integration.dao.ITransactionManager;
import org.opencommunity.bazaar.utils.integration.dao.OidDAOFactory;
import org.opencommunity.bazaar.utils.integration.dao.TransactionManagerFactory;


public class OidManager {
    private static OidManager instance = null;

    private HashMap cache = new HashMap();

    private OidManager() throws OidManagerException {
        Collection oids = null;

        ITransactionManager tm = TransactionManagerFactory.getTransactionManager();
        ITransaction tx = tm.suspend();
        
        try {
            tm.begin();
            IOidDAO dao = OidDAOFactory.getOidDAO();
            oids = dao.findAll();
            tm.commit();
        } catch (TransactionException exception) {
            tm.rollback();
            throw new OidManagerException(exception.getErrorCode());
        } catch (DAOException exception) {
            tm.rollback();
            throw new OidManagerException(exception.getErrorCode());
        } finally {
            tm.end();
            tm.resume(tx);
        }
 
        // populate cache
        for (Iterator itr = oids.iterator(); itr.hasNext();) {
            Oid oid = (Oid) itr.next();
            cache.put(oid.getEntityName(), oid);
        }
    }

    public static OidManager getInstance() throws OidManagerException {
        if (instance == null) {
            synchronized (OidManager.class) {
                if (instance == null) {
                    instance = new OidManager();
                }
            }
        }
        return instance;
    }

    public long getNextOid(String entityName) throws OidManagerException {
        String name = entityName.toUpperCase();

        Oid oid = (Oid) this.cache.get(name);

        if (oid == null) {
            throw new IllegalArgumentException("Not valid entityName " + entityName + " in input");
        }

        if (oid.getCurrentOid() >= oid.getLastId()) {
            ITransactionManager tm = TransactionManagerFactory.getTransactionManager();
            ITransaction tx = null;
            
            try {
            	tx = tm.suspend();
                tm.begin();
                IOidDAO dao = OidDAOFactory.getOidDAO();
                long lastOid = dao.findNextBlockOid(oid.getEntityName(), oid.getBlockSize());
                tm.commit();
                
                oid.setLastId(lastOid);
                oid.setCurrentOid(lastOid - oid.getBlockSize() + 1);
            } catch (TransactionException exception) {
                tm.rollback();
                throw new OidManagerException(exception.getErrorCode());
            } catch (DAOException exception) {
                tm.rollback();
                throw new OidManagerException(exception.getErrorCode());
            } finally {
                tm.end();
                tm.resume(tx);
            }
        } else {
            oid.setCurrentOid(oid.getCurrentOid() + 1);
        }

        return oid.getCurrentOid();
    }
}
