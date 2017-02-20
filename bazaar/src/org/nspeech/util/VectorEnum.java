package org.nspeech.util;

public class VectorEnum extends java.util.Vector 
{
	int nCurrentElement = 0;
	
	// Reset Enumerator
	public synchronized void reset()
	{
		nCurrentElement = 0;
	}
	
	public Object next()
	{
		if(nCurrentElement >= elementCount)
			return null;
		return elementData[nCurrentElement++];
	}
	
	public Object nextReset()
	{
		if(nCurrentElement >= elementCount)
		{
			if(elementCount==0)
				return null;
			nCurrentElement = 0;
		}
		return elementData[nCurrentElement++];
	}
}
