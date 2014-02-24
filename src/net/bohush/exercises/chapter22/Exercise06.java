package net.bohush.exercises.chapter22;

import java.util.Iterator;
import java.util.LinkedList;

public class Exercise06 {

	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<>();
		for (int i = 0; i < 50000; i++) {
			list.add((int) (Math.random() * 1000000));
		}
		
		@SuppressWarnings("unused")
		int tmp;
		
		
		long time1 = System.currentTimeMillis();
		for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext();) {
			tmp = iterator.next();
			
		}
		System.out.println((System.currentTimeMillis() - time1) + " msec.");
		
		
		long time2 = System.currentTimeMillis();
		for (Integer integer : list) {
			tmp = integer;
		}
		System.out.println((System.currentTimeMillis() - time2) + " msec.");
		
		
		long time3 = System.currentTimeMillis();
		for (int i = 0; i < list.size(); i++) {
			tmp = list.get(i);
		}
		System.out.println((System.currentTimeMillis() - time3) + " msec.");
	}

}
