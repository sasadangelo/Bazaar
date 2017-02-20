package org.opencommunity.bazaar.utility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtility {
	public static void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch(SQLException exception) {
				exception.printStackTrace();
			}
		}
		
	}

	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch(SQLException exception) {
				exception.printStackTrace();
			}
		}
	}
}
