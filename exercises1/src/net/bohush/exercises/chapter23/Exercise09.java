package net.bohush.exercises.chapter23;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Exercise09 {

	public static void main(String[] args) {
		LinkedHashMap<String, String> states = new LinkedHashMap<>();
		states.put("Alabama", "Montgomery"); 
		states.put("Alaska", "Juneau");
		states.put("Arizona", "Phoenix"); 
		states.put("Arkansas", "Littlerock"); 
		states.put("California", "Sacramento"); 
		states.put("Colorado", "Denver");
		states.put("Connecticut", "Hartford"); 
		states.put("Delaware", "Dover");
		states.put("Florida", "Tallahassee"); 
		states.put("Georgia", "Atlanta");
		states.put("Hawaii", "Honolulu"); 
		states.put("Idaho", "Boise");
		states.put("Illinois", "Springfield"); 
		states.put("Indiana", "Indianapolis"); 
		states.put("Iowa", "Des Moines");
		states.put("Kansas", "Topeka");
		states.put("Kentucky", "Frankfort"); 
		states.put("Louisiana", "Baton Rouge"); 
		states.put("Maine", "Augusta");
		states.put("Maryland", "Annapolis"); 
		states.put("Massachusetts", "Boston"); 
		states.put("Michigan", "Lansing");
		states.put("Minnesota", "St. Paul"); 
		states.put("Mississippi", "Jackson"); 
		states.put("Missouri", "Jefferson City"); 
		states.put("Montana", "Helena");
		states.put("Nebraska", "Lincoln"); 
		states.put("Nevada", "Carson City"); 
		states.put("New Hampshire", "Concord"); 
		states.put("New Jersey", "Trenton");
		states.put("New Mexico", "SantaFe"); 
		states.put("New York", "Albany");
		states.put("North Carolina", "Raleigh"); 
		states.put("North Dakota", "Bismarck");
		states.put("Ohio", "Columbus");
		states.put("Oklahoma", "Oklahoma City"); 
		states.put("Oregon", "Salem");
		states.put("Pennsylvania", "Harrisburg"); 
		states.put("Rhode Island", "Providence"); 
		states.put("South Carolina", "Columbia"); 
		states.put("South Dakota", "Pierre");
		states.put("Tennessee", "Nashville"); 
		states.put("Texas", "Austin");
		states.put("Utah", "Salt Lake City"); 
		states.put("Vermont", "Montpelier");
		states.put("Virginia", "Richmond");
		states.put("Washington", "Olympia"); 
		states.put("WestVirginia", "Charleston"); 
		states.put("Wisconsin", "Madison");
		states.put("Wyoming", "Cheyenne");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		int correctCount = 0;
		for (Map.Entry<String, String> entry : states.entrySet()) {
			System.out.print("What is the capital of " + entry.getKey() + "? ");
			String capital = input.nextLine();
			if (capital.equals(entry.getValue())) {
				correctCount++;
				System.out.println("Your answer is correct");
			} else {
				System.out.println("The correct answer should be " + entry.getValue());
			}
			
		}
		System.out.println("The correct count is " + correctCount);
	}

}
