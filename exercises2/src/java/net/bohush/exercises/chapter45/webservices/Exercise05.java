package net.bohush.exercises.chapter45.webservices;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;
import java.util.ArrayList;
import com.sun.xml.ws.developer.servlet.HttpSessionScope;

@HttpSessionScope
@WebService(name = "Exercise05", serviceName = "Exercise45_05")
public class Exercise05 {

    private ArrayList<Object[]> quiz = new ArrayList<Object[]>();

    public Exercise05() {
        quiz.add(new Object[]{ "Is Atlanta the capital of Georgia?", 1});
        quiz.add(new Object[]{ "Is Columbia the capital of South Carolina?", 1});
        quiz.add(new Object[]{ "Is Fort Wayne the capital of Indiana?", 0});
        quiz.add(new Object[]{ "Is New Orleans the capital of Louisiana?", 0});
        quiz.add(new Object[]{ "Is Chicago the capital of Illinois?", 0});
        java.util.Collections.shuffle(quiz);
    }

    @WebMethod(operationName = "getQuestions")
    public java.util.List<String> getQuestions() {
        List<String> questions = new ArrayList<String>();
        for (int i = 0; i < quiz.size(); i++) {
            questions.add((String) (quiz.get(i)[0]));
        }
        return questions;
    }

    @WebMethod(operationName = "gradeQuiz")
    public List<Integer> gradeQuiz(List<Integer> answers) {
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < quiz.size(); i++) {
            if(answers.get(i).equals(-1)) {
                result.add(-1);    
            } else if(quiz.get(i)[1].equals(answers.get(i))) {
                result.add(1);    
            } else {
                result.add(0);
            }
            
        }
        return result;
    }
}
