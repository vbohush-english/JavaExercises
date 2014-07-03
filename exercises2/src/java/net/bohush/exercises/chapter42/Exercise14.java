package net.bohush.exercises.chapter42;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Exercise14 extends HttpServlet {
    private Statement statement;
    private Connection connection;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Exercise42_14</title>");            
            out.println("</head><body><center><form action = \"Exercise42_14\" method = \"post\">");
            out.println("Are you a CS major? <input type=\"radio\" name=\"answer\" value=\"Yes\"> Yes");
            out.println("<input type=\"radio\" name=\"answer\" value=\"No\"> No<br><br>");
            out.println("<input type = \"submit\" value = \"Submit\" /> <input type = \"reset\" value = \"Reset\" />");
            out.println("</form></center></body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int yes = 0;
        int no = 0;
        try {
            ResultSet rset = statement.executeQuery("select * from Poll limit 1;");
            if(rset.next()) {
                yes = Integer.parseInt(rset.getString(2));
                no = Integer.parseInt(rset.getString(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exercise14.class.getName()).log(Level.SEVERE, null, ex);
        }
        String answer = request.getParameter("answer");
        if(answer != null) {
            if(answer.equals("Yes")) {
                yes++;
            } else if(answer.equals("No")) {
                no++;
            }
        }
        try {
            statement.executeUpdate("update Poll set yesCount = " + yes + ", noCount = " + no);
        } catch (SQLException ex) {
            Logger.getLogger(Exercise14.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Exercise42_14</title>");            
            out.println("</head><body><center><br>");
            out.println("The current Yes count is " + yes + "<br>");
            out.println("The current No count is " + no + "<br>");
            out.println("</center></body>");
            out.println("</html>");
        }
    }
    
    @Override
    public void init() throws ServletException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/javabook", "scott", "tiger");
            statement = connection.createStatement();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Exercise14.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}