package net.bohush.exercises.chapter32;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exercise04 {
	private static Integer sum = 0;
	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
	
		for (int i = 0; i < 1000; i++) {
			executor.execute(new AddOne());
		}

		executor.shutdown();

		while (!executor.isTerminated()) {
		}

		System.out.println("sum = " + sum);
	}

	private static class AddOne implements Runnable {
		public void run() {
			sum++;
		}
	}
}
