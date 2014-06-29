package net.bohush.exercises.chapter04;

public class Exercise18 {

	public static void main(String[] args) {
		System.out.println("Pattern A");
		for (int i = 1; i <= 6; i++) {
			for (int j = 1; j <= i; j++) {
				System.out.print(j + " ");
			}
			System.out.println();
		}
		System.out.println("\nPattern B");
		for (int i = 1; i <= 6; i++) {
			for (int j = 1; j <= 7 - i; j++) {
				System.out.print(j + " ");
			}
			System.out.println();
		}
		System.out.println("\nPattern C");
		for (int i = 1; i <= 6; i++) {
			for (int j = 6; j >= 1; j--) {
				if (i >= j) {
					System.out.print(j + " ");
				} else {
					System.out.print("  ");
				}
			}
			System.out.println();
		}
		System.out.println("\nPattern D");
		for (int i = 1; i <= 6; i++) {
			for (int j = 1; j <= 6; j++) {
				if (i <= j) {
					System.out.print((j - i + 1) + " ");
				} else {
					System.out.print("  ");
				}
			}
			System.out.println();
		}
	}

}
