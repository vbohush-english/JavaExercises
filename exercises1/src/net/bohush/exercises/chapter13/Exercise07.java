package net.bohush.exercises.chapter13;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Exercise07 extends JFrame {
	
	public Exercise07() {
		final int GAME_SIZE = 3;
		setLayout(new GridLayout(GAME_SIZE, GAME_SIZE, 10, 10));
		for (int i = 0; i < GAME_SIZE * GAME_SIZE; i++) {
			int next = (int)(Math.random() * 3);
			add(new TicTacToe(next));
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

class TicTacToe extends JPanel {
	
	private int value;

	private static final long serialVersionUID = 1L;

	public TicTacToe(int value) {
		super();
		this.value = value;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = (int)(getWidth() * 0.9);
		int height = (int)(getHeight() * 0.9);
		int startWidth = (int)(getWidth() * 0.1);
		int startHeight = (int)(getHeight() * 0.1);
		if (value == 1) {
			g.setColor(Color.RED);
			g.drawLine(startWidth, startHeight, width, height);
			g.drawLine(startWidth, height, width, startHeight);			
		} else if (value == 2) {
			g.setColor(Color.BLUE);
			g.drawOval(startWidth, startHeight, width - startWidth, height - startHeight);
		}
	}

}