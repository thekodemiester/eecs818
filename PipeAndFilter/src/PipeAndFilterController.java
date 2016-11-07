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
 * ObjectOriented.java
 ********************************************************/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

public class PipeAndFilterController 
{
	
	
	public static void main(String[] args) 
	{
		BufferedReader inputReader = new BufferedReader( new InputStreamReader(System.in));
		String inputString = "";
		String inputText = "";
		boolean continueRunning = true; 
				
		try 
		{
			System.out.println("KWIC OO Program" );
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
						inputText += ( inputReader.readLine() + '&' );
						break;
					case "d":
					case "D":
						// Display current output and continue
						String cleanedLines;
						String shiftedLines; 
						String sortedLines;
						System.out.println("All lines: ");
						stringCleaner cleaner = new stringCleaner();
						cleanedLines = cleaner.run( inputText );
						circularShifter shifter = new circularShifter();
						shiftedLines = shifter.run( cleanedLines );
						sorter stringSorter = new sorter();
						sortedLines = stringSorter.run( shiftedLines );
						output stringOutputter = new output();
						stringOutputter.run( sortedLines );
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
		}
		catch (IOException e)
		{
			System.out.println( e.getMessage() );
		}
	}

}

