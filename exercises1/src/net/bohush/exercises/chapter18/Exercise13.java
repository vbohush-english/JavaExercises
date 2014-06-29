package net.bohush.exercises.chapter18;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class Exercise13 extends JApplet{
	private final int FLOORS = 8;
	private static final long serialVersionUID = 1L;
	private	Elevator elevator = new Elevator();
	private JLabel jLabel = new JLabel(" ");
	
	public Exercise13() {
		
		JPanel panel3 = new JPanel(new BorderLayout());
		panel3.setBackground(Color.WHITE);
		panel3.add(jLabel, BorderLayout.WEST);
		add(panel3, BorderLayout.NORTH);
		
		
		elevator.setBorder(new LineBorder(Color.GRAY));
		add(elevator, BorderLayout.CENTER);
		
		
		JPanel panel1 = new JPanel(new GridLayout(FLOORS, 1));
		JButton[] jButtons1 = new JButton[FLOORS];
		for (int i = jButtons1.length; i > 0; i--) {
			jButtons1[i - 1] = new JButton("F" + i);
			jButtons1[i - 1].addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					elevator.goToPassenger(((JButton) (e.getSource())).getText().charAt(1) - '0');				
				}
			});
			panel1.add(jButtons1[i - 1]);
		}
		add(panel1, BorderLayout.WEST);
		
		JPanel panel2 = new JPanel(new GridLayout(FLOORS, 1));
		JButton[] jButtons2 = new JButton[FLOORS];
		for (int i = jButtons1.length; i > 0; i--) {
			jButtons2[i - 1] = new JButton("F" + i);
			jButtons2[i - 1].addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					elevator.goToFloor(((JButton) (e.getSource())).getText().charAt(1) - '0');				
				}
			});
			panel2.add(jButtons2[i - 1]);
		}
		add(panel2, BorderLayout.EAST);
		
	}

	class Elevator extends JPanel {
		private static final long serialVersionUID = 1L;
		private int currentFloor = 1;
		private boolean isMoving = false;
		private int moveToFloor;
		private Color elevatorColor = Color.BLACK;
		Timer timer;
		
		public Elevator() {
			
		}
		
		public void goToPassenger(int floor) {
			if ((!isMoving) && (elevatorColor == Color.BLACK)) {
				jLabel.setText("getting passangers");
				moveToFloor = floor;
				isMoving = true;
				timer = new Timer(500, new ActionListener() {					
					@Override
					public void actionPerformed(ActionEvent e) {
						if (currentFloor < moveToFloor) {
							currentFloor++;
						} else if (currentFloor > moveToFloor) {
							currentFloor--;
						}
						repaint();
						if (currentFloor == moveToFloor) {
							timer.stop();
							elevatorColor = Color.BLUE;
							isMoving = false;
							jLabel.setText(" ");
						}
					}
				});
				timer.start();
			}			
		}
		
		
		public void goToFloor(int floor) {
			if ((!isMoving) && (elevatorColor == Color.BLUE)) {
				jLabel.setText("sending passangers");
				moveToFloor = floor;
				isMoving = true;
				timer = new Timer(500, new ActionListener() {					
					@Override
					public void actionPerformed(ActionEvent e) {
						if (currentFloor < moveToFloor) {
							currentFloor++;
						} else if (currentFloor > moveToFloor) {
							currentFloor--;
						}
						repaint();
						if (currentFloor == moveToFloor) {
							timer.stop();
							elevatorColor = Color.BLACK;
							isMoving = false;
							jLabel.setText(" ");
						}
					}
				});
				timer.start();
			}			
		}
		
		@Override
		protected void paintComponent(Graphics g) {	
			super.paintComponent(g);
			double step = getHeight() / ((double) FLOORS);
			for (int i = 0; i < FLOORS; i++) {
				g.setColor(Color.GRAY);
				g.drawLine(0, (int) (i * step), getWidth(), (int) (i * step));
				int elevetorWidth = 40;
				g.setColor(elevatorColor);
				g.fillRect((getWidth() - elevetorWidth) / 2, (int) ((FLOORS - currentFloor) * step), elevetorWidth, (int) (step));
			}
			
		}
	}
}
