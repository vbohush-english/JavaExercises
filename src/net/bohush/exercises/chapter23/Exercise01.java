package net.bohush.exercises.chapter23;

import java.util.Arrays;
import java.util.HashSet;

public class Exercise01 {

	public static void main(String[] args) {
		HashSet<String> set1 = new HashSet<>(Arrays.asList(new String[]{"George", "Jim", "John", "Blake", "Kevin", "Michael"}));
		HashSet<String> set2 = new HashSet<>(Arrays.asList(new String[]{"George", "Katie", "Kevin", "Michelle", "Ryan"}));
		set1.addAll(set2);
		System.out.println("union " + set1);
		set1 = new HashSet<>(Arrays.asList(new String[]{"George", "Jim", "John", "Blake", "Kevin", "Michael"}));
		set1.removeAll(set2);
		System.out.println("difference " + set1);
		set1 = new HashSet<>(Arrays.asList(new String[]{"George", "Jim", "John", "Blake", "Kevin", "Michael"}));
		set1.retainAll(set2);
		System.out.println("intersection " + set1);
	}

}
