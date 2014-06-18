package net.bohush.exercises.chapter39;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.border.*;

public class Exercise08 extends JApplet {
	private static final long serialVersionUID = 1L;
	private final static int NUMBER_OF_NATIONS = 7;
	private String[] nations = new String[] { "Denmark", "Germany", "China", "India", "Norway", "UK", "US" };
	private ImageIcon[] bigIcons = new ImageIcon[NUMBER_OF_NATIONS];

	// Create a list model
	private DefaultListModel<Object[]> listModel = new DefaultListModel<Object[]>();

	// Create a list using the list model
	private JList<Object[]> jlstNations = new JList<Object[]>(listModel);

	// Create a list cell renderer
	private ListCellRenderer<Object> renderer = new MyListCellRenderer<Object>();

	// Create a split pane
	private JSplitPane jSplitPane1 = new JSplitPane();

	// Create a label for displaying iamge
	private JLabel jlblImage = new JLabel("", JLabel.CENTER);

	/** Construct ListCellRenderer */
	public Exercise08() {
		// Load small and large image icons
		for (int i = 0; i < NUMBER_OF_NATIONS; i++) {
			bigIcons[i] = new ImageIcon(getClass().getResource("image/flag" + i + ".gif"));
			listModel.addElement(new Object[] { bigIcons[i], nations[i] });
		}

		// Set list cell renderer
		jlstNations.setCellRenderer(renderer);
		jlstNations.setPreferredSize(new Dimension(200, 200));
		jSplitPane1.setLeftComponent(new JScrollPane(jlstNations));
		jSplitPane1.setRightComponent(jlblImage);
		jSplitPane1.setDividerLocation(150);
		jlstNations.setSelectedIndex(0);
		jlblImage.setIcon(bigIcons[0]);
		add(jSplitPane1, BorderLayout.CENTER);

		// Register listener
		jlstNations.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!listModel.isEmpty()) {
					if(jlstNations.getSelectedIndex() == -1) {
						jlstNations.setSelectedIndex(0);
					}
					jlblImage.setIcon((ImageIcon)((Object[])(jlstNations.getSelectedValue()))[0]);					
				} else {
					jlblImage.setIcon(null);
				}
			}
		});
		
		jlstNations.addKeyListener(new KeyAdapter() {			
			@Override
			public void keyPressed(KeyEvent e) {
				if((!listModel.isEmpty())&&(e.getKeyCode() == KeyEvent.VK_DELETE)) {
					listModel.removeElementAt(jlstNations.getSelectedIndex());					
				}
			}
		});
	}

	public static void main(String[] args) {
		Exercise08 applet = new Exercise08();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Exercise08");
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
		public Component getListCellRendererComponent(JList<? extends E> list,
				E value, int index, boolean isSelected, boolean cellHasFocus) {
			Object[] pair = (Object[]) value; // Cast value into an array
			JPanel jpCell = new JPanel(new BorderLayout(5, 5));
			jpCell.setOpaque(true);

			JLabel jLabel = new JLabel("123", JLabel.LEFT);
			ImageViewer imageViewer = new ImageViewer(((ImageIcon) pair[0]).getImage());
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
			jpCell.add(imageViewer, BorderLayout.WEST);
			return jpCell;
		}
	}

	public class ImageViewer extends JPanel {
		private static final long serialVersionUID = 1L;

		/** Hold value of property image */
		private java.awt.Image image;

		/** Hold value of property stretched */
		private boolean stretched = true;

		/** Hold value of property xCoordinate */
		private int xCoordinate;

		/** Hold value of property yCoordinate */
		private int yCoordinate;

		/** Construct an empty image viewer */
		public ImageViewer() {
		}

		/** Construct an image viewer for a specified Image object */
		public ImageViewer(Image image) {
			this.image = image;
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			if (image != null)
				if (isStretched())
					g.drawImage(image, xCoordinate, yCoordinate,
							getSize().width, getSize().height, this);
				else
					g.drawImage(image, xCoordinate, yCoordinate, this);
		}

		/** Return value of property image */
		public java.awt.Image getImage() {
			return image;
		}

		/** Set a new value for property image */
		public void setImage(java.awt.Image image) {
			this.image = image;
			repaint();
		}

		/** Return value of property stretched */
		public boolean isStretched() {
			return stretched;
		}

		/** Set a new value for property stretched */
		public void setStretched(boolean stretched) {
			this.stretched = stretched;
			repaint();
		}

		/** Return value of property xCoordinate */
		public int getXCoordinate() {
			return xCoordinate;
		}

		/** Set a new value for property xCoordinate */
		public void setXCoordinate(int xCoordinate) {
			this.xCoordinate = xCoordinate;
			repaint();
		}

		/** Return value of property yCoordinate */
		public int getYCoordinate() {
			return yCoordinate;
		}

		/** Set a new value for property yCoordinate */
		public void setYCoordinate(int yCoordinate) {
			this.yCoordinate = yCoordinate;
			repaint();
		}
		
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(30, 20);
		}
	}
}