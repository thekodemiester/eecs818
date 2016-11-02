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

public class ObjectOriented 
{
	
	
	public static void main(String[] args) 
	{
		BufferedReader inputReader = new BufferedReader( new InputStreamReader(System.in));
		String inputString = null;
		boolean continueRunning = true; 
		Vector<String> allLines = new Vector<String>(1,1);
		
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
						String inputText = inputReader.readLine(); 
						allLines.add( inputText );
						break;
					case "d":
					case "D":
						// Display current output and continue
						Vector<String> cleanedLines = new Vector<String>(1,1); 
						Vector<String> shiftedLines = new Vector<String>(1,1); 
						System.out.println("All lines: ");
						StringCleaner cleaner = new StringCleaner( allLines );
						cleanedLines = cleaner.doClean();
						for( int i = 0; i < cleanedLines.size(); i++ )
						{
							if( !cleanedLines.elementAt(i).isEmpty() )
							{
								CircularShift shifter = new CircularShift( cleanedLines.elementAt(i) );
								String[] shifted = shifter.doShift();
								if( shifted.length > 1)
								{
									for( int j = 0; j < shifted.length - 1; j++ )
									{
										shiftedLines.add( shifted[j] );
									}
								}
								else
								{
									shiftedLines.add( shifted[0] );
								}
							}
						}				
						if( shiftedLines.size() > 0 )
						{
							VectoredStringSorter aSorter = new VectoredStringSorter( shiftedLines );
							Vector<String> sortedLines = aSorter.doSort();
							System.out.println( "" );
							for( int i = 0; i < sortedLines.size(); i++) 
							{
								System.out.println( sortedLines.get(i));
							}
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
		}
		catch (IOException e)
		{
			System.out.println( e.getMessage() );
		}
	}

}

