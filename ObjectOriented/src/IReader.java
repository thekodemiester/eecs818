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
 * IReader.java
 ********************************************************/

public class IReader 
{
	protected String mPath;
	
	public IReader(String filePath)
	{
		mPath = filePath; 
	}
}
