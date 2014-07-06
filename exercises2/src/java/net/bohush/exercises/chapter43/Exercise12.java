package net.bohush.exercises.chapter43;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Exercise12 {
    private Statement statement;
    private String answer;
    private int yesCount;
    private int noCount;
    
    public Exercise12() {
        initializeJdbc();
        int yes = 0;
        int no = 0;
        try {
            ResultSet rset = statement.executeQuery("select * from Poll limit 1;");
            if(rset.next()) {
                yes = Integer.parseInt(rset.getString(2));
                no = Integer.parseInt(rset.getString(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exercise12.class.getName()).log(Level.SEVERE, null, ex);
        }
        yesCount = yes;
        noCount = no;
    }

    public String getAnswer() {
        return answer;
    }

    public int getYesCount() {
        return yesCount;
    }

    public int getNoCount() {
        return noCount;
    }

    public void setAnswer(String answer) {
        if(answer != null) {
            if(answer.equals("Yes")) {
                yesCount++;
            } else if(answer.equals("No")) {
                noCount++;
            }
            try {
                statement.executeUpdate("update Poll set yesCount = " + yesCount + ", noCount = " + noCount);
            } catch (SQLException ex) {
                Logger.getLogger(Exercise12.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.answer = answer;

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
