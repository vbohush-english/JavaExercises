package net.bohush.exercises.chapter13;

import java.awt.*;

import javax.swing.*;

public class Exercise21 extends JFrame {
	
	public Exercise21() {
		final int GAME_SIZE = 3;
		setLayout(new GridLayout(GAME_SIZE, GAME_SIZE, 10, 10));
		for (int i = 0; i < GAME_SIZE * GAME_SIZE; i++) {
			int next = (int)(Math.random() * 3);
			add(new TicTacToe2(next));
		}

	}
	
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		Exercise21 frame = new Exercise21();
		frame.setSize(200, 200);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Exercise21");
		frame.setVisible(true);
	}
	
}

class TicTacToe2 extends JPanel {
	
	private int value;

	private static final long serialVersionUID = 1L;

	public TicTacToe2(int value) {
		super();
		this.value = value;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (value != 0) {
			g.drawImage(new ImageIcon("image/TicTacToe/" +  (value == 1 ? "o" : "x") + ".gif").getImage(), 0, 0, getWidth(), getHeight(), this);
		}
	}

}