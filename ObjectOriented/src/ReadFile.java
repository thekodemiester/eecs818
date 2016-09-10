import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class ReadFile 
{
	private String mPath;
	
	public ReadFile(String filePath)
	{
		mPath = filePath; 
	}
	
	public String[] Read() throws IOException 
	{	
		FileReader fileReader = new FileReader( mPath );
		BufferedReader textReader = new BufferedReader( fileReader );
		
		int numberOfLines = ReadLines();
		String[] textData = new String[numberOfLines];
		
		for( int i = 0; i < numberOfLines; i++ )
		{
			textData[i] = textReader.readLine();
		}
		
		textReader.close();
		return textData;
	}
		
	int ReadLines() throws IOException 
	{
		FileReader fileReader = new FileReader( mPath );
		BufferedReader textReader = new BufferedReader( fileReader );
	
		int numberOfLines = 0; 
		while( ( textReader.readLine() ) != null )
		{
			numberOfLines++; 
		}
		textReader.close();		
			
		return numberOfLines;
	}
}
