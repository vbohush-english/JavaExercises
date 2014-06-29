package net.bohush.exercises.chapter04;

public class Exercise19 {

	public static void main(String[] args) {
		int number = 8;

		for (int i = 0, position = number - 1, count = 1; i < number; i++, position--, count += 2) {
			int tmp = 1;
			for (int j = 1; j < number*2; j++) {				
				if ((j > position) && (j <= position + count)) {
					System.out.print(tmp);
					if (j < number) {
						tmp *= 2;
					} else {
						tmp /= 2;
					}
				}
				System.out.print("\t");
			}
			System.out.println();
		}
	}

}
