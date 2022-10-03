import java.util.*;

class FibonacciDP
{
	public static long fib(int n, long[] memo)
	{
		if (n <= 1)
			return n;

		long f1, f2;

		if (memo[n - 1] != -1)
			f1 = memo[n - 1];
		else
			f1 = fib(n - 1, memo);
		
		if (memo[n - 2] != -1)
			f2 = memo[n - 2];
		else
			f2 = fib(n - 2, memo);

		return memo[n] = f1 + f2;
	}
}
