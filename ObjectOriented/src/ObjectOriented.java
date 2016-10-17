import java.io.IOException;
import java.util.Vector;

public class ObjectOriented 
{

	public static void main(String[] args) 
	{
		String fileName = "../../text.txt";
		
		try 
		{
			TextFileReader file = new TextFileReader( fileName );
			String[] allLines = file.Read();
			
			Vector<String> shiftedLines = new Vector<String>(1,1); 
			for( int i = 0; i < allLines.length; i++ )
			{
				CircularShift shifter = new CircularShift( allLines[i] );
				String[] shifted = shifter.doShift();
				for( int j = 0; j < shifted.length - 1; j++ )
				{
					shiftedLines.add( shifted[j] );
				}
			}
			
			VectoredStringSorter aSorter = new VectoredStringSorter( shiftedLines );
			shiftedLines = aSorter.doSort();
			
			for( int i = 0; i < shiftedLines.size(); i++) 
			{
				System.out.println( shiftedLines.get(i));
			}
		}
		catch (IOException e)
		{
			System.out.println( e.getMessage() );
		}
	}

}

