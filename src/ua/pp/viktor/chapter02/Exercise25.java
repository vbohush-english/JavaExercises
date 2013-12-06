package ua.pp.viktor.chapter02;

import java.util.Scanner;

public class Exercise25 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter employee's name: ");
		String name = input.next();
		System.out.print("Enter number of hours worked in a week: ");
		int hours = input.nextInt();
		System.out.print("Enter hourly pay rate: ");
		double rate = input.nextDouble();
		System.out.print("Enter federal tax withholding rate: ");
		double federalRate = input.nextDouble();
		System.out.print("Enter state tax withholding rate: ");
		double stateRate = input.nextDouble();
		input.close();
		System.out.println("Employee Name: " + name);
		System.out.println("Hours Worked: " + hours);
		System.out.println("Pay Rate: " + rate);
		double grossPay = rate * hours;
		System.out.println("Gross Pay: " + grossPay);
		System.out.println("Deductions:");
		double federalWithholding = grossPay * federalRate;
		System.out.println("  Federal Withholding (" + federalRate * 100 + "%): $" + (int)(federalWithholding * 100) / 100.0);
		double stateWithholding = grossPay * stateRate;
		System.out.println("  State Withholding (" + stateRate * 100 + "%): $" + (int)(stateWithholding * 100) / 100.0);
		double totalDeduction = federalWithholding + stateWithholding;
		System.out.println("  Total Deduction: $" + (int)(totalDeduction * 100) / 100.0);
		double netPay = grossPay - totalDeduction;
		System.out.println("Net Pay: $" + (int)(netPay * 100) / 100.0);
	}

}
