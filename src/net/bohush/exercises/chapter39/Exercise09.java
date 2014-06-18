package net.bohush.exercises.chapter39;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;

import javax.swing.border.*;

public class Exercise09 extends JApplet {
	private static final long serialVersionUID = 1L;
	private final static int NUMBER_OF_FIGURES = 4;
	private String[] figures = new String[] {"Line", "Rectangle", "Round Rectangle", "Oval"};
	private FigurePanel[] figurePanels = new FigurePanel[NUMBER_OF_FIGURES];

	// Create a list model
	private DefaultListModel<Object[]> listModel = new DefaultListModel<Object[]>();

	// Create a list using the list model
	private JList<Object[]> jlstFigures = new JList<Object[]>(listModel);

	// Create a list cell renderer
	private ListCellRenderer<Object> renderer = new MyListCellRenderer<Object>();

	// Create a split pane
	private JSplitPane jSplitPane1 = new JSplitPane();

	// Create a label for displaying iamge
	private FigurePanel figurePanel = new FigurePanel(FigurePanel.LINE);

	/** Construct ListCellRenderer */
	public Exercise09() {
		// Load small and large image icons
		figurePanels[0] = new FigurePanel(FigurePanel.LINE);
		figurePanels[1] = new FigurePanel(FigurePanel.RECTANGLE);
		figurePanels[2] = new FigurePanel(FigurePanel.ROUND_RECTANGLE);
		figurePanels[3] = new FigurePanel(FigurePanel.OVAL);
		for (int i = 0; i < NUMBER_OF_FIGURES; i++) {
			listModel.addElement(new Object[] { figurePanels[i], figures[i] });
		}

		// Set list cell renderer
		jlstFigures.setCellRenderer(renderer);
		jlstFigures.setPreferredSize(new Dimension(200, 200));
		jSplitPane1.setLeftComponent(new JScrollPane(jlstFigures));
		jSplitPane1.setRightComponent(figurePanel);
		jSplitPane1.setDividerLocation(150);
		jlstFigures.setSelectedIndex(0);
		add(jSplitPane1, BorderLayout.CENTER);

		// Register listener
		jlstFigures.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				figurePanel.setType(figurePanels[jlstFigures.getSelectedIndex()].getType());
			}
		});
	}

	public static void main(String[] args) {
		Exercise09 applet = new Exercise09();
		JFrame frame = new JFrame();
		// EXIT_ON_CLOSE == 3
		frame.setDefaultCloseOperation(3);
		frame.setTitle("Exercise09");
		frame.getContentPane().add(applet, BorderLayout.CENTER);
		applet.init();
		applet.start();
		frame.setSize(500, 420);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public class MyListCellRenderer<E> implements ListCellRenderer<E> {
		private Border lineBorder = BorderFactory.createLineBorder(Color.black,	1);
		private Border emptyBorder = BorderFactory.createEmptyBorder(2, 2, 2, 2);

		/** Implement this method in ListCellRenderer */
		public Component getListCellRendererComponent(JList<? extends E> list, E value, int index, boolean isSelected, boolean cellHasFocus) {
			Object[] pair = (Object[]) value; // Cast value into an array
			JPanel jpCell = new JPanel(new BorderLayout(5, 5));
			jpCell.setOpaque(true);

			JLabel jLabel = new JLabel(" ", JLabel.LEFT);
			FigurePanel figurePanel = new FigurePanel(((FigurePanel) pair[0]).getType());
			jLabel.setText(pair[1].toString());

			if (isSelected) {
				jpCell.setForeground(list.getSelectionForeground());
				jpCell.setBackground(list.getSelectionBackground());
			} else {
				jpCell.setForeground(list.getForeground());
				jpCell.setBackground(list.getBackground());
			}

			jpCell.setBorder(cellHasFocus ? lineBorder : emptyBorder);
			jpCell.add(jLabel, BorderLayout.CENTER);
			jpCell.add(figurePanel, BorderLayout.WEST);
			return jpCell;
		}
	}

	public class FigurePanel extends JPanel {
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
			return new Dimension(40, 30);
		}
	}
}