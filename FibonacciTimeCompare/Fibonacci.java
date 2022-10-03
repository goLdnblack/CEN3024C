/*
 In current or new class design multiple fib numbers
 to display the run time for each sequence for both
 recursive and iterative solutions.
*/

import java.util.*;

public class Fibonacci
{
	public static void main(String[] args)
	{
		int fibSequence = Integer.parseInt(args[0]);
		long fibNum = 0;

		// For DP Recursive
		long[] memo = new long[fibSequence + 2];
		Arrays.fill(memo, -1);

		System.out.println("Recursive Solution\n");
		ProgramTimer.start();
		fibNum = FibonacciRecursive.fib(fibSequence);
		System.out.println(fibNum);
		ProgramTimer.end();

		System.out.println("\nIterative Solution\n");
		ProgramTimer.start();
		fibNum = FibonacciIterative.fib(fibSequence);
		System.out.println(fibNum);
		ProgramTimer.end();

		System.out.println("\nRecursive with memoization Solution\n");
		ProgramTimer.start();
		fibNum = FibonacciDP.fib(fibSequence, memo);
		System.out.println(fibNum);
		ProgramTimer.end();
	}
}
