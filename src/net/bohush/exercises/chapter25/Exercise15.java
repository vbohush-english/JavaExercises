package net.bohush.exercises.chapter25;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class Exercise15 extends JApplet {

	private static final long serialVersionUID = 1L;
	private SelectionSortPanel SelectionSortPanel = new SelectionSortPanel();
	private JButton jButton1 = new JButton("Step");
	
	public Exercise15() {
		setLayout(new BorderLayout());
		add(SelectionSortPanel, BorderLayout.CENTER);
		
		JPanel panel1 = new JPanel();		
		panel1.add(jButton1);
		JButton jButton2 = new JButton("Reset");
		panel1.add(jButton2);
		add(panel1, BorderLayout.SOUTH);	
		
		jButton1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				SelectionSortPanel.nextStep();
			}
		});
		
		jButton2.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				SelectionSortPanel.reset();
				jButton1.setEnabled(true);
			}
		});
	}
	
	public static void selectionSort(int[] list) {
		for (int i = 0; i < list.length - 1; i++) {
			int currentMin = list[i];
			int currentMinIndex = i;

			for (int j = i + 1; j < list.length; j++) {
				if (currentMin > list[j]) {
					currentMin = list[j];
					currentMinIndex = j;
				}
			}
			if (currentMinIndex != i) {
				list[currentMinIndex] = list[i];
				list[i] = currentMin;
			}
		}
	}

	class SelectionSortPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private int searchSize = 50;
		private int stepPosition = -1;
		ArrayList<Integer> numbers = new ArrayList<>();
		
		public SelectionSortPanel() {
			for (int i = 1; i <= searchSize; i++) {
				numbers.add(i);
			}
			reset();
		}
		
		public void nextStep() {
			stepPosition++;
			if(stepPosition >= searchSize) {
				jButton1.setEnabled(false);
				stepPosition = -1;
			} else {
				int currentMin = numbers.get(stepPosition);
				int currentMinIndex = stepPosition;

				for (int j = stepPosition + 1; j < numbers.size(); j++) {
					if (currentMin > numbers.get(j)) {
						currentMin = numbers.get(j);
						currentMinIndex = j;
					}
				}
				if (currentMinIndex != stepPosition) {
					numbers.set(currentMinIndex, numbers.get(stepPosition));
					numbers.set(stepPosition, currentMin);
				}				
			}
			repaint();		
		}
		
		public void reset() {
			Collections.shuffle(numbers);
			stepPosition = -1;
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
			if((stepPosition != -1)&&(stepPosition < searchSize)) {
				g.fillRect(numberWidth * (stepPosition + 1), getHeight() - numberHeight * numbers.get(stepPosition), numberWidth, numberHeight * numbers.get(stepPosition));
			}
		}

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Exercise15");
		Exercise15 applet = new Exercise15();
		frame.add(applet);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(960, 500);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}