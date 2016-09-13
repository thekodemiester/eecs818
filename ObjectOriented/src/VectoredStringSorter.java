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
