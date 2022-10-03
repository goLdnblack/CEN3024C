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
		int fibNum = 0;

		System.out.println("Recursive Solution\n");
		ProgramTimer.start();
		fibNum = FibonacciRecursive.fib(Integer.parseInt(args[0]));
		System.out.println(fibNum);
		ProgramTimer.end();

		System.out.println("Iterative Solution\n");
		ProgramTimer.start();
		fibNum = FibonacciIterative.fib(Integer.parseInt(args[0]));
		System.out.println(fibNum);
		ProgramTimer.end();
	}
}
