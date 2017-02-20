package org.nspeech.xml;

import java.util.*;

/**
 * Parses XML file for only extracting tagged data and not properties
 * Does not support single tag data, data should always be in form of 
 * <tag>data</tag>
 * */
public class XMLParser
{
	Stack m_Stack = new Stack();
	XMLNode m_Node = new XMLNode();
	
	public void clear()
	{
		m_Node.childNodes.removeAllElements();
		m_Stack.removeAllElements();
	}
	
	public boolean parseFile(String filename)
	{
		String xml = "";
		try
		{
			java.io.FileReader fr = new java.io.FileReader(filename);
			java.io.BufferedReader br = new java.io.BufferedReader(fr);
		
			String line = br.readLine();
			while(line != null)
			{
				xml += line;
				line = br.readLine();
			}
			try{br.close();	fr.close();}catch(Exception e){}
		}
		catch(Exception e)
		{
			return false;
		}
		return parse(xml);
	}
	
	public boolean parse(String xml)
	{
		clear();
		XMLNode node = m_Node;
		StringTokenizer ST = new StringTokenizer(xml,"<>",true);
		
		String data,tag;
		while(ST.hasMoreTokens())
		{
			String token = ST.nextToken();
			if(token.equals("<"))
			{
				tag = ST.nextToken();
				ST.nextToken();
				
				if(tag.startsWith("/"))
				{
					tag = tag.substring(1);
					String oldTag=null,value="";
					boolean bContinue = false;
					while(!m_Stack.isEmpty())
					{
						oldTag = (String)m_Stack.pop();
						if(oldTag.equals("<"+tag+">"))
						{
							node.setValue(value);
							node = node.getParent();
							bContinue = true;
							break;
						}
						else
						{
							if(oldTag.startsWith("<"))
							{
								System.out.println("Beginng tag for "+tag+" not found");
								return false;
							}
							value += oldTag;
						}
					}
					
					if(bContinue)
						continue;
					System.out.println("Beginng tag for "+tag+" not found");
					return false;
				}
				else
				{
					// single tag element
					if(tag.endsWith("/"))
					{
						XMLNode newNode = new XMLNode();
						newNode.setName(tag);
						newNode.setParent(node);
						node.getChildNodes().addElement(newNode);
					}
					else
					{
						m_Stack.push("<"+tag+">");
						XMLNode newNode = new XMLNode();
						newNode.setName(tag);
						newNode.setParent(node);
						node.getChildNodes().addElement(newNode);
						node = newNode;
					}
				}
				
			}
			else
			{
				token = token.trim();
				if(token.length()>0)
					m_Stack.push(token);
			}
		}
		return true;
	}
	
	public String getValue(String path)
	{
		return m_Node.getValue(path);
	}
	
	public Vector search(String path)
	{
		return m_Node.search(path);
	}
	
	public void save(java.io.PrintWriter out)
	{
		m_Node.save(out);
	}
	
	public boolean save(String filename)
	{
		try
		{
			java.io.FileWriter fw = new java.io.FileWriter(filename,true);
			java.io.PrintWriter out = new java.io.PrintWriter(fw);
			m_Node.save(out);
		}
		catch(java.io.IOException e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
