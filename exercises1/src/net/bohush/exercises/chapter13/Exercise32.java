package net.bohush.exercises.chapter13;

import java.awt.*;

import javax.swing.*;

public class Exercise32 extends JFrame{

	private static final long serialVersionUID = 1L;

	public Exercise32(int[] points) {
		add(new TwoRectangles(points));
	}

	
	public static void main(String[] args) {
		
		if (args.length != 8) {
			JOptionPane.showMessageDialog(null, "Execute: java Exercise27 x1 y1 w1 h1 x2 y2 w2 h2", "Error", JOptionPane.ERROR_MESSAGE);	
			System.exit(1);
		}
		
		int[] points = new int[8];
		for (int i = 0; i < 8; i ++) {
			points[i] = Integer.parseInt(args[i]);
		}
		
		Exercise32 frame = new Exercise32(points);
		frame.setSize(250, 250);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Exercise32");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}

class TwoRectangles extends JPanel {
	private int x1;
	private int y1;
	private int w1;
	private int h1;
	
	private int x2;
	private int y2;
	private int w2;
	private int h2;

	
	public TwoRectangles(int[] points) {
		x1 = points[0];
		y1 = points[1];
		w1 = points[2];
		h1 = points[3];
		
		x2 = points[4];
		y2 = points[5];
		w2 = points[6];
		h2 = points[7];
	}
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
			
		g.setColor(Color.RED);
		g.drawRect(x1, y1, w1, h1);
		g.setColor(Color.BLUE);
		g.drawRect(x2, y2, w2, h2);
		g.setColor(Color.BLACK);
		if (isOverlapRect()) {
			if (isContainsRect()) {
				g.drawString("One restangle is conteined in another", 10, getHeight() - 10);
			} else {
				g.drawString("The restangles overlap", 10, getHeight() - 10);	
			}			
		} else {
			g.drawString("The restangles do not overlap", 10, getHeight() - 10);
		}
	}
	
	private boolean isContainsRect() {
		if (containsPoint(x2, y2) &&
				containsPoint(x2, y2 + h2) &&
				containsPoint(x2 + w2, y2) &&
				containsPoint(x2 + w2, y2 + h2)) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean isOverlapRect() {
		if ((!containsPoint(x2, y2)) &&
				(!containsPoint(x2, y2 + h2)) &&
				(!containsPoint(x2 + w2, y2)) &&
				(!containsPoint(x2 + w2, y2 + h2))) {
			return false;
		} else {
			return true;
		}
	}
	
	private boolean containsPoint(int x, int y) {
		if ((x >= x1) && (x <= x1 + w1) && (y >= y1) && (y <= y1 + h1)) {
			return true;
		} else {
			return false;
		}
	}

}