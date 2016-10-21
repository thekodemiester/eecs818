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
 * fileReader.java
 ********************************************************/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class fileReader
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
		
		int numberOfLinesInFile = lineFileCounter( inputString );
	
		String linesOfText = getLinesOfTextFromFile( inputString, numberOfLinesInFile );
		
		System.out.println( linesOfText );
	}
	
	private static int lineFileCounter( String aFilePath )
	{
		int lineCount = 0;
		try
		{
			FileReader fileReaderCounter = new FileReader( aFilePath );
			BufferedReader textCounter = new BufferedReader( fileReaderCounter );
			
			while( ( textCounter.readLine() ) != null )
			{
				lineCount++; 
			}
			textCounter.close();
		}
		catch (IOException e)
		{
			System.out.println( e.getMessage() );
		}
		return lineCount;
	}
	
	private static String getLinesOfTextFromFile( String aFilePath, int aNumberOfLines )
	{
		String textData = "";
		try
		{
			FileReader fileReader = new FileReader( aFilePath );
			BufferedReader textReader = new BufferedReader( fileReader );
			
			for( int i = 0; i < aNumberOfLines; i++ )
			{
				textData += ( textReader.readLine() + '&' );
			}
			
			textReader.close();
		}
		catch (IOException e)
		{
			System.out.println( e.getMessage() );
		}
		return textData; 
		
	}
}