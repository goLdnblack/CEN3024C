/*
 In current or new class design multiple fib numbers
 to display the run time for each sequence for both
 recursive and iterative solutions.

 Jesus Sanchez-Rivera
 CEN3024
 Professor Walauskis
 10/03/2022
*/

import java.util.*;

public class Fibonacci
{
	public static void iterativeVsRecursive(int n)
	{
		// Calculate the Fibonacci sequence up to
		// the value n and display the time it took
		// for each method.
		long fibResult = 0;
		int x;
		// Iterative
		System.out.println("Iterative Method\n");

		for (x = 0; x <= n; x++)
		{
			System.out.println("Solving for fib(" + x + ")");
			System.out.print("F(" + x + ") = ");
			ProgramTimer.start();
			fibResult = FibonacciIterative.fib(x);
			ProgramTimer.end();
			System.out.println();

		}

		// Recursive
		System.out.println("\nRecursive Method\n");
		for (x = 0; x <= n; x++)
		{
			System.out.println("Solving for fib(" + x + ")");
			System.out.print("F(" + x + ") = ");
			ProgramTimer.start();
			fibResult = FibonacciRecursive.fib(x);
			ProgramTimer.end();
			System.out.println();

		}
	}

	public static void recursiveMemoization(int n)
	{
		// Compare the time difference of recursion
		// using memoization versus regular recursion.
		long fibResult = 0;
		int x;

		// Recursive Memoization method
		System.out.println("Recursive Memoization Method\n");

		for (x = 0; x <= n; x++)
		{
			long[] memo = new long[n + 2];
			System.out.println("Solving for fib(" + x + ")");
			System.out.print("F(" + x + ") = ");
			ProgramTimer.start();
			fibResult = FibonacciDP.fib(x, memo);
			ProgramTimer.end();
			System.out.println();

		}
	}

	public static void main(String[] args)
	{
		// Test for fib(40)
		iterativeVsRecursive(40);

		// Test using memoization
		recursiveMemoization(40);
	}
}
