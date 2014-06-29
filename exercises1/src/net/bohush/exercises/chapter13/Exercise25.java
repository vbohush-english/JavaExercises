package net.bohush.exercises.chapter13;

import java.awt.*;

import javax.swing.*;

public class Exercise25 extends JFrame{

	private static final long serialVersionUID = 1L;

	public Exercise25() {
		setLayout(new GridLayout(1, 6, 10, 10));
		for (int i = 5; i <= 10; i++) {
			add(new RegularPolygonPanel(i));	
		}
		
	}
	
	public static void main(String[] args) {
		Exercise25 frame = new Exercise25();
		frame.setSize(600, 150);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Exercise25");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}

class RegularPolygonPanel extends JPanel {
	
	private int numberOfSides;
	
	public RegularPolygonPanel(int numberOfSides) {
		this.numberOfSides = numberOfSides;
	}

	private static final long serialVersionUID = 1L;

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