package net.bohush.exercises.chapter19;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Exercise07 {

	public static void main(String[] args) throws FileNotFoundException,
			IOException, ClassNotFoundException {
		ObjectInputStream input = new ObjectInputStream(new FileInputStream("tmp/Exercise19_06.dat"));

		try {
			while (true) {
				Loan loan = (Loan) (input.readObject());
				System.out.println(loan.getTotalPayment());
			}
		} catch (EOFException e) {
		}
		input.close();
	}

}
