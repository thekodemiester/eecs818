import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TextFileReader extends IReader
{

	public TextFileReader(String filePath) 
	{
		super(filePath);
	}
	
	private BufferedReader getReader() throws IOException
	{
		FileReader fileReader = new FileReader( mPath );
		BufferedReader textReader = new BufferedReader( fileReader );
		
		return textReader; 
	}
	
	public String[] Read() throws IOException 
	{	
		BufferedReader textReader = getReader();
		
		int numberOfLines = getNumberLinesInFile( textReader );
		String[] textData = new String[numberOfLines];
		
		for( int i = 0; i < numberOfLines; i++ )
		{
			textData[i] = textReader.readLine();
		}
		
		textReader.close();
		return textData;
	}
	
	private int getNumberLinesInFile( BufferedReader aReader ) throws IOException 
	{
		BufferedReader textReader = getReader();
	
		int numberOfLines = 0; 
		while( ( textReader.readLine() ) != null )
		{
			numberOfLines++; 
		}
		textReader.close();		
			
		return numberOfLines;
	}
}
