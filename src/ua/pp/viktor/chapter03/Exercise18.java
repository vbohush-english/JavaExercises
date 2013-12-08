package ua.pp.viktor.chapter03;

import javax.swing.JOptionPane;

public class Exercise18 {

	public static void main(String args[]) {
	    String input = JOptionPane.showInputDialog(null, "Enter a year:", "Input Dialog", JOptionPane.QUESTION_MESSAGE);
		int year = Integer.parseInt(input);

		boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);

		if (isLeapYear) {
			JOptionPane.showMessageDialog(null, year + " is a leap year.");			
		} else {
			JOptionPane.showMessageDialog(null, year + " is not a leap year.");
		}

	}
}
