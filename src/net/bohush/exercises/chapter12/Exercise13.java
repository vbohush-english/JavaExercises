package net.bohush.exercises.chapter12;

import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Exercise13 extends JFrame {
	
	public Exercise13() {
		
		int[] deck = new int[54];
		for (int i = 0; i < deck.length; i++)
			deck[i] = i + 1;

		for (int i = 0; i < deck.length; i++) {
			int index = (int) (Math.random() * deck.length);
			int temp = deck[i];
			deck[i] = deck[index];
			deck[index] = temp;
		}
		setLayout(new GridLayout(0, 9, 0, 0));
		for (int i = 0; i < deck.length; i++) {
			add(new JLabel(new ImageIcon("image/Cards/" + deck[i] + ".png")));	
		}


	}
	
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		Exercise13 frame = new Exercise13();
		frame.setSize(800, 700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Exercise13");
		frame.setVisible(true);
	}
	
}
