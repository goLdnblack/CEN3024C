import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

class TestCase04
{
	@Test

	public static void test()
	{
		String sortedWordFreq;
		try
		{
			sortedWordFreq = TextAnalyzer.analyzeText(UnitTest.filepath + "\\cases\\test04.txt", "txt");
			assertEquals(sortedWordFreq, FetchSolution.readText(UnitTest.filepath + "\\cases\\test04_solution.txt"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

}