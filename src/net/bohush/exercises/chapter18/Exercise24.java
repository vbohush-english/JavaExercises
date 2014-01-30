package net.bohush.exercises.chapter18;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Exercise24 extends JApplet{
	private int numberOfSlots = 5;
	private JTextField jTextField = new JTextField(numberOfSlots + "", 5);
	private JButton jButton1 = new JButton("Start");
	private JButton jButton2 = new JButton("Stop");
	private BeanMachine beanMachine = new BeanMachine(numberOfSlots);
	private JPanel jPanel2 = new JPanel(new BorderLayout());
	
	private static final long serialVersionUID = 1L;

	public Exercise24() {
		JPanel jPanel1 = new JPanel();
		jButton2.setEnabled(false);
		jPanel1.add(new JLabel("Number of slots: "));
		jPanel1.add(jTextField);
		jPanel1.add(jButton1);
		jPanel1.add(jButton2);
		add(jPanel1, BorderLayout.SOUTH);
		
		jPanel2.add(beanMachine, BorderLayout.CENTER);
		add(jPanel2, BorderLayout.CENTER);
		
		jButton1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				jButton1.setEnabled(false);
				jButton2.setEnabled(true);
				jTextField.setEditable(false);
				if (Integer.parseInt(jTextField.getText()) != numberOfSlots) {
					jPanel2.removeAll();
					jPanel2.repaint();
					jPanel2.setLayout(new BorderLayout());
					numberOfSlots = Integer.parseInt(jTextField.getText());
					beanMachine = new BeanMachine(numberOfSlots);
					jPanel2.add(beanMachine, BorderLayout.CENTER);
					jPanel2.updateUI();
				}
				beanMachine.start();
			}
		});
		jButton2.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				jButton2.setEnabled(false);
				jButton1.setEnabled(true);
				jTextField.setEditable(true);
				beanMachine.stop();
			}
		});
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new Exercise24());
		frame.setTitle("Exercise24");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	class BeanMachine extends JPanel {
		private final int NUMBER_OF_BALLS = 100000;
		private	int beenCount = 8;
		private static final long serialVersionUID = 1L;
		private int[][] mashine;
		private int nextBall = 0;
		private int nextStep = 0;
		private Timer timer;
		private boolean finished = false;
		

		public BeanMachine(int inputBeenCount) {
			this.beenCount = inputBeenCount;
			mashine = new int[NUMBER_OF_BALLS][beenCount - 1];
			for (int i = 0; i < NUMBER_OF_BALLS; i++) {
				for (int j = 0; j < beenCount - 1; j++) {
					mashine[i][j] = (int)(Math.random() * 2);
				}
			}
			timer = new Timer(100, new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					nextStep++;
					if (nextStep >= beenCount) {
						nextBall++;
						nextStep = 0;
						if (nextBall >= NUMBER_OF_BALLS) {
							finished = true;
							timer.stop();							
						}
					}
					repaint();
				}
			});
			
		}
		
		public void start() {
			timer.start();
		}
		
		public void stop() {
			timer.stop();
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			int size = Math.min(getWidth(), getHeight());
			int startX = (getWidth() - size) / 2;
			int startY = (getHeight() - size) / 2;
			
			g.setColor(Color.DARK_GRAY);
			
			g.drawLine(startX + 10, startY + size - 10, startX + size - 10, startY + size - 10);
			
			g.drawLine(startX + 10, startY + size - 10, startX + 10, startY + (int)(size * 0.8));
			g.drawLine(startX + size - 10, startY + (int)(size * 0.8), startX + size - 10, startY + size - 10);
			
			g.drawLine(startX + (int)(size * 0.4), startY + (int)(size * 0.2), startX + 10, startY + (int)(size * 0.8));
			g.drawLine(startX + size - 10, startY + (int)(size * 0.8), startX + (int)(size * 0.6), startY + (int)(size * 0.2));
			
			g.drawLine(startX + (int)(size * 0.4), startY + (int)(size * 0.2), startX + (int)(size * 0.4), startY + 10);
			g.drawLine(startX + (int)(size * 0.6), startY + 10, startX + (int)(size * 0.6), startY + (int)(size * 0.2));
			
			double step = (size - 20.0) / beenCount;
			for (int i = 1; i < beenCount; i++) {
				int nextX = (int)(i * step);
				g.drawLine(startX + 10 + nextX, startY + (int)(size * 0.8), startX + 10 + nextX, startY + size - 10);	
			}			
			
			g.setColor(Color.RED);
			int nextBX = startX;
			int nextBY = startY;
			int OVAL_DIAMETR = (int) (size * 0.02);
			for (int j = (beenCount - 1); j >= 1; j--) {
				for (int i = 1; i <= j; i++) {
					int nextX = (int)(i * step);
					g.fillOval(nextBX + 10 + nextX - OVAL_DIAMETR / 2,
							   nextBY + (int)(size * 0.8) - OVAL_DIAMETR / 2,
							   OVAL_DIAMETR,
							   OVAL_DIAMETR);
				}
				nextBY = startY - (int)((beenCount - j) * ((size * 0.6)) / (beenCount - 2));
				nextBX = startX + (int)((beenCount - j) * (step / 2.0));
			}
			
			g.setColor(Color.BLUE);
			int ballOvalDiametr = (int) (size * 0.04);
			if (!finished) {
				nextBX = startX  - (int) (step / 2);
				nextBY = startY;
				
				int position = 0;
				for (int j = (beenCount - 1); j >= 0; j--) {
					if (j == nextStep) {
						position = 0;
						for (int i = 0; i < j; i++) {
							if (mashine[nextBall][i] == 1) {
								position++;
							}
						}
						g.fillOval(nextBX + 10 + (int)((position + 1) * step) - ballOvalDiametr / 2, nextBY + (int)(size * 0.8) - ballOvalDiametr / 2, ballOvalDiametr, ballOvalDiametr);
					}
					nextBY = startY - (int)((beenCount - j) * ((size * 0.6)) / (beenCount - 2));
					nextBX = startX + (int)((beenCount - j) * (step / 2.0)) - (int) (step / 2);
				}
			}
			
			int[] balls = new int [beenCount];
			
			for (int i = 0; i < nextBall; i++) {
				int position = 0;
				for (int j = 0; j < mashine[i].length; j++) {
					if (mashine[i][j] == 1) {
						position++;
					}
				}
				balls[position]++;
			}
			
			for (int i = 0; i < beenCount; i++) {
				for (int j = 0; j < balls[i]; j++) {
					g.fillOval(startX + 10 + (int) (i * step) + (int) (step / 2) - ballOvalDiametr / 2,
							startY + size - 10 - (j + 1) * ballOvalDiametr,
							ballOvalDiametr,
							ballOvalDiametr);
				}
			}

		}		
	}	
}

