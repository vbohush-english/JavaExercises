package net.bohush.exercises.chapter13;

import java.awt.*;

import javax.swing.*;

public class Exercise03 extends JFrame{

	private static final long serialVersionUID = 1L;

	public Exercise03() {
		add(new Checkerboard());
	}
	
	public static void main(String[] args) {
		Exercise03 frame = new Exercise03();
		frame.setSize(300, 300);
		frame.setTitle("Exercise03");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}

class Checkerboard extends JPanel {

	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = getWidth();
		int height = getHeight();
		int size = 8;
		Color nextColor = Color.WHITE;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (j != 0) {
					if (nextColor == Color.BLACK) {
						nextColor = Color.WHITE;
					} else {
						nextColor = Color.BLACK;
					}
				}
				g.setColor(nextColor);
				g.fillRect((int)(i * (width / (double)size)), (int)(j * (height / (double)size)), width / size, height / size);
			}
		}
	}

}