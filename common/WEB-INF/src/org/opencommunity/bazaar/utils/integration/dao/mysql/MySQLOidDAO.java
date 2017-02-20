/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.utils.integration.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.opencommunity.bazaar.utils.dto.Oid;
import org.opencommunity.bazaar.utils.exceptions.DAOException;
import org.opencommunity.bazaar.utils.integration.dao.TransactionManagerFactory;
import org.opencommunity.bazaar.utils.integration.dao.IOidDAO;
import org.opencommunity.bazaar.utils.messages.IErrorCodes;

public class MySQLOidDAO implements IOidDAO {
    private static String SQL_FIND_ALL = "SELECT nLastId, txtTableName, nBlockSize FROM Oid";
    
    public long findNextBlockOid(String name, int blockSize) throws DAOException {
        long lastOid;
        
        String sqlUpdate = "UPDATE Oid SET nLastId = nLastId + " + blockSize + " WHERE txtTableName = '" + name + "'";
        String sqlQuery = "SELECT nLastId FROM Oid WHERE txtTableName = '" + name + "'";

        MySQLTransaction tx = (MySQLTransaction) TransactionManagerFactory.getTransactionManager().getTransaction();
        PreparedStatement updateStatement = null;
        PreparedStatement queryStatement = null;
        ResultSet rs = null;

        try {
            updateStatement = tx.getConnection().prepareStatement(sqlUpdate);
            updateStatement.executeUpdate();

            queryStatement = tx.getConnection().prepareStatement(sqlQuery);
            rs = queryStatement.executeQuery();

            if (rs.next()) {
                lastOid = rs.getLong(1);    
            } else {
                throw new DAOException(IErrorCodes.ERR_DATABASE_ACCESS);
            }
            return lastOid;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new DAOException(IErrorCodes.ERR_DATABASE_ACCESS);
        } finally {
            MySQLUtility.close(rs);
            MySQLUtility.close(queryStatement);
        }
    }

    public Collection findAll() throws DAOException {
        MySQLTransaction tx = (MySQLTransaction) TransactionManagerFactory.getTransactionManager().getTransaction();
        PreparedStatement queryStatement = null;
        ResultSet rs = null;

        try {
            queryStatement = tx.getConnection().prepareStatement(SQL_FIND_ALL);
            rs = queryStatement.executeQuery();
            
            ArrayList oids = new ArrayList();
            
            while (rs.next()) {
                oids.add(new Oid(rs.getInt(3), rs.getString(2), rs.getLong(1)));    
            }
            
            return oids;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new DAOException(IErrorCodes.ERR_DATABASE_ACCESS);
        } finally {
        	MySQLUtility.close(rs);
            MySQLUtility.close(queryStatement);
        }
    }
}
