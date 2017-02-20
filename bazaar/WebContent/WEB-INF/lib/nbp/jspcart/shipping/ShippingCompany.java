package nbp.jspcart.shipping;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.nspeech.data.DataConnection;


public class ShippingCompany{

	
	protected static String insertSQL;
	protected static String editSQL;
	public static ShippingCompany defaultObject;
	
	static{
		//Default Object for default parameters
		defaultObject = new ShippingCompany();
		
		
		insertSQL="INSERT INTO ShippingCompanies(";
		insertSQL += "txtName,";
		insertSQL += "txtURL,";
		insertSQL += "txtTrackingURL";
		insertSQL += ") VALUES (";
		insertSQL += "?,";
		insertSQL += "?,";
		insertSQL += "?";
		insertSQL += ")";

		
		editSQL="UPDATE ShippingCompanies SET ";
		editSQL += "txtName=?,";
		editSQL += "txtURL=?,";
		editSQL += "txtTrackingURL=?";
		editSQL += " WHERE ";
		editSQL += "nID=?";

	}
	
	// Members
	
	public 	int			nID;
	public 	String			txtName;
	public 	String			txtURL;
	public 	String			txtTrackingURL;

	
	public ShippingCompany()
	{
		
		nID			= 0;
		txtName			= "";
		txtURL			= "";
		txtTrackingURL			= "";

	}
	
	public ShippingCompany(ResultSet rs) throws SQLException
	{
		
		nID			=rs.getInt("nID");
		txtName			=rs.getString("txtName");
		txtURL			=rs.getString("txtURL");
		txtTrackingURL			=rs.getString("txtTrackingURL");

	}
	
	public ShippingCompany(HttpServletRequest rs)
	{
		
		nID	=Integer.parseInt(rs.getParameter("nID"));
		txtName			=rs.getParameter("txtName");
		txtURL			=rs.getParameter("txtURL");
		txtTrackingURL			=rs.getParameter("txtTrackingURL");

	}
	
	public boolean insert(DataConnection conn)
	{
		
		try
		{
			PreparedStatement stmt = conn.prepareStatement(insertSQL);
			stmt.setString(1,txtName);
			stmt.setString(2,txtURL);
			stmt.setString(3,txtTrackingURL);
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
			stmt.setString(2,txtURL);
			stmt.setString(3,txtTrackingURL);
			stmt.setInt(4,nID);
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
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM ShippingCompanies WHERE nID=?");
			stmt.setInt(1,nID);
			stmt.execute();
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}
}
