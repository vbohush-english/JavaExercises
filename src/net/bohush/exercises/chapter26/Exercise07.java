package net.bohush.exercises.chapter26;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class Exercise07 extends JApplet {

	private static final long serialVersionUID = 1L;
	private LinkedListAnimation linkedListAnimation = new LinkedListAnimation();
	private LinkedList<Integer> list = new LinkedList<>();
	private JTextField jtaValue = new JTextField(5);
	private JTextField jtaIndex = new JTextField(5);
	private int foundedValue = -1;
	
	public Exercise07() {
		add(linkedListAnimation, BorderLayout.CENTER);
		JPanel jPanel = new JPanel();
		jPanel.add(new JLabel("Enter a value: "));
		jPanel.add(jtaValue);
		jPanel.add(new JLabel("Enter an index: "));
			jPanel.add(jtaIndex);
		JButton jbtSearch = new JButton("Search");
		jPanel.add(jbtSearch);
		JButton jbtInsert = new JButton("Insert");
		jPanel.add(jbtInsert);
		JButton jbtDelete = new JButton("Delete");
		jPanel.add(jbtDelete);
		add(jPanel, BorderLayout.SOUTH);
		
		jbtInsert.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int value = Integer.parseInt(jtaValue.getText());
					if(jtaIndex.getText().equals("")) {
						list.add(value);
					} else {
						int index = Integer.parseInt(jtaIndex.getText());
						list.add(index, value);
						jtaIndex.setText("");						
					}
					linkedListAnimation.repaint();	
					jtaValue.setText("");
					foundedValue = -1;
					jtaValue.requestFocus();
				} catch (NumberFormatException e2) {
				} catch (IndexOutOfBoundsException e2) {
				}
			}
		});
		
		jbtDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int value = Integer.parseInt(jtaValue.getText());
					list.remove(new Integer(value));
					foundedValue = -1;
					linkedListAnimation.repaint();
					jtaValue.setText("");
					jtaValue.requestFocus();
				} catch (NumberFormatException e2) {
				}
			}
		});
		
		jbtSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int value = Integer.parseInt(jtaValue.getText());					
					foundedValue = list.indexOf(new Integer(value));	
					linkedListAnimation.repaint();
					jtaValue.requestFocus();
				} catch (NumberFormatException e2) {
				}
			}
		});
	}

	class LinkedListAnimation extends JPanel {
		private static final long serialVersionUID = 1L;
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			int size = 30;
			for (int i = 0; i < list.size(); i++) {
				g.setColor(Color.BLACK);
				g.drawRect(2 * size + (i * 3 * size), 2 * size, 2 * size, size);
				if(i == foundedValue) {
					g.setColor(Color.RED);
				}
				g.drawString(list.get(i) + "", 2 * size + (i * 3 * size) + 4, (int)(2.7 * size));
				if(i == foundedValue) {
					g.setColor(Color.BLACK);
				}
				g.drawLine(3 * size + (i * 3 * size), 2 * size, 3 * size + (i * 3 * size), 3 * size);
				if(i + 1 < list.size()) {
					g.setColor(Color.RED);
					g.drawLine((int)(3.5 * size) + (i * 3 * size), (int)(2.5 * size), 5 * size + (i * 3 * size), (int)(2.5 * size));
					g.drawLine(5 * size + (i * 3 * size) - 10, (int)(2.5 * size) - 5, 5 * size + (i * 3 * size), (int)(2.5 * size));
					g.drawLine(5 * size + (i * 3 * size) - 10, (int)(2.5 * size) + 5, 5 * size + (i * 3 * size), (int)(2.5 * size));
				}
			}
			if(list.size() == 0) {
				g.setColor(Color.BLACK);
				g.drawString("head:null", size, size);
				g.drawString("tail:null", size, 2 * size);
			} else {
				g.setColor(Color.RED);
				g.drawLine(size, size, 2 * size, 2 * size);
				g.drawLine(2 * size - 5, 2 * size - 15, 2 * size, 2 * size);
				g.drawLine(2 * size - 15, 2 * size - 5, 2 * size, 2 * size);
				
				g.drawLine(list.size() * 3 * size - size, 2 * size, list.size() * 3 * size, size);
				g.drawLine(list.size() * 3 * size - size, 2 * size, list.size() * 3 * size - size + 15, 2 * size - 5);
				g.drawLine(list.size() * 3 * size - size, 2 * size, list.size() * 3 * size - size + 5, 2 * size - 15);
				g.setColor(Color.BLACK);
				g.drawString("head", size, size);
				g.drawString("tail", list.size() * 3 * size, size);
			}
		}

	}


	public static void main(String[] args) {
		JFrame frame = new JFrame("Exercise07");
		Exercise07 applet = new Exercise07();
		frame.add(applet);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 200);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	

}