// Jesus Sanchez-Rivera
// CEN 3024C
// Prof Walauskis
// 10/26/2022
// Using four threads resulted in a faster runtime than using
// a single thread, with eight threads providing a small
// increase over using four threads. 16 threads did not
// significantly improve the runtime performance.

import java.util.ArrayList;
import java.util.Arrays;

public class SingleVSMulti
{
	public static void main(String[] args) 
	{
		// Initialize 200m values
		ArrayList<Integer> alist = new ArrayList<>();
		for (int x = 0; x < 200000000; x++)
		{
			alist.add(RandNum.newInt());
		}

		Object[] randomArr = alist.toArray();

		System.out.println("Generated a list of 200m new random values.\n");

		try
		{
			TestSingleThread(randomArr);
			TestTwoThread(randomArr);
			TestFourThread(randomArr);
			TestEightThread(randomArr);
			Test16Thread(randomArr);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	public static void Test16Thread(Object[] randomArr) throws InterruptedException
	{	
		// Split array into 16 parts to send
		// to 16 threads
		int size = 12500000;
		long totalSum;

		Object[] arr1 = Arrays.copyOfRange(randomArr, 0, size);
		Object[] arr2 = Arrays.copyOfRange(randomArr, size, size * 2);
		Object[] arr3 = Arrays.copyOfRange(randomArr, size * 2, size * 3);
		Object[] arr4 = Arrays.copyOfRange(randomArr, size * 3, size * 4);
		Object[] arr5 = Arrays.copyOfRange(randomArr, size * 4, size * 5);
		Object[] arr6 = Arrays.copyOfRange(randomArr, size * 5, size * 6);
		Object[] arr7 = Arrays.copyOfRange(randomArr, size * 6, size * 7);
		Object[] arr8 = Arrays.copyOfRange(randomArr, size * 7, size * 8);
		Object[] arr9 = Arrays.copyOfRange(randomArr, size * 8, size * 9);
		Object[] arr10 = Arrays.copyOfRange(randomArr, size * 9, size * 10);
		Object[] arr11 = Arrays.copyOfRange(randomArr, size * 10, size * 11);
		Object[] arr12 = Arrays.copyOfRange(randomArr, size * 11, size * 12);
		Object[] arr13 = Arrays.copyOfRange(randomArr, size * 12, size * 13);
		Object[] arr14 = Arrays.copyOfRange(randomArr, size * 13, size * 14);
		Object[] arr15 = Arrays.copyOfRange(randomArr, size * 14, size * 15);
		Object[] arr16 = Arrays.copyOfRange(randomArr, size * 15, size * 16);

		// Test multi threaded performance
		long startTime = System.nanoTime();
		MultiThread thread1 = new MultiThread(arr1);
		MultiThread thread2 = new MultiThread(arr2);
		MultiThread thread3 = new MultiThread(arr3);
		MultiThread thread4 = new MultiThread(arr4);
		MultiThread thread5 = new MultiThread(arr5);
		MultiThread thread6 = new MultiThread(arr6);
		MultiThread thread7 = new MultiThread(arr7);
		MultiThread thread8 = new MultiThread(arr8);
		MultiThread thread9 = new MultiThread(arr9);
		MultiThread thread10 = new MultiThread(arr10);
		MultiThread thread11 = new MultiThread(arr11);
		MultiThread thread12 = new MultiThread(arr12);
		MultiThread thread13 = new MultiThread(arr13);
		MultiThread thread14 = new MultiThread(arr14);
		MultiThread thread15 = new MultiThread(arr15);
		MultiThread thread16 = new MultiThread(arr16);
		
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();
		thread6.start();
		thread7.start();
		thread8.start();
		thread9.start();
		thread10.start();
		thread11.start();
		thread12.start();
		thread13.start();
		thread14.start();
		thread15.start();
		thread16.start();
		
		thread1.join();
		thread2.join();
		thread3.join();
		thread4.join();
		thread5.join();
		thread6.join();
		thread7.join();
		thread8.join();
		thread9.join();
		thread10.join();
		thread11.join();
		thread12.join();
		thread13.join();
		thread14.join();
		thread15.join();
		thread16.join();
		
		totalSum = thread1.getSum() + thread2.getSum() +
		thread3.getSum() + thread4.getSum() + thread5.getSum() +
		thread6.getSum() + thread7.getSum() + thread8.getSum() +
		thread9.getSum() + thread10.getSum() + thread11.getSum() +
		thread12.getSum() + thread13.getSum() + thread14.getSum() +
		thread15.getSum() + thread16.getSum();

		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		
		System.out.println("16 threaded sum answer: " + totalSum);
		System.out.println("16 threaded time spent: " + duration + " nanoseconds.");
	}

	public static void TestEightThread(Object[] randomArr) throws InterruptedException
	{	
		// Split array into 8 parts to send
		// to 8 threads
		int size = 25000000;
		long totalSum;

		Object[] arr1 = Arrays.copyOfRange(randomArr, 0, size);
		Object[] arr2 = Arrays.copyOfRange(randomArr, size, size * 2);
		Object[] arr3 = Arrays.copyOfRange(randomArr, size * 2, size * 3);
		Object[] arr4 = Arrays.copyOfRange(randomArr, size * 3, size * 4);
		Object[] arr5 = Arrays.copyOfRange(randomArr, size * 4, size * 5);
		Object[] arr6 = Arrays.copyOfRange(randomArr, size * 5, size * 6);
		Object[] arr7 = Arrays.copyOfRange(randomArr, size * 6, size * 7);
		Object[] arr8 = Arrays.copyOfRange(randomArr, size * 7, size * 8);

		// Test multi threaded performance
		long startTime = System.nanoTime();
		MultiThread thread1 = new MultiThread(arr1);
		MultiThread thread2 = new MultiThread(arr2);
		MultiThread thread3 = new MultiThread(arr3);
		MultiThread thread4 = new MultiThread(arr4);
		MultiThread thread5 = new MultiThread(arr5);
		MultiThread thread6 = new MultiThread(arr6);
		MultiThread thread7 = new MultiThread(arr7);
		MultiThread thread8 = new MultiThread(arr8);
		
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();
		thread6.start();
		thread7.start();
		thread8.start();
		thread1.join();
		thread2.join();
		thread3.join();
		thread4.join();
		thread5.join();
		thread6.join();
		thread7.join();
		thread8.join();
		
		totalSum = thread1.getSum() + thread2.getSum() +
		thread3.getSum() + thread4.getSum() + thread5.getSum() +
		thread6.getSum() + thread7.getSum() + thread8.getSum();

		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		
		System.out.println("Eight threaded sum answer: " + totalSum);
		System.out.println("Eight threaded time spent: " + duration + " nanoseconds.");
	}

	public static void TestTwoThread(Object[] randomArr) throws InterruptedException
	{	
		// Split array into 2 parts to send
		// to 2 threads
		int size = 100000000;
		long totalSum;

		Object[] arr1 = Arrays.copyOfRange(randomArr, 0, size);
		Object[] arr2 = Arrays.copyOfRange(randomArr, size, size * 2);

		// Test multi threaded performance
		long startTime = System.nanoTime();
		MultiThread thread1 = new MultiThread(arr1);
		MultiThread thread2 = new MultiThread(arr2);
		
		thread1.start();
		thread2.start();
		thread1.join();
		thread2.join();
		
		totalSum = thread1.getSum() + thread2.getSum();

		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		
		System.out.println("Two threaded sum answer: " + totalSum);
		System.out.println("Two threaded time spent: " + duration + " nanoseconds.");
	}

	public static void TestFourThread(Object[] randomArr) throws InterruptedException
	{	
		// Split array into 4 parts to send
		// to 4 threads
		int size = 50000000;
		long totalSum;
		//int size = 10;
		Object[] arr1 = Arrays.copyOfRange(randomArr, 0, size);
		Object[] arr2 = Arrays.copyOfRange(randomArr, size, size * 2);
		Object[] arr3 = Arrays.copyOfRange(randomArr, size * 2, size * 3);
		Object[] arr4 = Arrays.copyOfRange(randomArr, size * 3, size * 4);

		// Test multi threaded performance
		long startTime = System.nanoTime();
		MultiThread thread1 = new MultiThread(arr1);
		MultiThread thread2 = new MultiThread(arr2);
		MultiThread thread3 = new MultiThread(arr3);
		MultiThread thread4 = new MultiThread(arr4);
		
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread1.join();
		thread2.join();
		thread3.join();
		thread4.join();
		
		totalSum = thread1.getSum() + thread2.getSum() +
		thread3.getSum() + thread4.getSum();

		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		
		System.out.println("Four threaded sum answer: " + totalSum);
		System.out.println("Four threaded time spent: " + duration + " nanoseconds.");
	}
	
	public static void TestSingleThread(Object[] randomArr) throws InterruptedException
	{
		long totalSum;

		// Test single threaded performance
		long startTime = System.nanoTime();

		MultiThread thread1 = new MultiThread(randomArr);

		thread1.start();
		thread1.join();

		totalSum = thread1.getSum();

		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		
		System.out.println("Single threaded sum answer: " + totalSum);
		System.out.println("Single threaded time spent: " + duration + " nanoseconds.");


	}
}
