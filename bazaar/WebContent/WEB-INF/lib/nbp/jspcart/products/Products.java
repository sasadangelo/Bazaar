package nbp.jspcart.products;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Enumeration;
import java.util.Vector;

import nbp.jspcart.WebSite;
import nbp.jspcart.categories.Category;
import nbp.jspcart.data.DBConnection;

import org.nspeech.util.IntMap;

public class Products {

	public WebSite website = null;
	public IntMap products = new IntMap();

	public Products(WebSite ws) {
		website = ws;
		loadMaxID();
		loadProducts();
	}

	public int nMaxID = 0;

	int getNewID() {
		int nID;
		synchronized (this) {
			nID = ++nMaxID;
		}
		return nID;
	}

	void loadMaxID() {
		DBConnection conn = website.getConnection();
		synchronized (conn) {
			try {
				PreparedStatement stmt = conn
						.prepareStatement("SELECT nID FROM Products ORDER BY nID DESC LIMIT 1");
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					nMaxID = rs.getInt(1);
				}
			} catch (Exception e) {
				website.logger.write(e);
			}
		}
	}

	void loadProducts() {
		DBConnection conn = website.getConnection();
		synchronized (conn) {
			try {
				PreparedStatement stmt = conn
						.prepareStatement("SELECT * FROM Products");
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					Product product = new Product(rs);
					products.put(product.nID, product);
				}
				rs.close();
				stmt.close();
			} catch (Exception e) {
				website.logger.write(e);
			}
		}
	}

	public String insert(Product obj) {
		DBConnection conn = website.getConnection();
		synchronized (conn) {
			obj.nID = getNewID();
			if (!obj.insert(conn))
				return "Si e' verificato un errore nel database.";
		}
		
		loadProducts();
		/*
		try {
			IProductDAO productDAO = ProductDAOFactory.makeFactory(website).getDAOProduct();
			productDAO.create(obj);
		} catch(DAOException exc) {
			exc.printStackTrace();
			return exc.getMessage();
		}*/
		return null;
	}

	public String edit(Product obj) {
		DBConnection conn = website.getConnection();
		synchronized (conn) {
			if (!obj.edit(conn))
				return "Si e' verificato un errore nel database.";
		}
		products.put(obj.nID, obj);
		Product s = (Product) products.get(obj.nID);
/*
		try {
			DAOProduct productDAO = DAOFactory.makeFactory(website).getDAOProduct();
			productDAO.update(obj);
		} catch(DAOException exc) {
			exc.printStackTrace();
			return exc.getMessage();
		}
	*/	
		return null;
	}

	public String delete(Product obj) {
		Vector cats = getProductCategories(obj.nID);

		DBConnection conn = website.getConnection();
		synchronized (conn) {
			if (!obj.delete(conn))
				return "Si e' verificato un errore nel database.";
		}
		products.remove(obj.nID);

		Enumeration enumCats = cats.elements();
		while (enumCats.hasMoreElements()) {
			Category cat = (Category) enumCats.nextElement();
			//website.categories.deleteProduct(cat, obj);
			website.categories.deleteProductFromCache(cat, obj);
		}
/*
		try {
			DAOProduct productDAO = DAOFactory.makeFactory(website).getDAOProduct();
			productDAO.delete(obj.nID);
		} catch(DAOException exc) {
			exc.printStackTrace();
			return exc.getMessage();
		}
	*/	
		return null;
	}

	public Vector getAll(int nStart, int nSize) {
		Vector items = new Vector();
		DBConnection conn = website.getConnection();
		synchronized (conn) {
			try {
				PreparedStatement stmt = conn
						.prepareStatement("SELECT nID FROM Products LIMIT ?,?");
				stmt.setInt(1, nStart);
				stmt.setInt(2, nSize);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					items.addElement(products.get(rs.getInt(1)));
				}
				rs.close();
				stmt.close();
			} catch (Exception e) {
				website.logger.write(e);
			}
		}
		return items;
	}

	public Vector getAll(String txtSearch,String txtExceptIDs,int nStart,int nSize)
	{
		Vector items = new Vector();
		DBConnection conn = website.getConnection();
		synchronized(conn){
			try
			{
				PreparedStatement stmt= null;
				if(txtExceptIDs.length()==0)
					stmt = conn.prepareStatement("SELECT nID FROM Products WHERE (txtName LIKE ?) LIMIT ?,?");
				else
					stmt = conn.prepareStatement("SELECT nID FROM Products WHERE (txtName LIKE ?) AND (nID NOT IN ("+txtExceptIDs+")) LIMIT ?,?");
				stmt.setString(1,txtSearch);
				stmt.setInt(2,nStart);
				stmt.setInt(3,nSize);
				ResultSet rs = stmt.executeQuery();
				while(rs.next())
				{
					items.addElement( products.get(rs.getInt(1)) );
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
	
	public Vector getAll() {
		Vector items = new Vector();
		DBConnection conn = website.getConnection();
		synchronized (conn) {
			try {
				PreparedStatement stmt = conn
						.prepareStatement("SELECT nID FROM Products");
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					items.addElement(products.get(rs.getInt(1)));
				}
				rs.close();
				stmt.close();
			} catch (Exception e) {
				website.logger.write(e);
			}
		}
		return items;
	}
	
	public Vector getAllShow(int nStart, int nSize) {
		Vector items = new Vector();
		DBConnection conn = website.getConnection();
		synchronized (conn) {
			try {
				PreparedStatement stmt = conn
						.prepareStatement("SELECT nID FROM Products WHERE bShow=1 LIMIT ?,?");
				stmt.setInt(1, nStart);
				stmt.setInt(2, nSize);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					items.addElement(products.get(rs.getInt(1)));
				}
				rs.close();
				stmt.close();
			} catch (Exception e) {
				website.logger.write(e);
			}
		}
		return items;
	}

	public Vector getAllShow() {
		Vector items = new Vector();
		DBConnection conn = website.getConnection();
		synchronized (conn) {
			try {
				PreparedStatement stmt = conn
						.prepareStatement("SELECT nID FROM Products WHERE bShow=1");
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					items.addElement(products.get(rs.getInt(1)));
				}
				rs.close();
				stmt.close();
			} catch (Exception e) {
				website.logger.write(e);
			}
		}
		return items;
	}

	/*
	 * public Vector getAll(String txtSearch,int nStart,int nSize) { Vector
	 * items = new Vector(); DBConnection conn = website.getConnection();
	 * synchronized(conn){ try { PreparedStatement stmt =
	 * conn.prepareStatement("SELECT nID FROM Products WHERE txtName LIKE ?
	 * LIMIT ?,?"); stmt.setString(1,txtSearch); stmt.setInt(2,nStart);
	 * stmt.setInt(3,nSize); ResultSet rs = stmt.executeQuery();
	 * while(rs.next()) { items.addElement( products.get(rs.getInt(1)) ); }
	 * rs.close(); stmt.close(); } catch (Exception e) {
	 * website.logger.write(e); } } return items; }
	 * 
	 * public Vector getAll(String txtSearch,String txtExceptIDs,int nStart,int
	 * nSize) { Vector items = new Vector(); DBConnection conn =
	 * website.getConnection(); synchronized(conn){ try { PreparedStatement
	 * stmt= null; if(txtExceptIDs.length()==0) stmt =
	 * conn.prepareStatement("SELECT nID FROM Products WHERE (txtName LIKE ?)
	 * LIMIT ?,?"); else stmt = conn.prepareStatement("SELECT nID FROM Products
	 * WHERE (txtName LIKE ?) AND (nID NOT IN ("+txtExceptIDs+")) LIMIT ?,?");
	 * stmt.setString(1,txtSearch); stmt.setInt(2,nStart); stmt.setInt(3,nSize);
	 * ResultSet rs = stmt.executeQuery(); while(rs.next()) { items.addElement(
	 * products.get(rs.getInt(1)) ); } rs.close(); stmt.close(); } catch
	 * (Exception e) { website.logger.write(e); } } return items; }
	 */

	public Product get(int nID) {
		return (Product) products.get(nID);
	}

	public Vector getProductCategories(int nID) {
		Vector categories = new Vector();
		DBConnection conn = website.getConnection();
		synchronized (conn) {
			try {
				PreparedStatement stmt = conn
						.prepareStatement("select nCategoryID FROM CategoryProducts WHERE nProductID=?");
				stmt.setInt(1, nID);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					int nCatID = rs.getInt(1);
					categories.addElement(website.categories.get(nCatID));
				}
			} catch (Exception e) {
				website.logger.write(e);
			}
		}
		return categories;
	}
}
