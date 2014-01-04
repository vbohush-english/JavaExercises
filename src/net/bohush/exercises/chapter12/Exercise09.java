package net.bohush.exercises.chapter12;

import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Exercise09 extends JFrame {
	
	public Exercise09() {
		
		int[] deck = new int[54];
		for (int i = 0; i < deck.length; i++)
			deck[i] = i + 1;

		for (int i = 0; i < deck.length; i++) {
			int index = (int) (Math.random() * deck.length);
			int temp = deck[i];
			deck[i] = deck[index];
			deck[index] = temp;
		}
		setLayout(new GridLayout(1, 3, 0, 0));
		add(new JLabel(new ImageIcon("image/Cards/" + deck[0] + ".png")));
		add(new JLabel(new ImageIcon("image/Cards/" + deck[1] + ".png")));
		add(new JLabel(new ImageIcon("image/Cards/" + deck[2] + ".png")));

	}
	
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		Exercise09 frame = new Exercise09();
		frame.setSize(300, 200);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Exercise09");
		frame.setVisible(true);
	}
	
}
