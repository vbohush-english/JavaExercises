package net.bohush.exercises.chapter08;

import java.util.Random;

public class Exercise14 {

	public static void main(String[] args) {
		int[] data = new int[100000];
		Random random = new Random();
		for (int i = 0; i < data.length; i++) {
			data[i] = random.nextInt(1000000000);
		}
		StopWatch stopWatch = new StopWatch();
		selectionSort(data);
		//java.util.Arrays.sort(data);
		stopWatch.stop();
		System.out.println(stopWatch.getElapsedTime());
	}
	
	public static void selectionSort(int[] list) {
		for (int i = 0; i < list.length - 1; i++) {
			// Find the minimum in the list[i..list.length-1]
			int currentMin = list[i];
			int currentMinIndex = i;

			for (int j = i + 1; j < list.length; j++) {
				if (currentMin > list[j]) {
					currentMin = list[j];
					currentMinIndex = j;
				}
			}

			// Swap list[i] with list[currentMinIndex] if necessary;
			if (currentMinIndex != i) {
				list[currentMinIndex] = list[i];
				list[i] = currentMin;
			}
		}
	}

}

class StopWatch {
	private long startTime;
	private long endTime;
	
	public long getStartTime() {
		return startTime;
	}
	
	public long getEndTime() {
		return endTime;
	}
	
	public StopWatch() {
		startTime = System.currentTimeMillis();
		endTime = -1;
	}
	
	public void start() {
		startTime = System.currentTimeMillis();
	}
	
	public void stop() {
		endTime = System.currentTimeMillis();
	}
	
	public long getElapsedTime() {
		if (endTime != -1) {
			return endTime - startTime;
		} else {
			return -1L;
		}
	}
}
