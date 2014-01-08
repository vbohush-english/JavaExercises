package net.bohush.exercises.chapter13;

import javax.swing.*;

import java.awt.*;

public class Exercise26 extends JFrame{

	private static final long serialVersionUID = 1L;

	public Exercise26() {
		setLayout(new GridLayout(1, 2));
		MessagePanel mp1 = new MessagePanel("Java");
		MessagePanel mp2 = new MessagePanel("HTML");
		mp2.setBackground(Color.YELLOW);
		MessagePanel mp3 = new MessagePanel("Tomcat");
		MessagePanel mp4 = new MessagePanel("PHP");
		mp4.setBackground(Color.GREEN);
		mp1.setCentered(true);
		mp2.setCentered(true);
		mp3.setCentered(true);
		mp4.setCentered(true);
		
		add(mp1, BorderLayout.EAST);
		JPanel panel = new JPanel(new GridLayout(3, 1));
		panel.add(mp2);
		panel.add(mp3);
		panel.add(mp4);
		add(mp1);
		add(panel);
	}
	
	public static void main(String[] args) {
		Exercise26 frame = new Exercise26();
		frame.setSize(300, 100);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Exercise26");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
