package net.bohush.exercises.chapter17;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.*;

public class Exercise20 extends JFrame {
	int activeSlide = 0;
	JTextArea slides = new JTextArea(getSlide(activeSlide));

	private static final long serialVersionUID = 1L;

	public Exercise20() {
		add(slides);
		Timer timer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (slideExists(++activeSlide)) {
					slides.setText(getSlide(activeSlide));
				} else  {
					activeSlide = -1;
				}
				
			}

		});
		timer.start();
	}
	
	public boolean slideExists(int number) {
		String fileName = "tmp/slide" + number + ".txt";
		File slideFile = new File(fileName);
		return slideFile.exists();	
	}
	
	public String getSlide(int number) {
		String fileName = "tmp/slide" + number + ".txt";
		File slideFile = new File(fileName);
		if (slideFile.exists()) {
			Scanner input;
			try {
				input = new Scanner(slideFile);
				StringBuilder stringBuilder = new StringBuilder("");
				while (input.hasNextLine()) {
					stringBuilder.append(input.nextLine());
					stringBuilder.append('\n');
				}
				input.close();
				return stringBuilder.toString();
			} catch (FileNotFoundException e) {
				return "";
			}
		} else {
			return "";
		}
	}

	public static void main(String[] args) {
		Exercise20 frame = new Exercise20();
		frame.setSize(300, 300);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Exercise20");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}