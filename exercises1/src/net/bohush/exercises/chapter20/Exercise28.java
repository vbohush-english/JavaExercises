package net.bohush.exercises.chapter20;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner; 

public class Exercise28 {
	public static void main(String[] args) {
		System.out.print("Enter a directory or a file: ");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String directory = input.nextLine();

		long time1 = System.currentTimeMillis();		
		System.out.println(getSize(new File(directory)) + " bytes");		
		long time2 = System.currentTimeMillis();
		System.out.println((time2 - time1) + " msec.");
	}

	public static long getSize(File file) {
		long size = 0;		
		ArrayList<File> fileTree = new ArrayList<>();
		fileTree.add(file);

		int beginIndex = 0;
		int endIndex = 1;
		while(beginIndex != endIndex) {
			for (int i = beginIndex; i < endIndex; i++) {
				
				File tmpfile = fileTree.get(i);
				File[] files = tmpfile.listFiles();
				
				int tmpBeginIndex = fileTree.size();
				int tmpEndIndex = tmpBeginIndex;
				for (int j = 0; files != null && j < files.length; j++) {
					if (files[j].isDirectory()) {
						tmpEndIndex++;
						fileTree.add(files[j]);	
					} else {
						size += files[j].length();
					}				
				}	
				beginIndex = tmpBeginIndex;
				endIndex = tmpEndIndex;
			}
		}
		return size;
	}
		
}