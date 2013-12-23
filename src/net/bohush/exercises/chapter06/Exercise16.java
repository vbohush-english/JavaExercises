package net.bohush.exercises.chapter06;

public class Exercise16 {

	public static void main(String[] args) {

		int[] integers = new int[100000];
		for (int i = 0; i < integers.length; i++) {
			integers[i] = (int) (Math.random() * 100000);
		}
		int key = (int) (Math.random() * 100000);

		long startTime = System.currentTimeMillis();
		linearSearch(integers, key);
		long endTime = System.currentTimeMillis();
		long executionTime = endTime - startTime;
		System.out.println("linearSearch: " + executionTime);
		
		startTime = System.currentTimeMillis();
		selectionSort(integers);
		endTime = System.currentTimeMillis();
		executionTime = endTime - startTime;
		System.out.println("selectionSort: " + executionTime);
		
		
		startTime = System.currentTimeMillis();
		binarySearch(integers, key);
		endTime = System.currentTimeMillis();
		executionTime = endTime - startTime;
		System.out.println("binarySearch: " + executionTime);
		
		
	}

	public static int linearSearch(int[] list, int key) {
		for (int i = 0; i < list.length; i++) {
			if (key == list[i]) {
				return i;
			}
		}
		return -1;
	}

	public static int binarySearch(int[] list, int key) {
		int low = 0;
		int high = list.length - 1;
		while (high >= low) {
			int mid = (low + high) / 2;
			if (key < list[mid])
				high = mid - 1;
			else if (key == list[mid])
				return mid;
			else
				low = mid + 1;
		}
		return -low - 1;
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
