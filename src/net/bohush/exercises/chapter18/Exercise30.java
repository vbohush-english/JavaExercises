package net.bohush.exercises.chapter18;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.geom.Line2D;

public class Exercise30 extends JApplet {
	private static final long serialVersionUID = 1L;
	private JTextField jTextField1 = new JTextField("0");
	private JTextField jTextField2 = new JTextField("100");
	private JTextField jTextField3 = new JTextField("100");
	private JTextField jTextField4 = new JTextField("0");
	private JTextField jTextField5 = new JTextField("100");
	private JTextField jTextField6 = new JTextField("100");
	private JTextField jTextField7 = new JTextField("0");
	private JTextField jTextField8 = new JTextField("20");
	private JTextField jTextField9 = new JTextField("20");
	private JTextField jTextField10 = new JTextField("0");
	private JTextField jTextField11 = new JTextField("20");
	private JTextField jTextField12 = new JTextField("20");
	
	private JButton button = new JButton("Redraw Triangles");
	private JLabel jLabel1 = new JLabel("Two triangles intersect?");
	private Triangle2D triangle1;
	private Triangle2D triangle2;

	public Exercise30() {
		JPanel panel1 = new JPanel(new GridLayout(6, 2, 5, 5));
		panel1.setBorder(new TitledBorder("Enter triangle 1 info"));
		panel1.add(new JLabel("p1.x: "));
		panel1.add(jTextField1);
		panel1.add(new JLabel("p1.y: "));
		panel1.add(jTextField2);
		panel1.add(new JLabel("p2.x: "));
		panel1.add(jTextField3);
		panel1.add(new JLabel("p2.y: "));
		panel1.add(jTextField4);
		panel1.add(new JLabel("p3.x: "));
		panel1.add(jTextField5);
		panel1.add(new JLabel("p3.y: "));
		panel1.add(jTextField6);
		
		JPanel panel2 = new JPanel(new GridLayout(6, 2, 5, 5));
		panel2.setBorder(new TitledBorder("Enter triangle 2 info"));
		panel2.add(new JLabel("p1.x: "));
		panel2.add(jTextField7);
		panel2.add(new JLabel("p1.y: "));
		panel2.add(jTextField8);
		panel2.add(new JLabel("p2.x: "));
		panel2.add(jTextField9);
		panel2.add(new JLabel("p2.y: "));
		panel2.add(jTextField10);
		panel2.add(new JLabel("p3.x: "));
		panel2.add(jTextField11);
		panel2.add(new JLabel("p3.y: "));
		panel2.add(jTextField12);

		JPanel panel4 = new JPanel(new GridLayout(1, 1, 5, 5));
		panel4.add(button);

		JPanel panel3 = new JPanel(new GridLayout(1, 2, 5, 5));
		panel3.add(panel1);
		panel3.add(panel2);

		JPanel panel5 = new JPanel(new BorderLayout(5, 5));
		panel5.add(panel3, BorderLayout.CENTER);
		panel5.add(panel4, BorderLayout.SOUTH);

		jLabel1.setHorizontalAlignment(JLabel.CENTER);

		Triangle2DPanel triangle2DPanel = new Triangle2DPanel();

		add(jLabel1, BorderLayout.NORTH);
		add(triangle2DPanel, BorderLayout.CENTER);
		add(panel5, BorderLayout.SOUTH);

		paintTriangle();

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paintTriangle();
			}
		});
	}

	public void paintTriangle() {
		try {
			int p1x = Integer.parseInt(jTextField1.getText());
			int p1y = Integer.parseInt(jTextField2.getText());
			int p2x = Integer.parseInt(jTextField3.getText());
			int p2y = Integer.parseInt(jTextField4.getText());
			int p3x = Integer.parseInt(jTextField5.getText());
			int p3y = Integer.parseInt(jTextField6.getText());
			int p4x = Integer.parseInt(jTextField7.getText());
			int p4y = Integer.parseInt(jTextField8.getText());
			int p5x = Integer.parseInt(jTextField9.getText());
			int p5y = Integer.parseInt(jTextField10.getText());
			int p6x = Integer.parseInt(jTextField11.getText());
			int p6y = Integer.parseInt(jTextField12.getText());
			triangle1 = new Triangle2D(p1x, p1y, p2x, p2y, p3x, p3y);
			triangle2 = new Triangle2D(p4x, p4y, p5x, p5y, p6x, p6y);
			repaint();
			jLabel1.setText("Two triangles intersect? " + (triangle1.overlaps(triangle2) ? "Yes" : "No"));
		} catch (NumberFormatException e) {
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new Exercise30());
		frame.setTitle("Exercise30");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 400);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	class Triangle2DPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private boolean moving1 = false;
		private boolean moving2 = false;
		private int d1x;
		private int d1y;
		private int d2x;
		private int d2y;
		private int d3x;
		private int d3y;
		
		public Triangle2DPanel() {
			addMouseListener(new MouseAdapter() {

				@Override
				public void mouseReleased(MouseEvent e) {
					moving1 = false;
					moving2 = false;
				}

				@Override
				public void mousePressed(MouseEvent e) {
					if (triangle1.contains(new MyPoint(e.getX(), e.getY()))) {
						d1x = (int) triangle1.getP1().getX() - e.getX();
						d1y = (int) triangle1.getP1().getY() - e.getY();
						d2x = (int) triangle1.getP2().getX() - e.getX();
						d2y = (int) triangle1.getP2().getY() - e.getY();
						d3x = (int) triangle1.getP3().getX() - e.getX();
						d3y = (int) triangle1.getP3().getY() - e.getY();
						moving1 = true;
					} else if (triangle2.contains(new MyPoint(e.getX(), e.getY()))) {
						d1x = (int) triangle2.getP1().getX() - e.getX();
						d1y = (int) triangle2.getP1().getY() - e.getY();
						d2x = (int) triangle2.getP2().getX() - e.getX();
						d2y = (int) triangle2.getP2().getY() - e.getY();
						d3x = (int) triangle2.getP3().getX() - e.getX();
						d3y = (int) triangle2.getP3().getY() - e.getY();
						moving2 = true;
					}
				}

			});

			addMouseMotionListener(new MouseMotionListener() {

				@Override
				public void mouseMoved(MouseEvent e) {
				}

				@Override
				public void mouseDragged(MouseEvent e) {
					if (moving1) {
						jTextField1.setText((e.getX() + d1x) + "");
						jTextField2.setText((e.getY() + d1y) + "");
						jTextField3.setText((e.getX() + d2x) + "");
						jTextField4.setText((e.getY() + d2y) + "");
						jTextField5.setText((e.getX() + d3x) + "");
						jTextField6.setText((e.getY() + d3y) + "");
						paintTriangle();
						
					} else if (moving2) {
						jTextField7.setText((e.getX() + d1x) + "");
						jTextField8.setText((e.getY() + d1y) + "");
						jTextField9.setText((e.getX() + d2x) + "");
						jTextField10.setText((e.getY() + d2y) + "");
						jTextField11.setText((e.getX() + d3x) + "");
						jTextField12.setText((e.getY() + d3y) + "");
						paintTriangle();
					}
				}
			});
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (triangle1 != null) {
				Polygon p = new Polygon();
				p.addPoint((int)(triangle1.getP1().getX()),  (int)(triangle1.getP1().getY()));
				p.addPoint((int)(triangle1.getP2().getX()),  (int)(triangle1.getP2().getY()));
				p.addPoint((int)(triangle1.getP3().getX()),  (int)(triangle1.getP3().getY()));
				g.drawPolygon(p);
				}
			if (triangle2 != null) {
				Polygon p = new Polygon();
				p.addPoint((int)(triangle2.getP1().getX()),  (int)(triangle2.getP1().getY()));
				p.addPoint((int)(triangle2.getP2().getX()),  (int)(triangle2.getP2().getY()));
				p.addPoint((int)(triangle2.getP3().getX()),  (int)(triangle2.getP3().getY()));
				g.drawPolygon(p);
			}
		}

	}

	class Triangle2D {
		private MyPoint p1;
		private MyPoint p2;
		private MyPoint p3;

		public MyPoint getP1() {
			return p1;
		}

		public MyPoint getP2() {
			return p2;
		}

		public MyPoint getP3() {
			return p3;
		}

		public void setP1(MyPoint p1) {
			this.p1 = p1;
		}

		public void setP2(MyPoint p2) {
			this.p2 = p2;
		}

		public void setP3(MyPoint p3) {
			this.p3 = p3;
		}

		public Triangle2D(MyPoint p1, MyPoint p2, MyPoint p3) {
			this.p1 = p1;
			this.p2 = p2;
			this.p3 = p3;
		}

		public Triangle2D(double p1x, double p1y, double p2x, double p2y,
				double p3x, double p3y) {
			this(new MyPoint(p1x, p1y), new MyPoint(p2x, p2y), new MyPoint(p3x,
					p3y));
		}

		public Triangle2D() {
			this(0, 0, 1, 1, 2, 5);
		}

		private double getSide1() {
			return p2.distance(p3);
		}

		private double getSide2() {
			return p1.distance(p3);
		}

		private double getSide3() {
			return p1.distance(p2);
		}

		public double getPerimeter() {
			return getSide1() + getSide2() + getSide3();
		}

		public double getArea() {
			double s = (getSide1() + getSide2() + getSide3()) / 2;
			return Math.sqrt(s * (s - getSide1()) * (s - getSide2())
					* (s - getSide3()));
		}

		public boolean contains(MyPoint p) {

			Line2D.Double dashedLine1 = new Line2D.Double(p.getX(), p.getY(),
					p1.getX(), p1.getY());
			Line2D.Double dashedLine2 = new Line2D.Double(p.getX(), p.getY(),
					p2.getX(), p2.getY());
			Line2D.Double dashedLine3 = new Line2D.Double(p.getX(), p.getY(),
					p3.getX(), p3.getY());
			Line2D.Double line1 = new Line2D.Double(p2.getX(), p2.getY(),
					p3.getX(), p3.getY());
			Line2D.Double line2 = new Line2D.Double(p1.getX(), p1.getY(),
					p3.getX(), p3.getY());
			Line2D.Double line3 = new Line2D.Double(p2.getX(), p2.getY(),
					p1.getX(), p1.getY());

			if (dashedLine1.intersectsLine(line1)) {
				return false;
			}
			if (dashedLine2.intersectsLine(line2)) {
				return false;
			}
			if (dashedLine3.intersectsLine(line3)) {
				return false;
			}

			double maxX = getMax(p1.getX(), p2.getX(), p3.getX());
			double maxY = getMax(p1.getY(), p2.getY(), p3.getY());
			double minX = getMin(p1.getX(), p2.getX(), p3.getX());
			double minY = getMin(p1.getY(), p2.getY(), p3.getY());
			if ((p.getX() > maxX) || (p.getX() < minX) || (p.getY() > maxY)
					|| (p.getY() < minY)) {
				return false;
			}
			return true;
		}

		private double getMax(double n1, double n2, double n3) {
			double max;
			if (n1 > n2) {
				max = n1;
			} else {
				max = n2;
			}
			if (n3 > max) {
				max = n3;
			}
			return max;
		}

		private double getMin(double n1, double n2, double n3) {
			double min;
			if (n1 < n2) {
				min = n1;
			} else {
				min = n2;
			}
			if (n3 < min) {
				min = n3;
			}
			return min;
		}

		public boolean contains(Triangle2D t) {
			if ((contains(t.getP1()) && contains(t.getP2()) && contains(t.getP3())) ||
				(t.contains(p1) && t.contains(p2) && t.contains(p3))) {
				return true;
			} else {
				return false;
			}
		}

		public boolean overlaps(Triangle2D t) {
			if (contains(t)) {
				return true;
			}
			Line2D.Double line1 = new Line2D.Double(p2.getX(), p2.getY(), p3.getX(), p3.getY());
			Line2D.Double line2 = new Line2D.Double(p1.getX(), p1.getY(), p3.getX(), p3.getY());
			Line2D.Double line3 = new Line2D.Double(p2.getX(), p2.getY(), p1.getX(), p1.getY());

			Line2D.Double tLine1 = new Line2D.Double(t.getP2().getX(), t.getP2().getY(), t.getP3().getX(), t.getP3().getY());
			Line2D.Double tLine2 = new Line2D.Double(t.getP1().getX(), t.getP1().getY(), t.getP3().getX(), t.getP3().getY());
			Line2D.Double tLine3 = new Line2D.Double(t.getP2().getX(), t.getP2().getY(), t.getP1().getX(), t.getP1().getY());

			if (line1.intersectsLine(tLine1)
					|| line1.intersectsLine(tLine2)
					|| line1.intersectsLine(tLine3)
					|| line2.intersectsLine(tLine1)
					|| line2.intersectsLine(tLine2)
					|| line2.intersectsLine(tLine3)
					|| line3.intersectsLine(tLine1)
					|| line3.intersectsLine(tLine2)
					|| line3.intersectsLine(tLine3)) {
				return true;
			} else {
				return false;
			}
		}
	}

	class MyPoint {
		private double x;
		private double y;

		public double getX() {
			return x;
		}

		public double getY() {
			return y;
		}

		public void setX(double x) {
			this.x = x;
		}

		public void setY(double y) {
			this.y = y;
		}

		public MyPoint(double x, double y) {
			this.x = x;
			this.y = y;
		}

		public MyPoint() {
			this(0, 0);
		}

		public double distance(MyPoint point) {
			return Math.sqrt((point.x - x) * (point.x - x) + (point.y - y)
					* (point.y - y));
		}

		public double distance(double x, double y) {
			return distance(new MyPoint(x, y));
		}
	}
}
