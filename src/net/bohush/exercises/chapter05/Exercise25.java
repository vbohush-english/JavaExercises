package net.bohush.exercises.chapter05;

public class Exercise25 {

	public static void main(String[] args) {
		System.out.println(convertMillis(5500));
		System.out.println(convertMillis(100000));
		System.out.println(convertMillis(555550000));
	}
	
	public static String convertMillis(long millis) {
		String result = "";
		long totalSeconds = millis / 1000;
		result += totalSeconds % 60;
		long totalMinutes = totalSeconds / 60;
		result = totalMinutes % 60 + ":" + result;
		result = totalMinutes / 60 + ":" + result;
		return result;
	}

}
