import java.util.concurrent.TimeUnit;

class ProgramTimer
{
	static long startTime;
	static long endTime;

	public static void start()
	{
		startTime = System.nanoTime();
	}

	public static void end()
	{
		endTime = System.nanoTime();
		calcTime();
	}

	private static void calcTime()
	{	
		long totalTime = endTime - startTime;
		
		// Convert to seconds
		// totalTime = TimeUnit.SECONDS.convert(totalTime, TimeUnit.NANOSECONDS);
		
		System.out.println("Program execution time: " + totalTime + " nanoseconds.");
		//return totalTime;
	}
}
