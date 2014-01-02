package net.bohush.exercises.chapter11;

import java.util.ArrayList;
import java.util.Scanner;

public class Exercise10 {

	public static void main(String[] args) {
		MyStack myStack = new MyStack();
		System.out.print("Enter 5 numbers: ");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		for (int i = 0; i < 5; i++) {
			myStack.push(input.nextInt());
		}
		for (int i = 0; i < 5; i++) {
			System.out.println(myStack.pop() + " ");
		}
	}

}

class MyStack extends ArrayList<Object> {
	
	private static final long serialVersionUID = 1L;

	public int getSize() {
		return super.size();
	}

	public Object peek() {
		return super.get(getSize() - 1);
	}

	public Object pop() {
		Object o = super.get(getSize() - 1);
		super.remove(getSize() - 1);
		return o;
	}

	public void push(Object o) {
		super.add(o);
	}

	@Override
	public String toString() {
		return "stack: " + super.toString();
	}
}