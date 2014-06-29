package net.bohush.exercises.chapter25;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class Exercise16 extends JApplet {

	private static final long serialVersionUID = 1L;
	private BubbleSortPanel bubbleSortPanel = new BubbleSortPanel();
	private JButton jButton1 = new JButton("Step");
	
	public Exercise16() {
		setLayout(new BorderLayout());
		add(bubbleSortPanel, BorderLayout.CENTER);
		
		JPanel panel1 = new JPanel();		
		panel1.add(jButton1);
		JButton jButton2 = new JButton("Reset");
		panel1.add(jButton2);
		add(panel1, BorderLayout.SOUTH);	
		
		jButton1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				bubbleSortPanel.nextStep();
			}
		});
		
		jButton2.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				bubbleSortPanel.reset();
				jButton1.setEnabled(true);
			}
		});
	}

	class BubbleSortPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private int searchSize = 20;
		private int stepPosition = -1;
		private boolean needNextPass = true;
		private boolean isCylce = false;
		private int iCycle = -1; 
		ArrayList<Integer> numbers = new ArrayList<>();
		
		public BubbleSortPanel() {
			for (int i = 1; i <= searchSize; i++) {
				numbers.add(i);
			}
			reset();
		}
		
		public void nextStep() {
			if(stepPosition == -1) {
				stepPosition = 1;
			} else {
				if(!isCylce) {
					stepPosition++;
				}
			}
			if(stepPosition >= searchSize) {
				jButton1.setEnabled(false);
				stepPosition = -1;
			} else {				
				if(!isCylce) {
					needNextPass = false;
					isCylce = true;
					iCycle = 0;
				} else if (isCylce) {
					if (numbers.get(iCycle) > numbers.get(iCycle + 1)) {
						// Swap list[i] with list[i + 1]
						int temp = numbers.get(iCycle);
						numbers.set(iCycle, numbers.get(iCycle + 1));
						numbers.set(iCycle + 1, temp);
						needNextPass = true; // Next pass still needed						
					}
					iCycle++;
					if(!(iCycle < numbers.size() - stepPosition)) {
						isCylce = false;
						//iCycle = 0;
					}
				}
				if(!isCylce) {
					if(!needNextPass) {
						jButton1.setEnabled(false);
						stepPosition = -1;
						iCycle = -1; 
					}
				}				
						
			}
			repaint();		
		}
		
		public void reset() {
			Collections.shuffle(numbers);
			stepPosition = -1;
			needNextPass = true;
			isCylce = false;
			iCycle = -1; 
			repaint();
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			int numberWidth = getWidth() / (searchSize + 2);
			int numberHeight = getHeight() / (searchSize + 2);
			for (int i = 0; i < searchSize; i++) {
				g.drawRect(numberWidth * (i + 1), getHeight() - numberHeight * numbers.get(i), numberWidth, numberHeight * numbers.get(i));
				g.drawString(numbers.get(i) + "", numberWidth * (i + 1), getHeight() - numberHeight * numbers.get(i) - (int)(numberHeight * 0.5));
			}
			if((iCycle != -1)&&(iCycle < searchSize)) {
				g.fillRect(numberWidth * (iCycle + 1), getHeight() - numberHeight * numbers.get(iCycle), numberWidth, numberHeight * numbers.get(iCycle));
			}
		}

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Exercise16");
		Exercise16 applet = new Exercise16();
		frame.add(applet);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(960, 500);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}