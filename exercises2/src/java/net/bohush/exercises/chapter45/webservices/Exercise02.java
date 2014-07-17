package net.bohush.exercises.chapter45.webservices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(name = "Exercise02", serviceName = "Exercise45_02")
public class Exercise02 {
    private HashMap<String, Double> scores;
    private HashMap<String, Boolean> permissions;

    public Exercise02() {
        scores = new HashMap<>();
        permissions = new HashMap<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javabook", "scott", "tiger");
            Statement statement = connection.createStatement();
            ResultSet rset = statement.executeQuery("select * from Scores;");
            while(rset.next()) {
                scores.put(rset.getString(1), rset.getDouble(2));
                permissions.put(rset.getString(1), rset.getBoolean(3));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(net.bohush.exercises.chapter45.webservices.Exercise01.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @WebMethod(operationName = "findScore")
    public double findScore(String name) {
        if (getPermission(name) != -1) {
            return scores.get(name);
        } else {
            return -1;
        }

    }
    
    @WebMethod(operationName = "getPermission")
    public int getPermission(String name) {
        Boolean b = permissions.get(name);
        if (b == null) {
            return -1;
        } else if (b.equals(Boolean.TRUE)){
            return 1;
        } else {
            return 0;
        }       
    }


}