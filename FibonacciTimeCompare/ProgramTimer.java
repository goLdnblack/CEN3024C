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
		// When end is called, it should print
		// the calculation of the time spent
		endTime = System.nanoTime();
		calcTime();
	}

	private static void calcTime()
	{
		// Print the result
		long totalTime = endTime - startTime;
					
		System.out.println("Program execution time: " +
							totalTime + " nanoseconds.");
	}
}
