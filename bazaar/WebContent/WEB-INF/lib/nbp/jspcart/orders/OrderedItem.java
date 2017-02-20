package nbp.jspcart.orders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.nspeech.data.DataConnection;


public class OrderedItem{

	
	static String insertSQL;
	
	static{
		
		insertSQL="INSERT INTO OrderedItems(";
		insertSQL += "nOrderID,";
		insertSQL += "nProductID,";
		insertSQL += "dblPrice,";
		insertSQL += "dblCost,";
		insertSQL += "nQuantity,";
		insertSQL += "txtReason";
		insertSQL += ") VALUES (";
		insertSQL += "?,";
		insertSQL += "?,";
		insertSQL += "?,";
		insertSQL += "?,";
		insertSQL += "?,";
		insertSQL += "?";
		insertSQL += ")";

	}
	
	// Members
	
	public 	int			nOrderID;
	public 	int			nProductID;
	public 	double		dblPrice;
	public	double		dblCost;
	public 	int			nQuantity;
	
	public  String		txtReason;

	
	public OrderedItem()
	{
		
		nOrderID			= 0;
		nProductID			= 0;
		dblPrice			= 0;
		dblCost				= 0;
		nQuantity			= 0;

	}
	
	public OrderedItem(ResultSet rs) throws SQLException
	{
		
		nOrderID			=rs.getInt("nOrderID");
		nProductID			=rs.getInt("nProductID");
		dblPrice			=rs.getDouble("dblPrice");
		dblCost				=rs.getDouble("dblCost");
		nQuantity			=rs.getInt("nQuantity");
		txtReason			=rs.getString("txtReason");
	}
	
	public OrderedItem(HttpServletRequest rs)
	{
		
		nOrderID	=Integer.parseInt(rs.getParameter("nOrderID"));
		nProductID	=Integer.parseInt(rs.getParameter("nProductID"));
		dblPrice	=Double.parseDouble(rs.getParameter("dblPrice"));
		dblCost		=Double.parseDouble(rs.getParameter("dblCost"));
		nQuantity	=Integer.parseInt(rs.getParameter("nQuantity"));
	}
	
	public boolean insert(DataConnection conn)
	{
		
		try
		{
			PreparedStatement stmt = conn.prepareStatement(insertSQL);
			stmt.setInt(1,nOrderID);
			stmt.setInt(2,nProductID);
			stmt.setDouble(3,dblPrice);
			stmt.setDouble(4,dblCost);
			stmt.setInt(5,nQuantity);
			stmt.setString(6,txtReason);
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
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM OrderedItems WHERE nOrderID=?");
			stmt.setInt(1,nOrderID);
			stmt.execute();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean equals(Object object)
	{
		if(object instanceof OrderedItem)
		{
			OrderedItem oi = (OrderedItem)object;
			return nOrderID==oi.nOrderID && nProductID==oi.nProductID;
		}
		return super.equals(object);
	}

	public String toString()
	{
		String ret;
		ret = "com.oph.orders.OrderedItem:\n";
		ret += "nOrderID=" + String.valueOf(nOrderID) + "\n";
		ret += "nProductID=" + String.valueOf(nProductID) + "\n";
		return ret;
	}
}
