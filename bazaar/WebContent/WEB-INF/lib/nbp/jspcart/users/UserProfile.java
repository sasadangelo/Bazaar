package nbp.jspcart.users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.nspeech.data.DataConnection;

public class UserProfile {

	static String insertSQL;

	static String editSQL;

	static {

		insertSQL = "INSERT INTO Users(";
		insertSQL += "txtEmailAddress,";
		insertSQL += "txtFirstname,";
		insertSQL += "txtLastname,";
		insertSQL += "txtStreetAddress,";
		insertSQL += "txtZipCode,";
		insertSQL += "txtCity,";
		insertSQL += "txtState,";
		insertSQL += "txtCountry,";
		insertSQL += "txtPhone,";
		insertSQL += "txtReference,";
		insertSQL += "txtReferredBy,";
		insertSQL += "bSendPromotion,";
		insertSQL += "bNeverSendMail,";
		insertSQL += "tsRegTime";
		insertSQL += ") VALUES (";
		insertSQL += "?,";
		insertSQL += "?,";
		insertSQL += "?,";
		insertSQL += "?,";
		insertSQL += "?,";
		insertSQL += "?,";
		insertSQL += "?,";
		insertSQL += "?,";
		insertSQL += "?,";
		insertSQL += "?,";
		insertSQL += "?,";
		insertSQL += "?,";
		insertSQL += "?,";
		insertSQL += "NULL";
		insertSQL += ")";

		editSQL = "UPDATE Users SET ";
		editSQL += "txtFirstname=?,";
		editSQL += "txtLastname=?,";
		editSQL += "txtStreetAddress=?,";
		editSQL += "txtZipCode=?,";
		editSQL += "txtCity=?,";
		editSQL += "txtState=?,";
		editSQL += "txtCountry=?,";
		editSQL += "txtPhone=?,";
		editSQL += "bSendPromotion=?,";
		editSQL += "bNeverSendMail=?";
		editSQL += " WHERE ";
		editSQL += "txtEmailAddress=?";

	}

	// Members
	public long nACLBitmap = 0;
	public String txtEmailAddress;
	public String txtFirstname;
	public String txtLastname;
	public String txtStreetAddress;
	public String txtZipCode;
	public String txtCity;
	public String txtState;
	public String txtCountry;
	public String txtPhone;
	public String txtReference; // This is link reference
	public String txtReferredBy;// This is user reference
	public int bSendPromotion;
	public int bNeverSendMail;
	public double dblCredit;
	public Timestamp tsRegTime;
    
	public UserProfile() {
		txtEmailAddress = txtFirstname = txtLastname = txtStreetAddress = txtZipCode = txtCity = txtState = txtCountry = txtPhone = txtReference = txtReferredBy = "";
		bSendPromotion = 0;
		bNeverSendMail = 0;
		dblCredit = 0.0;
		tsRegTime = null;
	}

	public UserProfile(ResultSet rs) throws SQLException {
		nACLBitmap = rs.getLong("nACLBitmap");
		txtEmailAddress = rs.getString("txtEmailAddress");
		txtFirstname = rs.getString("txtFirstname");
		txtLastname = rs.getString("txtLastname");
		txtStreetAddress = rs.getString("txtStreetAddress");
		txtZipCode = rs.getString("txtZipCode");
		txtCity = rs.getString("txtCity");
		txtState = rs.getString("txtState");
		txtCountry = rs.getString("txtCountry");
		txtPhone = rs.getString("txtPhone");
		txtReferredBy = rs.getString("txtReferredBy");
		bSendPromotion = rs.getInt("bSendPromotion");
		bNeverSendMail = rs.getInt("bNeverSendMail");
		dblCredit = rs.getDouble("dblCredit");
		tsRegTime = rs.getTimestamp("tsRegTime");

		txtReference = rs.getString("txtReference");
	}

	public UserProfile(HttpServletRequest rs) {
		txtEmailAddress = rs.getParameter("txtEmailAddress");
		txtFirstname = rs.getParameter("txtFirstname");
		txtLastname = rs.getParameter("txtLastname");
		txtStreetAddress = rs.getParameter("txtStreetAddress");
		txtZipCode = rs.getParameter("txtZipCode");
		txtCity = rs.getParameter("txtCity");
		txtState = rs.getParameter("txtState");
		txtCountry = rs.getParameter("txtCountry");
		txtPhone = rs.getParameter("txtPhone");
		txtReferredBy = rs.getParameter("txtReferredBy");
		bSendPromotion = rs.getParameter("bSendPromotion") != null ? 1 : 0;
		bNeverSendMail = rs.getParameter("bNeverSendMail") != null ? 1 : 0;
		txtReference = rs.getParameter("txtReference");
		if (txtReference == null)
			txtReference = "";

	}

	public boolean insert(DataConnection conn) {

		try {
			PreparedStatement stmt = conn.prepareStatement(insertSQL);
			stmt.setString(1, txtEmailAddress);
			stmt.setString(2, txtFirstname);
			stmt.setString(3, txtLastname);
			stmt.setString(4, txtStreetAddress);
			stmt.setString(5, txtZipCode);
			stmt.setString(6, txtCity);
			stmt.setString(7, txtState);
			stmt.setString(8, txtCountry);
			stmt.setString(9, txtPhone);
			stmt.setString(10, txtReference);
			stmt.setString(11, txtReferredBy);
			stmt.setInt(12, bSendPromotion);
			stmt.setInt(13, bNeverSendMail);
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	public boolean edit(DataConnection conn) {

		try {
			PreparedStatement stmt = conn.prepareStatement(editSQL);
			stmt.setString(1, txtFirstname);
			stmt.setString(2, txtLastname);
			stmt.setString(3, txtStreetAddress);
			stmt.setString(4, txtZipCode);
			stmt.setString(5, txtCity);
			stmt.setString(6, txtState);
			stmt.setString(7, txtCountry);
			stmt.setString(8, txtPhone);
			stmt.setInt(9, bSendPromotion);
			stmt.setInt(10, bNeverSendMail);
			stmt.setString(11, txtEmailAddress);

			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	public boolean delete(DataConnection conn) {
		try {
			PreparedStatement stmt = conn
					.prepareStatement("DELETE FROM Users WHERE txtEmailAddress=?");
			stmt.setString(1, txtEmailAddress);
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean isAdmin() {
		return (nACLBitmap & User.ADMIN_BIT_MAP) > 0;
	}
}
