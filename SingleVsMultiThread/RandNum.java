import java.util.Random;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RandNum 
{
	public static int newInt()
	{
		Random r = new Random();
		return (r.nextInt(10)) + 1;
	}

	public static void random200m()
	{
		try
		{
			File f = new File("200m_rand_1.txt");

			if (f.createNewFile())
				f.getName();
			else
				System.out.println("File exists");

			FileWriter fw = new FileWriter(f, false);
			
			// Create 200m randomly generated ints
			for (int x = 0; x < 200000000; x++)
			{
				fw.write(Integer.toString(newInt()) + "\n");
			}

			fw.close();

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
