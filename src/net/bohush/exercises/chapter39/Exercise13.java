package net.bohush.exercises.chapter39;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.Border;

public class Exercise13 extends JApplet {
	private static final long serialVersionUID = 1L;
	private final static int NUMBER_OF_NATIONS = 7;
	private String[] nations = new String[] { "Denmark", "Germany", "China", "India", "Norway", "UK", "US" };
	private ImageIcon[] bigIcons = new ImageIcon[NUMBER_OF_NATIONS];

	// Create a combo box model
	private DefaultComboBoxModel<Object> model = new DefaultComboBoxModel<Object>();

	// Create a combo box with the specified model
	private JComboBox<Object> jcboCountries = new JComboBox<Object>(model);

	// Create a list cell renderer
	private MyListCellRenderer<Object> renderer = new MyListCellRenderer<Object>();

	// Create a label for displaying iamge
	private JLabel jlblImage = new JLabel("", JLabel.CENTER);

	/** Construct the applet */
	public Exercise13() {
		// Load small and large image icons
		
		for (int i = 0; i < NUMBER_OF_NATIONS; i++) {
			bigIcons[i] = new ImageIcon(getClass().getResource("image/flag" + i + ".gif"));
			model.addElement(new Object[] { bigIcons[i], nations[i] });
		}

		// Set list cell renderer for the combo box
		jcboCountries.setRenderer(renderer);
		jlblImage.setIcon(bigIcons[0]);
		add(jcboCountries, java.awt.BorderLayout.NORTH);
		add(jlblImage, java.awt.BorderLayout.CENTER);

		// Register listener
		jcboCountries.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				if(jcboCountries.getSelectedIndex() == -1) {
					jcboCountries.setSelectedIndex(0);
				}
				jlblImage.setIcon((ImageIcon)((Object[])(jcboCountries.getSelectedItem()))[0]);					
			}
		});
		
		
	
		jcboCountries.addKeyListener(new KeyAdapter() {			
			@Override
			public void keyPressed(KeyEvent e) {
				if((model.getSize() > 1)&&(e.getKeyCode() == KeyEvent.VK_DELETE)) {
					model.removeElementAt(jcboCountries.getSelectedIndex());					
				}
			}
		});
	}

	/** Main method */
	public static void main(String[] args) {
		Exercise13 applet = new Exercise13();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Exercise13");
		frame.getContentPane().add(applet, BorderLayout.CENTER);
		applet.init();
		applet.start();
		frame.setSize(400, 320);
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