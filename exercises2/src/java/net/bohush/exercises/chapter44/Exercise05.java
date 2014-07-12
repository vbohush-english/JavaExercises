package net.bohush.exercises.chapter44;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped

public class Exercise05 {
    private int size = 10;
    private List<Numbers> numbers  = new ArrayList<Numbers>();
    private int correctCount = 0;
    
    public Exercise05() {
        renew();
    }    
    
    
    public void renew() {
        numbers.clear();
        for (int i = 0; i < size; i++) {
            numbers.add(new Numbers());
        }        
        correctCount = 0;
    }
    
    public List<Numbers> getNumbers() {
        return numbers;
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
    
    public class Numbers {
        private int number1;
        private int number2;
        private int result;
        private String guess = "";

        public Numbers() {
            number1 = (int)(Math.random() * 20);
            number2 = (int)(Math.random() * 20);
            result = number1 + number2;
        }
        
        public Numbers(int number1, int number2) {
            this.number1 = number1;
            this.number2 = number2;
            result = number1 + number2;
        }
        
        public int getNumber1() {
            return number1;
        }

        public int getNumber2() {
            return number2;
        }

        public int getResult() {
            return result;
        }

        public String getGuess() {
            return guess;
        }
        
        

        public void setNumber1(int number1) {
            this.number1 = number1;
            result = number1 + number2;
        }

        public void setNumber2(int number2) {
            this.number2 = number2;
            result = number1 + number2;
        }

        public void setGuess(String guess) {
            this.guess = guess;
        }
        
        public String check() {
            try {
                if(Integer.parseInt(guess) == result) {
                    correctCount++;
                    return "Correct";
                } else {
                    return "Wrong";
                }            
            } catch (NumberFormatException e) {
                return "Wrong";
            }
        }
    
    }
    
}
