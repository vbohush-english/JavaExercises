package net.bohush.exercises.chapter09;

public class Exercise24 {

	public static void main(String[] args) {
		MyStringBuilder2 m1 = new MyStringBuilder2(new char[]{'t', 'R', 'A', '1', 'f', 'f', 'A'});
		System.out.println(m1);
		MyStringBuilder2 m2 =  m1.toLowerCase();
		System.out.println(m2);
		MyStringBuilder2 m3 =  m2.substring(1, 6);
		System.out.println(m3);
		m3.append(m1);
		m3.append(m2);
		System.out.println(m3); 
		MyStringBuilder2 m4 = new MyStringBuilder2();
		m4.append(m1).append(m2);
		System.out.println(m4); 
		MyStringBuilder2 m5 = new MyStringBuilder2("Tratataaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		System.out.println(m5.toUpperCase());
		System.out.println(m5.substring(3));
		System.out.println(m5.reverse());
		m1.append(m5);
		System.out.println(m1);
		MyStringBuilder2 m6 = new MyStringBuilder2("0123456789QWERTYUI");
		MyStringBuilder2 m7 = new MyStringBuilder2("%+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=%");
		m6.insert(5,  m7);
		System.out.println(m6);
		
	}

}

class MyStringBuilder2 {
	char[] data;
	int size;
	int capacity;
	
	public MyStringBuilder2() {
		size = 0;
		capacity = 10;
		data = new char[capacity];
	}
	
	
	public MyStringBuilder2(char[] chars) {
		size = chars.length;
		capacity = size * 2;
		data = new char[capacity];
		System.arraycopy(chars, 0, data, 0, chars.length);
	}
	
	public MyStringBuilder2(String s) {
		this(s.toCharArray());
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
	
	public MyStringBuilder2 toLowerCase()  {
		char[] tmp = new char[size];
		for (int i = 0; i < tmp.length; i++) {
			tmp[i] = Character.toLowerCase(data[i]);
		}
		return new MyStringBuilder2(tmp);	
	}
	
	public MyStringBuilder2 toUpperCase()  {
		char[] tmp = new char[size];
		for (int i = 0; i < tmp.length; i++) {
			tmp[i] = Character.toUpperCase(data[i]);
		}
		return new MyStringBuilder2(tmp);	
	}
	
	public MyStringBuilder2 substring(int begin, int end) {
		char[] tmp = new char[end - begin];
		for (int i = 0; i < tmp.length; i++, begin++) {
			tmp[i] = data[begin];
		}
		return new MyStringBuilder2(tmp);	
	}
	
	public MyStringBuilder2 substring(int begin) {
		char[] tmp = new char[size - begin];
		for (int i = 0; i < tmp.length; i++, begin++) {
			tmp[i] = data[begin];
		}
		return new MyStringBuilder2(tmp);	
	}
	
	public MyStringBuilder2 append(MyStringBuilder2 s) {
		while ((s.size + size) > capacity) {		
			capacity *= 2;
			char[] tmp = new char[capacity];
			System.arraycopy(data, 0, tmp, 0, size);
			data = tmp;
		}
		System.arraycopy(s.data, 0, data, size, s.size);
		size += s.size;
		return this;
	}

	public MyStringBuilder2 reverse() {
		for (int i = 0; i < size / 2; i++) {
			char tmp = data[i];
			data[i] = data[size - i - 1];
			data[size - i - 1] = tmp;
		}
		return this;	
	}

	public MyStringBuilder2 insert(int offset, MyStringBuilder2 s) {
		while ((s.size + size) > capacity) {		
			capacity *= 2;
			char[] tmp = new char[capacity];
			System.arraycopy(data, 0, tmp, 0, size);
			data = tmp;
		}
		System.arraycopy(data, offset, data, offset + s.length(), size - offset);
		System.arraycopy(s.data, 0, data, offset, s.size);
		size += s.size;
		return this;	
	}

}
