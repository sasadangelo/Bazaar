package nbp.jspcart.orders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import nbp.jspcart.users.UserProfile;

import org.nspeech.data.DataConnection;


public class ShippingAddress{

	static String insertSQL;
	
	static{
		
		insertSQL="INSERT INTO OrderShippingAddress(";
		insertSQL += "nOrderID,";
		insertSQL += "txtName,";
		insertSQL += "txtStreetAddress,";
		insertSQL += "txtZipCode,";
		insertSQL += "txtCity,";
		insertSQL += "txtState,";
		insertSQL += "txtCountry,";
		insertSQL += "txtPhone,";
		insertSQL += "txtShippingMethod";
		insertSQL += ") VALUES (";
		insertSQL += "?,";
		insertSQL += "?,";
		insertSQL += "?,";
		insertSQL += "?,";
		insertSQL += "?,";
		insertSQL += "?,";
		insertSQL += "?,";
		insertSQL += "?,";
		insertSQL += "?";
		insertSQL += ")";
		
	}
	
	// Members
	
	public 	int				nOrderID;
	public 	String			txtName;
	public 	String			txtStreetAddress;
	public 	String			txtZipCode;
	public 	String			txtCity;
	public 	String			txtState;
	public 	String			txtCountry;
	public 	String			txtPhone;
	public	String			txtShippingMethod;

	
	public ShippingAddress()
	{
		
		nOrderID			= 0;
		txtName				= 
		txtStreetAddress	= 
		txtZipCode			= 
		txtCity				= 
		txtState			= 
		txtCountry			= 
		txtPhone			= 
		txtShippingMethod	= "";

	}
	
	public ShippingAddress(UserProfile profile)
	{
		nOrderID			= 0;
		txtName				= profile.txtFirstname + " " + profile.txtLastname;
		txtStreetAddress	= profile.txtStreetAddress;
		txtZipCode			= profile.txtZipCode;
		txtCity				= profile.txtCity;
		txtState			= profile.txtState;
		txtCountry			= profile.txtCountry;
		txtPhone			= profile.txtPhone;
		txtShippingMethod	= "ExpressCourier";
	}
	
	public ShippingAddress(ResultSet rs) throws SQLException
	{
		
		nOrderID			=rs.getInt("nOrderID");
		txtName				=rs.getString("txtName");
		txtStreetAddress	=rs.getString("txtStreetAddress");
		txtZipCode			=rs.getString("txtZipCode");
		txtCity				=rs.getString("txtCity");
		txtState			=rs.getString("txtState");
		txtCountry			=rs.getString("txtCountry");
		txtPhone			=rs.getString("txtPhone");
		txtShippingMethod	=rs.getString("txtShippingMethod");

	}
	
	public ShippingAddress(HttpServletRequest rs)
	{
		
		nOrderID			=Integer.parseInt(rs.getParameter("nOrderID"));
		txtName				=rs.getParameter("txtName");
		txtStreetAddress	=rs.getParameter("txtStreetAddress");
		txtZipCode			=rs.getParameter("txtZipCode");
		txtCity				=rs.getParameter("txtCity");
		txtState			=rs.getParameter("txtState");
		txtCountry			=rs.getParameter("txtCountry");
		txtPhone			=rs.getParameter("txtPhone");
		txtShippingMethod	=rs.getParameter("txtShippingMethod");
	}
	
	public boolean insert(DataConnection conn)
	{
		
		try
		{
			PreparedStatement stmt = conn.prepareStatement(insertSQL);
			stmt.setInt(1,nOrderID);
			stmt.setString(2,txtName);
			stmt.setString(3,txtStreetAddress);
			stmt.setString(4,txtZipCode);
			stmt.setString(5,txtCity);
			stmt.setString(6,txtState);
			stmt.setString(7,txtCountry);
			stmt.setString(8,txtPhone);
			stmt.setString(9,txtShippingMethod);
			stmt.execute();
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
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM OrderShippingAddress WHERE nOrderID=?");
			stmt.setInt(1,nOrderID);
			stmt.execute();
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}
}
