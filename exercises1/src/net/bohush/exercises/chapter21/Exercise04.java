package net.bohush.exercises.chapter21;

public class Exercise04 {
	public static void main(String[] args) {
		String[] list1 = new String[] {"as", "sad", "as11", "sad", "sad", "sadww"};
		insertionSort(list1);
		System.out.println(java.util.Arrays.toString(list1));
		
		Integer[] list2 = new Integer[] {1, 123, 123, 321, 2, 321};
		insertionSort(list2);
		System.out.println(java.util.Arrays.toString(list2));
	}
	
	public static <E extends Comparable<E>> void insertionSort(E[] list) {
		for (int i = 1; i < list.length; i++) {
			E tmp1 = list[i];
			for (int j = i - 1; j >= 0; j--) {
				if (list[j].compareTo(tmp1) > 0) {
					E tmp2 = list[j + 1];
					list[j + 1] = list[j];	
					list[j] = tmp2;
				} else {
					break;
				}
			}
		}
	}
}
