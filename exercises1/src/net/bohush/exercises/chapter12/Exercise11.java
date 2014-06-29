package net.bohush.exercises.chapter12;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;


public class Exercise11 extends JFrame {
	
	public Exercise11() {
		final int CARD_NUMBERS = 4;
		
		int[] deck = new int[54];
		for (int i = 0; i < deck.length; i++)
			deck[i] = i + 1;

		for (int i = 0; i < deck.length; i++) {
			int index = (int) (Math.random() * deck.length);
			int temp = deck[i];
			deck[i] = deck[index];
			deck[index] = temp;
		}
		setLayout(new GridLayout(1, CARD_NUMBERS, 0, 0));
		
		JButton[] jbtnArray = new JButton[CARD_NUMBERS];
		for (int i = 0; i < CARD_NUMBERS; i++) {
			jbtnArray[i] = new JButton(new ImageIcon("image/Cards/b1fv.png"));
			jbtnArray[i].setPressedIcon(new ImageIcon("image/Cards/" + deck[i] + ".png"));
			add(jbtnArray[i]);
		}
	}
	
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		Exercise11 frame = new Exercise11();
		frame.setSize(300, 130);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Exercise11");
		frame.setVisible(true);
	}
	
}
