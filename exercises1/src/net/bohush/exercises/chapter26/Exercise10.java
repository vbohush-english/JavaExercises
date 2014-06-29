package net.bohush.exercises.chapter26;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class Exercise10 extends JApplet {

	private static final long serialVersionUID = 1L;
	private StackAnimation stackAnimation = new StackAnimation();
	private LinkedList<Integer> list = new LinkedList<>();
	private JTextField jtaValue = new JTextField(5);

	public Exercise10() {
		add(stackAnimation, BorderLayout.CENTER);
		JPanel jPanel = new JPanel();
		jPanel.add(new JLabel("Enter a value: "));
		jPanel.add(jtaValue);
		JButton jbtPush = new JButton("Push");
		jPanel.add(jbtPush);
		JButton jbtPop = new JButton("Pop");
		jPanel.add(jbtPop);
		add(jPanel, BorderLayout.SOUTH);
		
		jbtPush.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int value = Integer.parseInt(jtaValue.getText());
					list.addFirst(value);					
					stackAnimation.repaint();	
					jtaValue.setText("");
					jtaValue.requestFocus();
				} catch (NumberFormatException e2) {
				}
			}
		});
		
		jbtPop.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!list.isEmpty()) {
					list.removeFirst();
					stackAnimation.repaint();
					jtaValue.requestFocus();
				}
			}
		});
		
	}

	class StackAnimation extends JPanel {
		private static final long serialVersionUID = 1L;
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			int size = 40;
			g.drawString("stack" + (list.size() == 0 ? " is empty" : ""), size, size);
			for (int i = 0; i < list.size(); i++) {
				g.drawRect(size + (i * size), 2 * size, size, size);
				g.drawString(list.get(i) + "", size + (i * size) + 4, (int)(2.6 * size));				
			}

		}

	}


	public static void main(String[] args) {
		JFrame frame = new JFrame("Exercise10");
		Exercise10 applet = new Exercise10();
		frame.add(applet);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 300);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	

}