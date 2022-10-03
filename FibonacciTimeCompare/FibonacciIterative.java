class FibonacciIterative
{
	public static long fib(int n)
	{
		if (n == 0)
			return 0;

		long sum = 0, a = 0, b = 1;

		for (int x = 1; x < n; x++)
		{
			sum = a;
			a = b;
			b = a + sum;
		}

		return b;
	}
}
