package net.bohush.exercises.chapter42;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Exercise13 extends HttpServlet {
    private Statement statement;
    private Connection connection;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            try {
                ResultSet rset = statement.executeQuery("select * from Staff limit 1;");
                if(rset.next()) {
                     out.println(getForm(rset));
                } else {
                    out.println(getForm("Table is empty"));   
                }                
            } catch (SQLException ex) {
                out.println(getForm("Table is empty"));
            }
            
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String operation = request.getParameter("operation");
            String id = request.getParameter("id");
            String lastName = request.getParameter("lastName");
            String firstName = request.getParameter("firstName");
            String mi = request.getParameter("mi");
            String address = request.getParameter("address");
            String city = request.getParameter("city");
            String state = request.getParameter("state");
            String telephone = request.getParameter("telephone");
            
            if(operation.equals("View")) {
                if((id == null) || (id.equals(""))) {
                    out.println(getForm("Enter ID"));   
                } else {
                    try {
                        ResultSet rset = statement.executeQuery("select * from Staff where id = " + id + " limit 1;");
                        if(rset.next()) {
                             out.println(getForm(rset));
                        } else {
                            out.println(getForm(id, "", "", "", "", "", "", "", "ID " + id + " not found"));   
                        }                
                    } catch (SQLException ex) {
                        out.println(getForm(id, "", "", "", "", "", "", "", "ID " + id + " not found"));   
                    }
                }
            } else if (operation.equals("Insert")) {
                if((id == null) || (id.equals(""))) {
                    out.println(getForm(id, lastName, firstName, mi, address, city, state, telephone, "Enter ID"));   
                } else {
                    String queryString = "insert into Staff (id, lastName, firstName, mi, address, city, state, telephone) values (?, ?, ?, ?, ?, ?, ?, ?);";
                    try {
                        PreparedStatement preparedStatement = connection.prepareStatement(queryString);
                        preparedStatement.setString(1, id);
                        preparedStatement.setString(2, lastName);
                        preparedStatement.setString(3, firstName);
                        preparedStatement.setString(4, mi);
                        preparedStatement.setString(5, address);
                        preparedStatement.setString(6, city);
                        preparedStatement.setString(7, state);
                        preparedStatement.setString(8, telephone);
                        preparedStatement.executeUpdate();
                        out.println(getForm(id, lastName, firstName, mi, address, city, state, telephone, ""));   
                    } catch (SQLException e2) {
                        out.println(getForm(id, lastName, firstName, mi, address, city, state, telephone, e2.getMessage()));   
                    }
                }
            } else if (operation.equals("Update")) {
                if((id == null) || (id.equals(""))) {
                     out.println(getForm(id, "", "", "", "", "", "", "", "ID " + id + " not found"));
                } else {
                    String queryString = "update Staff set lastName = ?, firstName = ?, mi = ?, address = ?, city = ?, state = ?, telephone = ? where id = ?";
                    try {
                        PreparedStatement preparedStatement = connection.prepareStatement(queryString);
                        preparedStatement.setString(1, lastName);
                        preparedStatement.setString(2, firstName);
                        preparedStatement.setString(3, mi);
                        preparedStatement.setString(4, address);
                        preparedStatement.setString(5, city);
                        preparedStatement.setString(6, state);
                        preparedStatement.setString(7, telephone);
                        preparedStatement.setString(8, id);
                        preparedStatement.executeUpdate();
                        out.println(getForm(id, lastName, firstName, mi, address, city, state, telephone, ""));   
                    } catch (SQLException e2) {
                        out.println(getForm(id, lastName, firstName, mi, address, city, state, telephone, e2.getMessage()));   
                    }
                }
            } else if (operation.equals("Delete")) {
                if((id == null) || (id.equals(""))) {
                    out.println(getForm("", "", "", "", "", "", "", "", "Enter ID"));
                } else {
                    try {
                        statement.executeUpdate("delete from Staff where id = " + id + "");
                        try {
                            ResultSet rset = statement.executeQuery("select * from Staff limit 1;");
                            if (rset.next()) {
                                out.println(getForm(rset));
                            } else {
                                out.println(getForm("Table is empty"));
                            }
                        } catch (SQLException ex) {
                            out.println(getForm("Table is empty"));
                        }
                    } catch (SQLException ex) {
                        out.println(getForm("", "", "", "", "", "", "", "", ex.getMessage()));   
                    }
                }
            }
        }
    }
    
    @Override
    public void init() throws ServletException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/javabook", "scott", "tiger");
            statement = connection.createStatement();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Exercise07.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static String getForm(ResultSet rset) throws SQLException {
        return getForm(rset, "");
    }
    
    private static String getForm(ResultSet rset, String errorMessage) throws SQLException {
        return getForm(rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), 
                rset.getString(5), rset.getString(6), rset.getString(7), rset.getString(8), errorMessage);
    }

    private static String getForm() {
        return getForm("", "", "", "", "", "", "", "", "");
    }
        
    private static String getForm(String errorMessage) {
        return getForm("", "", "", "", "", "", "", "", errorMessage);
    }
        
    private static String getForm(String id, String lastName, String firstName, String mi, String address, String city, String state, String telephone, String errorMessage) {
        return "<!DOCTYPE html><html><head><title>Exercise42_13</title></head><body>" +
        "<form method = \"post\" action = \"Exercise42_13\">\n" +
        "<center><table><tr align=\"center\"><td height=\"50\"><p><font color = \"#FF0000\">" + errorMessage + "</font></p></td></tr>" + 
        "<tr align=\"left\"><td>\n" +
        "<fieldset><legend>Staff Information</legend>\n" +
        "<p>ID: <input type = \"text\" name = \"id\" style=\"background-color: yellow\" value = \"" + id + "\"></p>\n" +
        "<p>Last Name: <input type = \"text\" name = \"lastName\" value = \"" + lastName + "\">\n" +
        "First Name: <input type = \"text\" name = \"firstName\" value = \"" + firstName + "\">\n" +
        "MI: <input type = \"text\" name = \"mi\" size = \"3\" value = \"" + mi + "\"></p>\n" +
        "<p>Address: <input type = \"text\" name = \"address\" size = \"20\" value = \"" + address + "\"></p>\n" +
        "<p>City: <input type = \"text\" name = \"city\" size = \"20\" value = \"" + city + "\">\n" +
        "State: <input type = \"text\" name = \"state\" size = \"5\" value = \"" + state + "\"></p>\n" +
        "<p>Telephone: <input type = \"text\" name = \"telephone\" size = \"20\" value = \"" + telephone + "\"></p>\n" +
        "</fieldset></td></tr><tr align=\"center\"><td>\n" +
        "<p><input type = \"submit\" name = \"operation\" value = \"View\">\n" +
        "<input type = \"submit\" name = \"operation\" value = \"Insert\">\n" +
        "<input type = \"submit\" name = \"operation\" value = \"Update\">\n" +
        "<input type = \"submit\" name = \"operation\" value = \"Delete\"></p></td></tr></table></center></form></body></html>";
    }
}