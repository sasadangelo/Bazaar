/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package order.opencommunity.bazaar.cart.integration.dao.db2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.opencommunity.bazaar.cart.dto.Order;
import org.opencommunity.bazaar.cart.integration.dao.IOrderDAO;
import org.opencommunity.bazaar.utils.exceptions.DAOException;
import org.opencommunity.bazaar.utils.exceptions.OidManagerException;
import org.opencommunity.bazaar.utils.integration.dao.TransactionManagerFactory;
import org.opencommunity.bazaar.utils.integration.dao.mysql.MySQLTransaction;
import org.opencommunity.bazaar.utils.integration.dao.mysql.MySQLUtility;
import org.opencommunity.bazaar.utils.messages.IErrorCodes;
import org.opencommunity.bazaar.utils.oid.OidManager;

public class MySQLOrderDAO implements IOrderDAO {
    private static String INSERT_SQL =
        "INSERT INTO Orders(nID, nBuyerID, nSellerID, nProductID, nQuantity, nOrderState, dblTotal, dblShippingCharges, dblGrandTotal, txtRemarks, tsDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static String UPDATE_SQL =
        "UPDATE Orders SET nQuantity=?, nOrderState=?, dblTotal=?, dblShippingCharges=?, dblGrandTotal=?, txtRemarks=?, tsDate=? WHERE nID=?";

    private static String DELETE_SQL =
        "DELETE from Orders WHERE nID=?";

    private static String FIND_BY_PRIMARY_KEY_SQL =
        "SELECT nBuyerID, nSellerID, nProductID, nQuantity, nOrderState, dblTotal, dblShippingCharges, dblGrandTotal, txtRemarks, tsDate FROM Orders WHERE nID=?";

    ////////////////////////////////////////////////////////////////////////////
    // CRUD methods
    ////////////////////////////////////////////////////////////////////////////

    public void create(Order order) throws DAOException, OidManagerException {
    	MySQLTransaction tx = (MySQLTransaction) TransactionManagerFactory.getTransactionManager().getTransaction();
		PreparedStatement stmt = null;
    	int i=1;

    	if (order.getNID() == 0) {
    		order.setNID(OidManager.getInstance().getNextOid(Order.OID_NAME));
		}

    	try {
			stmt = tx.getConnection().prepareStatement(INSERT_SQL);
			stmt.setLong(i++, order.getNID());
			stmt.setLong(i++, order.getNBuyerID());
			stmt.setLong(i++, order.getNSellerID());
			stmt.setLong(i++, order.getNProductID());
			stmt.setInt(i++, order.getNQuantity());
			stmt.setInt(i++, order.getNOrderState());
			stmt.setDouble(i++, order.getDblTotal());
			stmt.setDouble(i++, order.getDblShippingCharges());
			stmt.setDouble(i++, order.getDblGrandTotal());
			MySQLUtility.setOptString(stmt, i++, order.getTxtRemarks());
			stmt.setTimestamp(i++, order.getTsDate());
			stmt.executeUpdate();
    	} catch (SQLException exception) {
    		throw new DAOException(IErrorCodes.ERR_DATABASE_ACCESS);
		} finally {
			MySQLUtility.close(stmt);
		}
    }

    public void update(Order order) throws DAOException {
    	MySQLTransaction tx = (MySQLTransaction) TransactionManagerFactory.getTransactionManager().getTransaction();
		PreparedStatement stmt = null;
    	int i=1;

    	try {
			stmt = tx.getConnection().prepareStatement(UPDATE_SQL);
			stmt.setInt(i++, order.getNQuantity());
			stmt.setInt(i++, order.getNOrderState());
			stmt.setDouble(i++, order.getDblTotal());
			stmt.setDouble(i++, order.getDblShippingCharges());
			stmt.setDouble(i++, order.getDblGrandTotal());
			MySQLUtility.setOptString(stmt, i++, order.getTxtRemarks());
			stmt.setTimestamp(i++, order.getTsDate());
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

    public Order findByPrimaryKey(long nOrderID) throws DAOException {
		MySQLTransaction tx = (MySQLTransaction) TransactionManagerFactory.getTransactionManager().getTransaction();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int i = 1;

        try {
            stmt = tx.getConnection().prepareStatement(FIND_BY_PRIMARY_KEY_SQL);
            stmt.setLong(1, nOrderID);
	        rs = stmt.executeQuery();

	        if (rs.next()) {
	        	Order order = new Order();
                order.setNID(nOrderID);
                order.setNBuyerID(rs.getLong(i++));
                order.setNSellerID(rs.getLong(i++));
                order.setNProductID(rs.getLong(i++));
                order.setNQuantity(rs.getInt(i++));
                order.setNOrderState(rs.getInt(i++));
                order.setDblTotal(rs.getDouble(i++));
                order.setDblShippingCharges(rs.getDouble(i++));
                order.setDblGrandTotal(rs.getDouble(i++));
                order.setTxtRemarks(MySQLUtility.getOptString(rs, i++));
                order.setTsDate(rs.getTimestamp(i++));
	        	return order;
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
}
