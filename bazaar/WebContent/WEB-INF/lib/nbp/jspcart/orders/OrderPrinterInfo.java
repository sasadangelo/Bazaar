package nbp.jspcart.orders;

import java.io.*;
import java.util.*;

import java.util.zip.*;

import java.text.*;
import nbp.jspcart.*;
import nbp.jspcart.users.*;
import nbp.jspcart.products.*;

public class OrderPrinterInfo {
	PrintWriter writer;

	NumberFormat nf;

	WebSite website = null;

	Users users = null;

	Products products = null;

	public OrderPrinterInfo(WebSite ws) {
		website = ws;
		users = ws.users;
		products = ws.products;
	}

	// Usual Dot Matrix..
	static final int nWidth = 80;

	static final int nLength = 56;

	int nWritten = 0;

	public void print(String filename, String filepath, Vector orders)
			throws Exception {
		try {
			writer = new PrintWriter(new FileOutputStream(filepath));
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError(
					"Impossibile accedere al file.");
		}

		nf = NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);

		Enumeration enum2 = orders.elements();
		while (enum2.hasMoreElements()) {
			Order order = (Order) enum2.nextElement();
			printOrder(order);
		}

		writer.close();
		writer = null;

		zipFile(filename, filepath);
	}

	void zipFile(String filename, String filepath) {
		try {
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
					filepath + ".zip"));
			//out.setLevel(9);

			ZipEntry entry = new ZipEntry(filename);
			out.putNextEntry(entry);

			FileInputStream fis = new FileInputStream(filepath);
			byte buffer[] = new byte[1024];
			int nRead;

			do {
				nRead = fis.read(buffer, 0, 1024);
				if (nRead == -1)
					break;
				out.write(buffer, 0, nRead);
				out.flush();
			} while (true);
			fis.close();
			out.close();

			File file = new File(filepath);
			file.delete();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void printOrder(Order order) {
		writeLine("================================================================================");
		writeLine("                             " + website.shopName
				+ " Order     ");
		writeLine("--------------------------------------------------------------------------------");

		org.nspeech.util.Date dt = new org.nspeech.util.Date(order.tsDate);
		writeLine("OrderID      : " + String.valueOf(order.nID));
		writeLine("Date         : " + dt.getShortDate());

		UserProfile profile = users.getProfile(order.txtEmailAddress);

		writeLine("Name     : " + profile.txtFirstname + " "
				+ profile.txtLastname);

		writeLine(" ");

		Enumeration enum2 = order.orderedItems.elements();
		while (enum2.hasMoreElements()) {
			OrderedItem item = (OrderedItem) enum2.nextElement();
			printOrderedItem(item);
		}

		writeLine("================================================================================");
		pageBreak();
	}

	void printOrderedItem(OrderedItem item) {
		writeLine("--------------------------------------------------------------------------------");

		Product product = products.get(item.nProductID);

		writeLine("Value      : " + product.txtName + " " + product.txtWeight);
		writeLine("Quantity     : " + toString(item.nQuantity));
		writeLine("Order Reason : ");
		writeLine("    " + item.txtReason);
		writeLine("--------------------------------------------------------------------------------");
	}

	void writeContent(String name, String value) {
		String text = "            ";
		if (name.length() < text.length()) {
			name = name + text.substring(name.length());
		}
		writeLine(name + ":" + value);
	}

	void writeLine(String line) {
		while (line.length() > nWidth) {
			String left = line.substring(0, nWidth - 1);
			writeLineEx(left);
			line = line.substring(nWidth);
		}
		writeLineEx(line);
	}

	void writeLineEx(String line) {
		writer.println(line);
		nWritten++;
		if (nWritten == nLength) {
			pageBreak();
		}
	}

	void pageBreak() {
		writer.println("");
		nWritten = 0;
	}

	String toString(double dbl) {
		return nf.format(dbl);
	}

	String toString(int n) {
		return String.valueOf(n);
	}
}