import java.util.*;

public class Fibonacci
{
	public static void main(String[] args)
	{
		ProgramTimer.start();
		int fibNum = FibonacciRecursive.fib(Integer.parseInt(args[0]));
		System.out.println(fibNum);
		ProgramTimer.end();
	}
}
