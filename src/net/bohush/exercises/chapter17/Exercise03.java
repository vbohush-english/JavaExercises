package net.bohush.exercises.chapter17;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Exercise03 extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private TrafficLight trafficLight;
	
	public Exercise03() {
		JPanel controlPanel = new JPanel();
		JRadioButton jrbRed = new JRadioButton("Red");
		JRadioButton jrbYellow = new JRadioButton("Yellow");
		JRadioButton jrbGreen = new JRadioButton("Green");
		jrbRed.setMnemonic('R');
		jrbYellow.setMnemonic('Y');
		jrbGreen.setMnemonic('G');
		jrbRed.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				trafficLight.changeColor(TrafficLight.RED);
			}
		});
		jrbYellow.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				trafficLight.changeColor(TrafficLight.YELLOW);
			}
		});
		jrbGreen.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				trafficLight.changeColor(TrafficLight.GREEN);
			}
		});
		ButtonGroup figureGroup = new ButtonGroup();
		figureGroup.add(jrbRed);
		figureGroup.add(jrbYellow);
		figureGroup.add(jrbGreen);
		controlPanel.add(jrbRed);
		controlPanel.add(jrbYellow);
		controlPanel.add(jrbGreen);
		add(controlPanel, BorderLayout.SOUTH);		
		trafficLight = new TrafficLight();
		add(trafficLight, BorderLayout.CENTER);
		
	}

	public static void main(String[] args) {
		Exercise03 frame = new Exercise03();
		frame.setTitle("Exercise03");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	class TrafficLight extends JPanel {
		public static final int OFF = 0;
		public static final int RED = 1;
		public static final int YELLOW = 2;
		public static final int GREEN = 3;
		private int activeColor = OFF;
		
		public TrafficLight(int activeColor) {
			changeColor(activeColor);
		}
		
		public TrafficLight() {
			this(OFF);
		}
		
		public void changeColor(int newColor) {
			if ((newColor < 0) || (newColor > 3)) {
				newColor = 0;
			}
			activeColor = newColor;
			repaint();
		}
		
		private static final long serialVersionUID = 1L;
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			int size = Math.min(getWidth(), getHeight()) - 20;
			g.drawRect((getWidth() - size / 3) / 2, (getHeight() - size) / 2, size / 3, size);
			int diametr = size / 3 - 10;
			switch (activeColor) {
			case RED:g.setColor(Color.RED);break;
			case YELLOW:g.setColor(Color.YELLOW);break;
			case GREEN:g.setColor(Color.GREEN);break;
			}
			if (activeColor != OFF) {
				g.fillOval((getWidth() - size / 3) / 2 + 5, (getHeight() - size) / 2 + (activeColor - 1) * (size / 3) + 5, diametr, diametr);
			}
			g.setColor(Color.BLACK);
			for (int i = 0; i <= 2; i++) {
				g.drawOval((getWidth() - size / 3) / 2 + 5, (getHeight() - size) / 2 + i * (size / 3) + 5, diametr, diametr);
			}
		}
	}

}
