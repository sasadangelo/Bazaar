/*
 * ====================================================================
 *
 * The AcquisNet Software License, Version 1.0
 *
 * Copyright (c) 2002 The AcquisNet Software.  All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are not permitted. * 
 *
 * 1. This software belongs to AcquisNet Software with complete rights, *    to sale,promote and distribute to any other licensed AcquisNet  *    users.
 * * 2. AcquisNet and its people has all rights to modify,deploy,debug *    the software in any client's site. * * 3. AcquisNet has complete rights to change its policy and terms any *    time in the future without any notices.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE ACQUISNET SOFTWARE OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of contribution of Akash Kava and Ashok Kava's * efforts for writing the package. Akash Kava and Ashok Kava has complete * rights to distribute, sale, promote this software by means of any name * and any conditions.
 *  * http://www.acquisi.com
 * * Akash Kava [ackava@acquisi.com] * [Additional notices, if required by prior licensing conditions]
 *
 */ 


package org.nspeech.util;

import java.util.*;
import java.sql.*;

//import com.acquisi.system.*;

/**
 * Akash Kava: God knows what made java.sql.Date and java.sql.Time give only values of<br>
 * date and time and not both at time, also not supporting queries like getDay, etc.<br>
 * <br>
 * After few version of java, people will start getting bugged of DEPRICATED APIs, <br>
 * Deprication means lack of design skills.<br>
 * <br>
 * In that way, we would like to develop our own library (puting load on system anyway)<br>
 * an have freedom of code maintanenece.<br>
 * <br>
 * Date encapsulates GregorianCalendar and its api and gives very straight forward access<br>
 * to universal entity the Date. <br>
 * */
public class Date
{
	/**
	 * protected calendar member.
	 * */
	GregorianCalendar calendar = new GregorianCalendar();
	
	/**
	 * Default constructor:<br>
	 * Initializes Date to current time.<br>
	*/
	public Date()
	{
		this(System.currentTimeMillis());
	}
	
	public Date(java.util.Calendar c)
	{
		calendar = (GregorianCalendar)c;
	}
	
	/**
	 * Initializes Date to specified time in milliseconds, it should be long.
	 * */
	public Date(long nMilliSeconds)
	{
		calendar.setTime(new java.util.Date(nMilliSeconds));
	}
	
	/**
	 * Initialize directly from java.util.Date, no worries just type in and you get it.
	 * */
	public Date(java.util.Date date)
	{
		this(date.getTime());
	}
	
	
	/**
	 * Initialize directly from java.sql.Date, no worries just type in and you get it.
	 * */
	public Date(java.sql.Date date)
	{
		this(date.getTime());
	}
	
	/**
	 * Do i have to explain what this returns?
	*/
	public int getDay()
	{
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * Do i have to explain what this returns?
	*/
	public int getMonth()
	{
		return calendar.get(Calendar.MONTH)+1;
	}
	
	/**
	 * Do i have to explain what this returns?
	*/
	public int getYear()
	{
		return calendar.get(Calendar.YEAR);
	}
	
	/**
	 * Do i have to explain what this returns?
	*/
	public int getHours()
	{
		return calendar.get(Calendar.HOUR_OF_DAY);
	}
	
	/**
	 * Do i have to explain what this returns?
	*/
	public int getMinutes()
	{
		return calendar.get(Calendar.MINUTE);
	}
	
	/**
	 * Do i have to explain what this returns?
	*/
	public int getSeconds()
	{
		return calendar.get(Calendar.SECOND);
	}
	
	/**
	 * Do i have to explain what this returns? I think yes, it returns week day for example<br>
	 * if today is sunday it will return 1, if today is monday it will return 2 and so on...<br>
	*/
	public int getWeekDay()
	{
		return calendar.get(Calendar.DAY_OF_WEEK);
	}
	
	public static String days[] = {"Domenica","Lunedi","Martedi","Mercoledi","Giovedi","Venerdi","Sabato"};
	public static String months[] = {"Gennaio","Febbraio","Marzo","Aprile","Maggio","Giugno","Luglio","Agosto","Settembre","Ottobre","Novembre","Dicembre"};
	
	/**
	 * Do i have to explain what this returns? umm.. ok nothing but just returns days in string format
	*/
	public static String getWeekDay(int nDay)
	{
		return days[nDay-1];
	}
	
	public static String getMonthName(int nMonth)
	{
		return months[nMonth-1];
	}
	
	/**
	 * Returns string representing date in following format<br>
	 * DAY_NAME   MM/DD/YYYY HR:MIN<br>
	 * <br>
	 * example<br>
	 * Saturday   7/5/2002 1:49<br>
	 * */
	public String getLongDate()
	{
		String date;
		date = getWeekDay(getWeekDay());
		date += "    " + String.valueOf(getDay());
		date += "/";
		date += String.valueOf(getMonth());
		date += "/";
		date += String.valueOf(getYear());
		date += " ";
		date += String.valueOf(getHours());
		date += ":";
		date += String.valueOf(getMinutes());
		return date;
	}
	
	public String getCurrentLongDate()
	{
		String date;
		
		calendar.setTime(new java.util.Date(System.currentTimeMillis()));
		
		date = String.valueOf(getMonth());
		date += "/";
		date += String.valueOf(getDay());
		date += "/";
		date += String.valueOf(getYear());
		date += " ";
		date += String.valueOf(getHours());
		date += ":";
		date += String.valueOf(getMinutes());
		date += ":";
		date += String.valueOf(getSeconds());
		date += "  ";
		return date;
	}
	
	
	public void set(int nYear,int nMonth,int nDay,int nHour,int nMin,int nSec)
	{
		calendar.clear();
		calendar.set(nYear,nMonth,nDay,nHour,nMin,nSec);
	}
	
	public String getShortDate()
	{
		String date;
		date = String.valueOf(getMonth()) + "/";
		date += String.valueOf(getDay()) + "/";
		date += String.valueOf(getYear());
		return date;
	}
	
	public String getWeekDayString()
	{
		return getWeekDay(getWeekDay());
	}
	
	public String getShortWeekDay()
	{
		return getWeekDayString().substring(0,3);
	}
		
	public long getMilliSeconds()
	{
		return calendar.getTime().getTime();
	}
	
	public static String getSQLDate(Timestamp ts)
	{
		Date d = new Date(ts.getTime());
		String text;
		text = String.valueOf(d.getMonth());
		text += "/" + String.valueOf(d.getDay());
		text += "/" + String.valueOf(d.getYear());
		text += " " + String.valueOf(d.getHours());
		text += ":" + String.valueOf(d.getMinutes());
		text += ":" + String.valueOf(d.getSeconds());
		return text;
	}
	
	public static Timestamp getDate(javax.servlet.http.HttpServletRequest request,String name)
	{
		String text,param = request.getParameter(name+"_Year");
		if(param==null)
			throw new NullPointerException(name+"_Year parameter not in request");
		text = param;
		param = request.getParameter(name+"_Month");
		if(param==null)
			throw new NullPointerException(name+"_Month parameter not in request");
		text +="-" +  param;
		param = request.getParameter(name+"_Day");
		if(param==null)
			throw new NullPointerException(name+"_Day parameter not in request");
		text +="-" +  param;
		text += " 00:00:00.00000000";
		return Timestamp.valueOf(text);
	}

	public static Timestamp getTimestamp(javax.servlet.http.HttpServletRequest request,String name)
	{
		String text,param = request.getParameter(name+"_Year");
		if(param==null)
			throw new NullPointerException(name+"_Year parameter not in request");
		text = param;
		param = request.getParameter(name+"_Month");
		if(param==null)
			throw new NullPointerException(name+"_Month parameter not in request");
		text +="-" +  param;
		param = request.getParameter(name+"_Day");
		if(param==null)
			throw new NullPointerException(name+"_Day parameter not in request");
		text +="-" +  param;
		param = request.getParameter(name+"_Hour");
		if(param==null)
			throw new NullPointerException(name+"_Hour parameter not in request");
		text +=" " +  param;
		param = request.getParameter(name+"_Min");
		if(param==null)
			throw new NullPointerException(name+"_Min parameter not in request");
		text +=":" +  param;
		text += ":00.00000000";
		return Timestamp.valueOf(text);
	}
}
