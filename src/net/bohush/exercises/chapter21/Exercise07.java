package net.bohush.exercises.chapter21;

public class Exercise07 {
	public static void main(String[] args) {
		Integer[] list = new Integer[] {1, 2, 5, 23, 56, 123, 456, 1045};
		System.out.println(binarySearch(list, 56));
	}
	
	public static <E extends Comparable<E>>	int binarySearch(E[] list, E key) {
		int low = 0;
		int high = list.length - 1;
		while (high >= low) {
			int mid = (low + high) / 2;
			if (key.compareTo(list[mid]) < 0) {
				high = mid - 1;
			} else if (key.compareTo(list[mid]) == 0) {
				return mid;
			} else {
				low = mid + 1;
			}
		}
		return -low - 1;
	}

}
