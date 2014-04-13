package net.bohush.exercises.chapter31;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class Exercise07 {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		// Prompt the user to enter nine coins H and T's
		System.out.print("Enter the initial nine coins Hs and Ts: ");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String s = input.nextLine();
		char[] initialNode = s.toCharArray();

		ObjectInputStream objectInput = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("tmp/WeightedTailModel16.dat"))));
		Exercise06.WeightedTailModel16 model = (Exercise06.WeightedTailModel16) objectInput.readObject();
		objectInput.close();
		java.util.List<Integer> path = model.getShortestPath(Exercise06.TailModel16.getIndex(initialNode));

		if(path != null) {
			System.out.println("The steps to flip the coins are ");
			for (int i = 0; i < path.size(); i++)
				Exercise06.TailModel16.printNode(Exercise06.TailModel16.getNode(path.get(i).intValue()));
			System.out.println("The number of flips is " + model.getNumberOfFlips(Exercise06.TailModel16.getIndex(initialNode)));
		} else {
			System.out.println("No solution");
		}
	}
}