package net.bohush.exercises.chapter13;

import java.awt.*;

import javax.swing.*;

public class Exercise12 extends JFrame{

	private static final long serialVersionUID = 1L;

	public Exercise12() {
		add(new SinCosFunction());
	}
	
	public static void main(String[] args) {
		Exercise12 frame = new Exercise12();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(600, 300);
		frame.setTitle("Exercise12");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}

class SinCosFunction extends JPanel {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//x-line
		int width = getWidth();
		int height = getHeight();
		g.drawLine((int)(width * 0.1), (int)(height * 0.5), (int)(width * 0.9), (int)(height * 0.5));
		g.drawLine((int)(width * 0.9) - 15, (int)(height * 0.5) - 15, (int)(width * 0.9), (int)(height * 0.5));
		g.drawLine((int)(width * 0.9) - 15, (int)(height * 0.5) + 15, (int)(width * 0.9), (int)(height * 0.5));
		g.drawString("X", (int)(width * 0.9), (int)(height * 0.5) - 15);
		//y-line
		g.drawLine((int)(width * 0.5), (int)(height * 0.1), (int)(width * 0.5), (int)(height * 0.9));
		g.drawLine((int)(width * 0.5), (int)(height * 0.1), (int)(width * 0.5) - 15, (int)(height * 0.1) + 15);
		g.drawLine((int)(width * 0.5), (int)(height * 0.1), (int)(width * 0.5) + 15, (int)(height * 0.1) + 15);
		g.drawString("Y", (int)(width * 0.5) + 15, (int)(height * 0.1));
		//sin function
		Polygon p = new Polygon();
		g.setColor(Color.RED);
		for (int x = -(int)(width * 0.4); x <= (int)(width * 0.4); x++) {
			p.addPoint(x + (int)(width * 0.5), (int)(height * 0.5) - (int)(50 * Math.sin((x / 100.0) * 2 * Math.PI)));
		}
		g.drawPolyline(p.xpoints, p.ypoints, p.npoints);
		//cos function		
		p = new Polygon();
		g.setColor(Color.BLUE);
		for (int x = -(int)(width * 0.4); x <= (int)(width * 0.4); x++) {
			p.addPoint(x + (int)(width * 0.5), (int)(height * 0.5) - (int)(50 * Math.cos((x / 100.0) * 2 * Math.PI)));
		}
		g.drawPolyline(p.xpoints, p.ypoints, p.npoints);
		//Pi
		g.setColor(Color.BLACK);
		g.drawString("0", (int)(width * 0.5) - 10, (int)(height * 0.5) + 15);
		g.drawString("-2\u03c0", (int)(width * 0.5) - 10 - 100, (int)(height * 0.5) + 15);
		g.drawString("-\u03c0", (int)(width * 0.5) - 10 - 50, (int)(height * 0.5) + 15);
		g.drawString("2\u03c0", (int)(width * 0.5) - 10 + 100, (int)(height * 0.5) + 15);
		g.drawString("\u03c0", (int)(width * 0.5) - 10 + 50, (int)(height * 0.5) + 15);
	}
}