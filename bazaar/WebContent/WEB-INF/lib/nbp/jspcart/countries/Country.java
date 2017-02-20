package nbp.jspcart.countries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.nspeech.data.DataConnection;


public class Country{

	
	protected static String insertSQL;
	protected static String editSQL;
	public static Country defaultObject;
	
	static{
		//Default Object for default parameters
		defaultObject = new Country();
		
		
		insertSQL="INSERT INTO Countries(";
		insertSQL += "txtID,";
		insertSQL += "txtName,";
		insertSQL += "dblRegisteredPost,";
		insertSQL += "dblExpressCourier";
		insertSQL += ") VALUES (";
		insertSQL += "?,";
		insertSQL += "?,";
		insertSQL += "?,";
		insertSQL += "?";
		insertSQL += ")";

		
		editSQL="UPDATE Countries SET ";
		editSQL += "txtName=?,";
		editSQL += "dblRegisteredPost=?,";
		editSQL += "dblExpressCourier=?";
		editSQL += " WHERE ";
		editSQL += "txtID=?";

	}
	
	// Members
	
	public 	String			txtID;
	public 	String			txtName;
	public	double			dblRegisteredPost;
	public	double			dblExpressCourier;

	
	public Country()
	{
		
		txtID			= 
		txtName			= "";
		
		dblRegisteredPost = 
		dblExpressCourier = 0.0;

	}
	
	public Country(ResultSet rs) throws SQLException
	{
		
		txtID			=rs.getString("txtID");
		txtName			=rs.getString("txtName");
		dblRegisteredPost = rs.getDouble("dblRegisteredPost");
		dblExpressCourier = rs.getDouble("dblExpressCourier");
	}
	
	public Country(HttpServletRequest rs)
	{
		
		txtID			=rs.getParameter("txtID");
		txtName			=rs.getParameter("txtName");
		dblRegisteredPost = Double.parseDouble(rs.getParameter("dblRegisteredPost"));
		dblExpressCourier = Double.parseDouble(rs.getParameter("dblExpressCourier"));
	}
	
	public boolean insert(DataConnection conn)
	{
		
		try
		{
			PreparedStatement stmt = conn.prepareStatement(insertSQL);
			stmt.setString(1,txtID);
			stmt.setString(2,txtName);
			stmt.setDouble(3,dblRegisteredPost);
			stmt.setDouble(4,dblExpressCourier);
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
			stmt.setString(1,txtName);
			stmt.setDouble(2,dblRegisteredPost);
			stmt.setDouble(3,dblExpressCourier);
			stmt.setString(4,txtID);
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
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM Countries WHERE txtID=?");
			stmt.setString(1,txtID);
			stmt.execute();
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}
}
