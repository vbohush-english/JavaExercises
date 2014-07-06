package net.bohush.exercises.chapter43;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;


public class Exercise13 {
    private Statement statement;
    private ArrayList<Answer> answers = new ArrayList<>();
    
    public Exercise13() {
        initializeJdbc();
        try {
            ResultSet rset = statement.executeQuery("select * from Poll;");
            while(rset.next()) {
                String question = rset.getString(1);
                int yesCount = Integer.parseInt(rset.getString(2));
                int noCount = Integer.parseInt(rset.getString(3));
                answers.add(new Answer(question, yesCount, noCount));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exercise13.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }
    
    public void parseAnswers(HttpServletRequest request) {
        for (int i = 0; i < answers.size(); i++) {
            String answer = request.getParameter("answer" + i);
            if(answer != null) {
                if(answer.equals("Yes")) {
                    answers.get(i).updateYesCount();
                } else if(answer.equals("No")) {
                    answers.get(i).updateNoCount();
                }
            }
        }
    
    }
 
    private void initializeJdbc() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javabook", "scott", "tiger");
            statement = connection.createStatement();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Exercise13.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public class Answer {
        private String question;
        private int yesCount;
        private int noCount;

        public Answer(String question, int yesCount, int noCount) {
            this.question = question;
            this.yesCount = yesCount;
            this.noCount = noCount;
        }

        public void updateYesCount() {
            yesCount++;
            updateTable();
        }
        
        public void updateNoCount() {
            noCount++;
            updateTable();
        }
        
        private void updateTable() {
            try {
               statement.executeUpdate("update Poll set yesCount = " + yesCount + ", noCount = " + noCount + " where question = \"" + question + "\"");
            } catch (SQLException ex) {
               Logger.getLogger(Exercise13.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public void setQuestion(String question) {
            this.question = question;
        }

        public void setYesCount(int yesCount) {
            this.yesCount = yesCount;
        }

        public void setNoCount(int noCount) {
            this.noCount = noCount;
        }

        public String getQuestion() {
            return question;
        }

        public int getYesCount() {
            return yesCount;
        }

        public int getNoCount() {
            return noCount;
        }
        
    }
}
