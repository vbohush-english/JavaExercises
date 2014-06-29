package net.bohush.exercises.chapter22;

import java.io.File;
import java.util.LinkedList;
import java.util.Scanner; 

public class Exercise18 {
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
		LinkedList<File> fileQueue = new LinkedList<>();
		fileQueue.add(file);

		while(!fileQueue.isEmpty()) {
			File t = fileQueue.getFirst();
			fileQueue.removeFirst();
			if(!t.isDirectory()) {
				size += t.length();
			} else {
				File[] files = t.listFiles();
				for (int i = 0; files != null && i < files.length; i++) {
					fileQueue.add(files[i]);
				}	
			}
		}
		return size;
	}
		
}