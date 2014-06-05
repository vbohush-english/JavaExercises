package net.bohush.exercises.chapter37;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Exercose06 extends JApplet {
	private static final long serialVersionUID = 1L;
	private JTabbedPane jtpFigures = new JTabbedPane();
	private FigurePanel squarePanel = new FigurePanel();
	private FigurePanel rectanglePanel = new FigurePanel();
	private FigurePanel circlePanel = new FigurePanel();
	private FigurePanel ovalPanel = new FigurePanel();

	public Exercose06() {
	
		
		JPanel jPanel1 = new JPanel(new BorderLayout());
		squarePanel.setType(FigurePanel.LINE);
		rectanglePanel.setType(FigurePanel.RECTANGLE);
		circlePanel.setType(FigurePanel.ROUND_RECTANGLE);
		ovalPanel.setType(FigurePanel.OVAL);

		jPanel1.add(jtpFigures, BorderLayout.CENTER);
		jtpFigures.add(squarePanel, "Line");
		jtpFigures.add(rectanglePanel, "Rectangle");
		jtpFigures.add(circlePanel, "Round Rectangle");
		jtpFigures.add(ovalPanel, "Oval");

		jtpFigures.setToolTipTextAt(0, "Square");
		jtpFigures.setToolTipTextAt(1, "Rectangle");
		jtpFigures.setToolTipTextAt(2, "Circle");
		jtpFigures.setToolTipTextAt(3, "Oval");
		add(jPanel1, BorderLayout.CENTER);
		
		JPanel jPanel2 = new JPanel(new GridLayout(1, 4, 5, 5));
		jPanel2.setBorder(new TitledBorder("Specify Tab Location"));
		JRadioButton jRadioButton1 = new JRadioButton("Top", true);
		JRadioButton jRadioButton2 = new JRadioButton("Left");
		JRadioButton jRadioButton3 = new JRadioButton("Right");
		JRadioButton jRadioButton4 = new JRadioButton("Bottom");
		jRadioButton1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				jtpFigures.setTabPlacement(JTabbedPane.TOP);
			}
		});
		jRadioButton2.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				jtpFigures.setTabPlacement(JTabbedPane.LEFT);
			}
		});
		jRadioButton3.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				jtpFigures.setTabPlacement(JTabbedPane.RIGHT);
			}
		});
		jRadioButton4.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				jtpFigures.setTabPlacement(JTabbedPane.BOTTOM);
			}
		});
		jPanel2.add(jRadioButton1);
		jPanel2.add(jRadioButton2);
		jPanel2.add(jRadioButton3);
		jPanel2.add(jRadioButton4);
		ButtonGroup buttonGroup1 = new ButtonGroup();
		buttonGroup1.add(jRadioButton1);
		buttonGroup1.add(jRadioButton2);
		buttonGroup1.add(jRadioButton3);
		buttonGroup1.add(jRadioButton4);
		add(jPanel2, BorderLayout.SOUTH);
	}

	/** Main method */
	public static void main(String[] args) {
		// Create a frame
		JFrame frame = new JFrame("Exercose06");

		// Create an instance of the applet
		Exercose06 applet = new Exercose06();
		applet.init();

		// Add the applet instance to the frame
		frame.add(applet, java.awt.BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		// Display the frame
		frame.setSize(400, 200);
		frame.setVisible(true);
	}

	static class FigurePanel extends JPanel {
		private static final long serialVersionUID = 1L;
		// Define constants
		public static final int LINE = 1;
		public static final int RECTANGLE = 2;
		public static final int ROUND_RECTANGLE = 3;
		public static final int OVAL = 4;

		private int type = 1;
		private boolean filled = false;

		/** Construct a default FigurePanel */
		public FigurePanel() {
		}

		/** Construct a FigurePanel with the specified type */
		public FigurePanel(int type) {
			this.type = type;
		}

		/** Construct a FigurePanel with the specified type and filled */
		public FigurePanel(int type, boolean filled) {
			this.type = type;
			this.filled = filled;
		}

		@Override
		// Draw a figure on the panel
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			// Get the appropriate size for the figure
			int width = getWidth();
			int height = getHeight();

			switch (type) {
			case LINE: // Display two cross lines
				g.setColor(Color.BLACK);
				g.drawLine(10, 10, width - 10, height - 10);
				g.drawLine(width - 10, 10, 10, height - 10);
				break;
			case RECTANGLE: // Display a rectangle
				g.setColor(Color.BLUE);
				if (filled)
					g.fillRect((int) (0.1 * width), (int) (0.1 * height),
							(int) (0.8 * width), (int) (0.8 * height));
				else
					g.drawRect((int) (0.1 * width), (int) (0.1 * height),
							(int) (0.8 * width), (int) (0.8 * height));
				break;
			case ROUND_RECTANGLE: // Display a round-cornered rectangle
				g.setColor(Color.RED);
				if (filled)
					g.fillRoundRect((int) (0.1 * width), (int) (0.1 * height),
							(int) (0.8 * width), (int) (0.8 * height), 20, 20);
				else
					g.drawRoundRect((int) (0.1 * width), (int) (0.1 * height),
							(int) (0.8 * width), (int) (0.8 * height), 20, 20);
				break;
			case OVAL: // Display an oval
				g.setColor(Color.BLACK);
				if (filled)
					g.fillOval((int) (0.1 * width), (int) (0.1 * height),
							(int) (0.8 * width), (int) (0.8 * height));
				else
					g.drawOval((int) (0.1 * width), (int) (0.1 * height),
							(int) (0.8 * width), (int) (0.8 * height));
			}
		}

		/** Set a new figure type */
		public void setType(int type) {
			this.type = type;
			repaint();
		}

		/** Return figure type */
		public int getType() {
			return type;
		}

		/** Set a new filled property */
		public void setFilled(boolean filled) {
			this.filled = filled;
			repaint();
		}

		/** Check if the figure is filled */
		public boolean isFilled() {
			return filled;
		}

		@Override
		// Specify preferred size
		public Dimension getPreferredSize() {
			return new Dimension(80, 80);
		}
	}
}