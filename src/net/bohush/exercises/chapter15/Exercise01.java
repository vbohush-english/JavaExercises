package net.bohush.exercises.chapter15;

import java.awt.*;

import javax.swing.*;

public class Exercise01  extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public Exercise01() {
		setLayout(new FlowLayout());
		add(new drawSquare());
		add(new drawSin());
		add(new drawCos());
		add(new drawTan());
		add(new drawFun1());
		add(new drawFun2());
		add(new drawFun3());
	}

	public static void main(String[] args){
		Exercise01 frame = new Exercise01();
		frame.setSize(1700, 900);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Exercise01");
		frame.setVisible(true);

	}

}

class drawSquare extends AbstractDrawFunction {

	private static final long serialVersionUID = 1L;

	@Override
	double f(double x) {
		return x * x * 0.01;
	}
}

class drawSin extends AbstractDrawFunction {

	private static final long serialVersionUID = 1L;

	@Override
	double f(double x) {
		return 50 * Math.sin((x / 100.0) * 2 * Math.PI);
	}
}

class drawCos extends AbstractDrawFunction {

	private static final long serialVersionUID = 1L;

	@Override
	double f(double x) {
		return 50 * Math.cos((x / 100.0) * 2 * Math.PI);
	}
}


class drawTan extends AbstractDrawFunction {

	private static final long serialVersionUID = 1L;

	@Override
	double f(double x) {
		return 50 * Math.tan((x / 100.0) * 2 * Math.PI);
	}
}


class drawFun1 extends AbstractDrawFunction {

	private static final long serialVersionUID = 1L;

	@Override
	double f(double x) {
		return 50 * Math.cos((x / 100.0) * 2 * Math.PI) + 5 * 50 * Math.sin((x / 100.0) * 2 * Math.PI);
	}
}

class drawFun2 extends AbstractDrawFunction {

	private static final long serialVersionUID = 1L;

	@Override
	double f(double x) {
		return 5 * 50 * Math.cos((x / 100.0) * 2 * Math.PI) + 50 * Math.sin((x / 100.0) * 2 * Math.PI);
	}
}

class drawFun3 extends AbstractDrawFunction {

	private static final long serialVersionUID = 1L;

	@Override
	double f(double x) {
		return Math.log(x * 0.01) + x * x * 0.01;
	}
}

abstract class AbstractDrawFunction extends JPanel {

	private static final long serialVersionUID = 1L;
	/** Polygon to hold the points */
	private Polygon p = new Polygon();

	protected AbstractDrawFunction() {
		drawFunction();
	}

	/** Return the y-coordinate */
	abstract double f(double x);

	/** Obtain points for x-coordinates 100, 101, . . ., 300 */
	public void drawFunction() {
		for (int x = -190; x <= 190; x++) {
			p.addPoint(x + 200, 200 - (int) f(x));
		}
	}

	@Override
	/** Draw axes, labels, and connect points */
	protected void paintComponent(Graphics g) {
		g.drawPolyline(p.xpoints, p.ypoints, p.npoints);
		g.drawLine(10, 200, 390, 200);
		g.drawLine(370, 210, 390, 200);
		g.drawLine(370, 190, 390, 200);
		g.drawString("X", 390, 190);
		g.drawLine(200, 10, 200, 390);
		g.drawString("Y", 210, 10);
		g.drawLine(190, 30, 200, 10);
		g.drawLine(210, 30, 200, 10);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(400, 400);
	}
}