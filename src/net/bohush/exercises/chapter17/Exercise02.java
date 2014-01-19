package net.bohush.exercises.chapter17;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Exercise02 extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private FigurePanel figurePanel;	
	private JRadioButton jrbLine = new JRadioButton("Line");
	private JRadioButton jrbRectangle = new JRadioButton("Rectangle");
	private JRadioButton jrbOval = new JRadioButton("Oval", true);
	private JRadioButton jrbArc = new JRadioButton("Arc");
	private JRadioButton jrbPolygon = new JRadioButton("Polygon");
	private JCheckBox jchkFille = new JCheckBox("Filled");
	private RepaintFigure repaintFigure = new RepaintFigure();
	
	public Exercise02() {
		JPanel controlPanel = new JPanel();
		jrbLine.setMnemonic('L');
		jrbRectangle.setMnemonic('R');
		jrbOval.setMnemonic('O');
		jrbArc.setMnemonic('A');
		jrbPolygon.setMnemonic('P');
		jchkFille.setMnemonic('F');
		jrbLine.addActionListener(repaintFigure);
		jrbRectangle.addActionListener(repaintFigure);
		jrbOval.addActionListener(repaintFigure);
		jrbArc.addActionListener(repaintFigure);
		jrbPolygon.addActionListener(repaintFigure);
		jchkFille.addActionListener(repaintFigure);
		ButtonGroup figureGroup = new ButtonGroup();
		figureGroup.add(jrbLine);
		figureGroup.add(jrbRectangle);
		figureGroup.add(jrbOval);
		figureGroup.add(jrbArc);
		figureGroup.add(jrbPolygon);
		controlPanel.add(jrbLine);
		controlPanel.add(jrbRectangle);
		controlPanel.add(jrbOval);
		controlPanel.add(jrbArc);
		controlPanel.add(jrbPolygon);
		controlPanel.add(jchkFille);
		add(controlPanel, BorderLayout.SOUTH);
		
		figurePanel = new FigurePanel(FigurePanel.OVAL);
		add(figurePanel, BorderLayout.CENTER);
		
	}
	class RepaintFigure implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (jrbLine.isSelected()) {
				figurePanel.setType(FigurePanel.LINE);
			} else if (jrbRectangle.isSelected()) {
				figurePanel.setType(FigurePanel.RECTANGLE);
			} else if (jrbOval.isSelected()) {
				figurePanel.setType(FigurePanel.OVAL);
			} else if (jrbArc.isSelected()) {
				figurePanel.setType(FigurePanel.ARC);
			} else if (jrbPolygon.isSelected()) {
				figurePanel.setType(FigurePanel.POLYGON);
			}
			figurePanel.setFilled(jchkFille.isSelected());
		}		
	}
	
	
	public static void main(String[] args) {
		Exercise02 frame = new Exercise02();
		frame.setTitle("Exercise02");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.pack();
		frame.setSize(Math.max(frame.getWidth(), frame.getHeight()), Math.max(frame.getWidth(), frame.getHeight()));
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	class FigurePanel extends JPanel {

		private static final long serialVersionUID = 1L;
		// Define constants
		public static final int LINE = 1;
		public static final int RECTANGLE = 2;
		public static final int ROUND_RECTANGLE = 3;
		public static final int OVAL = 4;
		public static final int ARC = 5;
		public static final int POLYGON = 6;

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
				break;
			case ARC:
				g.setColor(Color.MAGENTA);
				if (filled) {
					g.fillArc((int) (0.1 * width), (int) (0.1 * height), (int) (0.8 * width), (int) (0.8 * height), 0, 30);
					g.fillArc((int) (0.1 * width), (int) (0.1 * height), (int) (0.8 * width), (int) (0.8 * height), 90, 30);
					g.fillArc((int) (0.1 * width), (int) (0.1 * height), (int) (0.8 * width), (int) (0.8 * height), 180, 30);
					g.fillArc((int) (0.1 * width), (int) (0.1 * height), (int) (0.8 * width), (int) (0.8 * height), 270, 30);
				} else {
					g.drawArc((int) (0.1 * width), (int) (0.1 * height), (int) (0.8 * width), (int) (0.8 * height), 0, 30);
					g.drawArc((int) (0.1 * width), (int) (0.1 * height), (int) (0.8 * width), (int) (0.8 * height), 90, 30);
					g.drawArc((int) (0.1 * width), (int) (0.1 * height), (int) (0.8 * width), (int) (0.8 * height), 180, 30);
					g.drawArc((int) (0.1 * width), (int) (0.1 * height), (int) (0.8 * width), (int) (0.8 * height), 270, 30);
				}
				break;
			case POLYGON:
				g.setColor(Color.GREEN);
			    int xCenter = getWidth() / 2;
			    int yCenter = getHeight() / 2;
			    int radius = (int)(Math.min(getWidth(), getHeight()) * 0.4);
				Polygon polygon = new Polygon();
			    polygon.addPoint(xCenter + radius, yCenter);
			    polygon.addPoint((int)(xCenter + radius *
			      Math.cos(2 * Math.PI / 6)), (int)(yCenter - radius *
			      Math.sin(2 * Math.PI / 6)));
			    polygon.addPoint((int)(xCenter + radius *
			      Math.cos(2 * 2 * Math.PI / 6)), (int)(yCenter - radius *
			      Math.sin(2 * 2 * Math.PI / 6)));
			    polygon.addPoint((int)(xCenter + radius *
			      Math.cos(3 * 2 * Math.PI / 6)), (int)(yCenter - radius *
			      Math.sin(3 * 2 * Math.PI / 6)));
			    polygon.addPoint((int)(xCenter + radius *
			      Math.cos(4 * 2 * Math.PI / 6)), (int)(yCenter - radius *
			      Math.sin(4 * 2 * Math.PI / 6)));
			    polygon.addPoint((int)(xCenter + radius *
			      Math.cos(5 * 2 * Math.PI / 6)), (int)(yCenter - radius *
			      Math.sin(5 * 2 * Math.PI / 6)));
				if (filled)
					g.fillPolygon(polygon);
				else
					g.drawPolygon(polygon);
				break;
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
