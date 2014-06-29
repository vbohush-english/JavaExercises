package net.bohush.exercises.chapter16;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Exercise07 extends JFrame{
	private JPanel panel = new JPanel();
	
	private static final long serialVersionUID = 1L;

	public Exercise07() {
		
		panel.setBackground(Color.WHITE);
		panel.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				panel.setBackground(Color.WHITE);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				panel.setBackground(Color.BLACK);
			}
			
		});
		add(panel);
	}
	
	public static void main(String[] args) {
		Exercise07 frame = new Exercise07();
		frame.setTitle("Exercise07");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(300, 300);;
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
