package net.bohush.exercises.chapter40;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class Exercise09 extends JApplet {
	private static final long serialVersionUID = 1L;
	private JTextField jTextField1 = new JTextField();
	private JTree jTree = new JTree();
	
	public Exercise09() {
		JPanel jPanel1 = new JPanel(new BorderLayout());
		
		JButton jButton1 = new JButton("Set Root Dir");
		jPanel1.add(jTextField1, BorderLayout.CENTER);
		jPanel1.add(jButton1, BorderLayout.EAST);
		setLayout(new BorderLayout());
		add(jPanel1, BorderLayout.NORTH);
		
		jTree.setVisible(false);
		add(new JScrollPane(jTree), BorderLayout.CENTER);
		
		jButton1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if((jTextField1.getText() != null)&&(!jTextField1.getText().equals(""))) {
					File rootFile = new File(jTextField1.getText());
					if(rootFile.exists()) {
						DefaultMutableTreeNode root = new DefaultMutableTreeNode(jTextField1.getText());
						loadFirstTree(rootFile, root);
						jTree.setModel(new DefaultTreeModel(root));
						jTree.setVisible(true);						
					} else {
						jTree.setVisible(false);	
					}
				} else {
					jTree.setVisible(false);	
				}
			}
		});
	}
	
	private void loadFirstTree(File file, DefaultMutableTreeNode root) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (int i = 0; files != null && i < files.length; i++) {
				loadTree(files[i], root);
			}
		}
	}
	
	private void loadTree(File file, DefaultMutableTreeNode root) {
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(file.getName());
		root.add(newNode);
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (int i = 0; files != null && i < files.length; i++) {
				loadTree(files[i], newNode);
			}
		}

	}
	

	public static void main(String[] args) {
		Exercise09 applet = new Exercise09();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Exercise09");
		frame.getContentPane().add(applet, BorderLayout.CENTER);
		applet.init();
		applet.start();
		frame.setSize(900, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}