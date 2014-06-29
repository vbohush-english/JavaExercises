package net.bohush.exercises.chapter32;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class Exercise14 {

	public static void main(String[] args) {
		final int SIZE = 9000000;
		int[] list1 = new int[SIZE];
		int[] list2 = new int[SIZE];

		for (int i = 0; i < list1.length; i++) {
			list1[i] = list2[i] = (int) (Math.random() * 10000000);
		}
		
		long startTime = System.currentTimeMillis();
		parallelQuickSort(list1);
		long endTime = System.currentTimeMillis();
		System.out.println("\nParallel time with " + Runtime.getRuntime().availableProcessors() + " processors is " + (endTime - startTime) + " milliseconds");

		startTime = System.currentTimeMillis();
		quickSort(list2);
		endTime = System.currentTimeMillis();
		System.out.println("\nSequential time is " + (endTime - startTime) + " milliseconds");

	}

	public static void parallelQuickSort(int[] list) {
		RecursiveAction mainTask = new SortTask(list, 0, list.length - 1);
		ForkJoinPool pool = new ForkJoinPool();
		pool.invoke(mainTask);
	}

	private static class SortTask extends RecursiveAction {
		private static final long serialVersionUID = 1L;
		private int[] list;
		private int first;
		private int last;

		SortTask(int[] list, int first, int last) {
			this.list = list;
			this.first = first;
			this.last = last;
		}

		@Override
		protected void compute() {
			if (last > first) {				
				int pivotIndex = partition(list, first, last);
				invokeAll(new SortTask(list, first, pivotIndex - 1), new SortTask(list, pivotIndex + 1, last));
			}
		}
	}

	public static void quickSort(int[] list) {
		quickSort(list, 0, list.length - 1);
	}

	private static void quickSort(int[] list, int first, int last) {
		if (last > first) {
			int pivotIndex = partition(list, first, last);
			quickSort(list, first, pivotIndex - 1);
			quickSort(list, pivotIndex + 1, last);
		}
	}

	/** Partition the array list[first..last] */
	private static int partition(int[] list, int first, int last) {
		int pivot = list[first]; // Choose the first element as the pivot
		int low = first + 1; // Index for forward search
		int high = last; // Index for backward search

		while (high > low) {
			// Search forward from left
			while (low <= high && list[low] <= pivot)
				low++;

			// Search backward from right
			while (low <= high && list[high] > pivot)
				high--;

			// Swap two elements in the list
			if (high > low) {
				int temp = list[high];
				list[high] = list[low];
				list[low] = temp;
			}
		}

		while (high > first && list[high] >= pivot)
			high--;

		// Swap pivot with list[high]
		if (pivot > list[high]) {
			list[first] = list[high];
			list[high] = pivot;
			return high;
		} else {
			return first;
		}
	}
}