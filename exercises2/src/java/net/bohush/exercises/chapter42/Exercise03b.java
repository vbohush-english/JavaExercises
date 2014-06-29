package net.bohush.exercises.chapter42;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Exercise03b extends HttpServlet {

    private int count = 0;
    private RandomAccessFile input;

    @Override
    public void init() throws ServletException {
        try {
            input = new RandomAccessFile("Exercise42_03.dat", "rw");
            input.seek(0);
            count = input.readInt();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Exercise03b.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Exercise03b.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Exercise42_03b</title>");            
            out.println("</head>");
            out.println("You are visitor number: " + count + "<br>");
            out.println("Host name: " + request.getLocalName()  + "<br>");
            out.println("IP address: " + request.getLocalAddr() + "<br>");
            out.println("<body></body>");
            out.println("</html>");
            count++;
            input.seek(0);
            input.writeInt(count);
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