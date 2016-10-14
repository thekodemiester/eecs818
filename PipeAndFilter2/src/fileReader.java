import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Vector;


class IgnoreCaseComparator implements Comparator<String> 
{
	  public int compare(String strA, String strB) 
	  {
	    return strA.compareToIgnoreCase(strB);
	  }
}

public class fileReader
{	
	public static void main(String[] s)
	{
		BufferedReader inputReader = new BufferedReader( new InputStreamReader(System.in));
		String inputString = null;
		
		try 
		{
			inputString = inputReader.readLine();
		} 
		catch (IOException e) 
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
		String textData = null;
		try
		{
			FileReader fileReader = new FileReader( aFilePath );
			BufferedReader textReader = new BufferedReader( fileReader );
			
			for( int i = 0; i < aNumberOfLines; i++ )
			{
				textData += textReader.readLine() + "/n";
			}
			
			textReader.close();
		}
		catch (IOException e)
		{
			System.out.println( e.getMessage() );
		}
		return textData; 
		
	}
	
	static private String findAllLinePermuntations( String linesOfText )
	{
		String shiftedLines = null;
		
		while( linesOfText != null )
		{
			int endIndex = linesOfText.indexOf("/n");
			String subString = linesOfText.substring(0, endIndex);
			linesOfText = linesOfText.substring(endIndex + 1, linesOfText.length());
			String shifted = doShift( subString );
			shiftedLines += shifted + "/n";
		}
				
		return shiftedLines; 
	}
	
	static private String doShift( String aString )
	{	
		int numberOfWords = countWords( aString );
		String allPermuntations = new String[numberOfWords + 1];
		
		for( int i = 0; i < numberOfWords; i++ )
		{
			int index = aString.indexOf(' ');
			String subStringFirst = aString.substring( 0 , index);
			String subStringSecond = aString.substring( index + 1 );
			aString = subStringSecond + " " + subStringFirst; 
			allPermuntations[i] = aString; 
		}
		return allPermuntations; 
	}
	
	static private int countWords(String aString)
	{
	    int wordCount = 0;

	    boolean word = false;
	    int stringLength = aString.length() - 1;

	    for (int i = 0; i < aString.length(); i++) 
	    {
	        if ( Character.isLetter(aString.charAt(i)) && i != stringLength ) 
	        {
	            word = true;
	        } 
	        else if ( !Character.isLetter(aString.charAt(i)) && word ) 
	        {
	            wordCount++;
	            word = false;
	        }
	        else if ( Character.isLetter(aString.charAt(i)) && i == stringLength )
	        {
	            wordCount++;
	        }
	    }
	    return wordCount;
	}
	
	static private Vector<String> sortListInAlphabeticalOrder( Vector<String> aList )
	{
		Vector<String> listToSort = aList; 
		
		IgnoreCaseComparator comparator = new IgnoreCaseComparator();

	    listToSort.sort(comparator);
	    
	    return listToSort; 
	    
	    
	}
	
	static private void OutputVectorOfStringsToConsole( Vector<String> aList )
	{
		for( int i = 0; i < aList.size(); i++) 
		{
			System.out.println( aList.get(i));
		}		
	}
}