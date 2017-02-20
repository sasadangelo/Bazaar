package com.transecute;

import java.util.zip.Adler32;

public class functions
{
	int answer;

	public functions()
	{
	}

	public int getanswer()
	{
		return answer;
	}

	public void setanswer(int i) 
	{
		answer = i;
	}

	public String verifychecksum(String description , String amount , String status , String checksum , String key)
	{
		String str = description + "|" + amount + "|" + status + "|" + key ;
		
		Adler32  adl = new Adler32 ();
		adl.update(str.getBytes());
		long adler = adl.getValue();

		if (("" + adler).equals(checksum))
			return "true" ;
		else
			return "false" ;
	}

	public String getchecksum(String memberid,String totype,String amount,String description , String redirecturl,String key)
	{
		String 	str = memberid + "|" + totype + "|" + amount + "|" + description + "|" + redirecturl+ "|" + key;

		Adler32  adl = new Adler32 ();
		adl.update(str.getBytes());
		return String.valueOf(adl.getValue());
	}
}
