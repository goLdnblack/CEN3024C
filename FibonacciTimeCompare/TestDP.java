import java.util.*;

public class TestDP
{
	public static void main(String[] args)
	{
		int size = Integer.parseInt(args[0]);
		int[] memo = new int[size + 2];
		Arrays.fill(memo, -1);

		int fibNum = FibonacciDP.fib(size, memo);
		System.out.println(fibNum);
	}
}
