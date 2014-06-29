package net.bohush.exercises.chapter09;

public class Exercise18 {

	public static void main(String[] args) {
		MyString2 string = new MyString2(new char[]{'t', 'r', 'a', '1', 'f', 'f', 'A'});
		string.print();
		MyString2 string2 = string.toUpperCase();
		string2.print();
		MyString2 string3 = string2.substring(2);
		string3.print();
		MyString2 string4 = MyString2.valueOf(true);
		string4.print();
		System.out.println(string.compare(new MyString2(new char[]{'t', 'r', 'a', '1', 'f', 'f'})));
	}

}


class MyString2 {
	
	private char[] data;
	
	public MyString2(char[] chars) {
		data = new char[chars.length];
		System.arraycopy(chars, 0, data, 0, chars.length);
	}

	public MyString2 substring(int begin) {
		char[] tmp = new char[data.length - begin];
		for (int i = 0; i < tmp.length; i++, begin++) {
			tmp[i] = data[begin];
		}
		return new MyString2(tmp);			
	}
	
	public void print() {
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i]);
		}
		System.out.println();
	}
	
	public MyString2 toUpperCase() {
		char[] tmp = new char[data.length];
		for (int i = 0; i < tmp.length; i++) {
			tmp[i] = Character.toUpperCase(data[i]);
		}
		return new MyString2(tmp);	
	}
	
	public char[] toChars() {
		char[] tmp = new char[data.length];
		for (int i = 0; i < tmp.length; i++) {
			tmp[i] = data[i];
		}
		return tmp;
	}
	
	public int compare(MyString2 s) {
		if (equals(s)) {
			return 0;
		}
		int minLenght = this.length() < s.length() ? this.length() : s.length();
		for (int i = 0; i < minLenght; i++) {
			if (data[i] != s.data[i]) {
				return data[i] - s.data[i];
			} 
		}
		if (this.length() < s.length()) {
			return -1;
		} else {
			return 1;
		}
	}
	
	public int length() {
		return data.length;
	}

	public boolean equals(MyString2 s) {
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
	
	public char charAt(int index) {
		return data[index];
	}
	
	public static MyString2 valueOf(boolean b) {
		if (b) {
			return new MyString2(new char[]{'t', 'r', 'u', 'e'});
		} else {
			return new MyString2(new char[]{'f', 'a', 'l', 's', 'e'});
		}
	}
	
}