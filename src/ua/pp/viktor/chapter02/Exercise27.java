package ua.pp.viktor.chapter02;

import javax.swing.JOptionPane;

public class Exercise27 {

	public static void main(String[] args) {
		
		String name = JOptionPane.showInputDialog(null, "Enter employee's name: ", "Input Dialog", JOptionPane.QUESTION_MESSAGE);
		String input = JOptionPane.showInputDialog(null, "Enter number of hours worked in a week: ", "Input Dialog", JOptionPane.QUESTION_MESSAGE);
		int hours = Integer.parseInt(input);
		input = JOptionPane.showInputDialog(null, "Enter hourly pay rate: ", "Input Dialog", JOptionPane.QUESTION_MESSAGE);
		double rate = Double.parseDouble(input);
		input = JOptionPane.showInputDialog(null, "Enter federal tax withholding rate: ", "Input Dialog", JOptionPane.QUESTION_MESSAGE);
		double federalRate = Double.parseDouble(input);
		input = JOptionPane.showInputDialog(null, "Enter state tax withholding rate: ", "Input Dialog", JOptionPane.QUESTION_MESSAGE);
		double stateRate = Double.parseDouble(input);
		
		double grossPay = rate * hours;
		double federalWithholding = grossPay * federalRate;
		double stateWithholding = grossPay * stateRate;
		double totalDeduction = federalWithholding + stateWithholding;
		double netPay = grossPay - totalDeduction;
		
		
		JOptionPane.showMessageDialog(null, "Employee Name: " + name + "\n" +
		"Hours Worked: " + hours + "\n" +
		"Pay Rate: " + rate + "\n" +
		"Gross Pay: " + grossPay + "\n" +
		"Deductions:" + "\n" +
		"  Federal Withholding (" + federalRate * 100 + "%): $" + (int)(federalWithholding * 100) / 100.0 + "\n" +
		"  State Withholding (" + stateRate * 100 + "%): $" + (int)(stateWithholding * 100) / 100.0 + "\n" +
		"  Total Deduction: $" + (int)(totalDeduction * 100) / 100.0 + "\n" +
		"Net Pay: $" + (int)(netPay * 100) / 100.0);
	}

}
