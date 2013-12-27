package net.bohush.exercises.chapter09;

public class Exercise20 {

	public static void main(String[] args) {
		MyCharacter myCharacter = new MyCharacter('a');
		MyCharacter myCharacter2 = new MyCharacter('n');
		System.out.println(myCharacter.charValue());
		System.out.println(myCharacter.equals(myCharacter2));
		System.out.println(myCharacter.compareTo(myCharacter2));
		System.out.println(MyCharacter.toUpperCase('z'));
	}

}

class MyCharacter {
	private char myChar;
	
	public MyCharacter(char value) {
		myChar = value;
	}
	
	public char charValue() {
		return myChar;
	}
	
	public boolean equals(MyCharacter anotherCharacter) {
		return myChar == anotherCharacter.myChar;
	}
	
	public int compareTo(MyCharacter anotherCharacter) {
		return myChar - anotherCharacter.myChar;
	}	
	
	public static boolean isDigit(char ch) {
		return ((ch >= '0') && (ch <= '9'));
	} 
	
	public static boolean isLowerCase(char ch) {
		return ((ch >= 'a') && (ch <= 'z'));
	} 
	
	public static boolean isUpperCase(char ch) {
		return ((ch >= 'A') && (ch <= 'Z'));	
	} 
	
	public static boolean isLetter(char ch) {
		return isLowerCase(ch) || isLowerCase(ch);
	} 
	
	
	public static boolean isLetterOrDigit(char ch) {
		return isLetter(ch) || isDigit(ch);
	} 
		
	public static char toLowerCase(char ch) {
		if (isUpperCase(ch)) {
			return (char)(ch - ('A' - 'a'));
		} else {
			return ch;
		}
	}
		
	
	public static char toUpperCase(char ch) {
		if (isLowerCase(ch)) {
			return (char)(ch + ('A' - 'a'));
		} else {
			return ch;
		}
	}
	
}
