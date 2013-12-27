package net.bohush.exercises.chapter09;

public class Exercise22 {

	public static void main(String[] args) {
		MyStringBuilder1 myStringBuilder1 = new MyStringBuilder1(new char[]{'t', 'R', 'A', '1', 'f', 'f', 'A'});
		System.out.println(myStringBuilder1);
		MyStringBuilder1 myStringBuilder2 =  myStringBuilder1.toLowerCase();
		System.out.println(myStringBuilder2);
		MyStringBuilder1 myStringBuilder3 =  myStringBuilder1.substring(1, 6);
		System.out.println(myStringBuilder3);
		myStringBuilder3.append(myStringBuilder1);
		myStringBuilder3.append(myStringBuilder2);
		System.out.println(myStringBuilder3);
	}

}

class MyStringBuilder1 {
	char[] data;
	int size;
	int capacity;
	
	public MyStringBuilder1(char[] chars) {
		size = chars.length;
		capacity = size * 2;
		data = new char[capacity];
		System.arraycopy(chars, 0, data, 0, chars.length);
	}
	
	public String toString() {
		return new String(data, 0, size);
	}
	
	public int length() {
		return size;
	}
	
	public char charAt(int index) {
		return data[index];
	}
	
	public MyStringBuilder1 toLowerCase()  {
		char[] tmp = new char[size];
		for (int i = 0; i < tmp.length; i++) {
			tmp[i] = Character.toLowerCase(data[i]);
		}
		return new MyStringBuilder1(tmp);	
	}
	
	
	public MyStringBuilder1 substring(int begin, int end) {
		char[] tmp = new char[end - begin];
		for (int i = 0; i < tmp.length; i++, begin++) {
			tmp[i] = data[begin];
		}
		return new MyStringBuilder1(tmp);	
	}
	
	public MyStringBuilder1 append(MyStringBuilder1 s) {
		if ((s.size + size) > capacity) {		
			capacity *= 2;
			char[] tmp = new char[capacity];
			System.arraycopy(data, 0, tmp, 0, size);
			data = tmp;
		}
		System.arraycopy(s.data, 0, data, size, s.size);
		size += s.size;
		return this;
	}

}
