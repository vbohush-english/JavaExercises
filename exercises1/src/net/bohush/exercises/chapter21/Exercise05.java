package net.bohush.exercises.chapter21;

public class Exercise05 {
	public static void main(String[] args) {
		String[] list1 = new String[] {"as", "sad", "as11", "sad", "sad", "sadww"};
		System.out.println(max(list1));
		
		Integer[] list2 = new Integer[] {1, 123, 123, 421, 2, 321};
		System.out.println(max(list2));
	}
	
	public static <E extends Comparable<E>> E max(E[] list) {
		E result = list[0];
		for (int i = 1; i < list.length; i++) {
			if (list[i].compareTo(result) > 0) {
				result = list[i];
			}
		}
		return result;
	}
}
