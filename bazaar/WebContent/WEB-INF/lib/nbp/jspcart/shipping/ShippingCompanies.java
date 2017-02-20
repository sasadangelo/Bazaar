package nbp.jspcart.shipping;

import java.sql.*;
import java.util.*;

import nbp.jspcart.*;
import nbp.jspcart.data.*;

public class ShippingCompanies {

	public WebSite website = null;
	
	public String	txtDropDownOptions = "";
	
	public ShippingCompanies(WebSite ws)
	{
		website = ws;
		loadDropDown();
	}
	
	public void loadDropDown()
	{
		Vector items = getAll(0,-1);
		Enumeration enum2 = items.elements();
		while(enum2.hasMoreElements())
		{
			ShippingCompany sc = (ShippingCompany) enum2.nextElement();
			txtDropDownOptions += "<OPTION Value='"+String.valueOf(sc.nID)+"'>"+sc.txtName+"</OPTION>";
		}
	}
	
	public String insert(ShippingCompany obj)
	{
		DBConnection conn = website.getConnection();
		synchronized(conn){
			if(!obj.insert(conn))
				return "Si e' verificato un errore nel database.";
			loadDropDown();
			return null;
		}
	}
	
	
	public String edit(ShippingCompany obj)
	{
		DBConnection conn = website.getConnection();
		synchronized(conn){
			if(!obj.edit(conn))
				return "Si e' verificato un errore nel database.";
			loadDropDown();
			return null;
		}
	}
	
	
	public String delete(ShippingCompany obj)
	{
		DBConnection conn = website.getConnection();
		synchronized(conn){
			if(!obj.delete(conn))
				return "Si e' verificato un errore nel database.";
			loadDropDown();
			return null;
		}
	}
	
	
	public Vector getAll(int nStart,int nSize)
	{
		Vector items = new Vector();
		DBConnection conn = website.getConnection();
		synchronized(conn){
			try
			{
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ShippingCompanies LIMIT ?,?");
				stmt.setInt(1,nStart);
				stmt.setInt(2,nSize);
				ResultSet rs = stmt.executeQuery();
				while(rs.next())
				{
					items.addElement(new ShippingCompany(rs));
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
	
	
	public ShippingCompany get(int nID)
	{
		DBConnection conn = website.getConnection();
		synchronized(conn){
			try
			{
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ShippingCompanies WHERE nID=?");
				stmt.setInt(1,nID);
				ResultSet rs = stmt.executeQuery();
				if(rs.next())
				{
					ShippingCompany obj = new ShippingCompany(rs);
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
