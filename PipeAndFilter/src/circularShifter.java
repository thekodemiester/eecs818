/*********************************************************
 * EECS 818: Software Architecture
 * Professor Hossein Saiedian
 * Fall 2016
 * 
 * 
 * Cody Ronning; Brian Turrel; Michael Chatman; 
 * Apoorva Srivastava; Ben Underwood
 * 
 * Architecture Style: Pipe and Filter
 * circularShifter.java
 ********************************************************/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class circularShifter
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
		
		String allLinePermuntations = findAllLinePermuntations( inputString );
		
		System.out.println( allLinePermuntations );	
	}
	
	static private String findAllLinePermuntations( String linesOfText )
	{
		String linePermuntations = "";
		while( linesOfText != null )
		{
			int endIndex = linesOfText.indexOf('&');
			String subString = linesOfText.substring(0, endIndex);
			String shifted = doShift( subString );
			linePermuntations += ( shifted );
			if( endIndex > 0 && endIndex != linesOfText.length() - 1 )
			{
				linesOfText = linesOfText.substring(endIndex + 1, linesOfText.length());
			}
			else
			{
				linesOfText = null;
			}
		}
				
		return linePermuntations; 
	}
	
	static private String doShift( String aString )
	{	
		int numberOfWords = countWords( aString );
		String allPermuntations = "";
		
		for( int i = 0; i < numberOfWords; i++ )
		{
			int index = aString.indexOf(' ');
			String subStringFirst = aString.substring( 0 , index);
			String subStringSecond = aString.substring( index + 1 );
			aString = subStringSecond + " " + subStringFirst; 
			allPermuntations += aString + "&"; 
		}
		return allPermuntations; 
	}
	
	static private int countWords(String aString)
	{
	    int wordCount = 0;

	    boolean isWord = false;
	    int stringLength = aString.length() - 1;

	    for (int i = 0; i < aString.length(); i++) 
	    {
	    	if ( !aString.substring(i, i+1).equals(" ") && i != stringLength ) 
	        {
	        	isWord = true;
	        } 
	        else if ( aString.substring(i, i+1).equals(" ") && isWord ) 
	        {
	            wordCount++;
	            isWord = false;
	        }
	        else if ( !aString.substring(i, i+1).equals(" ") && i == stringLength )
	        {
	            wordCount++;
	        }
	    }
	    return wordCount;
	}
}