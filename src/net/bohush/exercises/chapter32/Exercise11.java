package net.bohush.exercises.chapter32;

public class Exercise11 {

	public static void main(String[] args) {
		Object object1 = new Object();
		Object object2 = new Object();
		new DeadLockThread(object1, object2, 1);
		new DeadLockThread(object2, object1, 2);
	}
	
	static class DeadLockThread implements Runnable {
		Object object1;
		Object object2;
		int threadNumber;
		
		public DeadLockThread(Object object1, Object object2, int threadNumber) {
			this.object1 = object1;
			this.object2 = object2;
			this.threadNumber = threadNumber;
			Thread thread = new Thread(this);
			thread.start();
		}
		
		@Override
		public void run() {
			System.out.println("Thread #" + threadNumber + ": starting");
			while(true) {
				synchronized (object1) {
					System.out.println("Thread #" + threadNumber + ": 1-st synchronized");		
					synchronized (object2) {
						System.out.println("Thread #" + threadNumber + ": 2-nd synchronized");
					}
				}
			}
		}		
	}
}
