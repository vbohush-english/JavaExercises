package net.bohush.exercises.chapter42;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Exercise05 extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        double amount = Double.parseDouble(request.getParameter("amount"));
        double rate = Double.parseDouble(request.getParameter("rate"));
        int years = Integer.parseInt(request.getParameter("years"));
        

        
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Exercise42_05</title>");            
            out.println("</head><center>");
            out.println("<p>Loan Amount: " + amount + "</p>");
            out.println("<p>Annual Interest Rate: " + rate + "</p>");
            out.println("<p>Number Of Years: " + years + "</p>");
            Loan loan = new Loan(rate, years, amount);
            out.println("<p style=\"color:red\">Monthly Payment: " + loan.getMonthlyPayment() + "</p>");
            out.println("<p style=\"color:red\">Total Payment: " + loan.getTotalPayment() + "</p>");
            out.println("</center><body></body>");
            out.println("</html>");
        }
    }

    public class Loan {

        private double annualInterestRate;
        private int numberOfYears;
        private double loanAmount;
        private java.util.Date loanDate;

        /**
         * Default constructor
         */
        public Loan() {
            this(2.5, 1, 1000);
        }

        /**
         * Construct a loan with specified annual interest rate, number of
         * years, and loan amount
         */
        public Loan(double annualInterestRate, int numberOfYears,
                double loanAmount) {
            this.annualInterestRate = annualInterestRate;
            this.numberOfYears = numberOfYears;
            this.loanAmount = loanAmount;
            loanDate = new java.util.Date();
        }

        /**
         * Return annualInterestRate
         */
        public double getAnnualInterestRate() {
            return annualInterestRate;
        }

        /**
         * Set a new annualInterestRate
         */
        public void setAnnualInterestRate(double annualInterestRate) {
            this.annualInterestRate = annualInterestRate;
        }

        /**
         * Return numberOfYears
         */
        public int getNumberOfYears() {
            return numberOfYears;
        }

        /**
         * Set a new numberOfYears
         */
        public void setNumberOfYears(int numberOfYears) {
            this.numberOfYears = numberOfYears;
        }

        /**
         * Return loanAmount
         */
        public double getLoanAmount() {
            return loanAmount;
        }

        /**
         * Set a newloanAmount
         */
        public void setLoanAmount(double loanAmount) {
            this.loanAmount = loanAmount;
        }

        /**
         * Find monthly payment
         */
        public double getMonthlyPayment() {
            double monthlyInterestRate = annualInterestRate / 1200;
            double monthlyPayment = loanAmount * monthlyInterestRate / (1
                    - (1 / Math.pow(1 + monthlyInterestRate, numberOfYears * 12)));
            return monthlyPayment;
        }

        /**
         * Find total payment
         */
        public double getTotalPayment() {
            double totalPayment = getMonthlyPayment() * numberOfYears * 12;
            return totalPayment;
        }

        /**
         * Return loan date
         */
        public java.util.Date getLoanDate() {
            return loanDate;
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}