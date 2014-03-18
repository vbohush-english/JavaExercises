package net.bohush.exercises.chapter25;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class Exercise18 extends JApplet {

	private static final long serialVersionUID = 1L;
	private MergePanel mergePanel = new MergePanel();
	private JButton jButton1 = new JButton("Step");
	
	public Exercise18() {
		setLayout(new BorderLayout());
		add(mergePanel, BorderLayout.CENTER);
		
		JPanel panel1 = new JPanel();		
		panel1.add(jButton1);
		JButton jButton2 = new JButton("Reset");
		panel1.add(jButton2);
		add(panel1, BorderLayout.SOUTH);	
		
		jButton1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				mergePanel.nextStep();
			}
		});
		
		jButton2.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				mergePanel.reset();
				jButton1.setEnabled(true);
			}
		});
	}
	
	class MergePanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private int searchSize = 10;
		private ArrayList<Integer> list1 = new ArrayList<>();
		private ArrayList<Integer> list2 = new ArrayList<>();
		private ArrayList<Integer> temp = new ArrayList<>();
		private int current1 = 0;
		private int current2 = 0;
		
		public MergePanel() {
			reset();
		}
		
		public void nextStep() {

			if (current1 < list1.size() && current2 < list2.size()) {
				if (list1.get(current1) < list2.get(current2))
					temp.add(list1.get(current1++));
				else
					temp.add(list2.get(current2++));
			} else if (current1 < list1.size()) {
				temp.add(list1.get(current1++));
			} else if (current2 < list2.size()) {
				temp.add(list2.get(current2++));
			}
			if(temp.size() == list1.size() + list2.size()) { 
				jButton1.setEnabled(false);
			}
					
			repaint();		
		}
		
		public void reset() {
			temp.clear();
			list1.clear();
			for (int i = 1; i <= searchSize; i++) {
				list1.add((int)(Math.random() * 1000));
			}
			Collections.sort(list1);
			list2.clear();
			for (int i = 1; i <= searchSize; i++) {
				list2.add((int)(Math.random() * 1000));
			}
			Collections.sort(list2);
			current1 = 0;
			current2 = 0;
			repaint();
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			int numberWidth = getWidth() / (searchSize * 2 + 3);
			int numberHeight = 40;
			
			if(current1 < list1.size()) {
				g.drawString("current1",  (int)(numberWidth * (1.0 + current1)), 28);
				g.drawLine((int)(numberWidth * (1.5 + current1)), 30,  (int)(numberWidth * (1.5 + current1)), 100);
				g.drawLine((int)(numberWidth * (1.5 + current1)) - 15, 100 - 15,  (int)(numberWidth * (1.5 + current1)), 100);
				g.drawLine((int)(numberWidth * (1.5 + current1)) + 15, 100 - 15,  (int)(numberWidth * (1.5 + current1)), 100);
			}
			
			g.drawString("list1", 15, 130);
			for (int i = 0; i < searchSize; i++) {
				g.drawRect(numberWidth * (i + 1), 100, numberWidth, numberHeight);
				g.drawString(list1.get(i) + "", numberWidth * (i + 1) + 5, 130);
			}
			
			if(current2 < list2.size()) {
				g.drawString("current2",  (int)(numberWidth * (12.0 + current2)), 28);
				g.drawLine((int)(numberWidth * (12.5 + current2)), 30,  (int)(numberWidth * (12.5 + current2)), 100);
				g.drawLine((int)(numberWidth * (12.5 + current2)) - 15, 100 - 15,  (int)(numberWidth * (12.5 + current2)), 100);
				g.drawLine((int)(numberWidth * (12.5 + current2)) + 15, 100 - 15,  (int)(numberWidth * (12.5 + current2)), 100);
			}
			
			g.drawString("list2", numberWidth * 11 + 15, 130);
			for (int i = 0; i < searchSize; i++) {
				g.drawRect(numberWidth * (i + 12), 100, numberWidth, numberHeight);
				g.drawString(list2.get(i) + "", numberWidth * (i + 12) + 5, 130);
			}
			
			
			if(temp.size() < list1.size() + list2.size()) {
				g.drawString("current3",  (int)(numberWidth * (1.0 + temp.size())), 320);
				g.drawLine((int)(numberWidth * (1.5 + temp.size())), 310,  (int)(numberWidth * (1.5 + temp.size())), 240);
				g.drawLine((int)(numberWidth * (1.5 + temp.size())) - 15, 240 + 15,  (int)(numberWidth * (1.5 + temp.size())), 240);
				g.drawLine((int)(numberWidth * (1.5 + temp.size())) + 15, 240 + 15,  (int)(numberWidth * (1.5 + temp.size())), 240);
			}
			g.drawString("temp", 10, 230);
			for (int i = 0; i < searchSize * 2; i++) {
				g.drawRect(numberWidth * (i + 1), 200, numberWidth, numberHeight);
				if(i < temp.size()) {
					g.drawString(temp.get(i) + "", numberWidth * (i + 1) + 5, 230);
				}
			}
		}

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Exercise18");
		Exercise18 applet = new Exercise18();
		frame.add(applet);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(960, 500);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}