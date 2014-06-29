package net.bohush.exercises.chapter38;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

public class Exercise05 extends JFrame{
	private static final long serialVersionUID = 1L;

	public Exercise05() {
		
		JMenuBar jmb = new JMenuBar();
		this.setJMenuBar(jmb);
		JMenu jMenu1 = new JMenu("Message Dialog");
		JMenuItem jMenuItem1 = new JMenuItem("Error Message");
		JMenuItem jMenuItem2 = new JMenuItem("Information Message");
		JMenuItem jMenuItem3 = new JMenuItem("Question Message");
		JMenuItem jMenuItem4 = new JMenuItem("Warning Message");
		JMenuItem jMenuItem5 = new JMenuItem("Plain Message");
		JMenuItem jMenuItem6 = new JMenuItem("Custom Icon");
		jMenuItem1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Error Message", "Message Dialog", JOptionPane.ERROR_MESSAGE);
			}
		});
		jMenuItem2.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Information Message", "Message Dialog", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		jMenuItem3.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Question Message", "Message Dialog", JOptionPane.QUESTION_MESSAGE);
			}
		});
		jMenuItem4.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Warning Message", "Message Dialog", JOptionPane.WARNING_MESSAGE);
			}
		});
		jMenuItem5.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Plain Message", "Message Dialog", JOptionPane.PLAIN_MESSAGE);
			}
		});
		jMenuItem6.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Custom Message", "Message Dialog", JOptionPane.PLAIN_MESSAGE, new ImageIcon(getClass().getResource("image/usIcon.gif")));
			}
		});
		jMenu1.add(jMenuItem1);
		jMenu1.add(jMenuItem2);
		jMenu1.add(jMenuItem3);
		jMenu1.add(jMenuItem4);
		jMenu1.add(jMenuItem5);
		jMenu1.add(jMenuItem6);
		jmb.add(jMenu1);
		
		JMenu jMenu2 = new JMenu("Confirmation Dialog");
		JMenuItem jMenuItem7 = new JMenuItem("YES_NO_OPTION");
		JMenuItem jMenuItem8 = new JMenuItem("YES_NO_CANCEL_OPTION");
		JMenuItem jMenuItem9 = new JMenuItem("OK_CANCEL_OPTION");
		jMenuItem7.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(null, "YES_NO_OPTION", "Confirmation Dialog", JOptionPane.YES_NO_OPTION);
			}
		});
		jMenuItem8.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(null, "YES_NO_CANCEL_OPTION", "Confirmation Dialog", JOptionPane.YES_NO_CANCEL_OPTION);
			}
		});
		jMenuItem9.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(null, "OK_CANCEL_OPTION", "Confirmation Dialog", JOptionPane.OK_CANCEL_OPTION);
			}
		});
		jMenu2.add(jMenuItem7);
		jMenu2.add(jMenuItem8);
		jMenu2.add(jMenuItem9);
		jmb.add(jMenu2);
		
		JMenu jMenu3 = new JMenu("Input Dialog");
		JMenuItem jMenuItem10 = new JMenuItem("TextField Input");
		JMenuItem jMenuItem11 = new JMenuItem("ComboBox Input");
		JMenuItem jMenuItem12 = new JMenuItem("List Input");
		jMenuItem10.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showInputDialog(null, "TextField Input", "Input Dialog", JOptionPane.QUESTION_MESSAGE);
			}
		});
		jMenuItem11.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showInputDialog(null, "ComboBox Input", "Input Dialog", JOptionPane.QUESTION_MESSAGE, null, new String[]{"value1", "value2", "value3"}, null);
			}
		});
		jMenuItem12.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] list = new String[25];
				for (int i = 0; i < list.length; i++) {
					list[i] = "list" + (i + 1); 
				}
				JOptionPane.showInputDialog(null, "List Input", "Input Dialog", JOptionPane.QUESTION_MESSAGE, null, list, null);
			}
		});
		jMenu3.add(jMenuItem10);
		jMenu3.add(jMenuItem11);
		jMenu3.add(jMenuItem12);
		jmb.add(jMenu3);
		
		JMenu jMenu4 = new JMenu("Input Dialog");
		JMenuItem jMenuItem13 = new JMenuItem("Custom Option");
		jMenuItem13.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				ColorDialog colorDialog1 = new ColorDialog();
				colorDialog1.setVisible(true);
			}
		});
		jMenu4.add(jMenuItem13);
		jmb.add(jMenu4);
	}
	
	public static void main(String[] args) {
		Exercise05 frame = new Exercise05();
		frame.setTitle("Exercise05");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(600, 400);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	class ColorDialog extends JDialog {
		private static final long serialVersionUID = 1L;
		// Declare color component values and selected color
		private int redValue, greenValue, blueValue;
		private Color color = null;

		// Create sliders
		private JSlider jslRed = new JSlider(0, 128);
		private JSlider jslGreen = new JSlider(0, 128);
		private JSlider jslBlue = new JSlider(0, 128);

		// Create two buttons
		private JButton jbtOK = new JButton("OK");
		private JButton jbtCancel = new JButton("Cancel");

		// Create a panel to display the selected color
		private JPanel jpSelectedColor = new JPanel();

		public ColorDialog() {
			this(null, true);
		}

		public ColorDialog(java.awt.Frame parent, boolean modal) {
			super(parent, modal);
			setTitle("Choose Color");

			// Group two buttons OK and Cancel
			JPanel jpButtons = new JPanel();
			jpButtons.add(jbtOK);
			jpButtons.add(jbtCancel);

			// Group labels
			JPanel jpLabels = new JPanel();
			jpLabels.setLayout(new GridLayout(3, 0));
			jpLabels.add(new JLabel("Red"));
			jpLabels.add(new JLabel("Green"));
			jpLabels.add(new JLabel("Blue"));

			// Group sliders for selecting red, green, and blue colors
			JPanel jpSliders = new JPanel();
			jpSliders.setLayout(new GridLayout(3, 0));
			jpSliders.add(jslRed);
			jpSliders.add(jslGreen);
			jpSliders.add(jslBlue);

			// Group jpLabels and jpSliders
			JPanel jpSelectColor = new JPanel();
			jpSelectColor.setLayout(new BorderLayout());
			jpSelectColor.setBorder(BorderFactory
					.createTitledBorder("Select Color"));
			jpSelectColor.add(jpLabels, BorderLayout.WEST);
			jpSelectColor.add(jpSliders, BorderLayout.CENTER);

			// Group jpSelectColor and jpSelectedColor
			JPanel jpColor = new JPanel();
			jpColor.setLayout(new BorderLayout());
			jpColor.add(jpSelectColor, BorderLayout.SOUTH);
			jpColor.add(jpSelectedColor, BorderLayout.CENTER);

			// Place jpButtons and jpColor into the dialog box
			add(jpButtons, BorderLayout.SOUTH);
			add(jpColor, BorderLayout.CENTER);
			pack();

			jbtOK.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});

			jbtCancel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					color = null;
					setVisible(false);
				}
			});

			jslRed.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					redValue = jslRed.getValue();
					color = new Color(redValue, greenValue, blueValue);
					jpSelectedColor.setBackground(color);
				}
			});

			jslGreen.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					greenValue = jslGreen.getValue();
					color = new Color(redValue, greenValue, blueValue);
					jpSelectedColor.setBackground(color);
				}
			});

			jslBlue.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					blueValue = jslBlue.getValue();
					color = new Color(redValue, greenValue, blueValue);
					jpSelectedColor.setBackground(color);
				}
			});
			setLocationRelativeTo(null);
		}

		@Override
		public Dimension getPreferredSize() {
			return new java.awt.Dimension(200, 200);
		}

		/** Return color */
		public Color getColor() {
			return color;
		}
	}
}
