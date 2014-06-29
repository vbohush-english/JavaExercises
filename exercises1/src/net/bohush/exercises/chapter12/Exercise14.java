package net.bohush.exercises.chapter12;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Exercise14 extends JFrame {
	
	public Exercise14() {
		
		setLayout(new GridLayout(10, 10, 0, 0));
		for (int i = 0; i < 100; i++) {
			add(new JLabel((int)(Math.random() * 2) + "", JLabel.CENTER));	
		}


	}
	
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		Exercise14 frame = new Exercise14();
		frame.setSize(300, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Exercise14");
		frame.setVisible(true);
	}
	
}
