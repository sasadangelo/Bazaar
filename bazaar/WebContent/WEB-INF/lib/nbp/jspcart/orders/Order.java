package nbp.jspcart.orders;

import java.sql.*;
import java.util.*;
import javax.servlet.http.*;

import org.nspeech.data.*;

import nbp.jspcart.*;
import nbp.jspcart.products.*;
import nbp.jspcart.countries.*;
import nbp.jspcart.variables.*;

public class Order {

	public static final int PAYMENT_PENDING = 0;         // Payment Not done
	public static final int PAYMENT_AWAITED = 1;         // Payment Done but Credit Card
												         // Authorization Pending
	public static final int PAYMENT_REJECTED = 2;        // Payment Rejected
	public static final int PROCESSING_PENDING = 3;      // Payment Done, Order
													     // Processing Pending
	public static final int PROCESSED = 4;               // Order is Processed, tracking pending
	public static final int REJECTED = 5;                // Order is rejected by site owner due to some reason
	public static final int CANCELLED = 6;               // Order is cancelled by user
	public static final int PROCESSED_TRACKING_SENT = 7; //Order is Processed,
														 // tracking sent
	protected static String insertSQL;
	protected static String editSQL;
	public static Order defaultObject;

	static {
		//Default Object for default parameters
		defaultObject = new Order();

		insertSQL = "INSERT INTO Orders(";
		insertSQL += "nID,";
		insertSQL += "txtEmailAddress,";
		insertSQL += "tsDate,";
		insertSQL += "nOrderState,";
		insertSQL += "dblTotal,";
		insertSQL += "dblProcessingFees,";
		insertSQL += "dblShippingCharges,";
		insertSQL += "dblGrandTotal,";
		insertSQL += "dblPaid,";
		insertSQL += "txtRemarks,";
		insertSQL += "txtTracking,";
		insertSQL += "txtTrackingCompany,";
		insertSQL += "txtTrackingURL";
		insertSQL += ") VALUES (";
		insertSQL += "?,";
		insertSQL += "?,";
		insertSQL += "NULL,";
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

		editSQL = "UPDATE Orders SET ";
		editSQL += "txtEmailAddress=?,";
		editSQL += "nOrderState=?,";
		editSQL += "dblTotal=?,";
		editSQL += "dblProcessingFees=?,";
		editSQL += "dblShippingCharges=?,";
		editSQL += "dblGrandTotal=?,";
		editSQL += "dblPaid=?,";
		editSQL += "txtRemarks=?,";
		editSQL += "txtTracking=?,";
		editSQL += "txtTrackingCompany=?,";
		editSQL += "txtTrackingURL=?";
		editSQL += " WHERE ";
		editSQL += "nID=?";

	}

	// Members
	public int nID;
	public String txtEmailAddress;
	public Timestamp tsChangeTime;
	public Timestamp tsDate;
	public int nOrderState;
	public double dblTotal;
	public double dblProcessingFees;
	public double dblShippingCharges;
	public double dblGrandTotal;
	public double dblPaid;
	public String txtRemarks;
	public String txtTracking;
	public String txtTrackingCompany;
	public String txtTrackingURL;
	public Vector orderedItems = new Vector();
	public ShippingAddress shippingAddress = null;

	public Order() {
		nID = 0;
		txtEmailAddress = "";
		tsChangeTime = null;
		tsDate = null;
		nOrderState = PAYMENT_PENDING;
		dblTotal = 0.0;
		dblProcessingFees = 0.0;
		dblShippingCharges = 0.0;
		dblGrandTotal = 0.0;
		dblPaid = 0.0;
		txtRemarks = txtTracking = txtTrackingCompany = txtTrackingURL = "";
	}

	public Order(ResultSet rs) throws SQLException {
		nID = rs.getInt("nID");
		txtEmailAddress = rs.getString("txtEmailAddress");
		tsChangeTime = rs.getTimestamp("tsChangeTime");
		tsDate = rs.getTimestamp("tsDate");
		nOrderState = rs.getInt("nOrderState");
		dblTotal = rs.getDouble("dblTotal");
		dblProcessingFees = rs.getDouble("dblProcessingFees");
		dblShippingCharges = rs.getDouble("dblShippingCharges");
		dblGrandTotal = rs.getDouble("dblGrandTotal");
		dblPaid = rs.getDouble("dblPaid");
		txtRemarks = rs.getString("txtRemarks");
		txtTracking = rs.getString("txtTracking");
		txtTrackingCompany = rs.getString("txtTrackingCompany");
		txtTrackingURL = rs.getString("txtTrackingURL");
	}

	public Order(HttpServletRequest rs) {
		nID = Integer.parseInt(rs.getParameter("nID"));
		txtEmailAddress = rs.getParameter("txtEmailAddress");
		nOrderState = Integer.parseInt(rs.getParameter("nOrderState"));
		dblTotal = Double.parseDouble(rs.getParameter("dblTotal"));
		dblProcessingFees = Double.parseDouble(rs
				.getParameter("dblProcessingFees"));
		dblShippingCharges = Double.parseDouble(rs
				.getParameter("dblShippingCharges"));
		dblGrandTotal = Double.parseDouble(rs.getParameter("dblGrandTotal"));
		dblPaid = Double.parseDouble(rs.getParameter("dblPaid"));
		txtRemarks = rs.getParameter("txtRemarks");
		txtTracking = rs.getParameter("txtTracking");
		txtTrackingCompany = rs.getParameter("txtTrackingCompany");
		txtTrackingURL = rs.getParameter("txtTrackingURL");
	}

	public void clear() {
		nID = 0;
		tsDate = null;
		nOrderState = PAYMENT_PENDING;
		dblTotal = 0;
		dblProcessingFees = 0;
		dblShippingCharges = 0;
		dblGrandTotal = 0;

		orderedItems.removeAllElements();

		shippingAddress = null;
	}

	public boolean insert(DataConnection conn) {
		try {
			PreparedStatement stmt = conn.prepareStatement(insertSQL);
			stmt.setInt(1, nID);
			stmt.setString(2, txtEmailAddress);
			stmt.setInt(3, nOrderState);
			stmt.setDouble(4, dblTotal);
			stmt.setDouble(5, dblProcessingFees);
			stmt.setDouble(6, dblShippingCharges);
			stmt.setDouble(7, dblGrandTotal);
			stmt.setDouble(8, dblPaid);
			stmt.setString(9, txtRemarks);
			stmt.setString(10, txtTracking);
			stmt.setString(11, txtTrackingCompany);
			stmt.setString(12, txtTrackingURL);
			stmt.execute();
			stmt.close();

			// Assign it to all ordered items
			Enumeration enum2 = orderedItems.elements();
			while (enum2.hasMoreElements()) {
				OrderedItem oi = (OrderedItem) enum2.nextElement();
				oi.nOrderID = nID;
				if (!oi.insert(conn)) {
					throw new Exception(
							"Impossibile aggiungere il prodotto nell'ordine.");
				}
			}

			// Insert Shipping Address now
			shippingAddress.nOrderID = nID;
			if (!shippingAddress.insert(conn)) {
				throw new Exception("Impossibile aggiungere l'indirizzo del destinatario.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean edit(DataConnection conn) {
		try {
			PreparedStatement stmt = conn.prepareStatement(editSQL);
			stmt.setString(1, txtEmailAddress);
			stmt.setInt(2, nOrderState);
			stmt.setDouble(3, dblTotal);
			stmt.setDouble(4, dblProcessingFees);
			stmt.setDouble(5, dblShippingCharges);
			stmt.setDouble(6, dblGrandTotal);
			stmt.setDouble(7, dblPaid);
			stmt.setString(8, txtRemarks);
			stmt.setString(9, txtTracking);
			stmt.setString(10, txtTrackingCompany);
			stmt.setString(11, txtTrackingURL);
			stmt.setInt(12, nID);
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
					.prepareStatement("DELETE FROM Orders WHERE nID=?");
			stmt.setInt(1, nID);
			stmt.execute();

			// Delete All Ordered Items too ...
			Enumeration enum2 = orderedItems.elements();
			while (enum2.hasMoreElements()) {
				OrderedItem oi = (OrderedItem) enum2.nextElement();
				if (!oi.delete(conn)) {
					throw new Exception(
							"Impossibile aggiungere il prodotto nell'ordine.");
				}
			}

			/*
			stmt = conn
					.prepareStatement("DELETE FROM OrderUserInfo WHERE nOrderID=?");
			stmt.setInt(1, nID);
			stmt.execute();
            */
			loadDetails(conn);

			// Delete Shipping Order too
			shippingAddress.delete(conn);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void loadDetails(DataConnection conn) {
		try {
			PreparedStatement stmt;
			ResultSet rs;

			stmt = conn
					.prepareStatement("SELECT * FROM OrderShippingAddress WHERE nOrderID=?");
			stmt.setInt(1, nID);
			rs = stmt.executeQuery();
			if (rs.next())
				shippingAddress = new ShippingAddress(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadOrderedItems(DataConnection conn) {
		try {
			PreparedStatement stmt = conn
					.prepareStatement("SELECT * FROM OrderedItems WHERE nOrderID=?");
			stmt.setInt(1, nID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				orderedItems.addElement(new OrderedItem(rs));
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getStatus() {
		return Order.getStatus(nOrderState);
	}

	public static String getStatus(int nState) {
		switch (nState) {
		case PAYMENT_PENDING:
			return "In Attesa di Spedizione/Pagamento";
		case PAYMENT_AWAITED:
			return "In Attesa di convalida Carta di Credito";
		case PAYMENT_REJECTED:
			return "Autorizzazione Carta Credito fallita";
		case PROCESSING_PENDING:
			return "Ordine Pagato ma non Eseguito ancora";
		case CANCELLED:
			return "Annullato";
		case REJECTED:
			return "Ordine Rifiutato";
		case PROCESSED:
			return "Ordine Eseguito e Spedito";
		case PROCESSED_TRACKING_SENT:
			return "Ordine Eseguito & Aggiornamento Tracking";
		}
		return "Sconosciuto";
	}

	// Cart Related Functions Here
	public void add(WebSite website, int nProductID, int nQuantity) {
		for (int i = 0; i < orderedItems.size(); i++) {
			OrderedItem oi = (OrderedItem) orderedItems.elementAt(i);
			if (oi.nProductID == nProductID) {
				oi.nQuantity += nQuantity;
				calculate(website);
				return;
			}
		}

		OrderedItem oi = new OrderedItem();
		oi.nProductID = nProductID;
		oi.nQuantity = nQuantity;
		Product product = website.products.get(nProductID);
		oi.dblPrice = product.dblPrice;
		oi.dblCost = product.dblCost;
		orderedItems.addElement(oi);

		calculateCart(website);
	}

	public void modify(WebSite website, int nProductID, int nQuantity) {
		for (int i = 0; i < orderedItems.size(); i++) {
			OrderedItem oi = (OrderedItem) orderedItems.elementAt(i);
			if (oi.nProductID == nProductID) {
				oi.nQuantity = nQuantity;
				calculateCart(website);
				return;
			}
		}
	}

	public void delete(WebSite website, int nProductID) {
		OrderedItem oi = new OrderedItem();
		oi.nProductID = nProductID;
		int i = orderedItems.indexOf(oi);
		if (i != -1)
			orderedItems.removeElementAt(i);
		calculateCart(website);
	}

	public void calculate(WebSite website) {
		Variable pcharge = website.variables.get("ProcessingCharge");
		Variable mxcharge = website.variables.get("MaximumProcessingValue");

		double dMaxCharge = mxcharge == null ? 0 : Double
				.parseDouble(mxcharge.txtValue);
		double dPCharge = pcharge == null ? 0 : Double
				.parseDouble(pcharge.txtValue);

		dblTotal = 0;
		Enumeration enum2 = orderedItems.elements();
		while (enum2.hasMoreElements()) {
			OrderedItem oi = (OrderedItem) enum2.nextElement();
			dblTotal += oi.nQuantity * oi.dblPrice;
		}

		if (dblProcessingFees <= 0)
			dblProcessingFees = dblTotal < dMaxCharge ? dPCharge : 0;

		dblGrandTotal = dblTotal + dblProcessingFees + dblShippingCharges;

		dblPaid = dblGrandTotal;
	}

	public void calculateCart(WebSite website) {
		Variable pcharge = website.variables.get("ProcessingCharge");
		Variable mxcharge = website.variables.get("MaximumProcessingValue");

		double dMaxCharge = mxcharge == null ? 0 : Double
				.parseDouble(mxcharge.txtValue);
		double dPCharge = pcharge == null ? 0 : Double
				.parseDouble(pcharge.txtValue);

		dblTotal = 0;
		Enumeration enum2 = orderedItems.elements();
		while (enum2.hasMoreElements()) {
			OrderedItem oi = (OrderedItem) enum2.nextElement();
			dblTotal += oi.nQuantity * oi.dblPrice;
		}

		dblProcessingFees = dblTotal < dMaxCharge ? dPCharge : 0;

		dblGrandTotal = dblTotal + dblProcessingFees + dblShippingCharges;

		dblPaid = dblGrandTotal;
	}

	public void setShippingCharge(WebSite website) {
		try {
			Country cn = website.countries.get(this.shippingAddress.txtCountry);
			if (shippingAddress.txtShippingMethod.equals("ExpressCourier"))
				dblShippingCharges = cn.dblExpressCourier;
			else
				dblShippingCharges = cn.dblRegisteredPost;
		} catch (Exception e) {
			//Logger.write(e);
			dblShippingCharges = 10;
		}
	}

	public int size() {
		return orderedItems.size();
	}
}
