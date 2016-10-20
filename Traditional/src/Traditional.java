/*********************************************************
 * EECS 818: Software Architecture
 * Professor Hossein Saiedian
 * Fall 2016
 * 
 * 
 * Cody Ronning; Brian Turrel; Michael Chatman; 
 * Apoorva Srivastava; Ben Underwood
 * 
 * Architecture Style: Traditional Call and Return
 * Traditional.java
 ********************************************************/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Vector;

class IgnoreCaseComparator implements Comparator<String> 
{
	  public int compare(String strA, String strB) 
	  {
	    return strA.compareToIgnoreCase(strB);
	  }
}

public class Traditional
{	
	public static void main(String[] s)
	{
		String fileName = "../text.txt";
		
		try 
		{
			FileReader fileReaderCounter = new FileReader( fileName );
			BufferedReader textCounter = new BufferedReader( fileReaderCounter );
			
			int numberOfLines = 0; 
			while( ( textCounter.readLine() ) != null )
			{
				numberOfLines++; 
			}
			textCounter.close();
			
			FileReader fileReader = new FileReader( fileName );
			BufferedReader textReader = new BufferedReader( fileReader );
			String[] textData = new String[numberOfLines];
			for( int i = 0; i < numberOfLines; i++ )
			{
				textData[i] = textReader.readLine();
			}
			
			textReader.close();
			
			Vector<String> shiftedLines = new Vector<String>(1,1); 
			for( int i = 0; i < textData.length; i++ )
			{
				String[] shifted = doShift( textData[i] );
				for( int j = 0; j < shifted.length - 1; j++ )
				{
					shiftedLines.add( shifted[j] );
				}
			}
			
			IgnoreCaseComparator comparator = new IgnoreCaseComparator();

		    shiftedLines.sort(comparator);
			
			
			for( int i = 0; i < shiftedLines.size(); i++) 
			{
				System.out.println( shiftedLines.get(i));
			}
		}
		catch (IOException e)
		{
			System.out.println( e.getMessage() );
		}
	}
	
	static private String[] doShift( String aString )
	{	
		int numberOfWords = countWords( aString );
		String[] allPermuntations = new String[numberOfWords + 1];
		
		for( int i = 0; i < numberOfWords; i++ )
		{
			int index = aString.indexOf(' ');
			String subStringFirst = aString.substring( 0 , index);
			String subStringSecond = aString.substring( index + 1 );
			aString = subStringSecond + " " + subStringFirst; 
			allPermuntations[i] = aString; 
		}
		return allPermuntations; 
	}
	
	static private int countWords(String aString)
	{
	    int wordCount = 0;

	    boolean word = false;
	    int stringLength = aString.length() - 1;

	    for (int i = 0; i < aString.length(); i++) 
	    {
	        if ( Character.isLetter(aString.charAt(i)) && i != stringLength ) 
	        {
	            word = true;
	        } 
	        else if ( !Character.isLetter(aString.charAt(i)) && word ) 
	        {
	            wordCount++;
	            word = false;
	        }
	        else if ( Character.isLetter(aString.charAt(i)) && i == stringLength )
	        {
	            wordCount++;
	        }
	    }
	    return wordCount;
	}
}