package nbp.jspcart.categories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import nbp.jspcart.WebSite;
import nbp.jspcart.products.Product;

import org.nspeech.data.DataConnection;

public class Category{

	
	protected static String insertSQL;
	protected static String editSQL;
	public static Category defaultObject;
	
	static{
		//Default Object for default parameters
		defaultObject = new Category();
		
		
		insertSQL="INSERT INTO Categories(";
		insertSQL += "nParentID,";
		insertSQL += "txtName,";
		insertSQL += "txtDescription,";
		insertSQL += "txtTitle,";
		insertSQL += "txtMeta,";
		insertSQL += "txtHeader,";
		insertSQL += "txtFooter,";
		insertSQL += "txtMetaReplyTo,";
		insertSQL += "txtMetaLanguage,";
		insertSQL += "txtMetaDistribution,";
		insertSQL += "txtMetaCopyright,";
		insertSQL += "txtMetaClassification,";
		insertSQL += "txtMetaAuthor,";
		insertSQL += "txtMetaRevisitAfter,";
		insertSQL += "txtMetaDescription,";
		insertSQL += "txtMetaRobots,";
		insertSQL += "txtMetaKeywords";
		insertSQL += ") VALUES (";
		insertSQL += "?,";
		insertSQL += "?,";
		insertSQL += "?,";
		insertSQL += "?,";
		insertSQL += "?,";
		insertSQL += "?,";
		insertSQL += "?,";
		insertSQL += "?,";
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

		
		editSQL="UPDATE Categories SET ";
		editSQL += "nParentID=?,";
		editSQL += "txtName=?,";
		editSQL += "txtDescription=?,";
		editSQL += "txtTitle=?,";
		editSQL += "txtMeta=?,";
		editSQL += "txtHeader=?,";
		editSQL += "txtFooter=?,";
		editSQL += "txtMetaReplyTo=?,";
		editSQL += "txtMetaLanguage=?,";
		editSQL += "txtMetaDistribution=?,";
		editSQL += "txtMetaCopyright=?,";
		editSQL += "txtMetaClassification=?,";
		editSQL += "txtMetaAuthor=?,";
		editSQL += "txtMetaRevisitAfter=?,";
		editSQL += "txtMetaDescription=?,";
		editSQL += "txtMetaRobots=?,";
		editSQL += "txtMetaKeywords=?";
		editSQL += " WHERE ";
		editSQL += "nID=?";

	}
	
	// Members
	
	public 	int			nID;
	public 	int			nParentID;
	public 	String			txtName;
	public 	String			txtDescription;
	public 	String			txtTitle;
	public 	String			txtMeta;
	public 	String			txtHeader;
	public 	String			txtFooter;
	public 	String			txtMetaReplyTo;
	public 	String			txtMetaLanguage;
	public 	String			txtMetaDistribution;
	public 	String			txtMetaCopyright;
	public 	String			txtMetaClassification;
	public 	String			txtMetaAuthor;
	public 	String			txtMetaRevisitAfter;
	public 	String			txtMetaDescription;
	public 	String			txtMetaRobots;
	public 	String			txtMetaKeywords;
	
	public	Category		parentCategory = null;
	public	Vector			childCategories = new Vector();
	public	Vector			products = new Vector();
	
	public Category()
	{
		
		nID						= 0;
		nParentID				= 0;
		txtName					= "";
		txtDescription			= "";
		txtTitle				= "";
		txtMeta					= "";
		txtHeader				= "";
		txtFooter				= "";
		txtMetaReplyTo			= "";
		txtMetaLanguage			= "EN";
		txtMetaDistribution		= "global";
		txtMetaCopyright		= "";
		txtMetaClassification	= "";
		txtMetaAuthor			= "";
		txtMetaRevisitAfter		= "14 days";
		txtMetaDescription		= "";
		txtMetaRobots			= "all";
		txtMetaKeywords			= "";

	}
	
	public Category(ResultSet rs) throws SQLException
	{
		
		nID						=rs.getInt("nID");
		nParentID				=rs.getInt("nParentID");
		if (rs.wasNull()) {
		    nParentID = 0;
		}
		txtName					=rs.getString("txtName");
		txtDescription			=rs.getString("txtDescription");
		txtTitle				=rs.getString("txtTitle");
		txtMeta					=rs.getString("txtMeta");
		txtHeader				=rs.getString("txtHeader");
		txtFooter				=rs.getString("txtFooter");
		txtMetaReplyTo			=rs.getString("txtMetaReplyTo");
		txtMetaLanguage			=rs.getString("txtMetaLanguage");
		txtMetaDistribution		=rs.getString("txtMetaDistribution");
		txtMetaCopyright		=rs.getString("txtMetaCopyright");
		txtMetaClassification	=rs.getString("txtMetaClassification");
		txtMetaAuthor			=rs.getString("txtMetaAuthor");
		txtMetaRevisitAfter		=rs.getString("txtMetaRevisitAfter");
		txtMetaDescription		=rs.getString("txtMetaDescription");
		txtMetaRobots			=rs.getString("txtMetaRobots");
		txtMetaKeywords			=rs.getString("txtMetaKeywords");

	}
	
	public void loadProducts(WebSite website, DataConnection conn)
	{
		try
		{
			PreparedStatement stmt = conn.prepareStatement("SELECT nProductID FROM CategoryProducts WHERE nCategoryID=?");
			stmt.setInt(1,nID);
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				Product p = website.products.get(rs.getInt(1));
				if((p != null) && (p.bActive != 0))
					products.addElement( p );
			}
			rs.close();
			stmt.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void loadChildCategories(Categories categories,DataConnection conn)
	{
		
		try
		{
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Categories WHERE nParentID=?  ORDER BY txtName");
			stmt.setInt(1,nID);
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				Category c = new Category(rs);
				categories.categoryMap.put(c.nID,c);
				childCategories.addElement(c);
				c.parentCategory = this;
			}
			rs.close();
			stmt.close();
			
			Enumeration enum2 = childCategories.elements();
			while(enum2.hasMoreElements())
			{
				Category c = (Category)enum2.nextElement();
				c.loadChildCategories(categories,conn);
				c.loadProducts(categories.website,conn);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public Category(HttpServletRequest rs)
	{
		
		nID						=Integer.parseInt(rs.getParameter("nID"));
		nParentID				=Integer.parseInt(rs.getParameter("nParentID"));
		txtName					=rs.getParameter("txtName");
		txtDescription			=rs.getParameter("txtDescription");
		txtTitle				=rs.getParameter("txtTitle");
		txtMeta					=rs.getParameter("txtMeta");
		txtHeader				=rs.getParameter("txtHeader");
		txtFooter				=rs.getParameter("txtFooter");
		txtMetaReplyTo			=rs.getParameter("txtMetaReplyTo");
		txtMetaLanguage			=rs.getParameter("txtMetaLanguage");
		txtMetaDistribution		=rs.getParameter("txtMetaDistribution");
		txtMetaCopyright		=rs.getParameter("txtMetaCopyright");
		txtMetaClassification	=rs.getParameter("txtMetaClassification");
		txtMetaAuthor			=rs.getParameter("txtMetaAuthor");
		txtMetaRevisitAfter		=rs.getParameter("txtMetaRevisitAfter");
		txtMetaDescription		=rs.getParameter("txtMetaDescription");
		txtMetaRobots			=rs.getParameter("txtMetaRobots");
		txtMetaKeywords			=rs.getParameter("txtMetaKeywords");

	}
	
	public boolean insert(DataConnection conn)
	{
		
		try
		{
			PreparedStatement stmt = conn.prepareStatement(insertSQL);
			if (nParentID == 0) {
			    stmt.setNull(1,java.sql.Types.INTEGER);
			} else {
			    stmt.setInt(1,nParentID);
			}
			stmt.setString(2,txtName);
			stmt.setString(3,txtDescription);
			stmt.setString(4,txtTitle);
			stmt.setString(5,txtMeta);
			stmt.setString(6,txtHeader);
			stmt.setString(7,txtFooter);
			stmt.setString(8,txtMetaReplyTo);
			stmt.setString(9,txtMetaLanguage);
			stmt.setString(10,txtMetaDistribution);
			stmt.setString(11,txtMetaCopyright);
			stmt.setString(12,txtMetaClassification);
			stmt.setString(13,txtMetaAuthor);
			stmt.setString(14,txtMetaRevisitAfter);
			stmt.setString(15,txtMetaDescription);
			stmt.setString(16,txtMetaRobots);
			stmt.setString(17,txtMetaKeywords);
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
			if (nParentID == 0) {
			    stmt.setNull(1,java.sql.Types.INTEGER);
			} else {
			    stmt.setInt(1,nParentID);
			}
			stmt.setString(2,txtName);
			stmt.setString(3,txtDescription);
			stmt.setString(4,txtTitle);
			stmt.setString(5,txtMeta);
			stmt.setString(6,txtHeader);
			stmt.setString(7,txtFooter);
			stmt.setString(8,txtMetaReplyTo);
			stmt.setString(9,txtMetaLanguage);
			stmt.setString(10,txtMetaDistribution);
			stmt.setString(11,txtMetaCopyright);
			stmt.setString(12,txtMetaClassification);
			stmt.setString(13,txtMetaAuthor);
			stmt.setString(14,txtMetaRevisitAfter);
			stmt.setString(15,txtMetaDescription);
			stmt.setString(16,txtMetaRobots);
			stmt.setString(17,txtMetaKeywords);
			stmt.setInt(18,nID);
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
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM Categories WHERE nID=?");
			stmt.setInt(1,nID);
			stmt.execute();
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}
	
	public String getHierarchy(String seperator,String query,String uri)
	{
		String html = "";
		if(parentCategory != null)
		{
			html = parentCategory.getHierarchy(seperator,query,uri);
			html += seperator;
		}
		html += "<A href='"+uri+"?nParentID="+String.valueOf(nID)+"&"+query+"'>" + txtName + "</A>" ;
		return html;
	}
	
	public String getFullname()
	{
		return getFullname("\\");
	}
	
	public String getFullname(String seperator)
	{
		String name = "";
		if(parentCategory != null)
		{
			name = parentCategory.getFullname(seperator);
			name += seperator;
		}
		name += txtName;
		return name;
	}
	
	public String getProductIDs()
	{
		String ids = "";
		Enumeration enum2 = products.elements();
		while(enum2.hasMoreElements())
		{
			Product p = (Product) enum2.nextElement();
			ids += String.valueOf(p.nID) +  ",";
		}
		if(ids.length()>1)
		{
			ids = ids.substring(0,ids.length()-1);
		}
		return ids;
		
	}
}
