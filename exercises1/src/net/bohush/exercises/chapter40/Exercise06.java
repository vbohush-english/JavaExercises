package net.bohush.exercises.chapter40;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;

public class Exercise06 extends JApplet {

	private static final long serialVersionUID = 1L;

	private DefaultMutableTreeNode[] parts = new DefaultMutableTreeNode[5];
	private DefaultMutableTreeNode[] chapters = new DefaultMutableTreeNode[50];
	private int[] exercises = {12, 27, 35, 47, 39, 34, 36, 14, 31, 21, 12, 14,
			32, 29, 22, 40, 21, 37, 21, 39, 10, 22, 10, 25, 20, 11, 24, 9, 5, 25,
			17, 21, 10, 10, 11, 6, 10, 9, 14, 11, 8, 14, 20, 12, 5, 8, 7, 4, 18, 4}; 
	private JTextArea jta = new JTextArea();
	
	public Exercise06() {
				
		parts[0] = new DefaultMutableTreeNode("Part I: Fundamentals of Programming");
		parts[1] = new DefaultMutableTreeNode("Part II: Object-Oriented Programming");
		parts[2] = new DefaultMutableTreeNode("Part III: GUI Programming");
		parts[3] = new DefaultMutableTreeNode("Part IV: Data Structures and Algorithms");
		parts[4] = new DefaultMutableTreeNode("Part V: Advanced Java Programming");
		
		chapters[0] = new DefaultMutableTreeNode("Chapter 1: Introduction to Computers, Programming, and Java");
		chapters[1] = new DefaultMutableTreeNode("Chapter 2: Elementary Programming");
		chapters[2] = new DefaultMutableTreeNode("Chapter 3: Selections");
		chapters[3] = new DefaultMutableTreeNode("Chapter 4: Loops");
		chapters[4] = new DefaultMutableTreeNode("Chapter 5: Methods");
		chapters[5] = new DefaultMutableTreeNode("Chapter 6: Single-Dimensional Arrays");
		chapters[6] = new DefaultMutableTreeNode("Chapter 7: Multidimensional Arrays");
		chapters[7] = new DefaultMutableTreeNode("Chapter 8: Objects and Classes");
		chapters[8] = new DefaultMutableTreeNode("Chapter 9: Strings");
		chapters[9] = new DefaultMutableTreeNode("Chapter 10: Thinking in Objects");
		chapters[10] = new DefaultMutableTreeNode("Chapter 11: Inheritance and Polymorphism");
		chapters[11] = new DefaultMutableTreeNode("Chapter 12: GUI Basics");
		chapters[12] = new DefaultMutableTreeNode("Chapter 13: Graphics");
		chapters[13] = new DefaultMutableTreeNode("Chapter 14: Exception Handling and Text I/O");
		chapters[14] = new DefaultMutableTreeNode("Chapter 15: Abstract Classes and Interfaces");
		chapters[15] = new DefaultMutableTreeNode("Chapter 16: Event-Driven Programming");
		chapters[16] = new DefaultMutableTreeNode("Chapter 17: GUI Componenets");
		chapters[17] = new DefaultMutableTreeNode("Chapter 18: Applets and Multimedia");
		chapters[18] = new DefaultMutableTreeNode("Chapter 19: Binary I/O");
		chapters[19] = new DefaultMutableTreeNode("Chapter 20: Recursion");
		chapters[20] = new DefaultMutableTreeNode("Chapter 21: Generics");
		chapters[21] = new DefaultMutableTreeNode("Chapter 22: Lists, Stacks, Queues, and Priority Queues");
		chapters[22] = new DefaultMutableTreeNode("Chapter 23: Sets and Maps");
		chapters[23] = new DefaultMutableTreeNode("Chapter 24: Developing Efficient Algorithms");
		chapters[24] = new DefaultMutableTreeNode("Chapter 25: Sorting");
		chapters[25] = new DefaultMutableTreeNode("Chapter 26: Implementing Lists, Stacks, Queues, and Priority Queues");
		chapters[26] = new DefaultMutableTreeNode("Chapter 27: Binary Search Trees");
		chapters[27] = new DefaultMutableTreeNode("Chapter 28: Hashing");
		chapters[28] = new DefaultMutableTreeNode("Chapter 29: AVL Trees");
		chapters[29] = new DefaultMutableTreeNode("Chapter 30: Graphs and Applications");
		chapters[30] = new DefaultMutableTreeNode("Chapter 31: Weighted Graphs and Applications");
		chapters[31] = new DefaultMutableTreeNode("Chapter 32: Multithreading and Parallel Programming");
		chapters[32] = new DefaultMutableTreeNode("Chapter 33: Networking");
		chapters[33] = new DefaultMutableTreeNode("Chapter 34: Java Database Programming");
		chapters[34] = new DefaultMutableTreeNode("Chapter 35: Internationalization");
		chapters[35] = new DefaultMutableTreeNode("Chapter 36: JavaBeans and Bean Events");
		chapters[36] = new DefaultMutableTreeNode("Chapter 37: Containers, Layout Managers, and Borders");
		chapters[37] = new DefaultMutableTreeNode("Chapter 38: Menus, Toolbars, and Dialogs");
		chapters[38] = new DefaultMutableTreeNode("Chapter 39: MVC and Swing Models");
		chapters[39] = new DefaultMutableTreeNode("Chapter 40: JTable and JTree");
		chapters[40] = new DefaultMutableTreeNode("Chapter 41: Advanced Java Database Programming");
		chapters[41] = new DefaultMutableTreeNode("Chapter 42: Servlets");
		chapters[42] = new DefaultMutableTreeNode("Chapter 43: JavaServer Pages");
		chapters[43] = new DefaultMutableTreeNode("Chapter 44: JavaServer Faces 2");
		chapters[44] = new DefaultMutableTreeNode("Chapter 45: Web Services");
		chapters[45] = new DefaultMutableTreeNode("Chapter 46: Remote Method Invocation");
		chapters[46] = new DefaultMutableTreeNode("Chapter 47: 2-4 Trees and B-Trees");
		chapters[47] = new DefaultMutableTreeNode("Chapter 48: Red-Black Trees");
		chapters[48] = new DefaultMutableTreeNode("Chapter 49: Java 2D");
		chapters[49] = new DefaultMutableTreeNode("Chapter 50: Testing Using JUnit");
		
		parts[0].add(chapters[0]);
		parts[0].add(chapters[1]);
		parts[0].add(chapters[2]);
		parts[0].add(chapters[3]);
		parts[0].add(chapters[4]);
		parts[0].add(chapters[5]);
		parts[0].add(chapters[6]);
		parts[1].add(chapters[7]);
		parts[1].add(chapters[8]);
		parts[1].add(chapters[9]);
		parts[1].add(chapters[10]);
		parts[2].add(chapters[11]);
		parts[2].add(chapters[12]);
		parts[1].add(chapters[13]);
		parts[1].add(chapters[14]);
		parts[2].add(chapters[15]);
		parts[2].add(chapters[16]);
		parts[2].add(chapters[17]);
		parts[1].add(chapters[18]);
		parts[3].add(chapters[19]);
		parts[3].add(chapters[20]);
		parts[3].add(chapters[21]);
		parts[3].add(chapters[22]);
		parts[3].add(chapters[23]);
		parts[3].add(chapters[24]);
		parts[3].add(chapters[25]);
		parts[3].add(chapters[26]);
		parts[3].add(chapters[27]);
		parts[3].add(chapters[28]);
		parts[3].add(chapters[29]);
		parts[3].add(chapters[30]);
		parts[4].add(chapters[31]);
		parts[4].add(chapters[32]);
		parts[4].add(chapters[33]);
		parts[4].add(chapters[34]);
		parts[2].add(chapters[35]);
		parts[2].add(chapters[36]);
		parts[2].add(chapters[37]);
		parts[2].add(chapters[38]);
		parts[2].add(chapters[39]);
		parts[4].add(chapters[40]);
		parts[4].add(chapters[41]);
		parts[4].add(chapters[42]);
		parts[4].add(chapters[43]);
		parts[4].add(chapters[44]);
		parts[4].add(chapters[45]);
		parts[3].add(chapters[46]);
		parts[3].add(chapters[47]);
		parts[2].add(chapters[48]);
		parts[2].add(chapters[49]);
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Introduction to Java Programming");
		for (int i = 0; i < parts.length; i++) {
			root.add(parts[i]);	
		}
		
		JTree jTree = new JTree(root);
		jTree.addTreeSelectionListener(new TreeSelectionListener() {			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				for (int i = 0; i < chapters.length; i++) {
					if(e.getPath().getLastPathComponent().equals(chapters[i])) {
						String text = e.getPath().getParentPath().getLastPathComponent().toString() + "\n";
						text += e.getPath().getLastPathComponent() + "\n";
						text += "Number of exercises: " + exercises[i];
						jta.setText(text);
						return;
					}
				}				
			}
		});
		jta.setEditable(false);
	    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,  new JScrollPane(jTree), new JScrollPane(jta));
	    splitPane.setDividerLocation(400);
	    splitPane.setContinuousLayout(true);
		setLayout(new BorderLayout(5, 5));
		add(splitPane, BorderLayout.CENTER);

	}

	public static void main(String[] args) {
		Exercise06 applet = new Exercise06();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Exercise06");
		frame.getContentPane().add(applet, BorderLayout.CENTER);
		applet.init();
		applet.start();
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}