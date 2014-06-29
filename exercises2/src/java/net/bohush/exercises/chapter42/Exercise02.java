package net.bohush.exercises.chapter42;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Exercise02 extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Exercise42_02</title>");            
            out.println("</head>");
            int tableSize = 9;
            out.println("<body><table align=\"center\"  border=\"1\">");
            out.println("<tr><td colspan=\"" + (tableSize + 1) + "\"  align=\"center\"  bgcolor=\"#cccccc\" height=\"30\"><b>Multiplication table</b></td></tr>");
            out.println("<tr><td  width=\"40\" bgcolor=\"#cccccc\"></td>");
            for (int i = 1; i <= tableSize; i++) {
                out.println("<td align=\"center\"  width=\"40\"  bgcolor=\"#cccccc\" >" + i + "</td>");
            }
            for (int i = 1; i <= tableSize; i++) {
                out.println("<tr><td align=\"center\"  bgcolor=\"#cccccc\" >" + i + "</td>");
                for (int j = 1; j <= tableSize; j++) {
                    out.println("<td align=\"center\" >" + (i * j) + "</td>");    
                }
                out.println("<tr>");
            }
            out.println("</table></body>");
            out.println("</html>");
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