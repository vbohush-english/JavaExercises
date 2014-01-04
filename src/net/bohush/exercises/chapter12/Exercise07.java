package net.bohush.exercises.chapter12;

import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Exercise07 extends JFrame {
	
	public Exercise07() {
		final int GAME_SIZE = 3;
		setLayout(new GridLayout(GAME_SIZE, GAME_SIZE, 0, 0));
		for (int i = 0; i < GAME_SIZE * GAME_SIZE; i++) {
			int next = (int)(Math.random() * 3);
			if (next == 0) {
				add(new JLabel());
			} else {
				add(new JLabel(new ImageIcon("image/12_07/" +  (next == 1 ? "o" : "x") + ".gif")));
			}
		}

	}
	
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		Exercise07 frame = new Exercise07();
		frame.setSize(200, 200);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Exercise07");
		frame.setVisible(true);
	}
	
}
