package nbp.jspcart.countries;

import java.sql.*;
import java.util.*;

import nbp.jspcart.*;
import nbp.jspcart.data.*;

public class Countries {
	
	public String CountryOptions = "";

	public WebSite website = null;
	
	public Countries(WebSite ws)
	{
		website = ws;
		loadOptions();
	}
	
	public void loadOptions()
	{
		Vector items = getAll(0,-1);
		Enumeration enum2 = items.elements();
		while(enum2.hasMoreElements())
		{
			Country cn = (Country)enum2.nextElement();
			CountryOptions += "<OPTION Value=\""+ cn.txtID +"\">" + cn.txtName + "</OPTION>\r\n";
		}
	}
	
	public String insert(Country obj)
	{
		DBConnection conn = website.getConnection();
		synchronized(conn){
			return obj.insert(conn) ? null : "Si e' verificato un errore nel database.";
		}
	}
	
	
	public String edit(Country obj)
	{
		DBConnection conn = website.getConnection();
		synchronized(conn){
			return obj.edit(conn) ? null : "Si e' verificato un errore nel database.";
		}
	}
	
	
	public String delete(Country obj)
	{
		DBConnection conn = website.getConnection();
		synchronized(conn){
			return obj.delete(conn) ? null : "Si e' verificato un errore nel database.";
		}
	}
	
	
	public Vector getAll(int nStart,int nSize)
	{
		Vector items = new Vector();
		DBConnection conn = website.getConnection();
		synchronized(conn){
			try
			{
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Countries LIMIT ?,?");
				stmt.setInt(1,nStart);
				stmt.setInt(2,nSize);
				ResultSet rs = stmt.executeQuery();
				while(rs.next())
				{
					items.addElement(new Country(rs));
				}
				rs.close();
				stmt.close();
			}
			catch (Exception e)
			{
				website.logger.write(e);
			}
		}
		return items;
	}
	
	
	public Country get(String txtID)
	{
		DBConnection conn = website.getConnection();
		synchronized(conn){
			try
			{
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Countries WHERE txtID=?");
				stmt.setString(1,txtID);
				ResultSet rs = stmt.executeQuery();
				if(rs.next())
				{
					Country obj = new Country(rs);
					rs.close();
					stmt.close();
					return obj;
				}
				rs.close();
				stmt.close();
			}
			catch (Exception e)
			{
				website.logger.write(e);
			}
		}
		return null;
	}
	
}
