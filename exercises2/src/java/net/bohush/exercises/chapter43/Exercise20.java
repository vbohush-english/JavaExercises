package net.bohush.exercises.chapter43;

public class Exercise20 {
    int number;
    int nextNumber;

    public int getNextNumber() {
        return nextNumber;
    }

    public void setNextNumber(int nextNumber) {
        this.nextNumber = nextNumber;
    }

    public Exercise20() {
        number = 1 + (int)(Math.random() * 1000);
    }
    
    public String getResult() {
        String result = nextNumber + " is ";
        if (number < nextNumber) {
            result += "too big";
        } else if (number > nextNumber) {
            result += "too low";
        } else {
            result += "correct";
        }
        return result;
    }
    
    public void restart() {
        number = 1 + (int)(Math.random() * 1000);
        nextNumber = 0;
    }
}
