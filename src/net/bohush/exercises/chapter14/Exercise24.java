package net.bohush.exercises.chapter14;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Exercise24 {

	public static void main(String[] args) throws FileNotFoundException {
		PrintWriter output = new PrintWriter("tmp/salary.txt");
		String[] ranks = {"assistant", "associate", "full"};
		for (int i = 1; i <= 1000; i++) {
			int rank = (int)(Math.random() * 3);
			double salary = 0;;
			switch (rank) {
			case 0:
				salary = 50000 + (Math.random() * 30000);
				break;
			case 1:
				salary = 60000 + (Math.random() * 50000);
				break;
			case 2:
				salary = 75000 + (Math.random() * 55000);
				break;
			}
			output.printf("FirstName%d\tLastName%d\t%s\t%.2f\n", i, i, ranks[rank], salary);
		}
		output.close();
	}

}
