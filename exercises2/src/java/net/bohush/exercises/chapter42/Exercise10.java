package net.bohush.exercises.chapter42;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Exercise10 extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Cookie cookie1 = new Cookie("color", "red");
        Cookie cookie2 = new Cookie("radius", "5.5");
        Cookie cookie3 = new Cookie("count", "2");
        int cookieTime = 60 * 60 * 24;
        cookie1.setMaxAge(cookieTime);
        cookie2.setMaxAge(cookieTime);
        cookie3.setMaxAge(cookieTime);
        response.addCookie(cookie1);
        response.addCookie(cookie2);
        response.addCookie(cookie3);

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Exercise42_10</title>");            
            out.println("</head><body>");
            out.println("</body>");
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