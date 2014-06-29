package net.bohush.exercises.chapter16;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Exercise13 extends JFrame {
	int activeSlide = 0;
	JLabel slides = new JLabel(new ImageIcon("image/Slides/slide" + activeSlide	+ ".jpg"));

	private static final long serialVersionUID = 1L;

	public Exercise13() {
		add(slides);
		Timer timer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				slides.setIcon(new ImageIcon("image/Slides/slide" + ++activeSlide + ".jpg"));
				if (activeSlide == 24) {
					activeSlide = -1;
				}
			}

		});
		timer.start();
	}

	public static void main(String[] args) {
		Exercise13 frame = new Exercise13();
		frame.pack();
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Exercise13");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}