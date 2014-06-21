package net.bohush.exercises.chapter40;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.tree.*;

import java.util.*;

public class Exercise08 extends JApplet {
	private static final long serialVersionUID = 1L;
	private JTree jTree = new JTree();
	private JTextArea jtaOutput = new JTextArea();
	private JButton jbtShowPath = new JButton("Show Path");
	private JButton jbtShowPaths = new JButton("Show Paths");
	private JButton jButton1 = new JButton("breadth-first");
	private JButton jButton2 = new JButton("depth-first");
	private JButton jButton3 = new JButton("preorder");
	private JButton jButton4 = new JButton("postorder");

	public Exercise08() {
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(jTree), new JScrollPane(jtaOutput));
		splitPane.setDividerLocation(200);
		splitPane.setContinuousLayout(true);
		
		JPanel panel = new JPanel();
		panel.add(jbtShowPath);
		panel.add(jbtShowPaths);
		panel.add(jButton1);
		panel.add(jButton2);
		panel.add(jButton3);
		panel.add(jButton4);

		jButton1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(jTree.getSelectionPath() != null) {
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree.getSelectionPath().getLastPathComponent();
					@SuppressWarnings("rawtypes")
					Enumeration enumeration = node.breadthFirstEnumeration();
					String result = "breadth-first\n";
					while (enumeration.hasMoreElements()) {
						result += enumeration.nextElement().toString() + " ";
					}
					jtaOutput.setText(result);					
				}
			}
		});
		
		jButton2.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(jTree.getSelectionPath() != null) {
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree.getSelectionPath().getLastPathComponent();
					@SuppressWarnings("rawtypes")
					Enumeration enumeration = node.depthFirstEnumeration();
					String result = "depth-first\n";
					while (enumeration.hasMoreElements()) {
						result += enumeration.nextElement().toString() + " ";
					}
					jtaOutput.setText(result);					
				}
			}
		});
		
		jButton3.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(jTree.getSelectionPath() != null) {
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree.getSelectionPath().getLastPathComponent();
					@SuppressWarnings("rawtypes")
					Enumeration enumeration = node.preorderEnumeration();
					String result = "preorder\n";
					while (enumeration.hasMoreElements()) {
						result += enumeration.nextElement().toString() + " ";
					}
					jtaOutput.setText(result);					
				}
			}
		});
		
		jButton4.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(jTree.getSelectionPath() != null) {
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree.getSelectionPath().getLastPathComponent();
					@SuppressWarnings("rawtypes")
					Enumeration enumeration = node.postorderEnumeration();
					String result = "postorder\n";
					while (enumeration.hasMoreElements()) {
						result += enumeration.nextElement().toString() + " ";
					}
					jtaOutput.setText(result);					
				}
			}
		});
		add(splitPane, BorderLayout.CENTER);
		add(panel, BorderLayout.NORTH);

		jbtShowPath.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TreePath path = jTree.getSelectionPath();
				if(path != null) {
					jtaOutput.append("\nProcessing a single path\n");
					jtaOutput.append("# of elements: " + path.getPathCount());
					jtaOutput.append("\nlast element: "	+ path.getLastPathComponent());
					jtaOutput.append("\nfrom last node in the path to the root: ");
					TreeNode node = (TreeNode) path.getLastPathComponent();
					jtaOutput.setText("");
					while (node != null) {
						jtaOutput.append(node.toString() + " ");
						node = node.getParent();
					}	
				}				
			}
		});

		jbtShowPaths.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jtaOutput.append("\nProcessing multiple paths\n");
				javax.swing.tree.TreePath[] paths = jTree.getSelectionPaths();
				if(paths != null) {
					jtaOutput.setText("");
					for (int i = 0; i < paths.length; i++)
						jtaOutput.append(paths[i].toString() + "\n");					
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
		frame.setSize(900, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}