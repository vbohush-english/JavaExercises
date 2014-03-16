package net.bohush.exercises.chapter25;

import java.util.ArrayList;
public class Exercise12 {

	public static void main(String[] args) {
		int maxOrder = 1000000;
		ArrayList<Integer> list = new ArrayList<>(); 
		for (int i = 0; i < 1000000; i++) {
			list.add((int) (Math.random() * maxOrder));
		}
		long time = System.currentTimeMillis();
		radixSort(list, maxOrder);
		System.out.println("Time = " + (System.currentTimeMillis() - time));
	}

	static void radixSort(ArrayList<Integer> list, int maxOrder) {
		for (int order = 1; order < maxOrder; order *= 10) {
			@SuppressWarnings("unchecked")
			ArrayList<Integer>[] bucket = new ArrayList[10];
			
			for (int i = 0; i < bucket.length; i++) {
				bucket[i] = new java.util.ArrayList<>();
			}
			
			for (int i = 0; i < list.size(); i++) {
				bucket[(list.get(i) / order) % 10].add(list.get(i));
			}
			
			int k = 0;
			for (int i = 0; i < bucket.length; i++) {
				if (bucket[i] != null) {
					for (int j = 0; j < bucket[i].size(); j++)
						list.set(k++, bucket[i].get(j));
				}
			}
		}
	}
}
