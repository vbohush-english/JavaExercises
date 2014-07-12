package net.bohush.exercises.chapter44;

import java.math.BigInteger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class Exercise06 {
    

    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int newValue) {
        number = newValue;
    }

    public String getFactorial() {
        BigInteger factorial = BigInteger.ONE;
        for (int i = 1; i <= number; i++) {
            factorial = factorial.multiply(new BigInteger(i + ""));
        }
        return factorial.toString();
    }
}
