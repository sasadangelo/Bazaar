package org.nspeech.util;

/*
 * ====================================================================
 *
 * The NeuroSpeech Technologies License, Version 1.0
 *
 * Copyright (c) 2002 The NeuroSpeech Technologies.  All rights reserved.
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
 * DISCLAIMED.  IN NO EVENT SHALL THE NeuroSpeech Technologies OR
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
 * * Akash Kava [ackava@neurospeech.com] * Ashok Kava [ashokkawa@hotmail.com]
 * [Additional notices, if required by prior licensing conditions]
 *
 */ 


import java.util.Vector;

/**
 * StringTokenizer Function of java.util package doesnt provide many tokenizing formates directly to parse data<br>
 * however there exits other tokenizers but quite complex to understand and implement<br>
 * <br>
 * This class does same as what StringTokenizer Does but interface is a bit differetn.<br>
 * */
public class StringTokenizerEx
{
	/**
	 * Private variable so you dont need to know for what it is.
	 * */
	Vector tokens = new Vector();
	
	/**
	 * @return int size of tokens
	 * */
	public int getSize()
	{
		return tokens.size();
	}
	
	/**
	 * Parses string, see tokenize(String,String,String) for details<br>
	 * this function is equivalent to tokenize(String,""," \r\n\t")<br>
	 * @see ac.util.StringTokenizertokenize#3
	 * */
	public void tokenize(String text)
	{
		tokenize(text," \r\n\t");
	}
	
	/**
	 * Parses string, see tokenize(String,String,String) for details.<br>
	 * this function is equivalent to tokenize(String,"",String)<br>
	 * */
	public void tokenize(String text,String delim)
	{
		tokenize(text,"",delim);
	}
	
	/**
	 * Well take a look at following data<br>
	 * User ackava "Akash Kava" <br>
	 * The above data has 3 tokens [User] [ackava] [Akash Kava], we often use " for packing data with white spaces.<br>
	 * StringTokenizer does not give this functionality<br>
	 * <br>
	 * But StringTokenizerEx does give. you can use StringTokenizerEx.tokenize(data,""," \r\t\n")<br>
	 * <br>
	 * Take a look at another data<br>
	 * User.ackava name."Akash Kava" NothingAttachedToMe "Nothing With me as well"<br>
	 * here I want tokens to be [User] [.] [ackava] [name] [.] [Akash Kava] [NothingAttachedToMe] [Nothing With me as well]<br>
	 * here I want [.] as delimitor and as well as return for token.<br>
	 * <br>
	 * you can use StringTokenizerEx.tokenize(data,"."," \r\t\n")<br>
	 * */
	public void tokenize(String text,String retdelim,String delim)
	{
		try
		{
			tokens.removeAllElements();
		}catch(Exception e){}
		int nLength = text.length();
		int i = 0;
		char ch;
		boolean bOn = false;
		
		String token = "";
		
		for(i=0;i<nLength;i++)
		{
			ch = text.charAt(i);
			switch(ch)
			{
			case '"':
				if(bOn)
				{
					if(i<nLength-1)
					{
						if(text.charAt(i+1)=='"')
						{
							i++;
							token += '"';
							continue;
						}
					}
					bOn = false;
					if(token.length()>0)
					{
						tokens.addElement(token);
						token = "";
					}
				}
				else
				{
					bOn = true;
				}
				break;
			default:
				if(bOn)
				{
					token += ch;
					continue;
				}
				if(retdelim.indexOf(ch)!=-1)
				{
					if(token.length()>0)
					{
						tokens.addElement(token);
						token = "";
					}
					tokens.addElement(String.valueOf(ch));
					continue;
				}
				if(delim.indexOf(ch) == -1)
				{
					token += ch;
				}
				else
				{
					if(token.length()>0)
					{
						tokens.addElement(token);
						token = "";
					}
				}
			}
		}
		if(token.length()>0)
			tokens.addElement(token);
	}
	
	/**
	 * Returns token at this index, call getSize() to number of tokens found.<br>
	 * */
	public String getAt(int nIndex)
	{
		if(nIndex >= tokens.size())
			return null;
		return (String)tokens.elementAt(nIndex);
	}
}
