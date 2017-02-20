package org.nspeech.web;

public class UrlEncoderEx
{
	public static String encode(String url)
	{
		try{
			return java.net.URLEncoder.encode(url,"UTF-8");
		}
		catch(java.io.UnsupportedEncodingException uee)
		{
			uee.printStackTrace();
		}
		
		int i,n=url.length();
		String urlResult = "";
		for(i=0;i<n;i++)
		{
			urlResult += encode(url.charAt(i));
		}
		return urlResult;
	}
	
	public static String encode(char ch)
	{
		switch(ch)
		{
		case '%':
		case '^':
		case '&':
		case ' ':
		case '+':
		case '.':
		case '/':
		case '\\':
		case '?':
		case '*':
		case '#':
			return "%"+Integer.toHexString(ch);
		default:
			return String.valueOf(ch);
		}
	}
}
