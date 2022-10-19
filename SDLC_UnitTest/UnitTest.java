public class UnitTest
{
	// Generate filepath to different testcases
	static String filepath = System.getProperty("user.dir");

	public static void  main(String[] args)
	{
		TestCase01.test();
		TestCase02.test();
		TestCase03.test();
		TestCase04.test();
		
		System.out.println("Sucess!");
	}
}
		