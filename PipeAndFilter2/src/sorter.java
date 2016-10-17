import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Vector;


class IgnoreCaseComparator implements Comparator<String> 
{
	  public int compare(String strA, String strB) 
	  {
	    return strA.compareToIgnoreCase(strB);
	  }
}

public class sorter
{	
	public static void main(String[] s)
	{
		BufferedReader inputReader = new BufferedReader( new InputStreamReader(System.in));
		String inputString = null;
		
		try 
		{
			inputString = inputReader.readLine();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		String sortedStrings = sortListInAlphabeticalOrder( inputString );
		
		System.out.println( sortedStrings );	
	}
		
	static private String sortListInAlphabeticalOrder( String aList )
	{
		Vector<String> listToSort = new Vector<String>(); 
		
		while( aList != null )
		{
			int endIndex = aList.indexOf('&');
			String subString = aList.substring(0, endIndex);
			listToSort.insertElementAt( subString, listToSort.size() );
			if( endIndex > 0 && endIndex != aList.length() - 1 )
			{
				aList = aList.substring(endIndex + 1, aList.length());
			}
			else
			{
				aList = null;
			}
		}
		
		IgnoreCaseComparator comparator = new IgnoreCaseComparator();

	    listToSort.sort(comparator);
	    String sortedStrings = ""; 
	    
	    for( int i = 0; i < listToSort.size(); i++ )
	    {
	    	sortedStrings += listToSort.elementAt(i) + '&';
	    }
	    return sortedStrings;
	}
}