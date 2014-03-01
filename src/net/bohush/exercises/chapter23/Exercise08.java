package net.bohush.exercises.chapter23;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Exercise08 {
	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.out.println("Usage: java Exercise08 fileName");
			System.exit(0);
		}
		File inputFile = new File(args[0]);
		DataInputStream input = new DataInputStream(new BufferedInputStream(new FileInputStream(inputFile)));
		int size = input.available();
		byte[] b = new byte[size];
		input.read(b);
		input.close();
		String text = new String(b);

		Map<String, Integer> map = new TreeMap<String, Integer>();

		text = text.replace('\'', ' ');
		text = text.replace('\"', ' ');
		text = text.replace('[', ' ');
		text = text.replace(']', ' ');
		text = text.replace('+', ' ');
		text = text.replace('-', ' ');
		text = text.replace('\\', ' ');
		text = text.replace('>', ' ');
		text = text.replace('<', ' ');
		String[] words = text.split("[ \n\t\r.,;:!?(){}]");
		for (int i = 0; i < words.length; i++) {
			String key = words[i].toLowerCase();
			if ((key.length() > 0)&&Character.isAlphabetic(words[i].charAt(0))) {
				if (!map.containsKey(key)) {
					map.put(key, 1);
				} else {
					int value = map.get(key);
					value++;
					map.put(key, value);
				}
			}
		}

		// Get all entries into a set
		Set<Map.Entry<String, Integer>> entrySet = map.entrySet();

		// Get key and value from each entry
		for (Map.Entry<String, Integer> entry : entrySet)
			System.out.println(entry.getKey() + "\t" + entry.getValue());
	}
}