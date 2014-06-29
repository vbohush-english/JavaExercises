package net.bohush.exercises.chapter39;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.*;


public class Exercise12 extends JApplet {
	private static final long serialVersionUID = 1L;
	private final static int NUMBER_OF_COLORS = 13;
	private String[] colorNames = new String[] {"BLACK", "BLUE", "CYAN",
			"DARK_GRAY", "GRAY", "GREEN", "LIGHT_GRAY", "MAGENTA",
			"ORANGE", "PINK", "RED", "WHITE", "YELLOW"};
	private Color[] colors = new Color[] {Color.BLACK, Color.BLUE, Color.CYAN,
			Color.DARK_GRAY, Color.GRAY, Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA,
			Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW};

	private DefaultComboBoxModel<Object[]> listModel = new DefaultComboBoxModel<Object[]>();
	private JComboBox<Object[]> jcboColors = new JComboBox<Object[]>(listModel);
	private ListCellRenderer<Object> renderer = new MyListCellRenderer<Object>();
	private JLabel  jLabel = new JLabel("Welcome to Java", JLabel.CENTER);

	/** Construct the applet */
	public Exercise12() {
		setLayout(new BorderLayout());

		for (int i = 0; i < NUMBER_OF_COLORS; i++) {
			listModel.addElement(new Object[] { colors[i], colorNames[i] });
		}

		jcboColors.setRenderer(renderer);
		jcboColors.setSelectedIndex(0);
		
		
	    add(jcboColors, BorderLayout.NORTH);
	    add(jLabel, BorderLayout.CENTER);

		jcboColors.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jLabel.setForeground(colors[jcboColors.getSelectedIndex()]);
			}
		});
		
	}

	/** Main method */
	public static void main(String[] args) {
		Exercise12 applet = new Exercise12();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Exercise12");
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
		public Component getListCellRendererComponent(JList<? extends E> list, E value, int index, boolean isSelected, boolean cellHasFocus) {
			Object[] pair = (Object[]) value;
			JLabel label = new JLabel((String)(pair[1]), JLabel.LEFT);
			label.setForeground((Color)(pair[0]));
			label.setOpaque(true);

			if (isSelected) {
				label.setBackground(list.getSelectionBackground());
			} else {
				label.setBackground(list.getBackground());
			}

			label.setBorder(cellHasFocus ? lineBorder : emptyBorder);
			return label;
		}
	}
}