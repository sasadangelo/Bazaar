package nbp.jspcart.orders;

import java.io.*;
import java.util.*;

import java.util.zip.*;

import java.text.*;
import nbp.jspcart.*;
import nbp.jspcart.users.*;
import nbp.jspcart.products.*;

public class OrderPrinter  
{
	PrintWriter writer;
	
	NumberFormat nf;
	
	WebSite website = null;
	Users users = null;
	Products products = null;
	
	public OrderPrinter(WebSite ws)
	{
		website = ws;
		users = ws.users;
		products = ws.products;
	}
		// Usual Dot Matrix..
	static final int			nWidth = 80;
	static final int			nLength = 56;
	
	int			nWritten = 0;
	
	public void print(String filename,String filepath,Vector orders) throws Exception
	{
		try
		{
			writer = new PrintWriter( new FileOutputStream ( filepath ) );
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new ExceptionInInitializerError("Impossibile accedere al file.");
		}
		
		nf = NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);

		Enumeration enum2 = orders.elements();
		while(enum2.hasMoreElements())
		{
			Order order = (Order)enum2.nextElement();
			printOrder(order);
		}
		
		writer.close();
		writer = null;
		
		zipFile(filename,filepath);	
	}
	
	void zipFile(String filename,String filepath)
	{
		try
		{
			ZipOutputStream out = new ZipOutputStream( new FileOutputStream(filepath+".zip"));
			//out.setLevel(9);
		
			ZipEntry entry = new ZipEntry(filename);
			out.putNextEntry(entry);
			
			FileInputStream fis = new FileInputStream(filepath);
			byte buffer[] = new byte [1024];
			int nRead;
			
			do
			{
				nRead = fis.read(buffer,0,1024);
				if(nRead==-1)
					break;
				out.write(buffer,0,nRead);
				out.flush();
			}while(true);
			fis.close();
			out.close();
			
			File file = new File(filepath);
			file.delete();
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	void printOrder(Order order)
	{
		writeLine("================================================================================");
		writeLine("                             "+website.shopName+" Order     ");
		writeLine("--------------------------------------------------------------------------------");
		
		org.nspeech.util.Date dt = new org.nspeech.util.Date(order.tsDate);
		writeLine("OrderID      : " + String.valueOf(order.nID));
		writeLine("Date         : " + dt.getShortDate());
		writeLine("Username     : " + order.txtEmailAddress);
		writeLine("Grand Total  : " + toString(order.dblGrandTotal) );
		writeLine(" ");
		printShipping(order);
		
		Enumeration enum2 = order.orderedItems.elements();
		while(enum2.hasMoreElements())
		{
			OrderedItem item = (OrderedItem) enum2.nextElement();
			printOrderedItem(item);
		}
		
		writeLine("--------------------------------------------------------------------------------");
		writeLine("Office Use:");
		writeLine(" ");
		writeLine("Order Processed By :- ");
		writeLine("Ref. No. :- ");
		writeLine("Courier Receipt No :- ");
		writeLine("Date And Time :-");
		writeLine("================================================================================");
		
		pageBreak();
	}
	
	void printShipping(Order order)
	{
		ShippingAddress addr = order.shippingAddress;
		if(addr==null)
			return;
		writeLine("--------------------------------------------------------------------------------");
		writeLine("Shipping Address:");
		writeLine("Name         : " + addr.txtName);
		writeLine("Phone        : " + addr.txtPhone);
		writeLine("Address      : " + addr.txtStreetAddress);
		writeLine("City         : " + addr.txtCity);
		writeLine("State        : " + addr.txtState);
		writeLine("Country      : " + addr.txtCountry);
		writeLine("Zip          : " + addr.txtZipCode);
		writeLine("Shipping Method = " + addr.txtShippingMethod);
		writeLine("--------------------------------------------------------------------------------");
	}
	
	void printOrderedItem(OrderedItem item)
	{
		writeLine("--------------------------------------------------------------------------------");
		
		Product product = products.get(item.nProductID);
		
		writeLine("Value        : " + product.txtName + " " + product.txtWeight);
		writeLine("Items Per Pack : " + toString(product.dblQtyPerPack));
		writeLine("Quantity       : " + toString(item.nQuantity));
		writeLine("Amount         : " + toString(item.dblPrice));
		writeLine("Order Reason   : ");
		writeLine("    " + item.txtReason);
		writeLine("--------------------------------------------------------------------------------");
	}
	
	void writeContent(String name,String value)
	{
		String text = "            ";
		if(name.length()<text.length())
		{
			name = name + text.substring(name.length());
		}
		writeLine(name + ":" + value);
	}
	
	void writeLine(String line)
	{
		while(line.length()>nWidth)
		{
			String left = line.substring(0,nWidth-1);
			writeLineEx(left);
			line = line.substring(nWidth);
		}
		writeLineEx(line);
	}
	
	void writeLineEx(String line)
	{
		writer.println(line);
		nWritten ++;
		if(nWritten==nLength)
		{
			pageBreak();
		}
	}
	
	void pageBreak()
	{
		writer.println("");
		nWritten = 0;
	}
	
	String toString(double dbl)
	{
		return nf.format(dbl);		
	}
	
	String toString(int n)
	{
		return String.valueOf(n);
	}
}