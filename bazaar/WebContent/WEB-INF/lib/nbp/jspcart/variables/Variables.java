package nbp.jspcart.variables;

import java.sql.*;
import java.util.*;

import nbp.jspcart.*;
import nbp.jspcart.data.*;

public class Variables {

	public WebSite website = null;

	public Variables(WebSite ws) {
		website = ws;
	}

	public String insert(Variable obj) {
		DBConnection conn = website.getConnection();
		synchronized (conn) {
			return obj.insert(conn) ? null : "Si e' verificato un errore nel database.";
		}
	}

	public String edit(Variable obj) {
		DBConnection conn = website.getConnection();
		synchronized (conn) {
			return obj.edit(conn) ? null : "Si e' verificato un errore nel database.";
		}
	}

	public String delete(Variable obj) {
		DBConnection conn = website.getConnection();
		synchronized (conn) {
			return obj.delete(conn) ? null : "Si e' verificato un errore nel database.";
		}
	}

	public Vector getAll(int nStart, int nSize) {
		Vector items = new Vector();
		DBConnection conn = website.getConnection();
		synchronized (conn) {
			try {
				PreparedStatement stmt = conn
						.prepareStatement("SELECT * FROM Variables LIMIT ?,?");
				stmt.setInt(1, nStart);
				stmt.setInt(2, nSize);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					items.addElement(new Variable(rs));
				}
				rs.close();
				stmt.close();
			} catch (Exception e) {
				website.logger.write(e);
			}
		}
		return items;
	}

	public Variable get(String txtName) {
		DBConnection conn = website.getConnection();
		synchronized (conn) {
			try {
				PreparedStatement stmt = conn
						.prepareStatement("SELECT * FROM Variables WHERE txtName=?");
				stmt.setString(1, txtName);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					Variable obj = new Variable(rs);
					rs.close();
					stmt.close();
					return obj;
				}
				rs.close();
				stmt.close();
			} catch (Exception e) {
				website.logger.write(e);
			}
		}
		return null;
	}
}
