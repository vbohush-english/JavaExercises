package net.bohush.exercises.chapter42;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Exercise03c extends HttpServlet {

    private HashMap<String, Integer> addresses;

    @Override
    public void init() throws ServletException {
        addresses = new HashMap<>();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        if(addresses.get(request.getLocalAddr()) == null) {
            addresses.put(request.getLocalAddr(), 0);
        }
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Exercise42_03c</title>");            
            out.println("</head>");
            out.println("You are visitor number: " + addresses.get(request.getLocalAddr()) + "<br>");
            out.println("Host name: " + request.getLocalName()  + "<br>");
            out.println("IP address: " + request.getLocalAddr() + "<br>");
            out.println("<body></body>");
            out.println("</html>");
            addresses.put(request.getLocalAddr(), addresses.get(request.getLocalAddr()) + 1);
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