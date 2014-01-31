package net.bohush.exercises.chapter18;


import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class Exercise27 extends JApplet{
	private ArrayList<Point> points = new ArrayList<>();
	private ArrayList<Line> lines = new ArrayList<>();
	private Graph graph = new Graph();
	
	private static final long serialVersionUID = 1L;

	public Exercise27() {
		setLayout(new BorderLayout(5, 5));
		JPanel panel1 = new JPanel(new GridLayout(1, 5, 5, 5));
		panel1.setBorder(new LineBorder(Color.BLACK));
		panel1.add(new JLabel("INSTRUCTIONS"));
		panel1.add(new JLabel("Add: Left Click"));
		panel1.add(new JLabel("Move: Ctrl Drag"));
		panel1.add(new JLabel("Connect: Drag"));
		panel1.add(new JLabel("Remove: Right Click"));
		add(panel1, BorderLayout.NORTH);
		add(graph, BorderLayout.CENTER);		
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new Exercise27());
		frame.setTitle("Exercise27");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 600);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	class Line {
		public Point p1;
		public Point p2;
		public Line(Point p1, Point p2) {
			this.p1 = p1;
			this.p2 = p2;
		}
	}
	
	class Graph extends JPanel {

		private static final long serialVersionUID = 1L;
		private final int RADIUS = 20;
		private boolean isCreatingConnection = false;
		private int creatingFromConnectionNumber;
		private int creatingToConnectionNumber;
		private int draggingX;
		private int draggingY;
		
		private Point movingPoint;
		private int deltaX;
		private int deltaY;
		
		public Graph() {
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getButton() == MouseEvent.BUTTON1) {
						boolean toClose = false;
						for (int i = 0; i < points.size(); i++) {
							if (getLenght(new Point(e.getX(), e.getY()), points.get(i)) < RADIUS * 2) {
								toClose = true;
								break;
							}
						}
						if (!toClose) {
							points.add(new Point(e.getX(), e.getY()));
							repaint();							
						}					
					} else if (e.getButton() == MouseEvent.BUTTON3) {
						for (int i = 0; i < points.size(); i++) {
							if (getLenght(new Point(e.getX(), e.getY()), points.get(i)) < RADIUS) {
								for (int j = 0; j < lines.size(); j++) {
									if ((points.get(i).equals(lines.get(j).p1)) || (points.get(i).equals(lines.get(j).p2))) {
										lines.remove(j);
										j--;
									}
								}
								points.remove(i);
								repaint();
								break;
							}
						}
					}
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					super.mousePressed(e);
					for (int i = 0; i < points.size(); i++) {
						if (getLenght(new Point(e.getX(), e.getY()), points.get(i)) < RADIUS) {
							if (e.isControlDown()) {
								movingPoint = points.get(i);
								deltaX = movingPoint.x - e.getX();
								deltaY = movingPoint.y - e.getY();
							} else {
								isCreatingConnection = true;
								creatingFromConnectionNumber = i;
								draggingX = points.get(creatingFromConnectionNumber).x;
								draggingY = points.get(creatingFromConnectionNumber).y;
							}
							repaint();
							break;
						}
					}
				}
				
				@Override
				public void mouseReleased(MouseEvent e) {
					super.mouseReleased(e);
					if (isCreatingConnection) {
						for (int i = 0; i < points.size(); i++) {
							if (getLenght(new Point(e.getX(), e.getY()), points.get(i)) < RADIUS) {
								if (creatingFromConnectionNumber == i) {
									continue;
								}
								creatingToConnectionNumber = i;								
								lines.add(new Line(points.get(creatingFromConnectionNumber), points.get(creatingToConnectionNumber)));
								break;
							}
						}
						isCreatingConnection = false;
						repaint();
					}
				}
			});
			addMouseMotionListener(new MouseMotionListener() {
				
				@Override
				public void mouseMoved(MouseEvent e) {
				}
				
				@Override
				public void mouseDragged(MouseEvent e) {
					if (e.isControlDown()) {
						movingPoint.x = e.getX() + deltaX;
						movingPoint.y = e.getY() + deltaY;
						repaint();
					} else {
						if (isCreatingConnection) {
							draggingX = e.getX();
							draggingY = e.getY();
							repaint();
						}
					}
				}
			});
		}
		
	
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (isCreatingConnection) {
				g.drawLine(draggingX, draggingY, points.get(creatingFromConnectionNumber).x, points.get(creatingFromConnectionNumber).y);
			}
			for (int i = 0; i < lines.size(); i++) {
				g.drawLine(lines.get(i).p1.x, lines.get(i).p1.y, lines.get(i).p2.x, lines.get(i).p2.y);
			}
			if (points.size() > 0) {
				int minX = points.get(0).x;
				int minY = points.get(0).y;
				int maxX = points.get(0).x;
				int maxY = points.get(0).y;
				for (int i = 0; i < points.size(); i++) {
					if (points.get(i).y < minY) {
						minY = points.get(i).y;
					}
					if (points.get(i).x < minX) {
						minX = points.get(i).x;
					}
					if (points.get(i).y > maxY) {
						maxY = points.get(i).y;
					}
					if (points.get(i).x > maxX) {
						maxX = points.get(i).x;
					}
					g.setColor(Color.YELLOW);
					g.fillOval(points.get(i).x - RADIUS, points.get(i).y - RADIUS, 2 * RADIUS, 2 * RADIUS);
					g.setColor(Color.BLACK);
					g.drawOval(points.get(i).x - RADIUS, points.get(i).y - RADIUS, 2 * RADIUS, 2 * RADIUS);					
				}

			}
		}
		
		private double getLenght(Point p1, Point p2){
			return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
		}
	}
}
