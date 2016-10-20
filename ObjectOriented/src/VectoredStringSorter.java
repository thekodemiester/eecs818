/*********************************************************
 * EECS 818: Software Architecture
 * Professor Hossein Saiedian
 * Fall 2016
 * 
 * 
 * Cody Ronning; Brian Turrel; Michael Chatman; 
 * Apoorva Srivastava; Ben Underwood
 * 
 * Architecture Style: Object-Oriented Call-and-Return
 * VectroedStringSorter.java
 ********************************************************/

import java.util.Comparator;
import java.util.Vector;

class IgnoreCaseComparator implements Comparator<String> 
{
	  public int compare(String strA, String strB) 
	  {
	    return strA.compareToIgnoreCase(strB);
	  }
}

public class VectoredStringSorter 
{
	Vector<String> shiftedLines = new Vector<String>(1,1); 
	
	public VectoredStringSorter( Vector<String> aStringArray )
	{
		shiftedLines = aStringArray; 
	}
	
	public Vector<String> doSort()
	{
		IgnoreCaseComparator comparator = new IgnoreCaseComparator();
		shiftedLines.sort(comparator);
		return shiftedLines; 
	}
    
}
