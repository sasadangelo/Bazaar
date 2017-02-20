package nbp.jspcart.products;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.nspeech.data.DataConnection;

public class Product {

	protected static String insertSQL =
		"INSERT INTO Products(nID, txtName, txtDescription, txtImgUrl, txtHeader, txtFooter, "               +
		"						txtBuyNowUrl, txtWeight, dblQtyPerPack, dblPricePerQty, bActive, "           +
		"						dblPrice, dblDummyPrice, dblShippingFactor, dblCostPerQty, dblCost, bShow) " +
		"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		;
	protected static String editSQL =
		"UPDATE Products SET txtName=?, "       	+
		"					txtDescription=?, " 	+
		"					txtImgUrl=?, "      	+
		"					txtHeader=?, "      	+
		"					txtFooter=?, "      	+
		"					txtBuyNowUrl=?, "		+
		"					txtWeight=?," 			+
		"					dblQtyPerPack=?, "  	+
		"					dblPricePerQty=?, " 	+
		"					bActive=?, "			+
		"					dblPrice=?, "			+
		"					dblDummyPrice=?, "  	+
		"					dblShippingFactor=?, " 	+
		"					dblCostPerQty=?, "		+
		"					dblCost=?, "			+
		"					bShow=? "				+
		"WHERE nID=?";

	public static Product defaultObject;

	static {
		//Default Object for default parameters
		defaultObject = new Product();
	}

	// Members
	public int nID;
	public String txtName;
	public String txtDescription;
	public String txtImgUrl;
	public String txtHeader;
	public String txtFooter;
	public String txtBuyNowUrl;
	public String txtWeight;
	public double dblQtyPerPack;
	public double dblPricePerQty;
	public int bActive;
	public double dblPrice;
	public double dblDummyPrice;
	public double dblShippingFactor;
	public double dblCostPerQty;
	public double dblCost;
	public int bShow;

	public Product() {

		nID = 0;
		txtName = txtDescription = txtImgUrl = txtHeader = txtFooter = txtBuyNowUrl = txtWeight = "";
		dblQtyPerPack = 1;
		dblPricePerQty = 0;
		bActive = 1;
		dblPrice = 0.0;
		dblDummyPrice = 0.0;
		dblShippingFactor = 0.0;
		dblCostPerQty = 0;
		dblCost = 0;
		bShow = 0;
	}

	public Product(ResultSet rs) throws SQLException {
		nID = rs.getInt("nID");
		txtName = rs.getString("txtName");
		txtDescription = rs.getString("txtDescription");
		txtImgUrl = rs.getString("txtImgUrl");
		txtHeader = rs.getString("txtHeader");
		txtFooter = rs.getString("txtFooter");
		txtBuyNowUrl = rs.getString("txtBuyNowUrl");
		txtWeight = rs.getString("txtWeight");
		dblQtyPerPack = rs.getDouble("dblQtyPerPack");
		dblPricePerQty = rs.getDouble("dblPricePerQty");
		bActive = rs.getInt("bActive");
		dblPrice = rs.getDouble("dblPrice");
		dblDummyPrice = rs.getDouble("dblDummyPrice");
		dblShippingFactor = rs.getDouble("dblShippingFactor");
		dblCostPerQty = rs.getDouble("dblCostPerQty");
		dblCost = rs.getDouble("dblCost");
		bShow = rs.getInt("bShow");

		if (txtBuyNowUrl != null) {
			txtBuyNowUrl = txtBuyNowUrl.trim();
			if (txtBuyNowUrl.length() == 0)
				txtBuyNowUrl = null;
		}

		if (txtHeader != null) {
			txtHeader = txtHeader.trim();
			if (txtHeader.length() == 0)
				txtHeader = null;
		}

		if (txtFooter != null) {
			txtFooter = txtFooter.trim();
			if (txtFooter.length() == 0)
				txtFooter = null;
		}
	}

	public Product(Multipart part) {
		nID = part.nID;
		txtName = part.txtName;
        txtDescription = part.txtDescription;
        txtImgUrl = part.txtImgUrl;
        txtHeader = part.txtHeader;
        txtFooter = part.txtFooter;
        txtBuyNowUrl = part.txtBuyNowUrl;
        txtWeight = part.txtWeight;
        dblQtyPerPack = part.dblQtyPerPack;
        dblPricePerQty = part.dblPricePerQty;
        bActive = part.bActive;
        dblPrice = part.dblPrice;
        dblDummyPrice = part.dblDummyPrice;
        dblShippingFactor = part.dblShippingFactor;
		dblCostPerQty = part.dblCostPerQty;
        dblCost = part.dblCost;
        bShow = part.bShow;
	}

	public Product(HttpServletRequest rs) {
		nID = Integer.parseInt(rs.getParameter("nID"));
		txtName = rs.getParameter("txtName");
		txtDescription = rs.getParameter("txtDescription");
		txtImgUrl = rs.getParameter("txtImgUrl");
		txtHeader = rs.getParameter("txtHeader");
		txtFooter = rs.getParameter("txtFooter");
		txtBuyNowUrl = rs.getParameter("txtBuyNowUrl");
		txtWeight = rs.getParameter("txtWeight");
		dblQtyPerPack = Double.parseDouble(rs.getParameter("dblQtyPerPack"));
		dblPricePerQty = Double.parseDouble(rs.getParameter("dblPricePerQty"));
		bActive = Integer.parseInt(rs.getParameter("bActive"));
		dblPrice = Double.parseDouble(rs.getParameter("dblPrice"));
		dblDummyPrice = Double.parseDouble(rs.getParameter("dblDummyPrice"));
		dblShippingFactor = Double.parseDouble(rs
				.getParameter("dblShippingFactor"));
		dblCostPerQty = Double.parseDouble(rs.getParameter("dblCostPerQty"));
		dblCost = Double.parseDouble(rs.getParameter("dblCost"));
		bShow = Integer.parseInt(rs.getParameter("bShow"));
	}

	public boolean insert(DataConnection conn) {

		if (txtBuyNowUrl != null) {
			txtBuyNowUrl = txtBuyNowUrl.trim();
			if (txtBuyNowUrl.length() == 0)
				txtBuyNowUrl = null;
		}

		if (txtHeader != null) {
			txtHeader = txtHeader.trim();
			if (txtHeader.length() == 0)
				txtHeader = null;
		}

		if (txtFooter != null) {
			txtFooter = txtFooter.trim();
			if (txtFooter.length() == 0)
				txtFooter = null;
		}

		try {
			PreparedStatement stmt = conn.prepareStatement(insertSQL);
			stmt.setInt(1, nID);
			stmt.setString(2, txtName);
			stmt.setString(3, txtDescription);
			stmt.setString(4, txtImgUrl);
			stmt.setString(5, txtHeader);
			stmt.setString(6, txtFooter);
			stmt.setString(7, txtBuyNowUrl);
			stmt.setString(8, txtWeight);
			stmt.setDouble(9, dblQtyPerPack);
			stmt.setDouble(10, dblPricePerQty);
			stmt.setInt(11, bActive);
			stmt.setDouble(12, dblPrice);
			stmt.setDouble(13, dblDummyPrice);
			stmt.setDouble(14, dblShippingFactor);
			stmt.setDouble(15, dblCostPerQty);
			stmt.setDouble(16, dblCost);
			stmt.setInt(17, bShow);
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	public boolean edit(DataConnection conn) {

		if (txtBuyNowUrl != null) {
			txtBuyNowUrl = txtBuyNowUrl.trim();
			if (txtBuyNowUrl.length() == 0)
				txtBuyNowUrl = null;
		}

		if (txtHeader != null) {
			txtHeader = txtHeader.trim();
			if (txtHeader.length() == 0)
				txtHeader = null;
		}

		if (txtFooter != null) {
			txtFooter = txtFooter.trim();
			if (txtFooter.length() == 0)
				txtFooter = null;
		}

		try {
			PreparedStatement stmt = conn.prepareStatement(editSQL);
			stmt.setString(1, txtName);
			stmt.setString(2, txtDescription);
			stmt.setString(3, txtImgUrl);
			stmt.setString(4, txtHeader);
			stmt.setString(5, txtFooter);
			stmt.setString(6, txtBuyNowUrl);
			stmt.setString(7, txtWeight);
			stmt.setDouble(8, dblQtyPerPack);
			stmt.setDouble(9, dblPricePerQty);
			stmt.setInt(10, bActive);
			stmt.setDouble(11, dblPrice);
			stmt.setDouble(12, dblDummyPrice);
			stmt.setDouble(13, dblShippingFactor);
			stmt.setDouble(14, dblCostPerQty);
			stmt.setDouble(15, dblCost);
			stmt.setInt(16, bShow);
			stmt.setInt(17, nID);
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	public boolean delete(DataConnection conn) {
		try {
			PreparedStatement stmt = conn
					.prepareStatement("DELETE FROM Products WHERE nID=?");
			stmt.setInt(1, nID);
			stmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}