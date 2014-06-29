package net.bohush.exercises.chapter13;

import java.awt.*;

import javax.swing.*;

public class Exercise11 extends JFrame{

	private static final long serialVersionUID = 1L;

	public Exercise11() {
		add(new SquareFunction());
	}
	
	public static void main(String[] args) {
		Exercise11 frame = new Exercise11();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(600, 600);
		frame.setTitle("Exercise11");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}

class SquareFunction extends JPanel {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//x-line
		int width = getWidth();
		int height = getHeight();
		g.drawLine((int)(width * 0.1), (int)(height * 0.9), (int)(width * 0.9), (int)(height * 0.9));
		g.drawLine((int)(width * 0.9) - 15, (int)(height * 0.9) - 15, (int)(width * 0.9), (int)(height * 0.9));
		g.drawLine((int)(width * 0.9) - 15, (int)(height * 0.9) + 15, (int)(width * 0.9), (int)(height * 0.9));
		g.drawString("X", (int)(width * 0.9), (int)(height * 0.9) - 15);
		//y-line
		g.drawLine((int)(width * 0.5), (int)(height * 0.1), (int)(width * 0.5), (int)(height * 0.9));
		g.drawLine((int)(width * 0.5), (int)(height * 0.1), (int)(width * 0.5) - 15, (int)(height * 0.1) + 15);
		g.drawLine((int)(width * 0.5), (int)(height * 0.1), (int)(width * 0.5) + 15, (int)(height * 0.1) + 15);
		g.drawString("Y", (int)(width * 0.5) + 15, (int)(height * 0.1));
		//function
		Polygon p = new Polygon();
		double scaleFactor = 0.01;
		for (int x = -200; x <= 200; x++) {
			p.addPoint(x + width / 2, (int)(height* 0.9) - (int)(scaleFactor * x * x));
		}
		g.drawPolyline(p.xpoints, p.ypoints, p.npoints);
		
	}
}