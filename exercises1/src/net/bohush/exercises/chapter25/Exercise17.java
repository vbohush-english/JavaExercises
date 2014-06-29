package net.bohush.exercises.chapter25;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Exercise17 extends JApplet {

	private static final long serialVersionUID = 1L;
	private RadixSortPanel radixSortPanel = new RadixSortPanel();
	private JButton jButton1 = new JButton("Step");
	
	public Exercise17() {
		setLayout(new BorderLayout());
		add(radixSortPanel, BorderLayout.CENTER);
		
		JPanel panel1 = new JPanel();		
		panel1.add(jButton1);
		JButton jButton2 = new JButton("Reset");
		panel1.add(jButton2);
		add(panel1, BorderLayout.SOUTH);	
		
		jButton1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				radixSortPanel.nextStep();
			}
		});
		
		jButton2.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				radixSortPanel.reset();
				jButton1.setEnabled(true);
			}
		});
	}
	

	class RadixSortPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private int searchSize = 30;
		private int maxOrder = 1000;
		private int order = 1;
		private boolean isCycle = false;
		private int iCycle = 0;
		private ArrayList<Integer>[] bucket;
		int lastNumberCol = 0;
		ArrayList<Integer> numbers = new ArrayList<>();
		
		public RadixSortPanel() {
			reset();
		}
		
		@SuppressWarnings("unchecked")
		public void nextStep() {
			if(!isCycle) {
				bucket = new ArrayList[10];
				for (int i = 0; i < bucket.length; i++) {
					bucket[i] = new java.util.ArrayList<>();
				}
				isCycle = true;
				iCycle = 0;
			}
			
			if(isCycle) {
				bucket[(numbers.get(iCycle) / order) % 10].add(numbers.get(iCycle));
				lastNumberCol = (numbers.get(iCycle) / order) % 10;
				iCycle++;
				if(iCycle >= numbers.size()) {
					isCycle = false;
				}
			}
			
			if(!isCycle) {
				int k = 0;
				for (int i = 0; i < bucket.length; i++) {
					if (bucket[i] != null) {
						for (int j = 0; j < bucket[i].size(); j++)
							numbers.set(k++, bucket[i].get(j));
					}
				}
				order *= 10;
				if(order >= maxOrder) {
					jButton1.setEnabled(false);
					order = 1;
				}
				bucket = null;
			}
			repaint();		
		}
		
		public void reset() {
			numbers.clear();
			for (int i = 1; i <= searchSize; i++) {
				numbers.add((int)(Math.random() * maxOrder));
			}
			order = 1;
			isCycle = false;
			iCycle = 0;
			bucket = null;
			lastNumberCol = 0;
			repaint();
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			int numberWidth = getWidth() / (searchSize + 2);
			int numberHeight = 20;
			for (int i = 0; i < searchSize; i++) {
				g.drawRect(numberWidth * (i + 1), 20, numberWidth, numberHeight);
				g.drawString(numbers.get(i) + "", numberWidth * (i + 1) + 5, 35);
			}
			
			int columns = 10;
			int columnWidth = getWidth() / columns;
			int columnHeight = getHeight() - 80;
			for (int i = 0; i < columns; i++) {
				g.drawRect(columnWidth * i + 10, 60, columnWidth - 20, columnHeight);
				if(bucket != null) {
				for (int j = 0; j < bucket[i].size(); j++) {
					if((i == lastNumberCol)&&(j == bucket[i].size() - 1)) {
						g.setColor(Color.RED);
					}
					g.drawString(bucket[i].get(j) + "", columnWidth * i + 20, 90 + j * 20);
					if((i == lastNumberCol)&&(j == bucket[i].size() - 1)) {
						g.setColor(Color.BLACK);
					}
				}
				}
			}
		}

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Exercise17");
		Exercise17 applet = new Exercise17();
		frame.add(applet);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(960, 500);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}