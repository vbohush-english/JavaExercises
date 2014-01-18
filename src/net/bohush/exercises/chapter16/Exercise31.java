package net.bohush.exercises.chapter16;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Exercise31 extends JFrame{
	private JButton jbtPlus = new JButton("+1");
	private JButton jbtMinus = new JButton("-1");
	private RegularPolygonPanel regularPolygonPanel = new RegularPolygonPanel(5);
	
	private static final long serialVersionUID = 1L;

	public Exercise31() {
		for (int i = 5; i <= 10; i++) {
			add(new RegularPolygonPanel(i));	
		}
		
		JPanel panel1 = new JPanel();
		jbtPlus.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				regularPolygonPanel.addSide();
			}
		} );		
		panel1.add(jbtPlus);
		
		jbtMinus.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				regularPolygonPanel.subSide();
			}
		} );
		panel1.add(jbtMinus);
		add(panel1, BorderLayout.SOUTH);
		add(regularPolygonPanel, BorderLayout.CENTER);

		
	}
	
	public static void main(String[] args) {
		Exercise31 frame = new Exercise31();
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Exercise31");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	
	class RegularPolygonPanel extends JPanel {
		
		private int numberOfSides;
		private static final long serialVersionUID = 1L;
		
		public RegularPolygonPanel(int numberOfSides) {
			this.numberOfSides = numberOfSides;
		}
		
		public void addSide() {
			numberOfSides++;
			repaint();
		}
		
		public void subSide() {
			if (numberOfSides > 3) {
				numberOfSides--;
				repaint();
			}
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			int size = Math.min(getWidth(), getHeight());
			
			int xCenter = getWidth() / 2;
			int yCenter = getHeight() / 2;
			int radius = (int) (size * 0.4);
					
			Polygon polygon = new Polygon();
			
			for (int i = 1; i <= numberOfSides; i ++) {
				polygon.addPoint((int) (xCenter + radius * Math.cos(i * 2 * (Math.PI / numberOfSides))),
								 (int) (yCenter - radius * Math.sin(i * 2 * (Math.PI / numberOfSides))));
			}

			g.drawPolygon(polygon);
		}
	}
	
}

