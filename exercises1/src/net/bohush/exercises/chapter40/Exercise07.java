package net.bohush.exercises.chapter40;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.tree.*;

public class Exercise07 extends JApplet {

	private static final long serialVersionUID = 1L;

	// Create a combo box for choosing selection modes
	private JComboBox<String> jcboSelectionMode = new JComboBox<>(new String[] {
			"CONTIGUOUS_TREE_SELECTION", "DISCONTIGUOUS_TREE_SELECTION",
			"SINGLE_TREE_SELECTION" });

	// Create a check box for specifying editable
	private JCheckBox jchkEditable = new JCheckBox();

	// Create two buttons
	private JButton jbtAdd = new JButton("Add a Child for Selected Node");
	private JButton jbtRemove = new JButton("Remove Selected Nodes");
	private JButton jButton1 = new JButton("Store Tree");
	private JButton jButton2 = new JButton("Restore Tree");

	// Declare two trees
	private JTree jTree1, jTree2;

	public Exercise07() {
		// Create the first tree
		DefaultMutableTreeNode root, europe, northAmerica, us;

		europe = new DefaultMutableTreeNode("Europe");
		europe.add(new DefaultMutableTreeNode("UK"));
		europe.add(new DefaultMutableTreeNode("Germany"));
		europe.add(new DefaultMutableTreeNode("France"));
		europe.add(new DefaultMutableTreeNode("Norway"));

		northAmerica = new DefaultMutableTreeNode("North America");
		us = new DefaultMutableTreeNode("US");
		us.add(new DefaultMutableTreeNode("California"));
		us.add(new DefaultMutableTreeNode("Texas"));
		us.add(new DefaultMutableTreeNode("New York"));
		us.add(new DefaultMutableTreeNode("Florida"));
		us.add(new DefaultMutableTreeNode("Illinois"));
		northAmerica.add(us);
		northAmerica.add(new DefaultMutableTreeNode("Canada"));

		root = new DefaultMutableTreeNode("World");
		root.add(europe);
		root.add(northAmerica);

		jcboSelectionMode.setSelectedIndex(1);

		JPanel p1 = new JPanel();
		p1.add(new JLabel("selectionMode"));
		p1.add(jcboSelectionMode);
		p1.add(new JLabel("editable"));
		p1.add(jchkEditable);

		JPanel p2 = new JPanel(new GridLayout(1, 2));
		p2.add(new JScrollPane(jTree1 = new JTree(root)));
		p2.add(new JScrollPane(jTree2 = new JTree(new DefaultTreeModel(root)))); 

		JPanel p3 = new JPanel();
		p3.add(jbtAdd);
		p3.add(jbtRemove);
		p3.add(jButton1);
		p3.add(jButton2);

		add(p1, BorderLayout.NORTH);
		add(p2, BorderLayout.CENTER);
		add(p3, BorderLayout.SOUTH);

		// Register listeners
		jcboSelectionMode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jcboSelectionMode.getSelectedItem().equals(
						"CONTIGUOUS_TREE_SELECTION"))
					jTree1.getSelectionModel().setSelectionMode(
							TreeSelectionModel.CONTIGUOUS_TREE_SELECTION);
				else if (jcboSelectionMode.getSelectedItem().equals(
						"DISCONTIGUOUS_TREE_SELECTION"))
					jTree1.getSelectionModel().setSelectionMode(
							TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
				else
					jTree1.getSelectionModel().setSelectionMode(
							TreeSelectionModel.SINGLE_TREE_SELECTION);
			}
		});

		jchkEditable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jTree1.setEditable(jchkEditable.isSelected());
			}
		});

		jbtAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode parent = (DefaultMutableTreeNode)jTree1.getLastSelectedPathComponent();

				if (parent == null) {
					JOptionPane.showMessageDialog(null, "No node in the left tree is selected");
					return;
				}

				// Enter a new node
				String nodeName = JOptionPane.showInputDialog(null, "Enter a child node for " + parent, "Add a Child", JOptionPane.QUESTION_MESSAGE);

				// Insert the new node as a child of treeNode
				parent.add(new DefaultMutableTreeNode(nodeName));

				// Reload the model since a new tree node is added
				((DefaultTreeModel) (jTree1.getModel())).reload();
				((DefaultTreeModel) (jTree2.getModel())).reload();
			}
		});

		jbtRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Get all selected paths
				TreePath[] paths = jTree1.getSelectionPaths();

				if (paths == null) {
					JOptionPane.showMessageDialog(null,
							"No node in the left tree is selected");
					return;
				}

				// Remove all selected nodes
				for (int i = 0; i < paths.length; i++) {
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) (paths[i]
							.getLastPathComponent());

					if (node.isRoot()) {
						JOptionPane.showMessageDialog(null,
								"Cannot remove the root");
					} else
						node.removeFromParent();
				}

				// Reload the model since a new tree node is added
				((DefaultTreeModel) (jTree1.getModel())).reload();
				((DefaultTreeModel) (jTree2.getModel())).reload();
			}
		});
		
		jButton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("table.dat"));
					out.writeObject(jTree1.getModel());
					out.writeObject(jTree2.getModel());
					out.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		jButton2.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ObjectInputStream in = new ObjectInputStream(new FileInputStream("table.dat"));
					TreeModel treeModel1 = (TreeModel)in.readObject();
					TreeModel treeModel2 = (TreeModel)in.readObject();
					jTree1.setModel(treeModel1);
					jTree2.setModel(treeModel2);
					in.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
	}

	public static void main(String[] args) {
		Exercise07 applet = new Exercise07();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Exercise07");
		frame.getContentPane().add(applet, BorderLayout.CENTER);
		applet.init();
		applet.start();
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}