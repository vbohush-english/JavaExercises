package net.bohush.exercises.chapter19;

public class Exercise18 {

	public static void main(String[] args) {
		System.out.println(getBits(123));
		System.out.println(getBits(12345678));
		System.out.println(getBits(111111));
		System.out.println(getBits(10 * 256));
		System.out.println();
		System.out.println(getFullBits(123));
		System.out.println(getFullBits(12345678));
		System.out.println(getFullBits(10 * 256));
	}

	public static String getBits(int value) {
		value = value % 256;
		String binaryInteger = "";
		int i = 0;
		int tmp = value >> i;
		for (int j = 0; j < 8; j++) {
			binaryInteger = (tmp & 1) + binaryInteger;
			i++;
			tmp = value >> i;
		} 
		return binaryInteger;
	}
	
	public static String getFullBits(int value) {
		String binaryInteger = "";
		int i = 0;
		int tmp = value >> i;
		while (tmp != 0) {
			binaryInteger = (tmp & 1) + binaryInteger;
			i++;
			tmp = value >> i;
		} 
		return binaryInteger;
	}
}
