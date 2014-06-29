package net.bohush.exercises.chapter24;

public class Exercise24 {

	public static void main(String[] args) {
		int size = 10;
		int[] data = new int[size];
		for (int i = 0; i < data.length; i++) {
			data[i] = (int)(Math.random() * 20);
		}
		System.out.println(java.util.Arrays.toString(data));
		System.out.println(min(data));
	}
	
	static int min(int[] data) {
		return min(data, 0, data.length);
	}

	static int min(int[] data, int low, int high) {
		if (low + 1 == high) {
			return data[low];
		} else if (low + 2 == high) {
			return Math.min(data[low], data[high - 1]);
		} else {
			int halfSize = (low + high) / 2;
			return Math.min(min(data, low, halfSize), min(data, halfSize, high));
		}
	}
}
