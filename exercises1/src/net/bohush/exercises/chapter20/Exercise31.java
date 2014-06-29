package net.bohush.exercises.chapter20;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Exercise31 {
	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("Usage: java Exercise30 dirName oldWord newWord");
			System.exit(0);
		}

		String directory = args[0];
		String oldWord = args[1];
		String newWord = args[2];

		long time1 = System.currentTimeMillis();
		replaceWords(new File(directory), oldWord, newWord);
		long time2 = System.currentTimeMillis();
		System.out.println((time2 - time1) + " msec.");
	}

	public static void replaceWords(File file, String oldWord, String newWord) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (int i = 0; files != null && i < files.length; i++) {
				replaceWords(files[i], oldWord, newWord);
			}
		} else {
			replaceWordsInFile(file, oldWord, newWord);
		}

	}
	
	public static void replaceWordsInFile(File file, String oldWord, String newWord) {
		try {
			DataInputStream input = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
			int size = input.available();
			byte[] b = new byte[size];
			input.read(b);
			input.close();

			String allFile = new String(b);
			allFile = allFile.replaceAll(oldWord, newWord);
			DataOutputStream output = new DataOutputStream(new FileOutputStream(file));
			output.writeBytes(allFile);
			output.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}

}