package net.bohush.exercises.chapter13;

import java.awt.*;

import javax.swing.*;

public class Exercise31 extends JFrame{

	private static final long serialVersionUID = 1L;

	public Exercise31(Polygon points, int x, int y) {
		add(new CheckPoligon2(points, x, y));
	}

	
	public static void main(String[] args) {
		
		if (args.length != 10) {
			JOptionPane.showMessageDialog(null, "Execute: java Exercise27 x1 y1 x2 y2 x3 y3 x4 y4 x5 y5", "Error", JOptionPane.ERROR_MESSAGE);	
			System.exit(1);
		}
		
		Polygon points = new Polygon();
		for (int i = 0; i < 8; i += 2) {
			points.addPoint(Integer.parseInt(args[i]), Integer.parseInt(args[i + 1]));
		}
		int x = Integer.parseInt(args[8]);
		int y = Integer.parseInt(args[9]);
		
		Exercise31 frame = new Exercise31(points, x, y);
		frame.setSize(250, 250);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Exercise31");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}

class CheckPoligon2 extends JPanel {
	private Polygon p;
	private int x;
	private int y;
	
	public CheckPoligon2(Polygon p, int x, int y) {
		 this.p = p;
		 this.x = x;
		 this.y = y;
	}
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawPolygon(p);
		for (int i = 0; i < p.npoints; i++) {
			g.fillOval(p.xpoints[i] - 3, p.ypoints[i] - 3, 6, 6);
			g.drawString("(" + p.xpoints[i] + ", " + p.ypoints[i] + ")", p.xpoints[i] - 3, p.ypoints[i] - 3);
		}
		g.drawOval(x - 3, y - 3, 6, 6);
		g.drawString("(" + x + ", " + y + ")", x - 3, y - 3);
		if (p.contains(new Point(x, y))) {
			g.drawString("The point is inside the poligone", 10, getHeight() - 10);
		} else {
			g.drawString("The point is not inside the poligone", 10, getHeight() - 10);
		}
	}

}