package net.bohush.exercises.chapter25;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Exercise19 extends JApplet {

	private static final long serialVersionUID = 1L;
	private PartitionPanel partitionPanel = new PartitionPanel();
	private JButton jButton1 = new JButton("Step");
	
	public Exercise19() {
		setLayout(new BorderLayout());
		add(partitionPanel, BorderLayout.CENTER);
		
		JPanel panel1 = new JPanel();		
		panel1.add(jButton1);
		JButton jButton2 = new JButton("Reset");
		panel1.add(jButton2);
		add(panel1, BorderLayout.SOUTH);	
		
		jButton1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				partitionPanel.nextStep();
			}
		});
		
		jButton2.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				partitionPanel.reset();
				jButton1.setEnabled(true);
			}
		});
	}
	
	class PartitionPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private int searchSize = 30;
		private ArrayList<Integer> list = new ArrayList<>();
		private int pivot = -1;
		private int pivotPos = -1;
		private int low = -1;
		private int high = -1;
		
		public PartitionPanel() {
			reset();
		}
		
		public void nextStep() {
			if(pivot == -1) {
				pivot = list.get(0); // Choose the first element as the pivot
				pivotPos = 0;
				low = 1; // Index for forward search
				high = list.size() - 1; // Index for backward search
			}
			if (high > low) {
				// Search forward from left
				while (low <= high && list.get(low) <= pivot)
					low++;

				// Search backward from right
				while (low <= high && list.get(high) > pivot)
					high--;

				// Swap two elements in the list
				if (high > low) {
					int temp = list.get(high);
					list.set(high, list.get(low));
					list.set(low, temp);
				}
			} else {
				while (high > 0 && list.get(high) >= pivot)
					high--;
	
				if (pivot > list.get(high)) {
					list.set(0, list.get(high));
					list.set(high, pivot);
				}
				pivot = -1;
				pivotPos = high;
				high = 0;
				jButton1.setEnabled(false);
			}
			repaint();		
		}
		
		public void reset() {
			list.clear();
			for (int i = 1; i <= searchSize; i++) {
				list.add((int)(Math.random() * 1000));
			}
			pivot = -1;
			low = -1;
			high = -1; 
			pivotPos = -1;
			repaint();
		}
		
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			int numberWidth = getWidth() / (searchSize + 2);
			int numberHeight = 40;
			
			for (int i = 0; i < searchSize; i++) {
				g.drawRect(numberWidth * (i + 1), 100, numberWidth, numberHeight);
				g.drawString(list.get(i) + "", numberWidth * (i + 1) + 5, 130);
			}
			
			if(low != -1) {
				g.drawString("low",  (int)(numberWidth * (1.0 + low)), 28);
				g.drawLine((int)(numberWidth * (1.5 + low)), 30,  (int)(numberWidth * (1.5 + low)), 100);
				g.drawLine((int)(numberWidth * (1.5 + low)) - 15, 100 - 15,  (int)(numberWidth * (1.5 + low)), 100);
				g.drawLine((int)(numberWidth * (1.5 + low)) + 15, 100 - 15,  (int)(numberWidth * (1.5 + low)), 100);
			}

			if(high != -1) {
				g.drawString("high",  (int)(numberWidth * (1.0 + high)), 28);
				g.drawLine((int)(numberWidth * (1.5 + high)), 30,  (int)(numberWidth * (1.5 + high)), 100);
				g.drawLine((int)(numberWidth * (1.5 + high)) - 15, 100 - 15,  (int)(numberWidth * (1.5 + high)), 100);
				g.drawLine((int)(numberWidth * (1.5 + high)) + 15, 100 - 15,  (int)(numberWidth * (1.5 + high)), 100);
			}
			

			if(pivotPos != -1) {
				g.drawString("pivot",  (int)(numberWidth * (1.0 + pivotPos)), 225);
				g.drawLine((int)(numberWidth * (1.5 + pivotPos)), 210,  (int)(numberWidth * (1.5 + pivotPos)), 140);
				g.drawLine((int)(numberWidth * (1.5 + pivotPos)) - 15, 140 + 15,  (int)(numberWidth * (1.5 + pivotPos)), 140);
				g.drawLine((int)(numberWidth * (1.5 + pivotPos)) + 15, 140 + 15,  (int)(numberWidth * (1.5 + pivotPos)), 140);
			}
		}

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Exercise19");
		Exercise19 applet = new Exercise19();
		frame.add(applet);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(960, 500);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}