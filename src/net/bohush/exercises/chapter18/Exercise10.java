package net.bohush.exercises.chapter18;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.*;

public class Exercise10 extends JFrame{

	private static final long serialVersionUID = 1L;

	public Exercise10() {
		JPanel panel1 = new DrawTemp();
		add(panel1, BorderLayout.CENTER);
		JLabel label = new JLabel("Current time: " + new Date().toString());
		label.setHorizontalAlignment(JLabel.CENTER);
		add(label, BorderLayout.SOUTH);
	}
	
	public static void main(String[] args) {
		Exercise10 frame = new Exercise10();
		frame.setTitle("Exercise10");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(600, 200);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	class DrawTemp extends JPanel {
		private boolean firstRun = true;
		private boolean stopRun = false;
		private int prevTemperature;
		private static final long serialVersionUID = 1L;
		private int currentStep = 0;
		
		public DrawTemp() {
			Timer timer = new Timer(1000, new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					repaint();
				}
			});
			timer.start();
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			int width =  getWidth();
			int height =  getHeight();
			double stepSize = (width - 40) / 24.0;
			if (firstRun) {
				g.drawLine(20, height - 20, width - 20, height - 20);
				for (int i = 0; i < 24; i++) {
					g.drawString(i + "", (int)(20 + stepSize / 2) + (int)(stepSize * i), height - 5);
				}
				firstRun = false;
			} else if (!stopRun) {
				int temperature = 20 + (int) (Math.random() * (height - 40));
				g.fillRect((int)(20 + stepSize / 2) + (int)(stepSize * currentStep),
						temperature,
						(int)(stepSize / 2),
						height - temperature - 20);
				if (currentStep > 0) {
					int prevStep = currentStep - 1;
					g.setColor(getBackground());
					g.fillRect((int)(20 + stepSize / 2) + (int)(stepSize * prevStep),
							prevTemperature,
							(int)(stepSize / 2),
							height - prevTemperature - 20);
					g.setColor(Color.BLACK);
					g.drawRect((int)(20 + stepSize / 2) + (int)(stepSize * prevStep),
							prevTemperature,
							(int)(stepSize / 2),
							height - prevTemperature - 20);
				}
				currentStep++;
				prevTemperature = temperature;
				if (currentStep >= 24) {
					stopRun = true;
				}
			}
		}
	}

}
