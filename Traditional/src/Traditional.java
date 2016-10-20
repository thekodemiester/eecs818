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


public class Traditional
{	
	public static void main(String[] s)
	{
		try 
		{	
			String fileName = "../text.txt";
			String[] textData = readFile(fileName);
			Vector<String> shiftedLines = shiftLines(textData);
			Vector<String> sortedLines = sortLines(shiftedLines);
			writeOutput(sortedLines);
		}
		catch (Exception e)
		{
			System.out.println( e.getMessage() );
		}
	}
	
	static private String[] readFile( String fileName )
	{
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
			return textData;
		}
		catch (IOException e)
		{
			System.out.println( e.getMessage() );
			return new String[0];
		}
	}
	
	static private Vector<String> shiftLines( String[] inStringArray )
	{
		Vector<String> shiftedLines = new Vector<String>(1,1); 
		for( int line = 0; line < inStringArray.length; line++ )
		{
			String[] shifted = doShift( inStringArray[line] );
			for( int word = 0; word < shifted.length - 1; word++ )
			{
				shiftedLines.add( shifted[word] );
			}
		}
		return shiftedLines;
	}
	
	static private String[] doShift( String aString )
	{	
		int numberOfWords = countWords( aString );
		String[] allPermutations = new String[numberOfWords + 1];
		
		for( int word = 0; word < numberOfWords; word++ )
		{
			int index = aString.indexOf(' ');
			String subStringFirst = aString.substring( 0 , index);
			String subStringSecond = aString.substring( index + 1 );
			aString = subStringSecond + " " + subStringFirst; 
			allPermutations[word] = aString; 
		}
		return allPermutations; 
	}
	
	static private int countWords(String aString)
	{
	    int wordCount = 0;

	    boolean isWord = false;
	    int stringLength = aString.length() - 1;

	    for (int i = 0; i < aString.length(); i++) 
	    {
	        if ( Character.isLetter(aString.charAt(i)) && i != stringLength ) 
	        {
	        	isWord = true;
	        } 
	        else if ( !Character.isLetter(aString.charAt(i)) && isWord ) 
	        {
	            wordCount++;
	            isWord = false;
	        }
	        else if ( Character.isLetter(aString.charAt(i)) && i == stringLength )
	        {
	            wordCount++;
	        }
	    }
	    return wordCount;
	}
	
	static private Vector<String> sortLines( Vector<String> shiftedLines)
	{
		IgnoreCaseComparator comparator = new IgnoreCaseComparator();
	    shiftedLines.sort(comparator);
	    return shiftedLines;
	}
	
	static private void writeOutput( Vector<String> sortedLines)
	{
		for( int i = 0; i < sortedLines.size(); i++) 
		{
			System.out.println( sortedLines.get(i));
		}
	}
}

class IgnoreCaseComparator implements Comparator<String> 
{
	  public int compare(String strA, String strB) 
	  {
	    return strA.compareToIgnoreCase(strB);
	  }
}