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
 * stringCleaner.java
 ********************************************************/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;


public class stringCleaner
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
		
		String cleanedStrings = cleanStringOfTrivialWords( inputString );
		
		System.out.println( cleanedStrings );	
	}
		
	static private String cleanStringOfTrivialWords( String aList )
	{
		Vector<String> blackListedWords = new Vector<String>(1,1);
		 
		blackListedWords.add("a");
		blackListedWords.add("A");
		blackListedWords.add("An");
		blackListedWords.add("an");
		blackListedWords.add("as");
		blackListedWords.add("As");
		blackListedWords.add("and");
		blackListedWords.add("And");
		blackListedWords.add("the");
		blackListedWords.add("The");
		
		Vector<String> cleanedLines = new Vector<String>(1,1); 
		
        Vector<String> listToClean = new Vector<String>(); 
		
		while( aList != null )
		{
			int endIndex = aList.indexOf('&');
			String subString = aList.substring(0, endIndex);
			listToClean.insertElementAt( subString, listToClean.size() );
			if( endIndex > 0 && endIndex != aList.length() - 1 )
			{
				aList = aList.substring(endIndex + 1, aList.length());
			}
			else
			{
				aList = null;
			}
		}
		
		for( int line = 0; line < listToClean.size(); line++ )
		{
			String temp = null; 
			for( int word = 0; word < blackListedWords.size(); word++ )
			{
				String stringToClean = null; 
				if( temp == null)
				{
				    stringToClean = listToClean.elementAt(line);
				}
				else
				{
					stringToClean = temp;
				}
				String test = blackListedWords.elementAt(word);
				temp = stringToClean.replaceAll("(?i)\\b"+test+"\\b", "");
				temp = temp.replaceAll("  ", " ");
				temp = temp.trim(); 
			}
			cleanedLines.add(temp);
		}
	    
		String cleanedStrings = ""; 
	    for( int i = 0; i < cleanedLines.size(); i++ )
	    {
	    	cleanedStrings += cleanedLines.elementAt(i) + '&';
	    }
	    return cleanedStrings;
	}
}