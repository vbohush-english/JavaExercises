package net.bohush.exercises.chapter14;

public class Exercise10 {

	public static void main(String[] args) {
		try {
			@SuppressWarnings("unused")
			int[] array = new int[100000000];
		} catch (OutOfMemoryError ex){
			System.out.println("Out of memory error");
		}
	}

}
