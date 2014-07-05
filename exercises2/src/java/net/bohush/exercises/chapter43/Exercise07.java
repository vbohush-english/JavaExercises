package net.bohush.exercises.chapter43;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

public class Exercise07 {
    private Statement statement;
    
    public Exercise07() {
        initializeJdbc();
    }
    
    public String changePassword(HttpServletRequest request) {
        String name = request.getParameter("name");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword1 = request.getParameter("newPassword1");
        String newPassword2 = request.getParameter("newPassword2");
        if ((name == null) || (name.equals(""))) {
            return "<br><br>Enter <b>name</b><br><br>" + 
            "<FORM><INPUT Type=\"button\" VALUE=\"Back\" onClick=\"history.go(-1);return true;\"></FORM>";
        } else if ((oldPassword == null) || (oldPassword.equals(""))) {
            return "<br><br>Enter <b>old password</b><br><br>" + 
            "<FORM><INPUT Type=\"button\" VALUE=\"Back\" onClick=\"history.go(-1);return true;\"></FORM>";
        } else if ((newPassword1 == null) || (newPassword1.equals(""))) {
            return "<br><br>Enter <b>new password</b><br><br>" + 
            "<FORM><INPUT Type=\"button\" VALUE=\"Back\" onClick=\"history.go(-1);return true;\"></FORM>";
        } else if ((newPassword2 == null) || (newPassword2.equals(""))) {
            return "<br><br>Enter <b>confirm new password</b><br><br>" + 
            "<FORM><INPUT Type=\"button\" VALUE=\"Back\" onClick=\"history.go(-1);return true;\"></FORM>";
        } else if (!newPassword1.equals(newPassword2)) {
            return "<br><br><b>new password</b> and <b>confirm new password</b> are not equal<br><br>" + 
            "<FORM><INPUT Type=\"button\" VALUE=\"Back\" onClick=\"history.go(-1);return true;\"></FORM>";
        } else {
            try {
                ResultSet rset = statement.executeQuery("select password from Account where username = \"" + name + "\";");
                if (rset.next()) {
                    if (rset.getString("password").equals(oldPassword)) {
                        statement.execute("update Account set password = '" + newPassword1 + "' where username = '" + name + "';");
                        return "<br><br><b>Password changed</b><br><br>";
                    } else {
                        return "<br><br>wrong password<br><br>" + 
                        "<FORM><INPUT Type=\"button\" VALUE=\"Back\" onClick=\"history.go(-1);return true;\"></FORM>";
                    }
                } else {
                    return "<br><br>user <b>" + name + "</b> not found<br><br>" + 
                    "<FORM><INPUT Type=\"button\" VALUE=\"Back\" onClick=\"history.go(-1);return true;\"></FORM>";
                }
            } catch (SQLException ex) {
                return "Error while execuring query";
            }
        }
    }

    private void initializeJdbc() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javabook", "scott", "tiger");
            statement = connection.createStatement();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Exercise07.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
