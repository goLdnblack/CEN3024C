import org.junit.Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class FetchSolution
{
	@Test

	public static String readText(String filePath)
	{
		BufferedReader br;

		try
		{
			File file = new File(filePath);
			br = new BufferedReader(new FileReader(file));
	
			StringBuilder sb = new StringBuilder();
	
			String text;
	
			while ((text = br.readLine()) != null)
			{
				// Process text file
				sb.append(text);
				sb.append("\n");
			}

			br.close();
			return sb.toString();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return "";
	}
}