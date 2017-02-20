package org.nspeech.web.tags.controls;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.jsp.PageContext;

public class DateInput extends javax.servlet.jsp.tagext.TagSupport
{
	//static String monthOptions = "<option value='1'>January</option>\r\n<option value='2'>February</option>\r\n<option value='3'>March</option>\r\n<option value='4'>April</option>\r\n<option value='5'>May</option>\r\n<option value='6'>June</option>\r\n<option value='7'>July</option>\r\n<option value='8'>August</option>\r\n<option value='9'>September</option>\r\n<option value='10'>October</option>\r\n<option value='11'>November</option>\r\n<option value='12'>December</option>\r\n";
	
	public String	name = "";
	public int		nYearStart = 2000;
	public int		nYearEnd   = 2010;
	public int		nSelectedYear;
	public int		nSelectedMonth;
	public int		nSelectedDay;
	
	public DateInput()
	{
	}
	
	public void setName( String txt )
	{
		name = txt;
	}
	
	public void setSelectedYear(int nYear)
	{
		nSelectedYear = nYear;	
	}
	
	public void setSelectedMonth(int nMonth)
	{
		nSelectedMonth = nMonth;
	}
	
	public void setSelectedDay(int nDay)
	{
		nSelectedDay = nDay;
	}
	
	public void setYearStart(int nStart)
	{
		nYearStart = nStart;
	}
	
	public void setYearEnd(int nEnd)
	{
		nYearEnd = nEnd;
	}
	
	public void setTimestamp(java.sql.Timestamp ts)
	{
		if(ts==null)
			return;
		org.nspeech.util.Date d = new org.nspeech.util.Date(ts);
		nSelectedDay = d.getDay();
		nSelectedMonth = d.getMonth();
		nSelectedYear = d.getYear();
	}
	
	public int doEndTag() throws javax.servlet.jsp.JspException 
	{
		if(nYearEnd < nYearStart)
			throw new javax.servlet.jsp.JspException("Last Year can not be less then Start Year");
		try
		{
			writeDays();
			write(" &nbsp; ");
			writeMonths();
			write(" &nbsp; ");
			writeYears();
		}
		catch(IOException e)
		{
		}
		return EVAL_PAGE;
	}
	
	public void write(String txt) throws IOException 
	{
		pageContext.getOut().print(txt);
	}
	
	public void writeYears() throws IOException 
	{
		String txt = "<select name='"+name+"_Year'>\r\n";
		int i;
		for(i=nYearStart;i<=nYearEnd;i++)
		{
			String year = String.valueOf(i);
			txt += (i==nSelectedYear) ?  "\t<option selected value='" + year + "'>" + year + "</option>\r\n"  : "\t<option value='" + year + "'>"+year+"</option>\r\n" ;
		}
		txt += "</select>\r\n";
		pageContext.getOut().println(txt);
	}
	
	public void writeMonths() throws IOException 
	{
		String txt = "<select name='"+name+"_Month'>\r\n";
		int i;
		for(i=1;i<=12;i++)
		{
			txt += (i==nSelectedMonth) ?  "\t<option selected value='" + String.valueOf(i) + "'>"+org.nspeech.util.Date.getMonthName(i)+"</option>\r\n"  : "\t<option value='" + String.valueOf(i) + "'>"+org.nspeech.util.Date.getMonthName(i)+"</option>\r\n" ;
		}
		txt += "</select>\r\n";
		pageContext.getOut().println(txt);
	}
	
	public void writeDays() throws IOException 
	{
		String txt = "<select name='"+name+"_Day'>\r\n";
		int i;
		for(i=1;i<=31;i++)
		{
			String day = String.valueOf(i);
			txt += (i==nSelectedDay) ?  "\t<option selected value='" + day + "'>" + day + "</option>\r\n"  : "\t<option value='" + day + "'>"+day+"</option>\r\n" ;
		}
		txt += "</select>\r\n";
		pageContext.getOut().println(txt);
	}

	public void setPageContext(PageContext p1)
	{
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis());
		initDefaultValues(c);		
		super.setPageContext(p1);
	}
	
	public void initDefaultValues(Calendar c)
	{
		nSelectedYear	= c.get(Calendar.YEAR);
		nSelectedMonth	= c.get(Calendar.MONTH)+1;
		nSelectedDay	= c.get(Calendar.DAY_OF_MONTH);
	}
}
