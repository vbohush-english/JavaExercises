package net.bohush.exercises.chapter45.client;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import net.bohush.exercises.chapter45.exercise03.Exercise03;
import net.bohush.exercises.chapter45.exercise03.Exercise4503;

public class Exercise03c extends JApplet {

    private Exercise4503 exercise4503 = new Exercise4503();
    private Exercise03 proxy = exercise4503.getExercise03Port();
    private JTextField jtfAnnualInterestRate = new JTextField("10");
    private JTextField jtfNumberOfYears = new JTextField("1");
    private JTextField jtfLoanAmount = new JTextField("1000");
    private JTextField jtfMonthlyPayment = new JTextField();
    private JTextField jtfTotalPayment = new JTextField();

    // Create a Compute Payment button
    private JButton jbtComputeLoan = new JButton("Compute Payment");

    public Exercise03c() {
        jtfMonthlyPayment.setEditable(false);
        jtfTotalPayment.setEditable(false);
        // Panel p1 to hold labels and text fields
        JPanel p1 = new JPanel(new GridLayout(5, 2));
        p1.add(new JLabel("Annual Interest Rate"));
        p1.add(jtfAnnualInterestRate);
        p1.add(new JLabel("Number of Years"));
        p1.add(jtfNumberOfYears);
        p1.add(new JLabel("Loan Amount"));
        p1.add(jtfLoanAmount);
        p1.add(new JLabel("Monthly Payment"));
        p1.add(jtfMonthlyPayment);
        p1.add(new JLabel("Total Payment"));
        p1.add(jtfTotalPayment);
        p1.setBorder(new TitledBorder("Enter loan amount, interest rate, and years"));

        // Panel p2 to hold the button
        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        p2.add(jbtComputeLoan);

        // Add the panels to the frame
        add(p1, BorderLayout.CENTER);
        add(p2, BorderLayout.SOUTH);

        // Register listener
        jbtComputeLoan.addActionListener(new ButtonListener());
    }

    /**
     * Handle the Compute Payment button
     */
    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // Get values from text fields
            double interest = Double.parseDouble(jtfAnnualInterestRate.getText());
            int year = Integer.parseInt(jtfNumberOfYears.getText());
            double loanAmount = Double.parseDouble(jtfLoanAmount.getText());
            proxy.setAnnualInterestRate(interest);
            proxy.setNumberOfYears(year);
            proxy.setLoanAmount(loanAmount);

            // Display monthly payment and total payment
            jtfMonthlyPayment.setText(proxy.getMonthlyPayment() + "");
            jtfTotalPayment.setText(proxy.getTotalPayment() + "");
        }
    }

}
