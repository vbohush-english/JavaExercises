package net.bohush.exercises.chapter44;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class Exercise01 {
    
    public String getTable() {
        String result = "<table border=\"1\"><tr><td>Number</td><td>Factorial</td></tr>";
        for (int i = 0; i <= 10; i++) {
            result += "<tr><td>" + i + "</td><td>" + computeFactorial(i) + "</td></tr>";
        }
        result += "</table>";
        return result;
    }
    
    private long computeFactorial(int n) {
        if (n == 0) {
            return 1;
        } else {
            return n * computeFactorial(n - 1);
        }
    }
}
