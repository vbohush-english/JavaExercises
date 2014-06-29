package net.bohush.exercises.chapter09;

public class Exercise21 {

	public static void main(String[] args) {
		String[] strArr = split("ab#12#453", "#");
		for (int i = 0; i < strArr.length; i++) {
			System.out.println(strArr[i]);
		}

	}

	public static String[] split(String s, String regex) {
		String[] tmp = s.split(regex);
		String[] result = new String[tmp.length * 2 - 1];
		for (int i = 0; i < result.length; i += 2) {
			result[i] = tmp[i / 2];
			if ((i + 1) < result.length) { 
				result[i + 1] = regex;
			}
		}
		return result;
	}
}
