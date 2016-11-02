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
 * CircularShift.java
 ********************************************************/
public class CircularShift 
{
	private String mInput; 
	
	public CircularShift( String aInput)
	{
		mInput = aInput; 
	}
	
	public String[] doShift()
	{	
		int numberOfWords = countWords( mInput );
		String[] allPermuntations = new String[numberOfWords + 1];
		if( numberOfWords > 1 )
		{
			for( int i = 0; i < numberOfWords; i++ )
			{
				int index = mInput.indexOf(' ');
				String subStringFirst = mInput.substring( 0 , index);
				String subStringSecond = mInput.substring( index + 1 );
				mInput = subStringSecond + " " + subStringFirst; 
				allPermuntations[i] = mInput; 
			}
			return allPermuntations; 
		}
		else
		{
			String[] onlyOneWord = new String[numberOfWords];
			onlyOneWord[0] = mInput;
			return onlyOneWord;
		}
	}
	
	private int countWords(String aString)
	{
	    int wordCount = 0;

	    boolean word = false;
	    int stringLength = aString.length() - 1;

	    for (int i = 0; i < aString.length(); i++) 
	    {
	    	if ( !aString.substring(i, i+1).equals(" ") && i != stringLength ) 
	        {
	            word = true;
	        } 
	        else if ( aString.substring(i, i+1).equals(" ") && word ) 
	        {
	            wordCount++;
	            word = false;
	        }
	        else if ( !aString.substring(i, i+1).equals(" ") && i == stringLength )
	        {
	            wordCount++;
	        }
	    }
	    return wordCount;
	}
}
