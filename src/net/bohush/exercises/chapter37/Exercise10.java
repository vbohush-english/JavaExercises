package net.bohush.exercises.chapter37;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;

public class Exercise10 extends JApplet {
	private static final long serialVersionUID = 1L;
	private FlowLayout flowLayout = new FlowLayout();
	private GridLayout gridLayout = new GridLayout(2, 2);
	private DiagonalLayout diagonalLayout = new DiagonalLayout();

	private JButton jbt1 = new JButton("Button 1");
	private JButton jbt2 = new JButton("Button 2");
	private JButton jbt3 = new JButton("Button 3");
	private JButton jbt4 = new JButton("Button 4");

	private JRadioButton jrbFlowLayout = new JRadioButton("FlowLayout");
	private JRadioButton jrbGridLayout = new JRadioButton("GridLayout");
	private JRadioButton jrbDiagonalLayout = new JRadioButton("DiagonalLayout",	true);

	private JPanel jPanel2 = new JPanel();
	private JPanel jPanel4 = new JPanel(new GridLayout(3, 1, 0, 0));
	private JTextField jTextField = new JTextField("20");
	private JCheckBox checkBox1 = new JCheckBox("Major diagonal", true);
	private JCheckBox checkBox2 = new JCheckBox("Last component stretched");
	
	public Exercise10() {
		diagonalLayout.setGap(20);
		JPanel jPanel3 = new JPanel(new BorderLayout(5, 5));
		JPanel jPanel5 = new JPanel(new BorderLayout());
		jPanel5.add(new JLabel("Diagonal gap: "), BorderLayout.WEST);
		jTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					diagonalLayout.setGap(Integer.parseInt(jTextField.getText()));
					jPanel2.setLayout(diagonalLayout);
					jPanel2.validate();
				} catch (NumberFormatException e2) {}				
			}
		});
		checkBox1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				diagonalLayout.setMajorDiagonal(checkBox1.isSelected());
				jPanel2.setLayout(diagonalLayout);
				jPanel2.validate();
			}
		});
		checkBox2.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				diagonalLayout.setLastFill(checkBox2.isSelected());
				jPanel2.setLayout(diagonalLayout);
				jPanel2.validate();
			}
		});
		jPanel5.add(jTextField, BorderLayout.CENTER);
		jPanel4.add(jPanel5);
		jPanel4.add(checkBox1);
		jPanel4.add(checkBox2);
		jPanel3.add(jPanel4, BorderLayout.EAST);
		
		// Set default layout in jPanel2
		jPanel2.setLayout(diagonalLayout);
		jPanel2.add(jbt1);
		jPanel2.add(jbt2);
		jPanel2.add(jbt3);
		jPanel2.add(jbt4);
		jPanel2.setBorder(new LineBorder(Color.black));

		JPanel jPanel1 = new JPanel();
		jPanel1.setBorder(new TitledBorder("Select a Layout Manager"));
		jPanel1.add(jrbFlowLayout);
		jPanel1.add(jrbGridLayout);
		jPanel1.add(jrbDiagonalLayout);

		ButtonGroup buttonGroup1 = new ButtonGroup();
		buttonGroup1.add(jrbFlowLayout);
		buttonGroup1.add(jrbGridLayout);
		buttonGroup1.add(jrbDiagonalLayout);

		jPanel3.add(jPanel1, BorderLayout.CENTER);
		add(jPanel3, BorderLayout.SOUTH);
		add(jPanel2, BorderLayout.CENTER);

		jrbFlowLayout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jPanel2.setLayout(flowLayout);
				jPanel4.setVisible(false);
				jPanel2.validate();
			}
		});
		jrbGridLayout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jPanel2.setLayout(gridLayout);
				jPanel4.setVisible(false);
				jPanel2.validate();
			}
		});
		jrbDiagonalLayout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jPanel2.setLayout(diagonalLayout);
				jPanel4.setVisible(true);
				jPanel2.validate();				
			}
		});
	}

	public static void main(String[] args) {
		Exercise10 applet = new Exercise10();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Exercise10");
		frame.getContentPane().add(applet, BorderLayout.CENTER);
		applet.init();
		applet.start();
		frame.setSize(600, 400);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((d.width - frame.getSize().width) / 2,
				(d.height - frame.getSize().height) / 2);
		frame.setVisible(true);
	}

	public class DiagonalLayout implements LayoutManager, java.io.Serializable {
		private static final long serialVersionUID = 1L;

		/** Vertical gap between the components */
		private int gap = 10;

		/** True if components are placed along the major diagonal */
		private boolean majorDiagonal = true;

		/* True if the last component is stretched to fill the space */
		private boolean lastFill = false;

		/** Constructor */
		public DiagonalLayout() {
		}

		public void addLayoutComponent(String name, Component comp) {
			// No need to implement it for DiaganolLayout
		}

		public void removeLayoutComponent(Component comp) {
			// No need to implement it for DiaganolLayout
		}

		public Dimension preferredLayoutSize(Container parent) {
			return minimumLayoutSize(parent);
		}

		public Dimension minimumLayoutSize(Container parent) {
			return new Dimension(0, 0);
		}

		public void layoutContainer(Container parent) {
			int numberOfComponents = parent.getComponentCount();

			Insets insets = parent.getInsets();
			int w = parent.getSize().width - insets.left - insets.right;
			int h = parent.getSize().height - insets.bottom - insets.top;

			if (majorDiagonal) {
				int x = 10, y = 10;

				for (int j = 0; j < numberOfComponents; j++) {
					Component c = parent.getComponent(j);
					Dimension d = c.getPreferredSize();

					if (c.isVisible())
						if (lastFill && (j == numberOfComponents - 1))
							c.setBounds(x, y, w - x, h - y);
						else
							c.setBounds(x, y, d.width, d.height);
					x += d.height + gap;
					y += d.height + gap;
				}
			} else { // It is subdiagonal
				int x = w - 10, y = 10;

				for (int j = 0; j < numberOfComponents; j++) {
					Component c = parent.getComponent(j);
					Dimension d = c.getPreferredSize();

					if (c.isVisible())
						if (lastFill & (j == numberOfComponents - 1))
							c.setBounds(0, y, x, h - y);
						else
							c.setBounds(x - d.width, y, d.width, d.height);

					x -= (d.height + gap);
					y += d.height + gap;
				}
			}
		}

		public int getGap() {
			return gap;
		}

		public void setGap(int gap) {
			this.gap = gap;
		}

		public void setMajorDiagonal(boolean newMajorDiagonal) {
			majorDiagonal = newMajorDiagonal;
		}

		public boolean isMajorDiagonal() {
			return majorDiagonal;
		}

		public void setLastFill(boolean newLastFill) {
			lastFill = newLastFill;
		}

		public boolean isLastFill() {
			return lastFill;
		}
	}
}