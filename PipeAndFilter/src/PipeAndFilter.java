import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Vector;


class IgnoreCaseComparator implements Comparator<String> 
{
	  public int compare(String strA, String strB) 
	  {
	    return strA.compareToIgnoreCase(strB);
	  }
}

public class PipeAndFilter
{	
	public static void main(String[] s)
	{
		
		String fileName = getInputFile();
		
		int numberOfLinesInFile = lineFileCounter( fileName );
	
		String[] linesOfText = getLinesOfTextFromFile( fileName, numberOfLinesInFile );
		
		Vector<String> allLinePermuntations = findAllLinePermuntations( linesOfText );
		
		Vector<String> sortedStrings = sortListInAlphabeticalOrder( allLinePermuntations );
		
		OutputVectorOfStringsToConsole ( sortedStrings );	
	}
	
	private static String getInputFile()
	{
		String file = "../../text.txt";
		
		return file; 
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
	
	private static String[] getLinesOfTextFromFile( String aFilePath, int aNumberOfLines )
	{
		String[] textData = new String[aNumberOfLines];
		try
		{
			FileReader fileReader = new FileReader( aFilePath );
			BufferedReader textReader = new BufferedReader( fileReader );
			
			
			for( int i = 0; i < aNumberOfLines; i++ )
			{
				textData[i] = textReader.readLine();
			}
			
			textReader.close();
		}
		catch (IOException e)
		{
			System.out.println( e.getMessage() );
		}
		return textData; 
		
	}
	
	static private Vector<String> findAllLinePermuntations( String[] linesOfText )
	{
		Vector<String> shiftedLines = new Vector<String>(1,1); 
		
		for( int i = 0; i < linesOfText.length; i++ )
		{
			String[] shifted = doShift( linesOfText[i] );
			for( int j = 0; j < shifted.length - 1; j++ )
			{
				shiftedLines.add( shifted[j] );
			}
		}
		
		return shiftedLines; 
	}
	
	static private String[] doShift( String aString )
	{	
		int numberOfWords = countWords( aString );
		String[] allPermuntations = new String[numberOfWords + 1];
		
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