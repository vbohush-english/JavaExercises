package net.bohush.exercises.chapter32;

public class Exercise01 {
	public static void main(String[] args) {
		// Create tasks
		Runnable printA = new PrintChar('a', 100, 200);
		Runnable printB = new PrintChar('b', 100, 100);
		Runnable print100 = new PrintNum(100);

		// Create threads
		Thread thread3 = new Thread(print100);
		Thread thread1 = new Thread(printA);
		Thread thread2 = new Thread(printB);
		

		// Start threads
		thread1.start();
		thread2.start();
		thread3.start();
	}
}

// The task for printing a specified character in specified times
class PrintChar implements Runnable {
	private char charToPrint; // The character to print
	private int times; // The times to repeat
	private int ms;

	/**
	 * Construct a task with specified character and number of times to print
	 * the character
	 */
	public PrintChar(char c, int t, int ms) {
		charToPrint = c;
		times = t;
		this.ms = ms;
	}

	@Override
	/** Override the run() method to tell the system
	 *  what the task to perform
	 */
	public void run() {
		try {
			Thread.sleep(50);
			System.out.print(charToPrint);
			Thread.sleep(ms);
			for (int i = 1; i < times; i++) {
				System.out.print(charToPrint);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
}

// The task class for printing number from 1 to n for a given n
class PrintNum implements Runnable {
	private int lastNum;

	/** Construct a task for printing 1, 2, ... i */
	public PrintNum(int n) {
		lastNum = n;
	}

	@Override
	/** Tell the thread how to run */
	public void run() {
		try {
			System.out.print(" 1");
			Thread.sleep(100);
			for (int i = 2; i <= lastNum; i++) {
				System.out.print(" " + i);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}
}
