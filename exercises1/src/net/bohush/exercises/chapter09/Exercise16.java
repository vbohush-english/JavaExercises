package net.bohush.exercises.chapter09;

public class Exercise16 {

	public static void main(String[] args) {
		MyString1 string = new MyString1(new char[]{'a', 'B', 'C', '1', 'f'});
		string.print();
		System.out.println(string.length());
		System.out.println(string.charAt(2));
		MyString1 string2 = string.toLowerCase();
		string2.print();
		MyString1 string3 = string2.substring(1, 4);
		string3.print();
		System.out.println(string2.equals(string3));
		MyString1.valueOf(912384).print();
	}

}

class MyString1 {
	private char[] data;
	
	public MyString1(char[] chars) {
		data = new char[chars.length];
		System.arraycopy(chars, 0, data, 0, chars.length);
	}
	
	public char charAt(int index) {
		return data[index];
	}
	
	public int length() {
		return data.length;
	}
	
	public MyString1 substring(int begin, int end) {
		char[] tmp = new char[end - begin];
		for (int i = 0; i < tmp.length; i++, begin++) {
			tmp[i] = data[begin];
		}
		return new MyString1(tmp);			
	}
	
	public MyString1 toLowerCase() {
		char[] tmp = new char[data.length];
		for (int i = 0; i < tmp.length; i++) {
			tmp[i] = Character.toLowerCase(data[i]);
		}
		return new MyString1(tmp);	
	}
	
	public void print() {
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i]);
		}
		System.out.println();
	}
	
	public boolean equals(MyString1 s) {
		if (length() != s.length()) {
			return false;
		}
		for (int i = 0; i < data.length; i++) {
			if (charAt(i) != s.charAt(i)) {
				return false;
			}
		}
		return true;
	}
	
	public static MyString1 valueOf(int i) {
		int size = 0;
		int tmp = i;
		while (tmp != 0) {
			size++;
			tmp /= 10;
		}
		char[] newData = new char[size];
		for (int j = 0; j < newData.length; j++, i /= 10) {
			newData[newData.length - j - 1] = (char)('0' + (i % 10));
			
		}
		return new MyString1(newData);
	}
	
}
