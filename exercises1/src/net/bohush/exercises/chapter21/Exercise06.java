package net.bohush.exercises.chapter21;

public class Exercise06 {
	public static void main(String[] args) {
		String[][] list1 = new String[][] {{"as", "sad"}, {"as11"}, {"sad", "sad", "sadww"}};
		System.out.println(max(list1));
		
		Integer[][] list2 = new Integer[][] {{1, 123, 123, 421, 2, 321}, {2, 5, 45, 67}, {1, 45, 34, 456, 3, 4}, {1, 2, 3}};
		System.out.println(max(list2));
	}
	
	public static <E extends Comparable<E>> E max(E[][] list) {
		E result = list[0][0];
		for (int i = 1; i < list.length; i++) {
			for (int j = 0; j < list[i].length; j++) {
				if (list[i][j].compareTo(result) > 0) {
					result = list[i][j];
				}	
			}			
		}
		return result;
	}
}
