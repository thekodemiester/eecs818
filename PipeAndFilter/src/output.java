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
 * output.java
 ********************************************************/

public class output
{	
	public void run(String input)
	{
		String outputText = input.replaceAll("&", "\n");
		System.out.println( outputText );
		return;
	}
}