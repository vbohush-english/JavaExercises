package net.bohush.exercises.chapter30;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class Exercise14 {


	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		// Prompt the user to enter nine coins H and T's
		System.out.print("Enter the initial nine coins Hs and Ts: ");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String s = input.nextLine();
		char[] initialNode = s.toCharArray();

		ObjectInputStream objectInput = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("tmp/TailModel16.dat"))));
		Exercise13.TailModel16 model = (Exercise13.TailModel16) objectInput.readObject();
		objectInput.close();

		java.util.List<Integer> path = model.getShortestPath(Exercise13.TailModel16.getIndex(initialNode));

		System.out.println("The steps to flip the coins are ");
		for (int i = 0; i < path.size(); i++)
			Exercise13.TailModel16.printNode(Exercise13.TailModel16.getNode(path.get(i).intValue()));
	}

}
