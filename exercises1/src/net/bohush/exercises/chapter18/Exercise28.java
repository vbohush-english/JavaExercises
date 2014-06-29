package net.bohush.exercises.chapter18;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Exercise28 extends JApplet{
	private static final long serialVersionUID = 1L;
	private JTextField jTextField1 = new JTextField("65");
	private JTextField jTextField2 = new JTextField("39");
	private JTextField jTextField3 = new JTextField("30");
	private JTextField jTextField4 = new JTextField("121");
	private JTextField jTextField5 = new JTextField("30");
	private JTextField jTextField6 = new JTextField("20");
	private JButton button = new JButton("Redraw Circle");
	private JLabel jLabel1 = new JLabel("Two circles intersect?");
	private Circle2D circle1;
	private Circle2D circle2;
	
	public Exercise28() {
		JPanel panel1 = new JPanel(new GridLayout(3, 2, 5, 5));
		panel1.setBorder(new TitledBorder("Enter circle 1 info"));
		panel1.add(new JLabel("Center x: "));
		panel1.add(jTextField1);
		panel1.add(new JLabel("Center y: "));		
		panel1.add(jTextField2);
		panel1.add(new JLabel("Radius: "));		
		panel1.add(jTextField3);
		
		JPanel panel2 = new JPanel(new GridLayout(3, 2, 5, 5));
		panel2.setBorder(new TitledBorder("Enter circle 2 info"));
		panel2.add(new JLabel("Center x: "));		
		panel2.add(jTextField4);
		panel2.add(new JLabel("Center y: "));		
		panel2.add(jTextField5);
		panel2.add(new JLabel("Radius: "));		
		panel2.add(jTextField6);
		
		JPanel panel4 = new JPanel(new GridLayout(1, 1, 5, 5));
		panel4.add(button);

		JPanel panel3 = new JPanel(new GridLayout(1, 2, 5, 5));
		panel3.add(panel1);
		panel3.add(panel2);
		
		JPanel panel5 = new JPanel(new BorderLayout(5, 5));
		panel5.add(panel3, BorderLayout.CENTER);
		panel5.add(panel4, BorderLayout.SOUTH);

		jLabel1.setHorizontalAlignment(JLabel.CENTER);
		
		Circle2DPanel circle2dPanel = new Circle2DPanel();
		
		add(jLabel1, BorderLayout.NORTH);
		add(circle2dPanel, BorderLayout.CENTER);
		add(panel5, BorderLayout.SOUTH);
		
		paintCircle();
		
		button.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				paintCircle();				
			}
		});
	}
	
	public void paintCircle() {
		try {
			int x1 = Integer.parseInt(jTextField1.getText());
			int y1 = Integer.parseInt(jTextField2.getText());
			int r1 = Integer.parseInt(jTextField3.getText());
			int x2 = Integer.parseInt(jTextField4.getText());
			int y2 = Integer.parseInt(jTextField5.getText());
			int r2 = Integer.parseInt(jTextField6.getText());
			circle1 = new Circle2D(x1, y1, r1);
			circle2 = new Circle2D(x2, y2, r2);
			repaint();
			jLabel1.setText("Two circles intersect? " + (circle1.overlaps(circle2) ? "Yes" : "No"));
		} catch (NumberFormatException e) {
		}
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new Exercise28());
		frame.setTitle("Exercise28");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 400);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	class Circle2DPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private boolean moving1 = false;
		private boolean moving2 = false;
		private int dx;
		private int dy;
		
		public Circle2DPanel() {
			addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					moving1 = false;
					moving2 = false;
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					if (getLenght(e.getX(), e.getY(), (int)circle1.getX(), (int)circle1.getY()) < circle1.getRadius()) {
						dx = (int)circle1.getX() - e.getX();
						dy = (int)circle1.getY() - e.getY();
						moving1 = true;
					} else if (getLenght(e.getX(), e.getY(), (int)circle2.getX(), (int)circle2.getY()) < circle2.getRadius()) {
						dx = (int)circle2.getX() - e.getX();
						dy = (int)circle2.getY() - e.getY();
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
						circle1.setX(e.getX() + dx);
						circle1.setY(e.getY() + dy);
						jTextField1.setText((int)(circle1.getX()) + "");
						jTextField2.setText((int)(circle1.getY()) + "");
						paintCircle();
					} else if (moving2) {
						circle2.setX(e.getX() + dx);
						circle2.setY(e.getY() + dy);
						jTextField4.setText((int)(circle2.getX()) + "");
						jTextField5.setText((int)(circle2.getY()) + "");
						paintCircle();
					}
				}
			});
		}
		
		private double getLenght(int x1, int y1, int x2, int y2){
			return getLenght(new Point(x1, y1), new Point(x2, y2));
		}
		
		private double getLenght(Point p1, Point p2){
			return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (circle1 != null) {
				g.drawOval((int)circle1.getX() - (int)circle1.getRadius(), (int)circle1.getY() - (int)circle1.getRadius(), (int)circle1.getRadius() * 2, (int)circle1.getRadius() * 2);
				g.drawString("c1", (int)circle1.getX(), (int)circle1.getY());
			}
			if (circle2 != null) {
				g.drawOval((int)circle2.getX() - (int)circle2.getRadius(), (int)circle2.getY() - (int)circle2.getRadius(), (int)circle2.getRadius() * 2, (int)circle2.getRadius() * 2);
				g.drawString("c2", (int)circle2.getX(), (int)circle2.getY());
			}
		}
		
	}

	class Circle2D {
		private double x;
		public void setX(double x) {
			this.x = x;
		}

		public void setY(double y) {
			this.y = y;
		}

		public void setRadius(double radius) {
			this.radius = radius;
		}

		private double y;
		private double radius;
		
		public Circle2D(double x, double y, double radius) {
			this.x = x;
			this.y = y;
			this.radius = radius;
		}
		
		public Circle2D() {
			this(0, 0, 1.0);
		}
		
		public double getX() {
			return x;
		}
		
		public double getY() {
			return y;
		}
		
		public double getRadius() {
			return radius;
		}
		
		public double getArea() {
			return radius * radius * Math.PI;
		}
		
		public double getPerimeter() {
			return 2 * radius * Math.PI;
		}
			
		public boolean contains(double x2, double y2) {
			double distance = Math.sqrt((x2 - x) * (x2 - x) + (y2 - y) * (y2 - y));
			if (distance > radius) {
				return false;
			} else {
				return true;
			}
		}
		
		public boolean contains(Circle2D circle) {
			double distance = Math.sqrt((circle.getX() - x) * (circle.getX() - x) + (circle.getY() - y) * (circle.getY() - y));
			if (distance <= radius - circle.getRadius()) {
				return true;
			} else {
				return false;
			}
		}
		
		public boolean overlaps(Circle2D circle) {
			double distance = Math.sqrt((circle.getX() - x) * (circle.getX() - x) + (circle.getY() - y) * (circle.getY() - y));
			if (distance <= (circle.getRadius() + radius)) {
				return true;
			} else {
				return false;
			}
		}
		
	}
}
