package net.bohush.exercises.chapter13;

import java.awt.*;

import javax.swing.*;

public class Exercise27 extends JFrame{

	private static final long serialVersionUID = 1L;

	public Exercise27(Polygon points) {
		int minX = points.xpoints[0];
		int maxX = points.xpoints[0];
		int minY = points.ypoints[0];
		int maxY = points.ypoints[0];
		
		for (int i = 0; i < points.npoints; i++) {
			if (minX > points.xpoints[i]) {
				minX = points.xpoints[i];
			}
			if (minY > points.ypoints[i]) {
				minY = points.ypoints[i];
			}
			if (maxX < points.xpoints[i]) {
				maxX = points.xpoints[i];
			}
			if (maxY < points.ypoints[i]) {
				maxY = points.ypoints[i];
			}
		}
		
		int strategicX = minX;
		int strategicY = minY;
		double minDistance = getTotalDistance(points, strategicX, strategicY);
		for (int i = minX; i <= maxX; i++) {
			for (int j = minY; j <= maxY; j++) {
				if (points.contains(new Point(i, j)) && (minDistance > getTotalDistance(points, i, j))) {
					minDistance = getTotalDistance(points, i, j);
					strategicX = i;
					strategicY = j;
				}
			}
		}		
		add(new CheckPoligon(points, strategicX, strategicY));
	}
	
	public static double getTotalDistance(Polygon p, int x, int y) {
		double result = 0;
		for (int i = 0; i < p.npoints; i++) {
			result += Math.sqrt((p.xpoints[i] - x) * (p.xpoints[i] - x) + (p.ypoints[i] - y) * (p.ypoints[i] - y));
		}
		return result;
	}
	
	public static void main(String[] args) {
		
		if ((args.length == 0) || (args.length / 2 == 1)) {
			JOptionPane.showMessageDialog(null, "Execute: java Exercise27 x1 y1 x2 y2 x3 y3 . . .", "Error", JOptionPane.ERROR_MESSAGE);	
			System.exit(1);
		}
		
		Polygon points = new Polygon();
		for (int i = 0; i < args.length; i += 2) {
			points.addPoint(Integer.parseInt(args[i]), Integer.parseInt(args[i + 1]));
		}
		
		Exercise27 frame = new Exercise27(points);
		frame.setSize(250, 150);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Exercise27");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}

class CheckPoligon extends JPanel {
	private Polygon p;
	private int strategicX;
	private int strategicY;
	
	public CheckPoligon(Polygon p, int strategicX, int strategicY) {
		 this.p = p;
		 this.strategicX = strategicX;
		 this.strategicY = strategicY;
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
		g.drawOval(strategicX - 3, strategicY - 3, 6, 6);
		g.drawString("(" + strategicX + ", " + strategicY + ")", strategicX - 3, strategicY - 3);
	}

}