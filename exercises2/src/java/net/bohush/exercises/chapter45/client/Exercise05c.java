package net.bohush.exercises.chapter45.client;

import java.util.List;
import javax.xml.ws.BindingProvider;
import net.bohush.exercises.chapter45.exercise05.Exercise05;
import net.bohush.exercises.chapter45.exercise05.Exercise4505;

public class Exercise05c {
    
    private Exercise4505 exercise4502 = new Exercise4505();
    private Exercise05 proxy = exercise4502.getExercise05Port();

    public Exercise05c() {
        ((BindingProvider)proxy).getRequestContext().put(BindingProvider.SESSION_MAINTAIN_PROPERTY,true);
    }

    public java.util.List<String> getQuestions() {
        return proxy.getQuestions();
    }

    public List<Integer> gradeQuiz(List<Integer> answers) {
        return proxy.gradeQuiz(answers);
    }
}
