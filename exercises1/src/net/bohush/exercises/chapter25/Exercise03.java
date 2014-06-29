package net.bohush.exercises.chapter25;

import java.util.Comparator;

public class Exercise03 {

	public static void main(String[] args) {
		Integer[] array1 = new Integer[10];
		for (int i = 0; i < array1.length; i++) {
			array1[i] = (int) (Math.random() * 100000);
		}
		System.out.println(java.util.Arrays.toString(array1));
		quickSort(array1);
		System.out.println(java.util.Arrays.toString(array1));

		Integer[] array2 = new Integer[10];
		for (int i = 0; i < array2.length; i++) {
			array2[i] = (int) (Math.random() * 100000);
		}
		System.out.println("\n\n" + java.util.Arrays.toString(array2));
		quickSort(array2, new IntegerComparator());
		System.out.println(java.util.Arrays.toString(array2));
	}

	
	public static <E extends Comparable<E>>	void quickSort(E[] list) {
		quickSort(list, 0, list.length - 1);
	}

	private static <E extends Comparable<E>> void quickSort(E[] list, int first, int last) {
		if (last > first) {
			int pivotIndex = partition(list, first, last);
			quickSort(list, first, pivotIndex - 1);
			quickSort(list, pivotIndex + 1, last);
		}
	}
	
	private static <E extends Comparable<E>> int partition(E[] list, int first, int last) {
		E pivot = list[first]; // Choose the first element as the pivot
		int low = first + 1; // Index for forward search
		int high = last; // Index for backward search

		while (high > low) {
			// Search forward from left
			while (low <= high && list[low].compareTo(pivot) <= 0)
				low++;

			// Search backward from right
			while (low <= high && list[high].compareTo(pivot) > 0)
				high--;

			// Swap two elements in the list
			if (high > low) {
				E temp = list[high];
				list[high] = list[low];
				list[low] = temp;
			}
		}

		while (high > first && list[high].compareTo(pivot) >= 0)
			high--;

		// Swap pivot with list[high]
		if (pivot.compareTo(list[high]) > 0) {
			list[first] = list[high];
			list[high] = pivot;
			return high;
		} else {
			return first;
		}
	}
	
	
	public static <E> void quickSort(E[] list, Comparator<? super E> comparator) {
		quickSort(list, 0, list.length - 1, comparator);
	}
	
	private static <E> void quickSort(E[]  list, int first, int last, Comparator<? super E> comparator) {
		if (last > first) {
			int pivotIndex = partition(list, first, last, comparator);
			quickSort(list, first, pivotIndex - 1, comparator);
			quickSort(list, pivotIndex + 1, last, comparator);
		}
	}
	
	
	static class IntegerComparator implements Comparator<Integer> {
		@Override
		public int compare(Integer o1, Integer o2) {
			return o1.intValue() - o2.intValue();
		}		
	}
	
	private static <E> int partition(E[] list, int first, int last, Comparator<? super E> comparator) {
		E pivot = list[first]; // Choose the first element as the pivot
		int low = first + 1; // Index for forward search
		int high = last; // Index for backward search

		while (high > low) {
			// Search forward from left
			while (low <= high && comparator.compare(list[low], pivot) <= 0)
				low++;

			// Search backward from right
			while (low <= high && comparator.compare(list[high], pivot) > 0)
				high--;

			// Swap two elements in the list
			if (high > low) {
				E temp = list[high];
				list[high] = list[low];
				list[low] = temp;
			}
		}

		while (high > first && comparator.compare(list[high], pivot) >= 0)
			high--;

		// Swap pivot with list[high]
		if (comparator.compare(pivot, list[high]) > 0) {
			list[first] = list[high];
			list[high] = pivot;
			return high;
		} else {
			return first;
		}
	}

}
