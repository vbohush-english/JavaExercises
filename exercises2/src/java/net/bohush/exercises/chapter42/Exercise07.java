package net.bohush.exercises.chapter42;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Exercise07 extends HttpServlet {

    private Statement statement;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String ssn = request.getParameter("ssn");
        String course = request.getParameter("course");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Exercise42_07</title>");            
            out.println("</head><center>");

            try {
                ResultSet rset = statement.executeQuery("select * from " + course + " where ssn = \"" + ssn + "\";");
                if (rset.next()) {
                    out.println(rset.getString("name") + " " + rset.getString("score"));    
                } else {
                    out.println("SSN <b>" + ssn + "</b> not found in course <b>" + course);                           
                }
            } catch (SQLException ex) {
                out.println("Error while execuring query");
            }

            out.println("</center><body></body>");
            out.println("</html>");
        }
    }

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javabook", "scott", "tiger");
            statement = connection.createStatement();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Exercise07.class.getName()).log(Level.SEVERE, null, ex);
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