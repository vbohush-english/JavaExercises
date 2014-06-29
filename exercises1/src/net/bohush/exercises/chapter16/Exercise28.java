package net.bohush.exercises.chapter16;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class Exercise28 extends JFrame{

	private static final long serialVersionUID = 1L;
	private JButton jbtStart = new JButton("Start");
	private int count = 16;
	private RandomWalk randomWalk = new RandomWalk(count);
	public Exercise28() {
				
		JPanel panel1 = new JPanel();
		panel1.add(jbtStart);
		jbtStart.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Point> walkPoints = new ArrayList<>();
				Point nextPoit = new Point(count / 2, count / 2);
				walkPoints.add(nextPoit);
				boolean allowNextPoint = true;
				
				while (allowNextPoint) {
					
					Point[] tmpPoint = new Point[4];
					tmpPoint[0] = new Point(nextPoit.x - 1, nextPoit.y);
					tmpPoint[1] = new Point(nextPoit.x + 1, nextPoit.y);
					tmpPoint[2] = new Point(nextPoit.x, nextPoit.y + 1);
					tmpPoint[3] = new Point(nextPoit.x, nextPoit.y - 1);

					for (int i = 0; i < tmpPoint.length; i++) {
						int index = (int) (Math.random() * tmpPoint.length);
						Point temp = tmpPoint[i];
						tmpPoint[i] = tmpPoint[index];
						tmpPoint[index] = temp;
					}
					
					boolean allowNext = false;
					for (int i = 0; i < tmpPoint.length; i++) {
						if (!walkPoints.contains(tmpPoint[i])) {
							walkPoints.add(tmpPoint[i]);
							nextPoit = tmpPoint[i];
							allowNext = true;
							if ((nextPoit.x <= 0) || (nextPoit.x >= count) || (nextPoit.y <= 0) || (nextPoit.y >= count)) {
								allowNext = false;
							}
							break;							
						}
					}
					allowNextPoint = allowNext;
				}
				randomWalk.writeWalk(walkPoints);
			}
		});
		add(panel1, BorderLayout.SOUTH);
		add(randomWalk, BorderLayout.CENTER);
	}
	
	public static void main(String[] args) {
		Exercise28 frame = new Exercise28();
		frame.setTitle("Exercise28");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	class RandomWalk extends JPanel {
		private ArrayList<Point> walkPoints;
		private int count;
		private static final long serialVersionUID = 1L;

		public RandomWalk(int count) {
			this.count = count;
		}
		
		public void writeWalk(ArrayList<Point> walkPoints) {
			this.walkPoints = walkPoints;
			repaint();
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			double stepX = getWidth() / (double)count;
			double stepY = getHeight() / (double)count;
			g.setColor(Color.GREEN);
			for (int i = 0; i < count; i++) {
				g.drawLine(0, (int) (i * stepY), getWidth(), (int) (i * stepY));
				g.drawLine((int) (i * stepX), 0, (int) (i * stepX), getHeight());
			}
			g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
			g.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight());
			g.setColor(Color.BLACK);
			if (walkPoints != null) {
				Polygon p = new Polygon();
				for (int i = 0; i < walkPoints.size(); i++) {
					p.addPoint((int) (walkPoints.get(i).x * stepX), (int)(walkPoints.get(i).y * stepY));
				}
				g.drawPolyline(p.xpoints, p.ypoints, p.npoints);
			}
		}
	}
}
