package net.bohush.exercises.chapter12;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Exercise03 extends JFrame {

	private static final long serialVersionUID = 1L;

	public Exercise03() {
		setLayout(new GridLayout(2, 3, 10, 10));
		add(new JButton("Button 1"));
		add(new JButton("Button 2"));
		add(new JButton("Button 3"));
		add(new JButton("Button 4"));
		add(new JButton("Button 5"));
		add(new JButton("Button 6"));
	}
	
	public static void main(String[] args) {
		Exercise03 frame = new Exercise03();
		frame.setSize(300, 200);
		frame.setTitle("Exercise03");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
