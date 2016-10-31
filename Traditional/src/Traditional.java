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
	
	public static void main(String[] s)
	{
		try 
		{	
			Vector<String> trivialWords = defineTrivialWords();
			Vector<String> inputLines = new Vector<String>(1,1);
			while (obtainInput(inputLines))
			{
				Vector<String> cleanedLines = cleanLines(inputLines, trivialWords);
				Vector<String> shiftedLines = shiftLines(cleanedLines);
				Vector<String> sortedLines = sortLines(shiftedLines);
				writeOutput(sortedLines);
			}
			writeConfirmation();
		}
		catch (Exception e)
		{
			System.out.println( e.getMessage() );
		}
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
	
	static private boolean obtainInput(Vector<String> inputStringVector)
	{
		BufferedReader inputReader = new BufferedReader( new InputStreamReader(System.in));
		String inputString = null;

		try 
		{
			System.out.println("");
			System.out.println("KWIC Traditional Program" );
			System.out.println("Make selection");
			while (true)
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
						inputStringVector.add(inputText);
						break;
					case "d":
					case "D":
						// return to main and process
						return true;
					case "q":
					case "Q":
						// return to main and quit
						return false;
					default: 
						System.out.println("Invalid entry try again");
					}
			} 			
			
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		return true;
	}
	
	static private Vector<String> cleanLines( Vector<String> inStringArray, Vector<String> wordsToRemove )
	{
		Vector<String> cleanedLines = new Vector<String>(1,1); 
		for( int line = 0; line < inStringArray.size(); line++ )
		{
			String temp = null; 
			for( int word = 0; word < wordsToRemove.size(); word++ )
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
				String test = wordsToRemove.elementAt(word);
				temp = stringToClean.replaceAll("(?i)\\b"+test+"\\b", "");
				temp = temp.replaceAll("  ", " ");
				temp = temp.trim(); 
			}
			if (temp != "")
			{
				cleanedLines.add(temp);
			}
		}
		return cleanedLines;
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
	
	static private Vector<String> sortLines( Vector<String> shiftedLines)
	{
		IgnoreCaseComparator comparator = new IgnoreCaseComparator();
	    shiftedLines.sort(comparator);
	    return shiftedLines;
	}
	
	static private void writeOutput( Vector<String> sortedLines)
	{
		System.out.println("");
		for( int i = 0; i < sortedLines.size(); i++) 
		{
			System.out.println( sortedLines.get(i));
		}
	}
	
	static private void writeConfirmation()
	{
		System.out.println("");
		System.out.println("Thank you for using the KWIC Program");
	}
}

class IgnoreCaseComparator implements Comparator<String> 
{
	  public int compare(String strA, String strB) 
	  {
	    return strA.compareToIgnoreCase(strB);
	  }
}