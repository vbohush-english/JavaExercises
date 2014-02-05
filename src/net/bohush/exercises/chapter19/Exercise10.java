package net.bohush.exercises.chapter19;

import java.io.*;

public class Exercise10 {

	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			System.out.println("Usage: java Exercise10 sourceFile numberOfPieces");
			System.exit(1);
		}

		File sourceFile = new File(args[0]);
		if (!sourceFile.exists()) {
			System.out.println("Source file " + args[0] + " does not exist");
			System.exit(2);
		}

		int numberOfPieces = 0;
		try {
			numberOfPieces = Integer.parseInt(args[1]);	
		} catch (NumberFormatException e) {
			System.out.println(args[1] + " is not correct number of files");
			System.exit(3);
		}
		
		BufferedInputStream input = new BufferedInputStream(new FileInputStream(sourceFile));
		
		int inputFileSize = input.available();
		int fileSize = inputFileSize / numberOfPieces + 1;
		int lastFileSize = inputFileSize - (fileSize * (numberOfPieces - 1));

		for (int i = 1; i < numberOfPieces; i++) {
			BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(args[0] + "." + i), 1024 * 1024);
			System.out.println("Creating \"" + args[0] + "." + i + "\" with size " + fileSize);
			for (int j = 0; j < fileSize; j++) {
				output.write((byte) (input.read()));				
			}
			output.close();
		}
		System.out.println("Creating \"" + args[0] + "." + numberOfPieces + "\" with size " + lastFileSize);
		BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(args[0] + "." + numberOfPieces), 1024 * 1024);
		for (int j = 0; j < lastFileSize; j++) {
			output.write((byte) (input.read()));
		}
		output.close();
		input.close();

		System.out.println(inputFileSize + " bytes copied in " + numberOfPieces + " files");
	}
}
