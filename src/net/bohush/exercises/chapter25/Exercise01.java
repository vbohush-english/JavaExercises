package net.bohush.exercises.chapter25;

import java.util.Comparator;

public class Exercise01 {

	public static void main(String[] args) {
		Integer[] array1 = new Integer[10];
		for (int i = 0; i < array1.length; i++) {
			array1[i] = (int)(Math.random() * 100000);
		}
		System.out.println(java.util.Arrays.toString(array1));
		bubbleSort(array1);
		System.out.println(java.util.Arrays.toString(array1));
		
		Integer[] array2 = new Integer[10];
		for (int i = 0; i < array2.length; i++) {
			array2[i] = (int)(Math.random() * 100000);
		}
		System.out.println("\n\n" + java.util.Arrays.toString(array2));
		bubbleSort(array2, new IntegerComparator());
		System.out.println(java.util.Arrays.toString(array2));
	}
	
	static class IntegerComparator implements Comparator<Integer> {
		@Override
		public int compare(Integer o1, Integer o2) {
			return o1.intValue() - o2.intValue();
		}
		
	}
	
	public static <E extends Comparable<E>> void bubbleSort(E[] list) {
		boolean needNextPass = true;
		for (int k = 1; k < list.length && needNextPass; k++) {
			needNextPass = false;
			for (int i = 0; i < list.length - k; i++) {
				if (list[i].compareTo(list[i + 1]) > 0) {
					E temp = list[i];
					list[i] = list[i + 1];
					list[i + 1] = temp;
					needNextPass = true;
				}
			}
		}
	}
	
	public static <E> void bubbleSort(E[] list, Comparator<? super E> comparator) {
		boolean needNextPass = true;
		for (int k = 1; k < list.length && needNextPass; k++) {
			needNextPass = false;
			for (int i = 0; i < list.length - k; i++) {
				if (comparator.compare(list[i], list[i + 1]) > 0) {
					E temp = list[i];
					list[i] = list[i + 1];
					list[i + 1] = temp;
					needNextPass = true;
				}
			}
		}
	}

}
