package net.bohush.exercises.chapter44;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Exercise12 {
    private int[] numbers = new int[4];
    
    public Exercise12() {
        numbers[0] = 1;
        numbers[1] = 2;
        numbers[2] = 3;
        numbers[3] = 4;
    }
    
    
    public String getSolution() {
        Arrays.sort(numbers);
        String NumbersAsString = "";
        for (int i = 0; i < numbers.length; i++) {
                NumbersAsString += (char)(int)(numbers[i]);
        }
        String solution = displayPermutation(NumbersAsString);
        if(solution == null) {
                return "No solution";
        } else {
                return solution + " is 24";
        }  
    }
    
    public void setNumber0(int newNumner) {
        numbers[0] = newNumner;
    }
    
    public void setNumber1(int newNumner) {
        numbers[1] = newNumner;
    }
    
    public void setNumber2(int newNumner) {
        numbers[2] = newNumner;
    }
    
    public void setNumber3(int newNumner) {
        numbers[3] = newNumner;
    }
    
    public int getNumber0() {
        return numbers[0];
    }  
    
    public int getNumber1() {
        return numbers[1];
    }

    public int getNumber2() {
        return numbers[2];
    }
    
    public int getNumber3() {
        return numbers[3];
    }
    
    
    public String displayPermutation(String s) {
        return displayPermutation("", s);
    }

    public String displayPermutation(String s1, String s2) {
        if (s2.length() == 2) {
            String result = checkValue(s1 + s2.charAt(0) + s2.charAt(1));
            if (result != null) {
                return result;
            }
            result = checkValue(s1 + s2.charAt(1) + s2.charAt(0));
            if (result != null) {
                return result;
            }

        } else {
            for (int i = 0; i < s2.length(); i++) {
                String newS2 = "";
                for (int j = 0; j < s2.length(); j++) {
                    if (j != i) {
                        newS2 += s2.charAt(j);
                    }
                }
                String result = displayPermutation(s1 + s2.charAt(i), newS2);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    public String checkValue(String value) {
        for (int i = 0; i < 4*4*4; i++) {
            int op1 = i % 4;
            int op2 = (i / 4) % 4;
            int op3 = (i / 16) % 4;
            String tmp = " " + (int)value.charAt(0) + " " + getValur(op3) + " " + (int)value.charAt(1) + " " +  getValur(op2) + " " + (int)value.charAt(2) + " " + getValur(op1) + " " + (int)value.charAt(3) + " ";
            if ((evaluateExpression(tmp) < 24.000001) && (evaluateExpression(tmp) > 23.999999)) {
                    return tmp;
            }

            tmp = "(" + (int)value.charAt(0) + " " + getValur(op3) + " " + (int)value.charAt(1) + ")" +  getValur(op2) + " " + (int)value.charAt(2) + " " + getValur(op1) + " " + (int)value.charAt(3) + " ";
            if ((evaluateExpression(tmp) < 24.000001) && (evaluateExpression(tmp) > 23.999999)) {
                    return tmp;
            }
            tmp = " " + (int)value.charAt(0) + " " + getValur(op3) + "(" + (int)value.charAt(1) + " " +  getValur(op2) + " " + (int)value.charAt(2) + ")" + getValur(op1) + " " + (int)value.charAt(3) + " ";
            if ((evaluateExpression(tmp) < 24.000001) && (evaluateExpression(tmp) > 23.999999)) {
                    return tmp;
            }
            tmp = " " + (int)value.charAt(0) + " " + getValur(op3) + " " + (int)value.charAt(1) + " " +  getValur(op2) + "(" + (int)value.charAt(2) + " " + getValur(op1) + " " + (int)value.charAt(3) + ")";
            if ((evaluateExpression(tmp) < 24.000001) && (evaluateExpression(tmp) > 23.999999)) {
                    return tmp;
            }
            tmp = "(" + (int)value.charAt(0) + " " + getValur(op3) + " " + (int)value.charAt(1) + ")" +  getValur(op2) + "(" + (int)value.charAt(2) + " " + getValur(op1) + " " + (int)value.charAt(3) + ")";
            if ((evaluateExpression(tmp) < 24.000001) && (evaluateExpression(tmp) > 23.999999)) {
                    return tmp;
            }

            tmp = "(" + (int)value.charAt(0) + " " + getValur(op3) + " " + (int)value.charAt(1) + " " +  getValur(op2) + " " + (int)value.charAt(2) + ")" + getValur(op1) + " " + (int)value.charAt(3) + " ";
            if ((evaluateExpression(tmp) < 24.000001) && (evaluateExpression(tmp) > 23.999999)) {
                    return tmp;
            }
            tmp = " " + (int)value.charAt(0) + " " + getValur(op3) + "(" + (int)value.charAt(1) + " " +  getValur(op2) + " " + (int)value.charAt(2) + " " + getValur(op1) + " " + (int)value.charAt(3) + ")";
            if ((evaluateExpression(tmp) < 24.000001) && (evaluateExpression(tmp) > 23.999999)) {
                    return tmp;
            }


            tmp = "((" + (int)value.charAt(0) + " " + getValur(op3) + " " + (int)value.charAt(1) + ")" +  getValur(op2) + " " + (int)value.charAt(2) + ")" + getValur(op1) + " " + (int)value.charAt(3) + " ";
            if ((evaluateExpression(tmp) < 24.000001) && (evaluateExpression(tmp) > 23.999999)) {
                    return tmp;
            }
            tmp = "(" + (int)value.charAt(0) + " " + getValur(op3) + "(" + (int)value.charAt(1) + " " +  getValur(op2) + " " + (int)value.charAt(2) + "))" + getValur(op1) + " " + (int)value.charAt(3) + " ";
            if ((evaluateExpression(tmp) < 24.000001) && (evaluateExpression(tmp) > 23.999999)) {
                    return tmp;
            }
            tmp = " " + (int)value.charAt(0) + " " + getValur(op3) + "((" + (int)value.charAt(1) + " " +  getValur(op2) + " " + (int)value.charAt(2) + ")" + getValur(op1) + " " + (int)value.charAt(3) + ")";
            if ((evaluateExpression(tmp) < 24.000001) && (evaluateExpression(tmp) > 23.999999)) {
                    return tmp;
            }
            tmp = " " + (int)value.charAt(0) + " " + getValur(op3) + "(" + (int)value.charAt(1) + " " +  getValur(op2) + "(" + (int)value.charAt(2) + " " + getValur(op1) + " " + (int)value.charAt(3) + "))";
            if ((evaluateExpression(tmp) < 24.000001) && (evaluateExpression(tmp) > 23.999999)) {
                    return tmp;
            }
        }
        return null;
    }    
    
    public char getValur(int intValue) {
        switch (intValue) {
            case 0:
                return '+';
            case 1:
                return '-';
            case 2:
                return '*';
            default:
                return '/';
        }
    }    
    
    public static double evaluateExpression(String expression) {
        double result = 0;
        try {
            result = evaluateExpression(expression, null);
        } catch (Exception ex) {
        }
        return result;
    }
    
    public static double evaluateExpression(String expression, ArrayList<Integer> verifyNumbers) {
        if (expression.length() == 0) {
            return 0;
        }
        // Create operandStack to store operands
        Stack<Double> operandStack = new Stack<Double>();

        // Create operatorStack to store operators
        Stack<Character> operatorStack = new Stack<Character>();

        // Insert blanks around (, ), +, -, /, and *
        expression = insertBlanks(expression);

        // Extract operands and operators
        String[] tokens = expression.split(" ");

        // Phase 1: Scan tokens
        for (String token : tokens) {
            if (token.length() == 0) // Blank space
            {
                continue; // Back to the while loop to extract the next token
            } else if (token.charAt(0) == '+' || token.charAt(0) == '-') {
                // Process all +, -, *, / in the top of the operator stack
                while (!operatorStack.isEmpty()
                        && (operatorStack.peek() == '+'
                        || operatorStack.peek() == '-'
                        || operatorStack.peek() == '*' || operatorStack
                        .peek() == '/')) {
                    processAnOperator(operandStack, operatorStack);
                }

                // Push the + or - operator into the operator stack
                operatorStack.push(token.charAt(0));
            } else if (token.charAt(0) == '*' || token.charAt(0) == '/') {
                // Process all *, / in the top of the operator stack
                while (!operatorStack.isEmpty()
                        && (operatorStack.peek() == '*' || operatorStack.peek() == '/')) {
                    processAnOperator(operandStack, operatorStack);
                }

                // Push the * or / operator into the operator stack
                operatorStack.push(token.charAt(0));
            } else if (token.trim().charAt(0) == '(') {
                operatorStack.push('('); // Push '(' to stack
            } else if (token.trim().charAt(0) == ')') {
                // Process all the operators in the stack until seeing '('
                while (operatorStack.peek() != '(') {
                    processAnOperator(operandStack, operatorStack);
                }

                operatorStack.pop(); // Pop the '(' symbol from the stack
            } else { // An operand scanned
                // Push an operand to the stack
                try {
                    operandStack.push(new Double(token));
                    if (verifyNumbers != null) {
                        verifyNumbers.add(new Integer(token));
                    }
                } catch (NumberFormatException e) {
                    return 0;
                }
            }
        }

        // Phase 2: process all the remaining operators in the stack
        while (!operatorStack.isEmpty()) {
            processAnOperator(operandStack, operatorStack);
        }

        // Return the result
        if (operandStack.isEmpty()) {
            return 0;
        } else {
            return operandStack.pop();
        }
    }  
    
    /**
     * Process one operator: Take an operator from operatorStack and apply it on
     * the operands in the operandStack
     */
    public static void processAnOperator(Stack<Double> operandStack,
            Stack<Character> operatorStack) {
        char op = operatorStack.pop();
        double op1 = operandStack.pop();
        double op2 = operandStack.pop();
        if (op == '+') {
            operandStack.push(op2 + op1);
        } else if (op == '-') {
            operandStack.push(op2 - op1);
        } else if (op == '*') {
            operandStack.push(op2 * op1);
        } else if (op == '/') {
            operandStack.push(op2 / op1);
        }
    }

    public static String insertBlanks(String s) {
        String result = "";

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == ')' || s.charAt(i) == '+'
                    || s.charAt(i) == '-' || s.charAt(i) == '*'
                    || s.charAt(i) == '/') {
                result += " " + s.charAt(i) + " ";
            } else {
                result += s.charAt(i);
            }
        }

        return result;
    }    
}
