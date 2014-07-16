package net.bohush.exercises.chapter45.webservices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;

@WebService(name = "Exercise01", serviceName = "Exercise45_01")
public class Exercise01 {
    private HashMap<String, Double> scores;

    public Exercise01() {
        this.scores = new HashMap<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javabook", "scott", "tiger");
            Statement statement = connection.createStatement();
            ResultSet rset = statement.executeQuery("select * from Scores;");
            while(rset.next()) {
                scores.put(rset.getString(1), rset.getDouble(2));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(net.bohush.exercises.chapter45.webservices.Exercise01.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @WebMethod(operationName = "findScore")
    public double findScore(String name) {
        Double d = scores.get(name);
        if (d == null) {
            System.out.println("Student " + name + " is not found ");
            return -1;
        } else {
            System.out.println("Student " + name + "\'s score is " + d);
            return d;
        }

    }


}