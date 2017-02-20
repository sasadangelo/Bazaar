/*
 * Copyleft (C) Open Community author Salvatore D'Angelo (koala.gnu@tiscali.it)
 */
package org.opencommunity.bazaar.account.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.opencommunity.bazaar.account.dto.Account;
import org.opencommunity.bazaar.account.integration.dao.IAccountDAO;
import org.opencommunity.bazaar.utils.exceptions.DAOException;
import org.opencommunity.bazaar.utils.exceptions.OidManagerException;
import org.opencommunity.bazaar.utils.integration.dao.TransactionManagerFactory;
import org.opencommunity.bazaar.utils.integration.dao.mysql.MySQLTransaction;
import org.opencommunity.bazaar.utils.integration.dao.mysql.MySQLUtility;
import org.opencommunity.bazaar.utils.messages.IErrorCodes;
import org.opencommunity.bazaar.utils.oid.OidManager;

public class MySQLAccountDAO implements IAccountDAO {
	private static String INSERT_SQL = "INSERT INTO Accounts(nID, txtEmailAddress, txtPassword, bAdmin, nActivationCode, bActivationStatus, "
			+ "	txtFirstname, txtLastname, txtStreetAddress, "
			+ "	txtZipCode, txtCity, txtState, txtCountry, txtPhone, "
			+ "	tsLastChange, tsRegTime) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static String UPDATE_SQL = "UPDATE Accounts SET txtFirstname=?, "
			+ " txtLastname=?, txtStreetAddress=?, txtZipCode=?, "
			+ " txtCity=?, txtState=?, txtCountry=?, "
			+ " txtPhone=?, tsLastChange=? WHERE txtEmailAddress=?";

	private static String UPDATE_PASSWD_SQL = "UPDATE Accounts SET txtPassword=? WHERE txtEmailAddress=?";

	private static String UPDATE_ACTIVATION_STATUS_SQL = "UPDATE Accounts SET bActivationStatus=1 WHERE txtEmailAddress=?";

	private static String DELETE_SQL = "DELETE FROM Accounts WHERE nID = ?";

	private static String FIND_BY_EMAIL_ADDRESS_SQL = "SELECT nID, txtEmailAddress, txtPassword, bAdmin, nActivationCode, bActivationStatus, txtFirstname, txtLastname, txtStreetAddress, "
			+ "txtZipCode, txtCity, txtState, txtCountry, txtPhone, tsLastChange, tsRegTime "
			+ "FROM Accounts WHERE txtEmailAddress=?";

	private static String FIND_ALL_SQL = "SELECT nID, txtEmailAddress, txtPassword, bAdmin, nActivationCode, bActivationStatus, txtFirstname, txtLastname, txtStreetAddress, "
		+ "txtZipCode, txtCity, txtState, txtCountry, txtPhone, tsLastChange, tsRegTime "
		+ "FROM Accounts "
		+ "WHERE bAdmin=0 "
		+ "LIMIT ?, ?";

	private static String COUNT_SQL = "SELECT count(*) FROM Accounts WHERE bAdmin=0";

	////////////////////////////////////////////////////////////////////////////
	// CRUD methods
	////////////////////////////////////////////////////////////////////////////

	public void create(Account account) throws DAOException, OidManagerException {
		// if the oid is equal to 0 it must be generate
		MySQLTransaction tx = (MySQLTransaction) TransactionManagerFactory
				.getTransactionManager().getTransaction();
		PreparedStatement stmt = null;
		int i = 1;

		if (account.getNID() == 0) {
	        account.setNID(OidManager.getInstance().getNextOid(Account.OID_NAME));
		}
		
		try {
			stmt = tx.getConnection().prepareStatement(INSERT_SQL);
			stmt.setLong(i++, account.getNID());
			stmt.setString(i++, account.getTxtEmailAddress());
			stmt.setString(i++, account.getTxtPassword());
			stmt.setShort(i++, account.isBAdmin() ? (short) 1 : (short) 0);
			stmt.setLong(i++, account.getNActivationCode());
			stmt.setShort(i++, account.isBActivationStatus() ? (short) 1
					: (short) 0);
			stmt.setString(i++, account.getTxtFirstname());
			stmt.setString(i++, account.getTxtLastname());
			stmt.setString(i++, account.getTxtStreetAddress());
			stmt.setString(i++, account.getTxtZipCode());
			stmt.setString(i++, account.getTxtCity());
			stmt.setString(i++, account.getTxtState());
			stmt.setString(i++, account.getTxtCountry());
			stmt.setString(i++, account.getTxtPhone());
			stmt.setTimestamp(i++, account.getTsLastChange());
			stmt.setTimestamp(i++, account.getTsRegTime());
			stmt.executeUpdate();
		} catch (SQLException exception) {
			throw new DAOException(IErrorCodes.ERR_DATABASE_ACCESS);
		} finally {
			MySQLUtility.close(stmt);
		}
	}

	public void update(Account account) throws DAOException {
		MySQLTransaction tx = (MySQLTransaction) TransactionManagerFactory
				.getTransactionManager().getTransaction();
		PreparedStatement stmt = null;
		int i = 1;

		try {
			stmt = tx.getConnection().prepareStatement(UPDATE_SQL);
			stmt.setString(i++, account.getTxtFirstname());
			stmt.setString(i++, account.getTxtLastname());
			stmt.setString(i++, account.getTxtStreetAddress());
			stmt.setString(i++, account.getTxtZipCode());
			stmt.setString(i++, account.getTxtCity());
			stmt.setString(i++, account.getTxtState());
			stmt.setString(i++, account.getTxtCountry());
			stmt.setString(i++, account.getTxtPhone());
			stmt.setTimestamp(i++, account.getTsLastChange());
			stmt.setString(i++, account.getTxtEmailAddress());
			stmt.executeUpdate();
		} catch (SQLException exception) {
			throw new DAOException(IErrorCodes.ERR_DATABASE_ACCESS);
		} finally {
			MySQLUtility.close(stmt);
		}
	}

	public void delete(long nID) throws DAOException {
		MySQLTransaction tx = (MySQLTransaction) TransactionManagerFactory
				.getTransactionManager().getTransaction();
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

	public void updatePasswd(String email, String passwd) throws DAOException {
		MySQLTransaction tx = (MySQLTransaction) TransactionManagerFactory
				.getTransactionManager().getTransaction();
		PreparedStatement stmt = null;
		int i = 1;

		try {
			stmt = tx.getConnection().prepareStatement(UPDATE_PASSWD_SQL);
			stmt.setString(i++, passwd);
			stmt.setString(i++, email);
			stmt.executeUpdate();
		} catch (SQLException exception) {
			throw new DAOException(IErrorCodes.ERR_DATABASE_ACCESS);
		} finally {
			MySQLUtility.close(stmt);
		}
	}

	public void updateActivationStatus(String email) throws DAOException {
		MySQLTransaction tx = (MySQLTransaction) TransactionManagerFactory
				.getTransactionManager().getTransaction();
		PreparedStatement stmt = null;
		int i = 1;

		try {
			stmt = tx.getConnection().prepareStatement(
					UPDATE_ACTIVATION_STATUS_SQL);
			stmt.setString(i++, email);
			stmt.executeUpdate();
		} catch (SQLException exception) {
			throw new DAOException(IErrorCodes.ERR_DATABASE_ACCESS);
		} finally {
			MySQLUtility.close(stmt);
		}
	}

	////////////////////////////////////////////////////////////////////////////
	// Finder methods
	////////////////////////////////////////////////////////////////////////////

	public Account findByEmailAddress(String emailAddress) throws DAOException {
		MySQLTransaction tx = (MySQLTransaction) TransactionManagerFactory
				.getTransactionManager().getTransaction();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int i = 1;

		try {
			stmt = tx.getConnection().prepareStatement(
					FIND_BY_EMAIL_ADDRESS_SQL);
			stmt.setString(1, emailAddress);
			rs = stmt.executeQuery();

			if (rs.next()) {
				return new Account(rs.getLong(i++), rs.getString(i++), rs
						.getString(i++), rs.getShort(i++) == 1 ? true : false,
						rs.getLong(i++), rs.getShort(i++) == 1 ? true : false,
						rs.getString(i++), rs.getString(i++),
						rs.getString(i++), rs.getString(i++),
						rs.getString(i++), rs.getString(i++),
						rs.getString(i++), rs.getString(i++), rs
								.getTimestamp(i++), rs.getTimestamp(i++));
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

	public List findAll(int startIndex, int length) throws DAOException {
		MySQLTransaction tx = (MySQLTransaction) TransactionManagerFactory.getTransactionManager().getTransaction();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList accounts = new ArrayList();
        int i = 1;

        try {
            stmt = tx.getConnection().prepareStatement(FIND_ALL_SQL);
	        stmt.setInt(1, startIndex);
	        stmt.setInt(2, startIndex+length);
	        rs = stmt.executeQuery();

	        while (rs.next()) {
		        accounts.add(new Account(rs.getLong(i++), 
				                         rs.getString(i++), 
				                         rs.getString(i++), 
				                         rs.getShort(i++) == 1 ? true : false,
				                         rs.getLong(i++), 
				                         rs.getShort(i++) == 1 ? true : false,
				                         rs.getString(i++), 
				                         rs.getString(i++),
				                         rs.getString(i++), 
				                         rs.getString(i++),
				                         rs.getString(i++), 
				                         rs.getString(i++),
				                         rs.getString(i++), 
				                         rs.getString(i++), 
				                         rs.getTimestamp(i++), 
				                         rs.getTimestamp(i++)));
		        i = 1;
	        }
	
	        return accounts;
         } catch (SQLException exception) {
	        throw new DAOException(IErrorCodes.ERR_DATABASE_ACCESS);
         } finally {
	        MySQLUtility.close(rs);
	        MySQLUtility.close(stmt);
         }
    }

	public int countAccounts() throws DAOException {
		MySQLTransaction tx = (MySQLTransaction) TransactionManagerFactory.getTransactionManager().getTransaction();
        PreparedStatement stmtCount = null;
        ResultSet rsCount = null;

        try {
	        stmtCount = tx.getConnection().prepareStatement(COUNT_SQL);
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
}
