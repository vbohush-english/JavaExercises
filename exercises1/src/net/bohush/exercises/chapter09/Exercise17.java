package net.bohush.exercises.chapter09;

import java.util.Scanner;

public class Exercise17 {

	public static void main(String[] args) {
		String[][] states = new String[][] {{"Alabama", "Montgomery"}, 
				{"Alaska", "Juneau"}, 
				{"Arizona", "Phoenix"}, 
				{"Arkansas", "Littlerock"}, 
				{"California", "Sacramento"}, 
				{"Colorado", "Denver"}, 
				{"Connecticut", "Hartford"}, 
				{"Delaware", "Dover"}, 
				{"Florida", "Tallahassee"}, 
				{"Georgia", "Atlanta"}, 
				{"Hawaii", "Honolulu"}, 
				{"Idaho", "Boise"}, 
				{"Illinois", "Springfield"}, 
				{"Indiana", "Indianapolis"}, 
				{"Iowa", "Des Moines"}, 
				{"Kansas", "Topeka"}, 
				{"Kentucky", "Frankfort"}, 
				{"Louisiana", "Baton Rouge"}, 
				{"Maine", "Augusta"}, 
				{"Maryland", "Annapolis"}, 
				{"Massachusetts", "Boston"}, 
				{"Michigan", "Lansing"}, 
				{"Minnesota", "St. Paul"}, 
				{"Mississippi", "Jackson"}, 
				{"Missouri", "Jefferson City"}, 
				{"Montana", "Helena"}, 
				{"Nebraska", "Lincoln"}, 
				{"Nevada", "Carson City"}, 
				{"New Hampshire", "Concord"}, 
				{"New Jersey", "Trenton"}, 
				{"New Mexico", "SantaFe"}, 
				{"New York", "Albany"}, 
				{"North Carolina", "Raleigh"}, 
				{"North Dakota", "Bismarck"}, 
				{"Ohio", "Columbus"}, 
				{"Oklahoma", "Oklahoma City"}, 
				{"Oregon", "Salem"}, 
				{"Pennsylvania", "Harrisburg"}, 
				{"Rhode Island", "Providence"}, 
				{"South Carolina", "Columbia"}, 
				{"South Dakota", "Pierre"}, 
				{"Tennessee", "Nashville"}, 
				{"Texas", "Austin"}, 
				{"Utah", "Salt Lake City"}, 
				{"Vermont", "Montpelier"}, 
				{"Virginia", "Richmond"}, 
				{"Washington", "Olympia"}, 
				{"WestVirginia", "Charleston"}, 
				{"Wisconsin", "Madison"}, 
				{"Wyoming", "Cheyenne"}};
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		int correctCount = 0;
		for (int  i = 0; i < states.length; i++) {
			System.out.print("What is the capital of " + states[i][0] + "? ");
			String capital = input.nextLine();
			if (capital.equals(states[i][1])) {
				correctCount++;
				System.out.println("Your answer is correct");
			} else {
				System.out.println("The correct answer should be " + states[i][1]);
			}
		}
		System.out.println("The correct count is " + correctCount);
	}

}
