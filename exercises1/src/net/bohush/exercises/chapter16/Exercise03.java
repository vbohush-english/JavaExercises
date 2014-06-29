package net.bohush.exercises.chapter16;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Exercise03 extends JFrame{
	private JButton jbtLeft = new JButton("Left");
	private JButton jbtRight = new JButton("Right");
	private JButton jbtUp = new JButton("Up");
	private JButton jbtDown = new JButton("Down");
	private BallPanel ballPanel = new BallPanel();
	
	private static final long serialVersionUID = 1L;

	public Exercise03() {
		JPanel panel = new JPanel();
		panel.add(jbtLeft);
		panel.add(jbtRight);
		panel.add(jbtUp);
		panel.add(jbtDown);
		add(panel, BorderLayout.SOUTH);
		add(ballPanel, BorderLayout.CENTER);
		jbtLeft.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ballPanel.moveLeft();				
			}			
		});
		jbtRight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ballPanel.moveRight();				
			}			
		});
		jbtUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ballPanel.moveUp();				
			}			
		});
		jbtDown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ballPanel.moveDown();				
			}			
		});
	}
	
	public static void main(String[] args) {
		Exercise03 frame = new Exercise03();
		frame.setTitle("Exercise03");
		frame.setSize(400, 200);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}

class BallPanel extends JPanel {
	private int radius = 5;
	private int step = 30;
	private int xPos;
	private int yPos;
	boolean firstStart = true;
	
	private static final long serialVersionUID = 1L;
	
	public void moveUp() {
		if (yPos - step > 0) {
			yPos -= step;
			repaint();
		} else {
			yPos = 0;
			repaint();
		}
	}
	
	public void moveDown() {
		if (yPos + radius * 2 + step < getHeight()) {
			yPos += step;
			repaint();
		} else {
			yPos = getHeight() - radius * 2 - 1;
			repaint();
		}
	}
	
	public void moveLeft() {
		if (xPos - step > 0) {
			xPos -= step;
			repaint();
		} else {
			xPos = 0;
			repaint();
		}
	}
	
	public void moveRight() {
		if (xPos + radius * 2 + step < getWidth()) {
			xPos += step;
			repaint();
		} else {
			xPos = getWidth() - radius * 2 - 1;
			repaint();
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (firstStart) {
			xPos = getWidth() / 2 - radius;
			yPos = getHeight() / 2 - radius;
			firstStart = false;
		}
		g.drawOval(xPos, yPos, radius * 2, radius * 2);
	}
}