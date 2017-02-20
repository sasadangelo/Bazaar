package nbp.jspcart.orders;

import java.io.*;
import java.util.*;

import java.util.zip.*;

import java.text.*;
import nbp.jspcart.*;
import nbp.jspcart.users.*;
import nbp.jspcart.products.*;

public class OrderPrinterCSV
{
	WebSite website = null;
	Users users = null;
	Products products = null;
	
	PrintWriter writer;
	
	public OrderPrinterCSV(WebSite ws)
	{
		website = ws;
		users = ws.users;
		products = ws.products;
	}
	
	NumberFormat nf;
	
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
		
		printHeader();

		Enumeration enum2 = orders.elements();
		int i=0;
		while(enum2.hasMoreElements())
		{
			Order order = (Order)enum2.nextElement();
			Enumeration e2 = order.orderedItems.elements();
			if(e2.hasMoreElements())
			{
				printOrder(++i,order,(OrderedItem)e2.nextElement());
			}
			while(e2.hasMoreElements())
			{
				this.printOrderItem(++i,(OrderedItem)e2.nextElement());
			}
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
	void printHeader()
	{
		print("Sr.No.");
		
		print("Order ID");		// OrderID
		print("Date");		// Date of Order
		print("Time");		// Time of Order
		
		print("Email");	// Email Address
		print("Firstname");	// Firstname
		print("Lastname");		// Lastname
		
		print("Street Address");	// Street Address
		print("City");			// City
		print("State");			// State
		print("Zip");		// Zip
		print("Country");		// Country
		print("Phone");			// Telephone Number
		
		print("Value Name");							// Drug Name
		print("Value Weight");						// Weight
		print("Items/Pack");						// Pills
		print("Quantity");							// Quantity
		print("Cost");								// Cost Per Drug !!!

		printLast("Shipping Method"); // Shipping Method
		
	}
	
	
	void printOrder(int srNo,Order order,OrderedItem oi)
	{
		org.nspeech.util.Date date = new org.nspeech.util.Date(order.tsDate);
		String orderDate = String.valueOf(date.getDay()) + "/" + String.valueOf(date.getMonth()) + "/" + String.valueOf(date.getYear());
		String orderTime = String.valueOf(date.getHours()) + ":" + String.valueOf(date.getMinutes());
		
		UserProfile profile = users.getProfile(order.txtEmailAddress);
		
		Product product = products.get(oi.nProductID);
		
		print(srNo);
		
		print(order.nID);		// OrderID
		print(orderDate);		// Date of Order
		print(orderTime);		// Time of Order
		
		print(order.txtEmailAddress);	// Email Address
		print(profile.txtFirstname);	// Firstname
		print(profile.txtLastname);		// Lastname
		
		if(order.shippingAddress!=null)
		{
			print(order.shippingAddress.txtStreetAddress);	// Street Address
			print(order.shippingAddress.txtCity);			// City
			print(order.shippingAddress.txtState);			// State
			print(order.shippingAddress.txtZipCode);		// Zip
			print(order.shippingAddress.txtCountry);		// Country
			print("#"+order.shippingAddress.txtPhone);			// Telephone Number
		}
		else
		{
			print();	// Street Address
			print();			// City
			print();			// State
			print();		// Zip
			print();		// Country
			print();			// Telephone Number
		}
		
		print(product.txtName);							// Drug Name
		print(product.txtWeight);						// Weight
		print(product.dblQtyPerPack);						// Pills
		print(oi.nQuantity);							// Quantity
		print(oi.dblPrice);								// Cost Per Drug !!!

		if(order.shippingAddress!=null)
		{
			printLast(order.shippingAddress.txtShippingMethod); // Shipping Method
		}
		else
		{
			printLast();
		}
		
	}
	
	void printOrderItem(int srNo,OrderedItem oi)
	{
		Product product = products.get(oi.nProductID);
		print(srNo);
		
		print();		// OrderID
		print();		// Date of Order
		print();		// Time of Order
		
		print();	// Email Address
		print();	// Firstname
		print();		// Lastname
		
		print();	// Street Address
		print();			// City
		print();			// State
		print();		// Zip
		print();		// Country
		print();			// Telephone Number
		
		print(product.txtName);							// Drug Name
		print(product.txtWeight);						// Weight
		print(product.dblQtyPerPack);						// Pills
		print(oi.nQuantity);							// Quantity
		print(oi.dblPrice);								// Cost Per Drug !!!

		printLast(); // Shipping Method
		
	}
	
	void print()
	{
		writer.print(",");
	}
	
	void printLast()
	{
		writer.println();
	}
	
	void printLast(double dbl)
	{
		writer.println(nf.format(dbl));
	}
	
	void printLast(int n)
	{
		writer.println(n);
	}
	
	void printLast(String str)
	{
		writer.println( "\"" + org.nspeech.util.StringEx.replace(str,'"',"\"\"") + "\"" );
	}
	
	void print(double dbl)
	{
		writer.print(nf.format(dbl));
		writer.print(',');
	}
	
	void print(int n)
	{
		writer.print(n);
		writer.print(',');
	}
	
	void print(String str)
	{
		writer.print( "\"" + org.nspeech.util.StringEx.replace(str,'"',"\"\"") + "\"" );
		writer.print(',');
	}
	
}