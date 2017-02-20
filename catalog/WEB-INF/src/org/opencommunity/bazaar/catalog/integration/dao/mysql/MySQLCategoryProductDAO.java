/*
 * Copyleft (C) Open Community
 * author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.catalog.integration.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.opencommunity.bazaar.catalog.dto.CategoryProduct;
import org.opencommunity.bazaar.catalog.integration.dao.ICategoryProductDAO;
import org.opencommunity.bazaar.utils.exceptions.DAOException;
import org.opencommunity.bazaar.utils.integration.dao.TransactionManagerFactory;
import org.opencommunity.bazaar.utils.integration.dao.mysql.MySQLTransaction;
import org.opencommunity.bazaar.utils.integration.dao.mysql.MySQLUtility;
import org.opencommunity.bazaar.utils.messages.IErrorCodes;

public class MySQLCategoryProductDAO implements ICategoryProductDAO {
    private static String INSERT_SQL =
        "INSERT INTO CategoryProducts(nCategoryID, nProductID) " +
		"VALUES (?, ?)";

    private static String DELETE_SQL =
    	"DELETE FROM CategoryProducts WHERE nProductID = ?";

	////////////////////////////////////////////////////////////////////////////
    // CRUD methods
    ////////////////////////////////////////////////////////////////////////////

    public void create(CategoryProduct catProd) throws DAOException {
    	MySQLTransaction tx = (MySQLTransaction) TransactionManagerFactory.getTransactionManager().getTransaction();
		PreparedStatement stmt = null;
    	int i=1;

    	try {
			stmt = tx.getConnection().prepareStatement(INSERT_SQL);
			stmt.setLong(i++, catProd.getNCategoryId());
			stmt.setLong(i++, catProd.getNProductId());
			stmt.executeUpdate();
    	} catch (SQLException exception) {
    		throw new DAOException(IErrorCodes.ERR_DATABASE_ACCESS);
		} finally {
			MySQLUtility.close(stmt);
		}
    }

    public void delete(long nProductId) throws DAOException {
    	MySQLTransaction tx = (MySQLTransaction) TransactionManagerFactory.getTransactionManager().getTransaction();
		PreparedStatement stmt = null;
		int i=1;
		
    	try {
			stmt = tx.getConnection().prepareStatement(DELETE_SQL);
			stmt.setLong(i++, nProductId);
			stmt.executeUpdate();
		} catch (SQLException exception) {
    		throw new DAOException(IErrorCodes.ERR_DATABASE_ACCESS);
		} finally {
			MySQLUtility.close(stmt);
		}
    }
}
