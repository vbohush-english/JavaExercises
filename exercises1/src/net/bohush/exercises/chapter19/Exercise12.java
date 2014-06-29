package net.bohush.exercises.chapter19;

import java.io.*;

public class Exercise12 {

	public static void main(String[] args) throws IOException {
		if (args.length < 3) {
			System.out.println("Usage: java Exercise12 SourceFile1 . . . SourceFilen TargetFile");
			System.exit(1);
		}

		File targetFile = new File(args[args.length - 1]);
		if (targetFile.exists()) {
			System.out.println("Target file " + args[args.length - 1] + " exists");
			System.exit(2);
		}
		
		BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(targetFile), 1024 * 1024);
		
		for (int i = 0; i < args.length - 1; i++) {
			BufferedInputStream input = new BufferedInputStream(new FileInputStream(args[i]), 1024 * 1024);
		    int r = 0;
		    while ((r = input.read()) != -1) {
		      output.write((byte)r);
		    }
		    input.close();
		}
		output.close();

	}
}
