class FibonacciIterative
{
	public static long fib(int n)
	{
		if (n == 0)
			return 0;

		long sum = 1, a = 0, b = 0;

		for (int x = 1; x < n; x++)
		{
			b = a;
			a = sum;
			sum = a + b;
		}

		return sum;
	}
}
