package net.bohush.exercises.chapter43;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Exercise06 {
    private Statement statement;
    
    public Exercise06() {
        initializeJdbc();
    }
    
    public Statement getStatement() {
        return statement;
    }

    private void initializeJdbc() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javabook", "scott", "tiger");
            statement = connection.createStatement();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Exercise06.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
