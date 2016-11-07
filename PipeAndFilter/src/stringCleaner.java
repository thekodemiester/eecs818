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

import java.util.Vector;

public class stringCleaner
{	
	public String run(String input)
	{		
		String cleanedStrings = cleanStringOfTrivialWords( input );
		
		return cleanedStrings;	
	}
		
	static private Vector<String> defineTrivialWords()
	{
		Vector<String> trivialWords = new Vector<String>(1,1);
		trivialWords.add("a");
		trivialWords.add("an");
		trivialWords.add("as");
		trivialWords.add("and");
		trivialWords.add("the");
		return trivialWords;
	}
	
	static private String cleanStringOfTrivialWords( String aList )
	{
		Vector<String> trivialWords = defineTrivialWords();
		
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
			for( int word = 0; word < trivialWords.size(); word++ )
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
				String test = trivialWords.elementAt(word);
				temp = stringToClean.replaceAll("(?i)\\b"+test+"\\b", "");
				temp = temp.replaceAll("  ", " ");
				temp = temp.trim(); 
			}
			if (temp != "")
			{
				cleanedLines.add(temp);
			}
		}
	    
		String cleanedStrings = ""; 
	    for( int i = 0; i < cleanedLines.size(); i++ )
	    {
	    	cleanedStrings += cleanedLines.elementAt(i) + '&';
	    }
	    return cleanedStrings;
	}
}