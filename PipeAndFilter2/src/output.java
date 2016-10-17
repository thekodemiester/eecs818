import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class output
{	
	public static void main(String[] s)
	{
		BufferedReader inputReader = new BufferedReader( new InputStreamReader(System.in));
		String inputText = null;
		
		try 
		{
			inputText = inputReader.readLine();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		String outputText = inputText.replaceAll("&", "\n");
		System.out.println( outputText );
	}
}