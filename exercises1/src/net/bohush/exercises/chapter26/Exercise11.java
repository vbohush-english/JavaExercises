package net.bohush.exercises.chapter26;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;

public class Exercise11 extends JApplet {

	private static final long serialVersionUID = 1L;
	private DoubleLinkedListAnimation doubleLinkedListAnimation = new DoubleLinkedListAnimation();
	private LinkedList<Integer> list = new LinkedList<>();
	private JTextField jtaValue = new JTextField(5);
	private JTextField jtaIndex = new JTextField(5);
	private int foundedValue = -1;
	
	public Exercise11() {
		add(doubleLinkedListAnimation, BorderLayout.CENTER);
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
		JButton jbtForward = new JButton("Forward Traversal");
		jPanel.add(jbtForward);
		JButton jbtBackward = new JButton("Backward Traversal");
		jPanel.add(jbtBackward);		
		add(jPanel, BorderLayout.SOUTH);
		
		
		jbtForward.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				String result = "";
				for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext();) {
					result += iterator.next() + " ";					
				}
				JOptionPane.showMessageDialog(null, "The elements are " + result, "Forward Traversal", JOptionPane.INFORMATION_MESSAGE);				
			}
		});
		
		jbtBackward.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				String result = "";
				for (Iterator<Integer> iterator = list.descendingIterator(); iterator.hasNext();) {
					result += iterator.next() + " ";					
				}
				JOptionPane.showMessageDialog(null, "The elements are " + result, "Backward Traversal", JOptionPane.INFORMATION_MESSAGE);				
			}
		});
		
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
					doubleLinkedListAnimation.repaint();	
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
					doubleLinkedListAnimation.repaint();
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
					doubleLinkedListAnimation.repaint();
					jtaValue.requestFocus();
				} catch (NumberFormatException e2) {
				}
			}
		});
	}

	class DoubleLinkedListAnimation extends JPanel {
		private static final long serialVersionUID = 1L;
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			int size = 30;
			for (int i = 0; i < list.size(); i++) {
				
				g.setColor(Color.BLACK);
				g.drawRect(2 * size + (i * 4 * size), 2 * size, 2 * size, size);
				g.drawRect(2 * size + (i * 4 * size), 3 * size, 2 * size, size);
				g.drawRect(2 * size + (i * 4 * size), 4 * size, 2 * size, size);
				
				if(i == foundedValue) {
					g.setColor(Color.RED);
				}
				g.drawString(list.get(i) + "", 2 * size + (i * 4 * size) + 4, (int)(2.7 * size));
				if(i == foundedValue) {
					g.setColor(Color.BLACK);
				}
				g.drawString("next", 2 * size + (i * 4 * size) + 4, (int)(3.7 * size));
				g.drawString("previous", 2 * size + (i * 4 * size) + 4, (int)(4.7 * size));
				
				if(i + 1 < list.size()) {
					g.setColor(Color.RED);
					g.drawLine((int)(4 * size) + (i * 4 * size), (int)(3.5 * size), 6 * size + (i * 4 * size), (int)(2.5 * size));
					g.drawLine(6 * size + (i * 4 * size) - 15, (int)(2.5 * size), 6 * size + (i * 4 * size), (int)(2.5 * size));
					g.drawLine(6 * size + (i * 4 * size) - 10, (int)(2.5 * size) + 12, 6 * size + (i * 4 * size), (int)(2.5 * size));
					
					g.drawLine((int)(4 * size) + (i * 4 * size), (int)(2.5 * size), 6 * size + (i * 4 * size), (int)(4.5 * size));
					g.drawLine((int)(4 * size) + (i * 4 * size) + 5, (int)(2.5 * size) + 15, (int)(4 * size) + (i * 4 * size), (int)(2.5 * size));
					g.drawLine((int)(4 * size) + (i * 4 * size) + 15, (int)(2.5 * size) + 5, (int)(4 * size) + (i * 4 * size), (int)(2.5 * size));
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
				
				g.drawLine(list.size() * 4 * size, 2 * size, list.size() * 4 * size + size, size);
				g.drawLine(list.size() * 4 * size, 2 * size, list.size() * 4 * size + 15, 2 * size - 5);
				g.drawLine(list.size() * 4 * size, 2 * size, list.size() * 4 * size + 5, 2 * size - 15);
				g.setColor(Color.BLACK);
				g.drawString("head", size, size);
				g.drawString("tail", list.size() * 4 * size + size, size);
			}
		}

	}


	public static void main(String[] args) {
		JFrame frame = new JFrame("Exercise11");
		Exercise11 applet = new Exercise11();
		frame.add(applet);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900, 300);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	

}