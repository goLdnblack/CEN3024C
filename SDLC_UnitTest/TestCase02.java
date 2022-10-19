import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

class TestCase02
{
	@Test

	public static void test()
	{
		String sortedWordFreq;
		try
		{
			sortedWordFreq = TextAnalyzer.analyzeText(UnitTest.filepath + "\\cases\\test02.txt", "txt");
			assertEquals(sortedWordFreq, FetchSolution.readText(UnitTest.filepath + "\\cases\\test02_solution.txt"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

}