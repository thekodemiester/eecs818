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
 * StringCleaner.java
 ********************************************************/

import java.util.Vector;

public class StringCleaner 
{
	private Vector<String> mInput;
	
	public StringCleaner( Vector<String> aInput )
	{
		mInput = aInput; 
	}
	
	public Vector<String> doClean()
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
		
		for( int line = 0; line < mInput.size(); line++ )
		{
			String temp = null; 
			for( int word = 0; word < blackListedWords.size(); word++ )
			{
				String stringToClean = null; 
				if( temp == null)
				{
				    stringToClean = mInput.elementAt(line);
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
		
		return cleanedLines; 
	}
}