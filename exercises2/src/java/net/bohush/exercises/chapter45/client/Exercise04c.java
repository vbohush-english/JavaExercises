package net.bohush.exercises.chapter45.client;

import javax.xml.ws.BindingProvider;
import net.bohush.exercises.chapter45.exercise04.Exercise04;
import net.bohush.exercises.chapter45.exercise04.Exercise4504;

public class Exercise04c {
    
    private Exercise4504 exercise4502 = new Exercise4504();
    private Exercise04 proxy = exercise4502.getExercise04Port();

    public Exercise04c() {
        ((BindingProvider)proxy).getRequestContext().put(BindingProvider.SESSION_MAINTAIN_PROPERTY,true);
    }

    public int getCount() {
        return proxy.getCount();
    }
    
}
