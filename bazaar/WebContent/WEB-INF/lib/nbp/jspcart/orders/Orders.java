package nbp.jspcart.orders;

import java.sql.*;
import java.util.*;

import nbp.jspcart.*;
import nbp.jspcart.data.*;
import nbp.jspcart.users.*;

public class Orders {
	
	private synchronized int getNextOrderID()
	{
		int nID = nMaxID++;
		return nID;
	}
	
	private int nMaxID = 1;
	
	private void loadLastMaxID()
	{
		DBConnection conn = website.getConnection();
		synchronized(conn)
		{
			try
			{
				PreparedStatement stmt = conn.prepareStatement("SELECT MAX(nID) AS nMaxID FROM Orders");
				ResultSet rs = stmt.executeQuery();
				if(rs.next())
				{
					nMaxID = rs.getInt(1) + 1;
				}
			}
			catch(Exception e)
			{
				website.logger.write(e);
			}
		}
	}
	
	public WebSite website = null;
	
	public Orders(WebSite ws)
	{
		website = ws;
		loadLastMaxID();
	}
	
	public String insert(Order obj)
	{
		obj.nID = getNextOrderID();
		
		DBConnection conn = website.getConnection();
		synchronized(conn){
			return obj.insert(conn) ? null : "Si e' verificato un errore nel database.";
		}
	}
	
	
	public String edit(Order obj)
	{
		DBConnection conn = website.getConnection();
		synchronized(conn){
			return obj.edit(conn) ? null : "Si e' verificato un errore nel database.";
		}
	}
	
	public String edit(Order obj,Users users)
	{
		String ret = edit(obj);
		if(ret != null)
			return ret;
		//return website.mailer.sendOrderStatus(obj,users);
		website.mailer.sendOrderStatus(obj,users);
		return null;
	}
	
	
	public String delete(Order obj)
	{
		if (obj == null) {
		    return null;
		}
			
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
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Orders LIMIT ?,?");
				stmt.setInt(1,nStart);
				stmt.setInt(2,nSize);
				ResultSet rs = stmt.executeQuery();
				while(rs.next())
				{
					items.addElement(new Order(rs));
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
	
	public Vector getAll(int nStart,int nSize,String txtEmailAddress)
	{
		Vector items = new Vector();
		DBConnection conn = website.getConnection();
		synchronized(conn){
			try
			{
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Orders WHERE txtEmailAddress=? LIMIT ?,?");
				stmt.setString(1,txtEmailAddress);
				stmt.setInt(2,nStart);
				stmt.setInt(3,nSize);
				ResultSet rs = stmt.executeQuery();
				while(rs.next())
				{
					items.addElement(new Order(rs));
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
	
	
	public Vector getAll(Timestamp tsStart,Timestamp tsEnd,int nOrderState)
	{
		Vector items = new Vector();
		DBConnection conn = website.getConnection();
		synchronized(conn){
			try
			{
				PreparedStatement stmt;

				// if it is -1 then means return all orders !!
				if(nOrderState==-1)
				{
					stmt = conn.prepareStatement("SELECT * FROM Orders WHERE tsDate>=? AND tsDate<=? ORDER BY nID DESC");
					stmt.setTimestamp(1,tsStart);
					stmt.setTimestamp(2,tsEnd);
				}
				else
				{
					stmt = conn.prepareStatement("SELECT * FROM Orders WHERE nOrderState=? AND tsDate>=? AND tsDate<=? ORDER BY nID DESC");
					stmt.setInt(1,nOrderState);
					stmt.setTimestamp(2,tsStart);
					stmt.setTimestamp(3,tsEnd);
				}
				
				ResultSet rs = stmt.executeQuery();
				while(rs.next())
				{
					Order order = new Order(rs);
					order.loadDetails(conn);
					order.loadOrderedItems(conn);
					items.addElement(order);
				}
				rs.close();
				stmt.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return items;
	}
	
	
	
	public Order get(int nID)
	{
		DBConnection conn = website.getConnection();
		synchronized(conn){
			try
			{
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Orders WHERE nID=?");
				stmt.setInt(1,nID);
				ResultSet rs = stmt.executeQuery();
				if(rs.next())
				{
					Order obj = new Order(rs);
					rs.close();
					stmt.close();
					
					obj.loadOrderedItems(conn);
					obj.loadDetails(conn);
					
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
	
	public Order getCompleteOrder(int nID)
	{
		DBConnection conn = website.getConnection();
		synchronized(conn){
			try
			{
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Orders WHERE nID=?");
				stmt.setInt(1,nID);
				ResultSet rs = stmt.executeQuery();
				if(rs.next())
				{
					Order order = new Order(rs);
					order.loadOrderedItems(conn);
					order.loadDetails(conn);
					rs.close();
					stmt.close();
					return order;
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
	
	public Vector getAll(String ids[])
	{
		Vector items = new Vector();
		DBConnection conn = website.getConnection();
		synchronized(conn)
		{
			try
			{
				String idList = "";
				for(int i=0;i<ids.length;i++)
				{
					idList += ids[i] + ",";
				}
				if(idList.length()>1)
					idList = idList.substring(0,idList.length()-1);
				
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Orders WHERE nID IN ("+idList+")");
				ResultSet rs = stmt.executeQuery();
				while(rs.next())
				{
					Order order = new Order(rs);
					items.addElement(order);
				}
				rs.close();
				stmt.close();
			}
			catch(Exception e)
			{
				website.logger.write(e);
			}
		}
		return items;
	}

}
