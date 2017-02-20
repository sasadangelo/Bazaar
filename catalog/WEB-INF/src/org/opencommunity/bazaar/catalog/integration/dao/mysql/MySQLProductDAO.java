/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.catalog.integration.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.opencommunity.bazaar.catalog.dto.Product;
import org.opencommunity.bazaar.catalog.integration.dao.IProductDAO;
import org.opencommunity.bazaar.utils.exceptions.DAOException;
import org.opencommunity.bazaar.utils.exceptions.OidManagerException;
import org.opencommunity.bazaar.utils.integration.dao.TransactionManagerFactory;
import org.opencommunity.bazaar.utils.integration.dao.mysql.MySQLTransaction;
import org.opencommunity.bazaar.utils.integration.dao.mysql.MySQLUtility;
import org.opencommunity.bazaar.utils.messages.IErrorCodes;
import org.opencommunity.bazaar.utils.oid.OidManager;

public class MySQLProductDAO implements IProductDAO {
    private static String INSERT_SQL =
        "INSERT INTO Products(nID, txtName, txtShortDescription, txtDescription, bytImage, bytThumbImage, nQuantity, dblPrice, bActive, nAccountID) " +
		"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static String UPDATE_SQL =
        "UPDATE Products SET txtName=?, txtShortDescription=?, txtDescription=?, bytImage=?, bytThumbImage=?, nQuantity=?, dblPrice=?, bActive=?, nAccountID=? WHERE nID=?";

    private static String DELETE_SQL =
    	"DELETE FROM Products WHERE nID = ?";

    private static String FIND_ALL_SQL =
        "SELECT nID, txtName, txtShortDescription, txtDescription, bytImage, bytThumbImage, nQuantity, dblPrice, bActive, nAccountID FROM Products";

    private static String FIND_BY_ACCOUNT_SQL =
        "SELECT nID, txtName, txtShortDescription, txtDescription, bytImage, bytThumbImage, nQuantity, dblPrice, bActive FROM Products WHERE nAccountID=? LIMIT ?, ?";

    private static String FIND_BY_CATEGORY_SQL =
        "SELECT nID, txtName, txtShortDescription, txtDescription, bytImage, bytThumbImage, nQuantity, dblPrice, bActive, nAccountID FROM Products, CategoryProducts WHERE bActive=1 AND nCategoryID=? AND nProductID=nID LIMIT ?, ?";

    private static String FIND_BY_PRIMARY_KEY_SQL =
        "SELECT txtName, txtShortDescription, txtDescription, bytImage, bytThumbImage, nQuantity, dblPrice, bActive, nAccountID FROM Products WHERE nID=?";
    
	private static String COUNT_BY_ACCOUNT_SQL = "SELECT count(*) FROM Products WHERE nAccountID=?";
	private static String COUNT_BY_CATEGORY_SQL = "SELECT count(*) FROM CategoryProducts WHERE nCategoryID=?";

	////////////////////////////////////////////////////////////////////////////
    // CRUD methods
    ////////////////////////////////////////////////////////////////////////////

    public void create(Product product) throws DAOException, OidManagerException {
    	MySQLTransaction tx = (MySQLTransaction) TransactionManagerFactory.getTransactionManager().getTransaction();
		PreparedStatement stmt = null;
    	int i=1;
    	
    	if (product.getNID() == 0) {
    		product.setNID(OidManager.getInstance().getNextOid(Product.OID_NAME));
		}

    	try {
			stmt = tx.getConnection().prepareStatement(INSERT_SQL);
			stmt.setLong(i++, product.getNID());
			stmt.setString(i++, product.getTxtName());
			MySQLUtility.setOptString(stmt, i++, product.getTxtShortDescription());
			MySQLUtility.setOptString(stmt, i++, product.getTxtDescription());
			MySQLUtility.setOptImage(stmt, i++, product.getImgPhoto());
			MySQLUtility.setOptImage(stmt, i++, product.getImgThumbnailPhoto());
			stmt.setInt(i++, product.getNumQuantity());
			stmt.setDouble(i++, product.getDblPrice());
			stmt.setBoolean(i++, product.isBActive());
			stmt.setLong(i++, product.getNAccountID());
			stmt.executeUpdate();
    	} catch (SQLException exception) {
    		throw new DAOException(IErrorCodes.ERR_DATABASE_ACCESS);
		} finally {
			MySQLUtility.close(stmt);
		}
    }

    public void update(Product product) throws DAOException {
    	MySQLTransaction tx = (MySQLTransaction) TransactionManagerFactory.getTransactionManager().getTransaction();
		PreparedStatement stmt = null;
    	int i=1;

    	try {
			stmt = tx.getConnection().prepareStatement(UPDATE_SQL);
			stmt.setString(i++, product.getTxtName());
			MySQLUtility.setOptString(stmt, i++, product.getTxtShortDescription());
			MySQLUtility.setOptString(stmt, i++, product.getTxtDescription());
			MySQLUtility.setOptImage(stmt, i++, product.getImgPhoto());
			MySQLUtility.setOptImage(stmt, i++, product.getImgThumbnailPhoto());
			stmt.setInt(i++, product.getNumQuantity());
			stmt.setDouble(i++, product.getDblPrice());
			stmt.setBoolean(i++, product.isBActive());
			stmt.setLong(i++, product.getNAccountID());
			stmt.setLong(i++, product.getNID());
			stmt.executeUpdate();
		} catch (SQLException exception) {
    		throw new DAOException(IErrorCodes.ERR_DATABASE_ACCESS);
		} finally {
			MySQLUtility.close(stmt);
		}
    }
    
    public void delete(long nID) throws DAOException {
    	MySQLTransaction tx = (MySQLTransaction) TransactionManagerFactory.getTransactionManager().getTransaction();
		PreparedStatement stmt = null;

    	try {
			stmt = tx.getConnection().prepareStatement(DELETE_SQL);
			stmt.setLong(1, nID);
			stmt.executeUpdate();
		} catch (SQLException exception) {
    		throw new DAOException(IErrorCodes.ERR_DATABASE_ACCESS);
		} finally {
			MySQLUtility.close(stmt);
		}
    }

    public void delete() throws DAOException {
		throw new DAOException(IErrorCodes.ERR_FUNCTION_NOT_IMPLEMENTED);
    }
    
    ////////////////////////////////////////////////////////////////////////////
    // Finder methods
    ////////////////////////////////////////////////////////////////////////////
    
    public HashMap findAll() throws DAOException {
    	MySQLTransaction tx = (MySQLTransaction) TransactionManagerFactory.getTransactionManager().getTransaction();
    	HashMap map = new HashMap();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Product product = null;
    	int i=1;
    	
    	try {
			stmt = tx.getConnection().prepareStatement(FIND_ALL_SQL);
			rs = stmt.executeQuery();
			
			while (rs != null) {
				long id = rs.getLong(i++);
				product.setNID(id);
				product.setTxtName(rs.getString(i++));
				product.setTxtShortDescription(MySQLUtility.getOptString(rs, i++));
				product.setTxtDescription(MySQLUtility.getOptString(rs, i++));
				product.setImgPhoto(MySQLUtility.getOptImage(rs, i++));
				product.setImgThumbnailPhoto(MySQLUtility.getOptImage(rs, i++));
				product.setDblPrice(rs.getDouble(i++));
				product.setNumQuantity(rs.getInt(i++));
				product.setBActive(rs.getBoolean(i++));
				product.setNAccountID(rs.getLong(i++));
				map.put(new Long(id), product);
				i=1;
			}
		} catch (SQLException exception) {
    		throw new DAOException(IErrorCodes.ERR_DATABASE_ACCESS);
		} finally {
			MySQLUtility.close(rs);
			MySQLUtility.close(stmt);
		}
		
		return map;
    }
    
    public List findByAccount(long nAccountID, int startIndex, int pageLength) throws DAOException {
    	MySQLTransaction tx = (MySQLTransaction) TransactionManagerFactory.getTransactionManager().getTransaction();
    	ArrayList list = new ArrayList();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Product product = null;
    	int i=1;
    	
    	try {
			stmt = tx.getConnection().prepareStatement(FIND_BY_ACCOUNT_SQL);
			stmt.setLong(1, nAccountID);
	        stmt.setInt(2, startIndex);
	        stmt.setInt(3, startIndex+pageLength);

			rs = stmt.executeQuery();
			
			while (rs.next()) {
				product = new Product();
				product.setNID(rs.getLong(i++));
				product.setTxtName(rs.getString(i++));
				product.setTxtShortDescription(MySQLUtility.getOptString(rs, i++));
				product.setTxtDescription(MySQLUtility.getOptString(rs, i++));
				product.setImgPhoto(MySQLUtility.getOptImage(rs, i++));
				product.setImgThumbnailPhoto(MySQLUtility.getOptImage(rs, i++));
				product.setNumQuantity(rs.getInt(i++));
				product.setDblPrice(rs.getDouble(i++));
				product.setBActive(rs.getBoolean(i++));
				product.setNAccountID(nAccountID);
	            list.add(product);
	            i=1;
			}
		} catch (SQLException exception) {
    		throw new DAOException(IErrorCodes.ERR_DATABASE_ACCESS);
		} finally {
			MySQLUtility.close(rs);
			MySQLUtility.close(stmt);
		}
		
		return list;
    }
    
    public List findByCategory(long nCategoryID, int startIndex, int pageLength) throws DAOException {
    	MySQLTransaction tx = (MySQLTransaction) TransactionManagerFactory.getTransactionManager().getTransaction();
    	ArrayList list = new ArrayList();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Product product = null;
    	int i=1;
    	
    	try {
			stmt = tx.getConnection().prepareStatement(FIND_BY_CATEGORY_SQL);
			stmt.setLong(1, nCategoryID);
	        stmt.setInt(2, startIndex);
	        stmt.setInt(3, startIndex+pageLength);

			rs = stmt.executeQuery();
			
			while (rs.next()) {
				product = new Product();
				product.setNID(rs.getLong(i++));
				product.setTxtName(rs.getString(i++));
				product.setTxtShortDescription(MySQLUtility.getOptString(rs, i++));
				product.setTxtDescription(MySQLUtility.getOptString(rs, i++));
				product.setImgPhoto(MySQLUtility.getOptImage(rs, i++));
				product.setImgThumbnailPhoto(MySQLUtility.getOptImage(rs, i++));
				product.setNumQuantity(rs.getInt(i++));
				product.setDblPrice(rs.getDouble(i++));
				product.setBActive(rs.getBoolean(i++));
				product.setNAccountID(rs.getLong(i++));
	            list.add(product);
	            i=1;
			}
		} catch (SQLException exception) {
    		throw new DAOException(IErrorCodes.ERR_DATABASE_ACCESS);
		} finally {
			MySQLUtility.close(rs);
			MySQLUtility.close(stmt);
		}
		
		return list;
    }

    public int countProductsByAccount(long nAccountID) throws DAOException {
		MySQLTransaction tx = (MySQLTransaction) TransactionManagerFactory.getTransactionManager().getTransaction();
        PreparedStatement stmtCount = null;
        ResultSet rsCount = null;

        try {
	        stmtCount = tx.getConnection().prepareStatement(COUNT_BY_ACCOUNT_SQL);
	        stmtCount.setLong(1, nAccountID);
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
    }
    
    public int countProductsByCategory(long nCategoryID) throws DAOException {
		MySQLTransaction tx = (MySQLTransaction) TransactionManagerFactory.getTransactionManager().getTransaction();
        PreparedStatement stmtCount = null;
        ResultSet rsCount = null;

        try {
	        stmtCount = tx.getConnection().prepareStatement(COUNT_BY_CATEGORY_SQL);
	        stmtCount.setLong(1, nCategoryID);
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
    }

    public Product findByPrimaryKey(long key) throws DAOException {
    	MySQLTransaction tx = (MySQLTransaction) TransactionManagerFactory.getTransactionManager().getTransaction();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Product product = null;
    	int i=1;
    	
    	try {
			stmt = tx.getConnection().prepareStatement(FIND_BY_PRIMARY_KEY_SQL);
			stmt.setLong(1, key);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				product = new Product();
				product.setNID(key);
				product.setTxtName(rs.getString(i++));
				product.setTxtShortDescription(MySQLUtility.getOptString(rs, i++));
				product.setTxtDescription(MySQLUtility.getOptString(rs, i++));
				product.setImgPhoto(MySQLUtility.getOptImage(rs, i++));
				product.setImgThumbnailPhoto(MySQLUtility.getOptImage(rs, i++));
				product.setNumQuantity(rs.getInt(i++));
				product.setDblPrice(rs.getDouble(i++));
				product.setBActive(rs.getBoolean(i++));
				product.setNAccountID(rs.getLong(i++));
				return product;
			} else {
				return null;
			}
		} catch (SQLException exception) {
    		throw new DAOException(IErrorCodes.ERR_DATABASE_ACCESS);
		} finally {
			MySQLUtility.close(rs);
			MySQLUtility.close(stmt);
		}
    }
    
    public List findByText(String text) throws DAOException {
		throw new DAOException(IErrorCodes.ERR_FUNCTION_NOT_IMPLEMENTED);
    }
}
