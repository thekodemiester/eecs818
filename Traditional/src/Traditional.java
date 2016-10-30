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
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Vector;


public class Traditional
{	
	static Vector<String> blackListedWords = new Vector<String>(1,1);
	
	public static void main(String[] s)
	{
		try 
		{	
			BufferedReader inputReader = new BufferedReader( new InputStreamReader(System.in));
			String inputString = null;
			boolean continueRunning = true; 
			Vector<String> allLines = new Vector<String>(1,1);
			
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
			
			
			try 
			{
				System.out.println("KWIC Traditional Program" );
				System.out.println("Make selection");
				while (continueRunning)
				{
					System.out.println("A: for add line");
					System.out.println("D: for display current output");
					System.out.println("Q: Quit program");
					inputString = inputReader.readLine();
					switch (inputString)
						{
						case "a":
						case "A":
							// Add input String
							System.out.println("Input text: ");
							String inputText = inputReader.readLine(); 
							allLines.add( inputText );
							break;
						case "d":
						case "D":
							// Display current output and continue
							System.out.println("All lines: ");
							Vector<String> cleanedLines = cleanLines(allLines);
							if( (cleanedLines.size() > 1) || !cleanedLines.elementAt(0).isEmpty() )
							{
								Vector<String> shiftedLines = shiftLines(cleanedLines);
								Vector<String> sortedLines = sortLines(shiftedLines);
								writeOutput(sortedLines);
							}
							else
							{
								System.out.println("Input only contained trivial words");
							}
							break;
						case "q":
						case "Q":
							// Quit the program
							System.out.println("Thank you for using the KWIC Program");
							continueRunning = false; 
							break;
						default: 
							System.out.println("Invalid entry try again");
						}
				} 			
				
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
			
			
		}
		catch (Exception e)
		{
			System.out.println( e.getMessage() );
		}
	}
	
	static private Vector<String> shiftLines( Vector<String> inStringArray )
	{
		Vector<String> shiftedLines = new Vector<String>(1,1); 
		for( int line = 0; line < inStringArray.size(); line++ )
		{
			if( !inStringArray.elementAt(line).isEmpty() )
			{
				String[] shifted = doShift( inStringArray.elementAt(line) );
				if( shifted.length > 1)
				{
					for( int word = 0; word < shifted.length - 1; word++ )
					{
						shiftedLines.add( shifted[word] );
					}
				}
				else
				{
					shiftedLines.add( shifted[0] );
				}
			}
		}
		return shiftedLines;
	}
	
	static private Vector<String> cleanLines( Vector<String> inStringArray )
	{
		Vector<String> cleanedLines = new Vector<String>(1,1); 
		for( int line = 0; line < inStringArray.size(); line++ )
		{
			String temp = null; 
			for( int word = 0; word < blackListedWords.size(); word++ )
			{
				String stringToClean = null; 
				if( temp == null)
				{
				    stringToClean = inStringArray.elementAt(line);
				}
				else
				{
					stringToClean = temp;
				}
				String test = blackListedWords.elementAt(word);
				temp = stringToClean.replaceAll("\\b"+test+"\\b", "");
				temp = temp.replaceAll("  ", "");
				temp = temp.trim(); 
			}
			cleanedLines.add(temp);
		}
		return cleanedLines;
	}
	
	static private String[] doShift( String aString )
	{	
		int numberOfWords = countWords( aString );
		if( numberOfWords > 1 )
		{
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
		else
		{
			String[] onlyOneWord = new String[numberOfWords];
			onlyOneWord[0] = aString;
			return onlyOneWord;
		}
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