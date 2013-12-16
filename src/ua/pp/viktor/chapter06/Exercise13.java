package ua.pp.viktor.chapter06;

public class Exercise13 {

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++)
		System.out.println(getRandom(1, 2, 3, 4, 5, 6, 7, 8, 9));
	}
	
	public static int getRandom(int... numbers) {
		int result;
		boolean isOk;
		do {
			result = (int)(Math.random() * 54) + 1;
			isOk = true;
			for (int data:numbers) {
				if (data == result) {
					 isOk = false;
					 break;
				}
			}			
		} while (!isOk);
		return result;
	}

}
