package net.bohush.exercises.chapter43;

import java.math.BigInteger;

public class Exercise18 {

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
