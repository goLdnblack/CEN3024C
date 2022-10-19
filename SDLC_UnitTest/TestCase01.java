import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

class TestCase01
{
	@Test

	public static void test()
	{
		String sortedWordFreq;
		try
		{
			sortedWordFreq = TextAnalyzer.analyzeText(UnitTest.filepath + "\\cases\\test01.txt", "txt");
			assertEquals(sortedWordFreq, FetchSolution.readText(UnitTest.filepath + "\\cases\\test01_solution.txt"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

}