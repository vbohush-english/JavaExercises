package net.bohush.exercises.chapter42;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Exercise09 extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getParameter("url");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String table = request.getParameter("table");
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Exercise42_09</title>");            
            out.println("</head><body>");

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();
                ResultSet rset = statement.executeQuery("select * from " + table + ";");

                out.println("<table align=\"center\"  border=\"1\">");
                ResultSetMetaData rsMetaData = rset.getMetaData();
                out.println("<tr><td colspan=\"" + rsMetaData.getColumnCount() + "\"  align=\"center\"  bgcolor=\"#cccccc\" height=\"30\"><b>" + table + "</b></td></tr>");
                
                out.println("<tr>");
                for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
                    out.println("<td align=\"center\"  bgcolor=\"#cccccc\" >" + rsMetaData.getColumnName(i) + "</td>");
                }
                out.println("</tr>");
                while (rset.next()) {
                    out.println("<tr>");
                    for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
                        out.println("<td align=\"center\">" + (rset.getString(i) == null ? "" : rset.getString(i)) + "</td>");
                    }
                    out.println("</tr>");
                }
                
                out.println("</table>");
            } catch (ClassNotFoundException | SQLException ex) {
                out.println(ex.getMessage());
            }
            
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