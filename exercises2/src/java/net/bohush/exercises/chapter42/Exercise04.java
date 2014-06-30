package net.bohush.exercises.chapter42;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Exercise04 extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        double income = Double.parseDouble(request.getParameter("income"));
        int status = Integer.parseInt(request.getParameter("status"));

        
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Exercise42_04</title>");            
            out.println("</head><center>");
            out.println("<p>Taxable Income: " + income + "</p>");
            out.println("<p>Filing Status: " + status + "</p>");
            out.println("<p style=\"color:red\">Tax: " + computetax(status, income) + "</p>");
            out.println("</center><body></body>");
            out.println("</html>");
        }
    }

    public static double computetax(int status, double income) {

        double tax = 0;

        if (status == 0) { // Compute tax for single filers
            if (income <= 8350) {
                tax = income * 0.10;
            } else if (income <= 33950) {
                tax = 8350 * 0.10 + (income - 8350) * 0.15;
            } else if (income <= 82250) {
                tax = 8350 * 0.10 + (33950 - 8350) * 0.15 + (income - 33950)
                        * 0.25;
            } else if (income <= 171550) {
                tax = 8350 * 0.10 + (33950 - 8350) * 0.15 + (82250 - 33950)
                        * 0.25 + (income - 82250) * 0.28;
            } else if (income <= 372950) {
                tax = 8350 * 0.10 + (33950 - 8350) * 0.15 + (82250 - 33950)
                        * 0.25 + (171550 - 82250) * 0.28 + (income - 171550)
                        * 0.33;
            } else {
                tax = 8350 * 0.10 + (33950 - 8350) * 0.15 + (82250 - 33950)
                        * 0.25 + (171550 - 82250) * 0.28 + (372950 - 171550)
                        * 0.33 + (income - 372950) * 0.35;
            }
        } else if (status == 1) { // Compute tax for married file jointly
            if (income <= 16700) {
                tax = income * 0.10;
            } else if (income <= 67900) {
                tax = 16700 * 0.10 + (income - 16700) * 0.15;
            } else if (income <= 137050) {
                tax = 16700 * 0.10 + (67900 - 16700) * 0.15 + (income - 67900)
                        * 0.25;
            } else if (income <= 208850) {
                tax = 16700 * 0.10 + (67900 - 16700) * 0.15 + (82250 - 67900)
                        * 0.25 + (income - 137050) * 0.28;
            } else if (income <= 372950) {
                tax = 16700 * 0.10 + (67900 - 16700) * 0.15 + (82250 - 67900)
                        * 0.25 + (208850 - 137050) * 0.28 + (income - 208850)
                        * 0.33;
            } else {
                tax = 16700 * 0.10 + (67900 - 16700) * 0.15 + (82250 - 67900)
                        * 0.25 + (208850 - 137050) * 0.28 + (372950 - 208850)
                        * 0.33 + (income - 372950) * 0.35;
            }
        } else if (status == 2) { // Compute tax for married separately
            if (income <= 8350) {
                tax = income * 0.10;
            } else if (income <= 33950) {
                tax = 8350 * 0.10 + (income - 8350) * 0.15;
            } else if (income <= 68525) {
                tax = 8350 * 0.10 + (33950 - 8350) * 0.15 + (income - 33950)
                        * 0.25;
            } else if (income <= 104425) {
                tax = 8350 * 0.10 + (33950 - 8350) * 0.15 + (68525 - 33950)
                        * 0.25 + (income - 68525) * 0.28;
            } else if (income <= 186475) {
                tax = 8350 * 0.10 + (33950 - 8350) * 0.15 + (68525 - 33950)
                        * 0.25 + (104425 - 68525) * 0.28 + (income - 104425)
                        * 0.33;
            } else {
                tax = 8350 * 0.10 + (33950 - 8350) * 0.15 + (68525 - 33950)
                        * 0.25 + (104425 - 68525) * 0.28 + (186475 - 104425)
                        * 0.33 + (income - 186475) * 0.35;
            }
        } else if (status == 3) { // Compute tax for head of household
            if (income <= 11950) {
                tax = income * 0.10;
            } else if (income <= 45500) {
                tax = 11950 * 0.10 + (income - 11950) * 0.15;
            } else if (income <= 117450) {
                tax = 11950 * 0.10 + (45500 - 11950) * 0.15 + (income - 45500)
                        * 0.25;
            } else if (income <= 190200) {
                tax = 11950 * 0.10 + (45500 - 11950) * 0.15 + (117450 - 45500)
                        * 0.25 + (income - 117450) * 0.28;
            } else if (income <= 372950) {
                tax = 11950 * 0.10 + (45500 - 11950) * 0.15 + (117450 - 45500)
                        * 0.25 + (190200 - 117450) * 0.28 + (income - 190200)
                        * 0.33;
            } else {
                tax = 11950 * 0.10 + (45500 - 11950) * 0.15 + (117450 - 45500)
                        * 0.25 + (190200 - 117450) * 0.28 + (372950 - 190200)
                        * 0.33 + (income - 372950) * 0.35;
            }
        } else {
            throw new IllegalArgumentException("Invalid status");
        }
        return tax;
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