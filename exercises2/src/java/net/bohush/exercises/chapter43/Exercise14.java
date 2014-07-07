package net.bohush.exercises.chapter43;

public class Exercise14 {
    private int size = 10;
    private int[] numbers1 = new int[size];
    private int[] numbers2 = new int[size];
    private int correctCount = 0;
    
    public Exercise14() {

    }
    
    public void renew() {
        for (int i = 0; i < size; i++) {
            numbers1[i] = (int)(Math.random() * 20);
            numbers2[i] = (int)(Math.random() * 20);
        }        
        correctCount = 0;
    }
    
    public String check(String value, int index) {
        try {
            if(Integer.parseInt(value) == (numbers1[index] + numbers2[index])) {
                correctCount++;
                return "Correct";
            } else {
                return "Wrong";
            }            
        } catch (NumberFormatException e) {
            return "Wrong";
        }
    }

    public int[] getNumbers1() {
        return numbers1;
    }

    public int[] getNumbers2() {
        return numbers2;
    }

    public int getCorrectCount() {
        return correctCount;
    }
    
    public void resetCorrectCount() {
        correctCount = 0;
    }
    
    public int getSize() {
        return size;
    }
    
}
