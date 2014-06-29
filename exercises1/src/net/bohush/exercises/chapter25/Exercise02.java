package net.bohush.exercises.chapter25;

import java.util.Comparator;

public class Exercise02 {

	public static void main(String[] args) {
		Integer[] array1 = new Integer[10];
		for (int i = 0; i < array1.length; i++) {
			array1[i] = (int)(Math.random() * 100000);
		}
		System.out.println(java.util.Arrays.toString(array1));
		mergeSort(array1);
		System.out.println(java.util.Arrays.toString(array1));
		
		Integer[] array2 = new Integer[10];
		for (int i = 0; i < array2.length; i++) {
			array2[i] = (int)(Math.random() * 100000);
		}
		System.out.println("\n\n" + java.util.Arrays.toString(array2));
		mergeSort(array2, new IntegerComparator());
		System.out.println(java.util.Arrays.toString(array2));
	}

	
	@SuppressWarnings("unchecked")
	public static <E extends Comparable<E>> void mergeSort(E[] list) {
		if (list.length > 1) {
			// Merge sort the first half
			E[] firstHalf = (E[])new Comparable[list.length / 2];
			System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
			mergeSort(firstHalf);

			// Merge sort the second half
			int secondHalfLength = list.length - list.length / 2;
			E[] secondHalf = (E[])new Comparable[secondHalfLength];
			System.arraycopy(list, list.length / 2, secondHalf, 0,	secondHalfLength);
			mergeSort(secondHalf);

			// Merge firstHalf with secondHalf into list
			merge(firstHalf, secondHalf, list);
		}
	}
	
	/** Merge two sorted lists */
	public static <E extends Comparable<E>> void merge(E[] list1, E[] list2, E[] temp) {
		int current1 = 0; // Current index in list1
		int current2 = 0; // Current index in list2
		int current3 = 0; // Current index in temp

		while (current1 < list1.length && current2 < list2.length) {
			if (list1[current1].compareTo(list2[current2]) < 0)
				temp[current3++] = list1[current1++];
			else
				temp[current3++] = list2[current2++];
		}

		while (current1 < list1.length)
			temp[current3++] = list1[current1++];

		while (current2 < list2.length)
			temp[current3++] = list2[current2++];
	}
	
	
	
	
	
	static class IntegerComparator implements Comparator<Integer> {
		@Override
		public int compare(Integer o1, Integer o2) {
			return o1.intValue() - o2.intValue();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public static<E>void mergeSort(E[] list, Comparator<? super E> comparator) {
		if (list.length > 1) {
			// Merge sort the first half
			E[] firstHalf = (E[])new Comparable[list.length / 2];
			System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
			mergeSort(firstHalf, comparator);

			// Merge sort the second half
			int secondHalfLength = list.length - list.length / 2;
			E[] secondHalf = (E[])new Comparable[secondHalfLength];
			System.arraycopy(list, list.length / 2, secondHalf, 0,	secondHalfLength);
			mergeSort(secondHalf, comparator);

			// Merge firstHalf with secondHalf into list
			merge(firstHalf, secondHalf, list, comparator);
		}
	}
	
	
	/** Merge two sorted lists */
	public static <E> void merge(E[] list1, E[] list2, E[] temp, Comparator<? super E> comparator) {
		int current1 = 0; // Current index in list1
		int current2 = 0; // Current index in list2
		int current3 = 0; // Current index in temp

		while (current1 < list1.length && current2 < list2.length) {
			if (comparator.compare(list1[current1], list2[current2]) < 0)
				temp[current3++] = list1[current1++];
			else
				temp[current3++] = list2[current2++];
		}

		while (current1 < list1.length)
			temp[current3++] = list1[current1++];

		while (current2 < list2.length)
			temp[current3++] = list2[current2++];
	}
	
}
