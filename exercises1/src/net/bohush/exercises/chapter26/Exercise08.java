package net.bohush.exercises.chapter26;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Exercise08 extends JApplet {

	private static final long serialVersionUID = 1L;
	private ArrayListAnimation arrayListAnimation = new ArrayListAnimation();
	private ArrayList<Integer> list = new ArrayList<>();
	private JTextField jtaValue = new JTextField(5);
	private JTextField jtaIndex = new JTextField(5);
	private int foundedValue = -1;
	private int capacity= 4;
		
	public Exercise08() {
		add(arrayListAnimation, BorderLayout.CENTER);
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
		JButton jbtTrim = new JButton("Trin To Size");
		jPanel.add(jbtTrim);
		add(jPanel, BorderLayout.SOUTH);
		
		jbtInsert.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int value = Integer.parseInt(jtaValue.getText());
					if(jtaIndex.getText().equals("")) {
						list.add(value);
						if(list.size() > capacity) {
							capacity *= 2;
						}
					} else {
						int index = Integer.parseInt(jtaIndex.getText());
						list.add(index, value);
						if(list.size() > capacity) {
							capacity *= 2;
						}
						jtaIndex.setText("");						
					}
					arrayListAnimation.repaint();	
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
					arrayListAnimation.repaint();
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
					arrayListAnimation.repaint();
					jtaValue.requestFocus();
				} catch (NumberFormatException e2) {
				}
			}
		});
		
		jbtTrim.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				capacity = list.size();
				if(capacity == 0) {
					capacity = 1;
				}
				arrayListAnimation.repaint();
			}
		});
	}

	class ArrayListAnimation extends JPanel {
		private static final long serialVersionUID = 1L;
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			int size = 40;
			String str = (list.size() == 0 ? "list is empty" : "array list") + ".     size = " + list.size() + " and capacity = " + capacity;
			g.drawString(str, size, size);
			for (int i = 0; i < capacity; i++) {
				g.drawRect(size + (i * size), 2 * size, size, size);
				if(i >= list.size()) {
					g.drawLine(2 * size + (i * size), 2 * size, size + (i * size), 3 * size);	
				} else {
					if(i == foundedValue) {
						g.setColor(Color.RED);
					}
					g.drawString(list.get(i) + "", size + (i * size) + 4, (int)(2.6 * size));
					if(i == foundedValue) {
						g.setColor(Color.BLACK);
					}
				}
			}

		}

	}


	public static void main(String[] args) {
		JFrame frame = new JFrame("Exercise08");
		Exercise08 applet = new Exercise08();
		frame.add(applet);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 300);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	

}