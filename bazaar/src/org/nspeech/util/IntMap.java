package org.nspeech.util;

import java.util.*;

class Item{
	int nKey;
	Object value;
}

public class IntMap
{
	Item[] items = new Item[5];
	int cursize = 0;
	
	Vector values = new Vector();
	
	void alloc(int size)
	{
		Item[] newitems = new Item[size];
		if(newitems.length<items.length)
		{
			System.arraycopy(items,0,newitems,0,newitems.length);
			if(cursize<newitems.length)
				cursize = newitems.length;
		}
		else
		{
			System.arraycopy(items,0,newitems,0,items.length);
		}
		items = newitems;
	}
	
	Item newItem(int key)
	{
		Item i = new Item();
		i.nKey = key;
		if(cursize == items.length)
		{
			alloc(items.length+5);
		}
		items[cursize++] = i;
		return i;
	}
	
	Item searchItem(int key)
	{
		for(int i=0;i<cursize;i++)
		{
			Item it = items[i];
			if(it.nKey == key)
				return it;
		}
		return newItem(key);
	}
	
	public synchronized void put(int key,Object obj)
	{
		Item item = searchItem(key);
		item.value = obj;
		makeVector();
	}
	
	public synchronized Object get(int key)
	{
		return searchItem(key).value;
	}
	
	public synchronized Object remove(int key)
	{
		int i;
		Object obj = null;
		for(i=0;i<cursize;i++)
		{
			Item it = items[i];
			if(it.nKey == key)
			{
				obj = it;
				break;
			}
		}
		for(;i<cursize-1;i++)
		{
			items[i] = items[i+1];
		}
		makeVector();
		return obj;
	}
	
	void makeVector()
	{
		values.removeAllElements();
		for(int i=0;i<cursize;i++)
		{
			values.addElement( items[i].value );
		}
	}
	
	public Vector getValues()
	{
		return values;
	}
	
	public synchronized void removeAll()
	{
		items = new Item[5];
		cursize = 0;
		values.removeAllElements();
	}
}
