package net.bohush.exercises.chapter18;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Exercise29 extends JApplet{
	private static final long serialVersionUID = 1L;
	private JTextField jTextField1 = new JTextField("143");
	private JTextField jTextField2 = new JTextField("49");
	private JTextField jTextField3 = new JTextField("70");
	private JTextField jTextField4 = new JTextField("50");
	private JTextField jTextField5 = new JTextField("90");
	private JTextField jTextField6 = new JTextField("50");
	private JTextField jTextField7 = new JTextField("50");
	private JTextField jTextField8 = new JTextField("70");
	private JButton button = new JButton("Redraw Rectangles");
	private JLabel jLabel1 = new JLabel("Two rectangles intersect?");
	private MyRectangle2D rectangle1;
	private MyRectangle2D rectangle2;
	
	public Exercise29() {
		JPanel panel1 = new JPanel(new GridLayout(4, 2, 5, 5));
		panel1.setBorder(new TitledBorder("Enter rectangle 1 info"));
		panel1.add(new JLabel("Center x: "));
		panel1.add(jTextField1);
		panel1.add(new JLabel("Center y: "));		
		panel1.add(jTextField2);
		panel1.add(new JLabel("Width: "));		
		panel1.add(jTextField3);
		panel1.add(new JLabel("Height: "));		
		panel1.add(jTextField4);
		
		JPanel panel2 = new JPanel(new GridLayout(4, 2, 5, 5));
		panel2.setBorder(new TitledBorder("Enter rectangle 2 info"));
		panel2.add(new JLabel("Center x: "));		
		panel2.add(jTextField5);
		panel2.add(new JLabel("Center y: "));		
		panel2.add(jTextField6);
		panel2.add(new JLabel("Width: "));		
		panel2.add(jTextField7);
		panel2.add(new JLabel("Height: "));		
		panel2.add(jTextField8);
		
		JPanel panel4 = new JPanel(new GridLayout(1, 1, 5, 5));
		panel4.add(button);

		JPanel panel3 = new JPanel(new GridLayout(1, 2, 5, 5));
		panel3.add(panel1);
		panel3.add(panel2);
		
		JPanel panel5 = new JPanel(new BorderLayout(5, 5));
		panel5.add(panel3, BorderLayout.CENTER);
		panel5.add(panel4, BorderLayout.SOUTH);

		jLabel1.setHorizontalAlignment(JLabel.CENTER);
		
		Rectangle2DPanel rectangle2DPanel = new Rectangle2DPanel();
		
		add(jLabel1, BorderLayout.NORTH);
		add(rectangle2DPanel, BorderLayout.CENTER);
		add(panel5, BorderLayout.SOUTH);
		
		paintRectangle();
		
		button.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				paintRectangle();				
			}
		});
	}
	
	public void paintRectangle() {
		try {
			int x1 = Integer.parseInt(jTextField1.getText());
			int y1 = Integer.parseInt(jTextField2.getText());
			int w1 = Integer.parseInt(jTextField3.getText());
			int h1 = Integer.parseInt(jTextField4.getText());
			int x2 = Integer.parseInt(jTextField5.getText());
			int y2 = Integer.parseInt(jTextField6.getText());
			int w2 = Integer.parseInt(jTextField7.getText());
			int h2 = Integer.parseInt(jTextField8.getText());
			rectangle1 = new MyRectangle2D(x1, y1, w1, h1);
			rectangle2 = new MyRectangle2D(x2, y2, w2, h2);
			repaint();
			jLabel1.setText("Two rectangles intersect? " + (rectangle1.overlaps(rectangle2) ? "Yes" : "No"));
		} catch (NumberFormatException e) {
		}
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new Exercise29());
		frame.setTitle("Exercise29");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 400);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	class Rectangle2DPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private boolean moving1 = false;
		private boolean moving2 = false;
		private int dx;
		private int dy;
		
		public Rectangle2DPanel() {
			addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					moving1 = false;
					moving2 = false;
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					if (rectangle1.contains(e.getX(), e.getY())) {
						dx = (int)rectangle1.getX() - e.getX();
						dy = (int)rectangle1.getY() - e.getY();
						moving1 = true;
					} else if (rectangle2.contains(e.getX(), e.getY())) {
						dx = (int)rectangle2.getX() - e.getX();
						dy = (int)rectangle2.getY() - e.getY();
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
						rectangle1.setX(e.getX() + dx);
						rectangle1.setY(e.getY() + dy);
						jTextField1.setText((int)(rectangle1.getX()) + "");
						jTextField2.setText((int)(rectangle1.getY()) + "");
						paintRectangle();
					} else if (moving2) {
						rectangle2.setX(e.getX() + dx);
						rectangle2.setY(e.getY() + dy);
						jTextField5.setText((int)(rectangle2.getX()) + "");
						jTextField6.setText((int)(rectangle2.getY()) + "");
						paintRectangle();
					}
				}
			});
		}
		
	
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (rectangle1 != null) {
				g.drawRect((int)rectangle1.getX() - (int)rectangle1.getWidth() / 2, (int)rectangle1.getY() - (int)rectangle1.getHeight() / 2, (int)rectangle1.getWidth(), (int)rectangle1.getHeight());
				g.drawString("r1", (int)rectangle1.getX(), (int)rectangle1.getY());
			}
			if (rectangle2 != null) {
				g.drawRect((int)rectangle2.getX() - (int)rectangle2.getWidth() / 2, (int)rectangle2.getY() - (int)rectangle2.getHeight() / 2, (int)rectangle2.getWidth(), (int)rectangle2.getHeight());
				g.drawString("r2", (int)rectangle2.getX(), (int)rectangle2.getY());
			}
		}
		
	}

	class MyRectangle2D {
		private double x;
		private double y;
		private double width;
		private double height;
		
		public MyRectangle2D(double x, double y, double width, double height) {
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
		}
		
		public MyRectangle2D() {
			this(0, 0, 1, 1);
		}
		
		public double getPerimeter() {
			return 2 * (width + height);
		}
		
		public double getArea() {
			return width * height;
		}
		
		public boolean contains(double x, double y) {
			double x1 = this.x - width / 2;
			double x2 = this.x + width / 2;
			double y1 = this.y - height / 2;
			double y2 = this.y + height / 2;
			if ((x >= x1) && (x <= x2) && (y >= y1) && (y <= y2)) {
				return true;
			} else {
				return false;
			}
		}
		
		public boolean contains(MyRectangle2D r) {
			if ((((this.width - r.width) / 2) >= Math.abs(this.x - r.x)) && (((this.height - r.height) / 2) >= Math.abs(this.y - r.y))) {
				return true;
			} else {
				return false;
			}
		}
		public boolean overlaps(MyRectangle2D r) {
			if ((((this.width + r.width) / 2) >= Math.abs(this.x - r.x)) && (((this.height + r.height) / 2) >= Math.abs(this.y - r.y))) {
				return true;
			} else {
				return false;
			}
		}
		
		public double getX() {
			return x;
		}
		public void setX(double x) {
			this.x = x;
		}
		public double getY() {
			return y;
		}
		public void setY(double y) {
			this.y = y;
		}
		public double getWidth() {
			return width;
		}
		public void setWidth(double width) {
			this.width = width;
		}
		public double getHeight() {
			return height;
		}
		public void setHeight(double height) {
			this.height = height;
		}
	}
}
