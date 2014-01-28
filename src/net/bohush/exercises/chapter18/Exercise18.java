package net.bohush.exercises.chapter18;

import java.awt.*;

import javax.swing.*;

public class Exercise18 extends JFrame{

	private static final long serialVersionUID = 1L;

	public Exercise18() {
		JPanel panel1 = new JPanel();
		add(panel1, BorderLayout.CENTER);
	}
	
	public static void main(String[] args) {
		Exercise18 frame = new Exercise18();
		frame.setTitle("Exercise18");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.pack();
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
