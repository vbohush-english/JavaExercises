package net.bohush.exercises.chapter22;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Exercise03 {

	public static void main(String[] args) {
		List<String[]> states = Arrays.asList(
				new String[]{"Alabama", "Montgomery"}, 
				new String[]{"Alaska", "Juneau"}, 
				new String[]{"Arizona", "Phoenix"}, 
				new String[]{"Arkansas", "Littlerock"}, 
				new String[]{"California", "Sacramento"}, 
				new String[]{"Colorado", "Denver"}, 
				new String[]{"Connecticut", "Hartford"}, 
				new String[]{"Delaware", "Dover"}, 
				new String[]{"Florida", "Tallahassee"}, 
				new String[]{"Georgia", "Atlanta"}, 
				new String[]{"Hawaii", "Honolulu"}, 
				new String[]{"Idaho", "Boise"}, 
				new String[]{"Illinois", "Springfield"}, 
				new String[]{"Indiana", "Indianapolis"}, 
				new String[]{"Iowa", "Des Moines"}, 
				new String[]{"Kansas", "Topeka"}, 
				new String[]{"Kentucky", "Frankfort"}, 
				new String[]{"Louisiana", "Baton Rouge"}, 
				new String[]{"Maine", "Augusta"}, 
				new String[]{"Maryland", "Annapolis"}, 
				new String[]{"Massachusetts", "Boston"}, 
				new String[]{"Michigan", "Lansing"}, 
				new String[]{"Minnesota", "St. Paul"}, 
				new String[]{"Mississippi", "Jackson"}, 
				new String[]{"Missouri", "Jefferson City"}, 
				new String[]{"Montana", "Helena"}, 
				new String[]{"Nebraska", "Lincoln"}, 
				new String[]{"Nevada", "Carson City"}, 
				new String[]{"New Hampshire", "Concord"}, 
				new String[]{"New Jersey", "Trenton"}, 
				new String[]{"New Mexico", "SantaFe"}, 
				new String[]{"New York", "Albany"}, 
				new String[]{"North Carolina", "Raleigh"}, 
				new String[]{"North Dakota", "Bismarck"}, 
				new String[]{"Ohio", "Columbus"}, 
				new String[]{"Oklahoma", "Oklahoma City"}, 
				new String[]{"Oregon", "Salem"}, 
				new String[]{"Pennsylvania", "Harrisburg"}, 
				new String[]{"Rhode Island", "Providence"}, 
				new String[]{"South Carolina", "Columbia"}, 
				new String[]{"South Dakota", "Pierre"}, 
				new String[]{"Tennessee", "Nashville"}, 
				new String[]{"Texas", "Austin"}, 
				new String[]{"Utah", "Salt Lake City"}, 
				new String[]{"Vermont", "Montpelier"}, 
				new String[]{"Virginia", "Richmond"}, 
				new String[]{"Washington", "Olympia"}, 
				new String[]{"WestVirginia", "Charleston"}, 
				new String[]{"Wisconsin", "Madison"}, 
				new String[]{"Wyoming", "Cheyenne"});
		
		Collections.shuffle(states);

		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		int correctCount = 0;
		for (int  i = 0; i < states.size(); i++) {
			System.out.print("What is the capital of " + states.get(i)[0] + "? ");
			String capital = input.nextLine();
			if (capital.equals(states.get(i)[1])) {
				correctCount++;
				System.out.println("Your answer is correct");
			} else {
				System.out.println("The correct answer should be " + states.get(i)[1]);
			}
		}
		System.out.println("The correct count is " + correctCount);
	}

}
