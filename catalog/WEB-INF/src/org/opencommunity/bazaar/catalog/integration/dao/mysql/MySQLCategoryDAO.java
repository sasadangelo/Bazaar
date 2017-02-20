/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.catalog.integration.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.opencommunity.bazaar.catalog.dto.Category;
import org.opencommunity.bazaar.catalog.integration.dao.ICategoryDAO;
import org.opencommunity.bazaar.utils.exceptions.DAOException;
import org.opencommunity.bazaar.utils.exceptions.OidManagerException;
import org.opencommunity.bazaar.utils.integration.dao.TransactionManagerFactory;
import org.opencommunity.bazaar.utils.integration.dao.mysql.MySQLTransaction;
import org.opencommunity.bazaar.utils.integration.dao.mysql.MySQLUtility;
import org.opencommunity.bazaar.utils.messages.IErrorCodes;
import org.opencommunity.bazaar.utils.oid.OidManager;

public class MySQLCategoryDAO implements ICategoryDAO {
    private static String INSERT_SQL =
        "INSERT INTO Categories(nID, nParentID, txtName, txtFullName, txtDescription) VALUES (?, ?, ?, ?, ?)";

    private static String UPDATE_SQL =
        "UPDATE Categories SET nParentID=?, txtName=?, txtFullName=?, txtDescription=? WHERE nID=?";

    private static String DELETE_SQL =
        "DELETE from Categories WHERE nID=?";

    //private static String FIND_TOP_CATEGORIES_SQL =
      //  "SELECT nID, nParentID, txtName, txtDescription FROM Categories WHERE nParentID is null LIMIT ?, ?";

    //private static String FIND_ALL_BY_PARENT_SQL =
      //  "SELECT nID, nParentID, txtName, txtDescription FROM Categories WHERE nParentID=? LIMIT ?, ?";

    //private static String COUNT_TOP_CATEGORIES_SQL =
      //  "SELECT count(*) FROM Categories WHERE nParentID is null";

    //private static String COUNT_ALL_BY_PARENT_SQL =
      //  "SELECT count(*) FROM Categories WHERE nParentID=?";

    //private static String FIND_BY_NAME_SQL =
      //  "SELECT nID, nParentID, txtName, txtDescription FROM Categories WHERE txtName=?";

    private static String FIND_ALL_SQL =
        "SELECT nID, nParentID, txtName, txtFullName, txtDescription FROM Categories";

    private static String FIND_BY_PRODUCT_SQL =
        "SELECT nID, nParentID, txtName, txtFullName, txtDescription FROM Categories cat, CategoryProducts catprod WHERE catprod.nCategoryID=cat.nID AND catprod.nProductID=?";

    ////////////////////////////////////////////////////////////////////////////
    // CRUD methods
    ////////////////////////////////////////////////////////////////////////////

    public void create(Category category) throws DAOException, OidManagerException {
    	MySQLTransaction tx = (MySQLTransaction) TransactionManagerFactory.getTransactionManager().getTransaction();
		PreparedStatement stmt = null;
    	int i=1;

    	if (category.getNID() == 0) {
    		category.setNID(OidManager.getInstance().getNextOid(Category.OID_NAME));
		}

    	try {
			stmt = tx.getConnection().prepareStatement(INSERT_SQL);
			stmt.setLong(i++, category.getNID());
			MySQLUtility.setOptLong(stmt, i++, category.getNParentID());
			stmt.setString(i++, category.getTxtName());
			stmt.setString(i++, category.getTxtFullName());
			MySQLUtility.setOptString(stmt, i++, category.getTxtDescription());
			stmt.executeUpdate();
    	} catch (SQLException exception) {
    		throw new DAOException(IErrorCodes.ERR_DATABASE_ACCESS);
		} finally {
			MySQLUtility.close(stmt);
		}
    }

    public void update(Category category) throws DAOException {
    	MySQLTransaction tx = (MySQLTransaction) TransactionManagerFactory.getTransactionManager().getTransaction();
		PreparedStatement stmt = null;
    	int i=1;

    	try {
			stmt = tx.getConnection().prepareStatement(UPDATE_SQL);
			MySQLUtility.setOptLong(stmt, i++, category.getNParentID());
			stmt.setString(i++, category.getTxtName());
			stmt.setString(i++, category.getTxtFullName());
			MySQLUtility.setOptString(stmt, i++, category.getTxtDescription());
			stmt.setLong(i++, category.getNID());
			stmt.executeUpdate();
		} catch (SQLException exception) {
    		throw new DAOException(IErrorCodes.ERR_DATABASE_ACCESS);
		} finally {
			MySQLUtility.close(stmt);
		}
    }
    
    public void delete(long key) throws DAOException {
    	MySQLTransaction tx = (MySQLTransaction) TransactionManagerFactory.getTransactionManager().getTransaction();
		PreparedStatement stmt = null;

    	try {
			stmt = tx.getConnection().prepareStatement(DELETE_SQL);
			stmt.setLong(1, key);
			stmt.executeUpdate();
		} catch (SQLException exception) {
    		throw new DAOException(IErrorCodes.ERR_DATABASE_ACCESS);
		} finally {
			MySQLUtility.close(stmt);
		}
    }
    /*
    public List findByParentId(Long nParentId, int startIndex, int length) throws DAOException {
		MySQLTransaction tx = (MySQLTransaction) TransactionManagerFactory.getTransactionManager().getTransaction();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList categories = new ArrayList();
        int i = 1;

        try {
        	if (nParentId == null) {
                stmt = tx.getConnection().prepareStatement(FIND_TOP_CATEGORIES_SQL);
    	        stmt.setInt(1, startIndex);
    	        stmt.setInt(2, startIndex+length);
        	} else {
                stmt = tx.getConnection().prepareStatement(FIND_ALL_BY_PARENT_SQL);
    	        stmt.setLong(1, nParentId.longValue());
    	        stmt.setInt(2, startIndex);
    	        stmt.setInt(3, startIndex+length);
        	}
	        rs = stmt.executeQuery();

	        while (rs.next()) {
        	    categories.add(new Category(rs.getLong(i++), 
        	    		MySQLUtility.getOptLong(rs, i++),
						rs.getString(i++),
						MySQLUtility.getOptString(rs, i++)));
		        i = 1;
	        }
	
	        return categories;
         } catch (SQLException exception) {
	        throw new DAOException(IErrorCodes.ERR_DATABASE_ACCESS);
         } finally {
	        MySQLUtility.close(rs);
	        MySQLUtility.close(stmt);
         }
    }*/
    
    public List findAll() throws DAOException {
		MySQLTransaction tx = (MySQLTransaction) TransactionManagerFactory.getTransactionManager().getTransaction();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList categories = new ArrayList();
        int i = 1;

        try {
            stmt = tx.getConnection().prepareStatement(FIND_ALL_SQL);
	        rs = stmt.executeQuery();

	        while (rs.next()) {
        	    categories.add(new Category(rs.getLong(i++), 
        	    		MySQLUtility.getOptLong(rs, i++),
						rs.getString(i++),
						rs.getString(i++),
						MySQLUtility.getOptString(rs, i++)));
		        i = 1;
	        }
	
	        return categories;
         } catch (SQLException exception) {
	        throw new DAOException(IErrorCodes.ERR_DATABASE_ACCESS);
         } finally {
	        MySQLUtility.close(rs);
	        MySQLUtility.close(stmt);
         }
    }
    
    public List findByProduct(long nProductID) throws DAOException {
		MySQLTransaction tx = (MySQLTransaction) TransactionManagerFactory.getTransactionManager().getTransaction();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList categories = new ArrayList();
        int i = 1;

        try {
            stmt = tx.getConnection().prepareStatement(FIND_BY_PRODUCT_SQL);
            stmt.setLong(1, nProductID);
	        rs = stmt.executeQuery();

	        while (rs.next()) {
        	    categories.add(new Category(rs.getLong(i++), 
        	    		MySQLUtility.getOptLong(rs, i++),
						rs.getString(i++),
						rs.getString(i++),
						MySQLUtility.getOptString(rs, i++)));
		        i = 1;
	        }
	
	        return categories;
         } catch (SQLException exception) {
	        throw new DAOException(IErrorCodes.ERR_DATABASE_ACCESS);
         } finally {
	        MySQLUtility.close(rs);
	        MySQLUtility.close(stmt);
         }
    }
    
/*
    public int countCategoriesByParentId(Long nParentId) throws DAOException {
		MySQLTransaction tx = (MySQLTransaction) TransactionManagerFactory.getTransactionManager().getTransaction();
        PreparedStatement stmtCount = null;
        ResultSet rsCount = null;

        try {
        	if (nParentId == null) {
    	        stmtCount = tx.getConnection().prepareStatement(COUNT_TOP_CATEGORIES_SQL);
        	} else {
    	        stmtCount = tx.getConnection().prepareStatement(COUNT_ALL_BY_PARENT_SQL);
    	        stmtCount.setLong(1, nParentId.longValue());
        	}
	        rsCount = stmtCount.executeQuery();
            if (rsCount.next()) {
    	        return rsCount.getInt(1);
            } else {
		        throw new DAOException(IErrorCodes.ERR_DATABASE_ACCESS);
            }
        } catch (SQLException exception) {
	        throw new DAOException(IErrorCodes.ERR_DATABASE_ACCESS);
        } finally {
	        MySQLUtility.close(rsCount);
	        MySQLUtility.close(stmtCount);
        }
    }*/
}
