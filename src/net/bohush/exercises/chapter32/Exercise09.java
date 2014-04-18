package net.bohush.exercises.chapter32;

import java.util.HashSet;
import java.util.Iterator;

public class Exercise09 {

	public static HashSet<Double> set = new HashSet<>();

	public static void main(String[] args) {
		new Thread1();
		new Thread2();
	}

	static class Thread1 implements Runnable {

		public Thread1() {
			Thread thread = new Thread(this);
			thread.start();
		}

		@Override
		public void run() {
			try {
				while (true) {
					set.add(Math.random());
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	static class Thread2 implements Runnable {

		public Thread2() {
			Thread thread = new Thread(this);
			thread.start();
		}

		@Override
		public void run() {
			try {
				while (true) {
					for (Iterator<Double> iterator = set.iterator(); iterator.hasNext();) {
						iterator.next();
						Thread.sleep(1000);
					}									
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
