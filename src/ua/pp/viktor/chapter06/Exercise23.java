package ua.pp.viktor.chapter06;

public class Exercise23 {

	public static void main(String[] args) {
		boolean[] lockers = new boolean[100];
		for (int i = 0; i < lockers.length; i++) {
			for (int j = i; j < lockers.length; j += i + 1) {
				lockers[j] = lockers[j] ? false : true;
				
			}
		}
		System.out.print("Opened lockers: ");
		for (int i = 0; i < lockers.length; i++) {
			if (lockers[i]) {
				System.out.print((i + 1) + " ");
			}
		}
	}

}
