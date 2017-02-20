/*
 * ====================================================================
 *
 * The NeuroSpeech License, Version 1.0
 *
 * Copyright (c) 2002 The NeuroSpeech.  All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are not permitted. * 
 *
 * 1. This software belongs to NeuroSpeech Technologies with complete rights, *    to sale,promote and distribute to any other licensed NeuroSpeech  *    users.
 * * 2. NeuroSpeech and its people has all rights to modify,deploy,debug *    the software in any client's site. * * 3. NeuroSpeech has complete rights to change its policy and terms any *    time in the future without any notices.
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
 * DISCLAIMED.  IN NO EVENT SHALL THE NEUROSPEECH TECHNOLOGIES OR
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
 *  * http://www.neurospeech.com
 * * Akash Kava [ackava@neurospeech.com] * [Additional notices, if required by prior licensing conditions]
 *
 */ 


package org.nspeech.util;

import java.util.*;

/**
 * The StringEx class provides useful methods for replacing sub strings in String classes<br>
 * This supports method provided in CString::Replace defined in MSDN<br>
 * @author Akash Kava
 * */
public class StringEx
{
	
	/**
	 * This function replaces each occurance of specified character with specified String to replace<br>
	 * remember String.replace of java.lang gives facility to replace only one character with matched character<br>
	 * where as this function gives full freedom of replacing it by string.<br>
	 * <br>
	 * For Example,<br>
	 * if you want to replace each occurance of ' '(whitespace) with "&nbsp;" then you can use<br>
	 *		ReplacedString = ac.util.StringEx.replace(OldString,' ',"&nbsp;");<br>
	 * <br>
	 * This may be time and resource consuming for longer strings.<br>
	 * <br>
	 * IMP : Comparison is Case Sensitive<br>
	 * @return String
	 * */
	public static String replace(String strText,char chFind,String strReplace)
	{
		String result = "";
		int i,n = strText.length();
		for(i=0;i<n;i++)
		{
			char ch = strText.charAt(i);
			if(ch!=chFind)
				result += ch;
			else
				result += strReplace;
		}
		return result;
	}

	/**
	 * This function replaces each occurance of specified string with specified String to replace<br>
	 * remember String.replace of java.lang gives facility to replace only one character with matched character<br>
	 * where as this function gives full freedom of replacing it by string.<br>
	 * <br>
	 * For Example,<br>
	 * if you want to replace each occurance of "\r\n" with "&ltg;BR&gt;" then you can use<br>
	 *		ReplacedString = ac.util.StringEx.replace(OldString,"\r\n","&ltg;BR&gt;");<br>
	 * <br>
	 * This may be time and resource consuming for longer strings.<br>
	 * <br>
	 * IMP : Comparison is Case Sensitive<br>
	 * @return String
	 * */
	public static String replace(String strText,String strFind,String strReplace)
	{
		String result = "";
		String text = new String(strText);
		while(true)
		{
			int i = text.indexOf(strFind);
			if(i==-1)
				break;
			result += text.substring(0,i);
			result += strReplace;
			text = text.substring(i+strFind.length());
		}
		result += text;
		return result;
	}

	/**
	 * This function splits string text into tokens by given delimters<br>
	 * <br>
	 * IMP : Comparison is Case Sensitive<br>
	 * @return String []
	 * */
	public static String [] split(String text, String delims)
	{
		StringTokenizer st = new StringTokenizer(text,delims);
		int len = st.countTokens();
		
		String [] tokens = new String [len];
		
		for(int i=0;i<len;i++)
		{
			tokens[i] = st.nextToken();
		}
		return tokens;
	}
}
