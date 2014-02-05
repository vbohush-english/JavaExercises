package net.bohush.exercises.chapter19;

import java.io.*;
import java.util.ArrayList;

public class Exercise17 {
	public static void main(String[] args) throws IOException {
		BitOutputStream output = new BitOutputStream(new File("tmp/Exercise19_17.dat"));
		output.writeBit("0000000011111111");
		output.writeBit("010000100100001001101");
		output.close();		
	}

}

class BitOutputStream {
	private ArrayList<Integer> bits = new ArrayList<>();
	private DataOutputStream output;
	
	public BitOutputStream(File file) throws FileNotFoundException {
		output = new DataOutputStream(new FileOutputStream(file));
	}

	public void writeBit(char bit) throws IOException {
		if (bit == '0') {
			bits.add(0);	
		} else {
			bits.add(1);
		}
		
		if (bits.size() == 8) {
			output.writeByte(getByte());
			bits.clear();
		}
	}

	public void writeBit(String bit) throws IOException {
		for (int i = 0; i < bit.length(); i++) {
			writeBit(bit.charAt(i));
		}
	}

	public void close() throws IOException {
		while(bits.size() != 0) {
			writeBit('0');
		}
		output.close();
	}
	
	private byte getByte() {
		int sum = 0;
		for (int i = 7, number = 1; i >= 0; i--, number*= 2) {
			sum += bits.get(i) * number;
		}
		return (byte)sum;
	}
}
