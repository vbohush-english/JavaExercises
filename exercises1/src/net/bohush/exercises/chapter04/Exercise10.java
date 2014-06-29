package net.bohush.exercises.chapter04;

public class Exercise10 {

	public static void main(String[] args) {
		
		for (int i = 100, count = 0; i <= 1000; i++) {
			if ((i % 5 == 0) && (i % 6 == 0)) {
				if (count % 10 == 0) {
					System.out.println();	
				}
				System.out.print(i + " ");
				count++;
			}
		}
	}

}
