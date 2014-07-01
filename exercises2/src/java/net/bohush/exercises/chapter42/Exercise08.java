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

public class Exercise08 extends HttpServlet {

    private Statement statement;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String name = request.getParameter("name");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword1 = request.getParameter("newPassword1");
        String newPassword2 = request.getParameter("newPassword2");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Exercise42_08</title>");            
            out.println("</head><center>");
            if((name == null) || (name.equals(""))) {
                out.println("<br><br>Enter <b>name</b><br><br>");
                out.println("<FORM><INPUT Type=\"button\" VALUE=\"Back\" onClick=\"history.go(-1);return true;\"></FORM>");
            } else if((oldPassword == null) || (oldPassword.equals(""))) {
                out.println("<br><br>Enter <b>old password</b><br><br>");
                out.println("<FORM><INPUT Type=\"button\" VALUE=\"Back\" onClick=\"history.go(-1);return true;\"></FORM>");
            } else if((newPassword1 == null) || (newPassword1.equals(""))) {
                out.println("<br><br>Enter <b>new password</b><br><br>");
                out.println("<FORM><INPUT Type=\"button\" VALUE=\"Back\" onClick=\"history.go(-1);return true;\"></FORM>");
            } else if((newPassword2 == null) || (newPassword2.equals(""))) {
                out.println("<br><br>Enter <b>confirm new password</b><br><br>");
                out.println("<FORM><INPUT Type=\"button\" VALUE=\"Back\" onClick=\"history.go(-1);return true;\"></FORM>");
            } else if(!newPassword1.equals(newPassword2)) {
                out.println("<br><br><b>new password</b> and <b>confirm new password</b> are not equal<br><br>");
                out.println("<FORM><INPUT Type=\"button\" VALUE=\"Back\" onClick=\"history.go(-1);return true;\"></FORM>");
            } else {

                try {
                    ResultSet rset = statement.executeQuery("select password from Account where username = \"" + name + "\";");
                    if (rset.next()) {
                        if(rset.getString("password").equals(oldPassword)) {
                            statement.execute("update Account set password = '" + newPassword1 + "' where username = '" + name + "';");
                            out.println("<br><br><b>Password changed</b><br><br>");
                        } else {
                            out.println("<br><br>wrong password<br><br>");
                            out.println("<FORM><INPUT Type=\"button\" VALUE=\"Back\" onClick=\"history.go(-1);return true;\"></FORM>");
                        }
                    } else {
                        out.println("<br><br>user <b>" + name + "</b> not found<br><br>");
                        out.println("<FORM><INPUT Type=\"button\" VALUE=\"Back\" onClick=\"history.go(-1);return true;\"></FORM>");
                    }
                } catch (SQLException ex) {
                    out.println("Error while execuring query");
                }
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