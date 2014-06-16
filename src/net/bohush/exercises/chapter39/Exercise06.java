package net.bohush.exercises.chapter39;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Exercise06 extends JApplet {
	private static final long serialVersionUID = 1L;
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
	private JList<String> jlst = new JList<String>(listModel);
	private JButton jbtAdd = new JButton("Add new item");
	private JButton jbtRemove = new JButton("Remove selected item");

	/** Construct the applet */
	public Exercise06() {
		// Add items to the list model
		listModel.addElement("Item1");
		listModel.addElement("Item2");
		listModel.addElement("Item3");
		listModel.addElement("Item4");
		listModel.addElement("Item5");
		listModel.addElement("Item6");

		JPanel panel = new JPanel();
		panel.add(jbtAdd);
		panel.add(jbtRemove);

		add(panel, BorderLayout.NORTH);
		add(new JScrollPane(jlst), BorderLayout.CENTER);

		jlst.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_DELETE) {
					int[] indices = jlst.getSelectedIndices();
					for (int i = indices.length - 1; i >= 0; i--) {
						listModel.remove(indices[i]);	
					}	
				}
			}
		});
		
		// Register listeners
		jbtAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String newItem = JOptionPane.showInputDialog("Enter a new item");
				if (newItem != null)
					if (jlst.getSelectedIndex() == -1)
						listModel.addElement(newItem);
					else
						listModel.add(jlst.getSelectedIndex(), newItem);
			}
		});

		jbtRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int[] indices = jlst.getSelectedIndices();
				for (int i = indices.length - 1; i >= 0; i--) {
					listModel.remove(indices[i]);	
				}				
			}
		});
	}

	public static void main(String[] args) {
		Exercise06 applet = new Exercise06();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(3);
		frame.setTitle("Exercise06");
		frame.getContentPane().add(applet, BorderLayout.CENTER);
		applet.init();
		applet.start();
		frame.setSize(400, 320);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}