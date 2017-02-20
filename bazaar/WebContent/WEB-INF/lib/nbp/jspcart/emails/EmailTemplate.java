package nbp.jspcart.emails;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.nspeech.data.DataConnection;

public class EmailTemplate {

	protected static String insertSQL;

	protected static String editSQL;

	public static EmailTemplate defaultObject;

	static {
		//Default Object for default parameters
		defaultObject = new EmailTemplate();

		insertSQL = "INSERT INTO EmailTemplates(";
		insertSQL += "txtName,";
		insertSQL += "txtSubject,";
		insertSQL += "txtMessage,";
		insertSQL += "txtVariables";
		insertSQL += ") VALUES (";
		insertSQL += "?,";
		insertSQL += "?,";
		insertSQL += "?,";
		insertSQL += "?";
		insertSQL += ")";

		editSQL = "UPDATE EmailTemplates SET ";
		editSQL += "txtSubject=?,";
		editSQL += "txtMessage=?,";
		editSQL += "txtVariables=?";
		editSQL += " WHERE ";
		editSQL += "txtName=?";

	}

	// Members

	public String txtName;

	public String txtSubject;

	public String txtMessage;

	public String txtVariables;

	public EmailTemplate() {

		txtName = "";
		txtSubject = "";
		txtMessage = "";
		txtVariables = "";

	}

	public EmailTemplate(ResultSet rs) throws SQLException {

		txtName = rs.getString("txtName");
		txtSubject = rs.getString("txtSubject");
		txtMessage = rs.getString("txtMessage");
		txtVariables = rs.getString("txtVariables");

	}

	public EmailTemplate(HttpServletRequest rs) {

		txtName = rs.getParameter("txtName");
		txtSubject = rs.getParameter("txtSubject");
		txtMessage = rs.getParameter("txtMessage");
		txtVariables = rs.getParameter("txtVariables");

	}

	public boolean insert(DataConnection conn) {

		try {
			PreparedStatement stmt = conn.prepareStatement(insertSQL);
			stmt.setString(1, txtName);
			stmt.setString(2, txtSubject);
			stmt.setString(3, txtMessage);
			stmt.setString(4, txtVariables);
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
			stmt.setString(1, txtSubject);
			stmt.setString(2, txtMessage);
			stmt.setString(3, txtVariables);
			stmt.setString(4, txtName);
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
					.prepareStatement("DELETE FROM EmailTemplates WHERE txtName=?");
			stmt.setString(1, txtName);
			stmt.execute();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
