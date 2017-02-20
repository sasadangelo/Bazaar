package nbp.jspcart.variables;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.nspeech.data.DataConnection;


public class Variable{

	
	protected static String insertSQL;
	protected static String editSQL;
	public static Variable defaultObject;
	
	static{
		//Default Object for default parameters
		defaultObject = new Variable();
		
		
		insertSQL="INSERT INTO Variables(";
		insertSQL += "txtName,";
		insertSQL += "txtValue";
		insertSQL += ") VALUES (";
		insertSQL += "?,";
		insertSQL += "?";
		insertSQL += ")";

		
		editSQL="UPDATE Variables SET ";
		editSQL += "txtValue=?";
		editSQL += " WHERE ";
		editSQL += "txtName=?";

	}
	
	// Members
	
	public 	String			txtName;
	public 	String			txtValue;

	
	public Variable()
	{
		
		txtName			= "";
		txtValue			= "";

	}
	
	public Variable(ResultSet rs) throws SQLException
	{
		
		txtName			=rs.getString("txtName");
		txtValue			=rs.getString("txtValue");

	}
	
	public Variable(HttpServletRequest rs)
	{
		
		txtName			=rs.getParameter("txtName");
		txtValue			=rs.getParameter("txtValue");

	}
	
	public boolean insert(DataConnection conn)
	{
		
		try
		{
			PreparedStatement stmt = conn.prepareStatement(insertSQL);
			stmt.setString(1,txtName);
			stmt.setString(2,txtValue);
			stmt.execute();
			stmt.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;

	}
	
	public boolean edit(DataConnection conn)
	{
		
		try
		{
			PreparedStatement stmt = conn.prepareStatement(editSQL);
			stmt.setString(1,txtValue);
			stmt.setString(2,txtName);
			stmt.execute();
			stmt.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;

	}
	
	public boolean delete(DataConnection conn)
	{
		try
		{
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM Variables WHERE txtName=?");
			stmt.setString(1,txtName);
			stmt.execute();
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}
}
