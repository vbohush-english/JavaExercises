package net.bohush.exercises.chapter12;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Exercise10 extends JFrame {

	private static final long serialVersionUID = 1L;

	public Exercise10() {
		setLayout(new GridLayout(10, 10, 0, 0));
		Color nextColor = Color.WHITE;
		for (int i = 1; i <= 100; i++) {
			JButton jbTmp = new JButton();
			jbTmp.setBackground(nextColor);
			if (i % 10 != 0) {
				if (nextColor == Color.BLACK) {
					nextColor = Color.WHITE;
				} else {
					nextColor = Color.BLACK;
				}
			}
			add(jbTmp);
		}
	}
	
	public static void main(String[] args) {
		Exercise10 frame = new Exercise10();
		frame.setSize(300, 300);
		frame.setTitle("Exercise10");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
