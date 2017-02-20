package nbp.jspcart;

import java.io.*;


/**This is default logger used by the website to log certain events. It can also log the exceptions.*/
public class Logger
{
	/**
	 * Static member out, as PrintStream, you can call Logger.out.println("") etc etc.
	 * By default it is set as System.out
	 * */
	java.io.PrintStream out = System.out, pout;
	
	/**
	 * Date object used for logging time and date
	 * */
	org.nspeech.util.Date date = new org.nspeech.util.Date();
	
	/**
	 * You can toggle the logging option.
	 * */
	public void setLogging(boolean bOn)
	{
		if(bOn)
		{
			out = pout;
			writeLine("Logging started");
		}
		else
		{
			out = null;
			writeLine("Logging stopped");
		}
	}
	
	public Logger(WebSite ws)
	{
		try
		{
			// Logging into the file
			pout = new PrintStream( new FileOutputStream(ws.getLogPath()+  java.io.File.separator + "log.txt"));
			out = pout;
			writeLine(" Logging Started");
			
		}
		catch(Exception e)
		{
		}
	}
	
	
	/**
	 * Log the Line
	 * */
	public void writeLine( String line )
	{
		try
		{
			out.println(date.getCurrentLongDate() + line);
		}
		catch(Exception e)
		{
			// incase logging is turned off, out is null so exception will be thrown here
			// just ignore it
		}
	}
	
	/**
	 * Log the exception
	 * */
	public void write(Exception e)
	{
		try
		{
			out.println(date.getCurrentLongDate() + e.getMessage());
			e.printStackTrace(out);
		}
		catch(Exception ex)
		{
			// incase logging is turned off, out is null so exception will be thrown here
			// just ignore it
		}
	}
	
	/**
	 * Log the object's class name and exception
	 * */
	public void write(Object obj, Exception e)
	{
		try
		{
			out.println(date.getCurrentLongDate() + " CLASS : " + obj.getClass().getName());
			out.println(date.getCurrentLongDate() + e.getMessage());
			e.printStackTrace(out);
		}
		catch(Exception ex)
		{
			// incase logging is turned off, out is null so exception will be thrown here
			// just ignore it
		}
	}
}
