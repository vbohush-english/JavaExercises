package net.bohush.exercises.chapter23;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Exercise07 {
	public static void main(String[] args) {
		String text = "Good morning. Have a good class. Have a good visit. Have fun!";

		Map<String, Integer> map = new TreeMap<String, Integer>();

		String[] words = text.split("[ \n\t\r.,;:!?(){}]");
		for (int i = 0; i < words.length; i++) {
			String key = words[i].toLowerCase();

			if (key.length() > 0) {
				if (!map.containsKey(key)) {
					map.put(key, 1);
				} else {
					int value = map.get(key);
					value++;
					map.put(key, value);
				}
			}
		}
		
		
		ArrayList<WordOccurrence> list = new ArrayList<>();
		Set<String> set = map.keySet();
		for (String string : set) {
			list.add(new WordOccurrence(string, map.get(string)));
		}
		Collections.sort(list);
		for (WordOccurrence wordOccurrence : list) {
			System.out.println(wordOccurrence.getWord() + "\t" + wordOccurrence.getCount());
		}
	}

}


class WordOccurrence implements Comparable<WordOccurrence> {
	private String word;
	private int count;
	public WordOccurrence(String word, int count) {
		super();
		this.word = word;
		this.count = count;
	}
	
	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public int compareTo(WordOccurrence o) {
		return count - o.count;
	}
	
}