package nbp.jspcart.categories;

import java.sql.*;
import java.util.*;

import nbp.jspcart.*;
import nbp.jspcart.data.*;
import nbp.jspcart.products.*;

import org.nspeech.util.*;

public class Categories {

	public WebSite website = null;
	
	public IntMap categoryMap = new IntMap();
	public Vector categories = new Vector();
	
	public Categories(WebSite ws)
	{
		website = ws;
		website.categories = this;
		loadCategories();
	}
	
	public void loadCategories()
	{
		categoryMap = new IntMap();
		categories = new Vector();
		DBConnection conn = website.getConnection();
		synchronized(conn)
		{
			try
			{
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Categories WHERE nParentID is null ORDER BY txtName");
				ResultSet rs = stmt.executeQuery();
				while(rs.next())
				{
					Category c = new Category(rs);
					categoryMap.put(c.nID,c);
					categories.addElement( c );
				}
				rs.close();
				stmt.close();
				
				Enumeration enum2 = categories.elements();
				while(enum2.hasMoreElements())
				{
					Category c = (Category) enum2.nextElement();
					c.loadChildCategories(this,conn);
					c.loadProducts(website,conn);
				}
			}
			catch(Exception e)
			{
				website.logger.write(e);
			}
		}
	}
	
	
	public String insert(Category obj)
	{
		DBConnection conn = website.getConnection();
		synchronized(conn){
			if(!obj.insert(conn))
				return "Si e' verificato un errore nel database.";
		}
		loadCategories();
		return null;
	}
	
	
	public String edit(Category obj)
	{
		DBConnection conn = website.getConnection();
		synchronized(conn){
			if(!obj.edit(conn))
				return "Si e' verificato un errore nel database.";
		}
		loadCategories();
		return null;
	}
	
	
	public String delete(Category obj)
	{
		DBConnection conn = website.getConnection();
		synchronized(conn){
			if(!obj.delete(conn))
				return "Si e' verificato un errore nel database.";
		}
		loadCategories();
		return null;
	}
	
	public List getAll(int nParentID , int nStart, int nSize)
	{ 
		if(nParentID==0)
			return categories.subList(nStart, nStart+nSize > categories.size() ? categories.size() : nStart+nSize);
		Category c = (Category)categoryMap.get(nParentID);
		return c.childCategories.subList(nStart, nStart+nSize > c.childCategories.size() ? c.childCategories.size() : nStart+nSize);
	}

	public List getAll(int nParentID)
	{ 
		if(nParentID==0)
			return categories;
		Category c = (Category)categoryMap.get(nParentID);
		return c.childCategories;
	}
	
	private void fillCategoryList(Category cat, Vector list)
	{
		if(cat.childCategories.size()<=0)
			return;
		Enumeration enum2 = cat.childCategories.elements();
		while(enum2.hasMoreElements())
		{
			Category ccat = (Category)enum2.nextElement();
			list.addElement(ccat);
			fillCategoryList(ccat,list);
		}
	}
	
	public Vector getCategoryList()
	{
		Vector list = new Vector();
		Enumeration enum2 = categories.elements();
		while(enum2.hasMoreElements())
		{
			Category cat = (Category)enum2.nextElement();
			list.addElement(cat);
			fillCategoryList(cat,list);
		}
		return list;
	}
	
	public Category get(int nID)
	{
		return (Category)categoryMap.get(nID);
	}
	
	public void updateProducts( Product product , String catIDs)
	{
		String[] ids = catIDs.split(",");
		
		int i;
		// First remove...
		Vector cats = website.products.getProductCategories(product.nID);
		
		Enumeration enum2 = cats.elements();
		while(enum2.hasMoreElements())
		{
			boolean bExists = false;
			Category cat = (Category)enum2.nextElement();
			for(i=0;i<ids.length;i++)
			{
				String id = ids[i];
				if(id==null || id.length()==0)
					continue;
				if(cat.nID == Integer.parseInt(id))
				{
					bExists = true;
					deleteProductFromCache(cat, product);
					addProductToCache(cat, product);
					break;
				}
			}
			if(!bExists)
			{
				deleteProduct(cat,product);
			}
		}
		
		for(i=0;i<ids.length;i++)
		{
			String id = ids[i];
			if(id==null || id.length()==0)
				continue;
			boolean bExists = false;
			enum2 = cats.elements();
			while(enum2.hasMoreElements())
			{
				Category cat = (Category)enum2.nextElement();
				if(cat.nID == Integer.parseInt(id))
				{
					bExists = true;
					deleteProductFromCache(cat, product);
					addProductToCache(cat, product);
					break;
				}
			}
			if(!bExists)
			{
				Category cat = get(Integer.parseInt(id));
				addProduct(cat,product);
			}
		}
	}
	
	// product related functions
	public void addProduct( Category c , Product p )
	{
		DBConnection conn = website.getConnection();
		synchronized(conn)
		{
			try
			{
				PreparedStatement stmt = conn.prepareStatement("INSERT INTO CategoryProducts (nCategoryID,nProductID) VALUES (?,?) ");
				stmt.setInt(1,c.nID);
				stmt.setInt(2,p.nID);
				stmt.execute();
				stmt.close();
			}
			catch(Exception e)
			{
				website.logger.write(e);
			}
		}

		addProductToCache(c, p);
	}

	public void addProductToCache( Category c , Product p )
	{
		c.products.addElement(p);
	}
	
	public void deleteProduct( Category c , Product p )
	{
		DBConnection conn = website.getConnection();
		synchronized(conn)
		{
			try
			{
				PreparedStatement stmt = conn.prepareStatement("DELETE FROM CategoryProducts WHERE nCategoryID=? AND nProductID=?");
				stmt.setInt(1,c.nID);
				stmt.setInt(2,p.nID);
				stmt.execute();
				stmt.close();
			}
			catch(Exception e)
			{
				website.logger.write(e);
			}
		}

		deleteProductFromCache(c, p);
	}

	public void deleteProductFromCache( Category c , Product p )
	{
		if (c == null) {
			return;
		}

		for(int i=0;i<c.products.size();i++)
		{
			Product p1 = (Product)c.products.elementAt(i);
			if(p1.nID==p.nID)
			{
				c.products.removeElementAt(i);
				return;
			}
		}
	}
}
