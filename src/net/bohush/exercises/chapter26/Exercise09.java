package net.bohush.exercises.chapter26;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class Exercise09 extends JApplet {

	private static final long serialVersionUID = 1L;
	private QueueAnimation queueAnimation = new QueueAnimation();
	private LinkedList<Integer> list = new LinkedList<>();
	private JTextField jtaValue = new JTextField(5);

	public Exercise09() {
		add(queueAnimation, BorderLayout.CENTER);
		JPanel jPanel = new JPanel();
		jPanel.add(new JLabel("Enter a value: "));
		jPanel.add(jtaValue);
		JButton jbtEnqueue = new JButton("Enqueue");
		jPanel.add(jbtEnqueue);
		JButton jbtDequeue = new JButton("Dequeue");
		jPanel.add(jbtDequeue);
		add(jPanel, BorderLayout.SOUTH);
		
		jbtEnqueue.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int value = Integer.parseInt(jtaValue.getText());
					list.addLast(value);					
					queueAnimation.repaint();	
					jtaValue.setText("");
					jtaValue.requestFocus();
				} catch (NumberFormatException e2) {
				}
			}
		});
		
		jbtDequeue.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!list.isEmpty()) {
					list.removeFirst();
					queueAnimation.repaint();
					jtaValue.requestFocus();
				}
			}
		});
		
	}

	class QueueAnimation extends JPanel {
		private static final long serialVersionUID = 1L;
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			int size = 40;
			g.drawString("queue" + (list.size() == 0 ? " is empty" : ""), size, size);
			for (int i = 0; i < list.size(); i++) {
				g.drawRect(size + (i * size), 2 * size, size, size);
				g.drawString(list.get(i) + "", size + (i * size) + 4, (int)(2.6 * size));				
			}

		}

	}


	public static void main(String[] args) {
		JFrame frame = new JFrame("Exercise09");
		Exercise09 applet = new Exercise09();
		frame.add(applet);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 300);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	

}