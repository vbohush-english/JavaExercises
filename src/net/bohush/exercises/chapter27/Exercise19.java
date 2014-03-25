package net.bohush.exercises.chapter27;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class Exercise19 extends JPanel {
	private static final long serialVersionUID = 1L;
	private Tree tree;
	private JTextField jtfText = new JTextField();
	private JTextField jtfBitString = new JTextField();
	private JButton jbtShow = new JButton("Show Huffman Tree");
	private JButton jbtDecode = new JButton("Decode Text");
	private TreeView view = new TreeView();
	private int[] counts;
	private String[] codes;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Exercise19");
		JApplet applet = new DisplayTree();
		frame.add(applet);
		frame.setSize(900, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	static class DisplayTree extends JApplet {
		private static final long serialVersionUID = 1L;
		public DisplayTree() {
			add(new Exercise19());
		}
	}

	public Exercise19() {
		setUI();
	}

	/** Initialize UI for binary tree */
	private void setUI() {
		this.setLayout(new BorderLayout(5, 5));
		add(view, BorderLayout.CENTER);
		JPanel panel1 = new JPanel(new BorderLayout(5, 5));
		panel1.add(new JLabel("  Enter a text: "), BorderLayout.WEST);
		panel1.add(jtfText, BorderLayout.CENTER);
		panel1.add(jbtShow, BorderLayout.EAST);
		add(panel1, BorderLayout.NORTH);
		
		JPanel panel2 = new JPanel(new BorderLayout(5, 5));
		panel2.add(new JLabel("  Enter a bit string: "), BorderLayout.WEST);
		panel2.add(jtfBitString, BorderLayout.CENTER);
		panel2.add(jbtDecode, BorderLayout.EAST);
		add(panel2, BorderLayout.SOUTH);

		jbtDecode.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(codes == null) {
					JOptionPane.showMessageDialog(null, "No tree", "Decode Text to Bits", JOptionPane.WARNING_MESSAGE);
				} else if(jtfBitString.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "No codes", "Decode Text to Bits", JOptionPane.WARNING_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, decode(), "Decode Text to Bits", JOptionPane.INFORMATION_MESSAGE);
				}				
			}
		});
		
		jbtShow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = jtfText.getText();
				if (text.length() == 0) {
					tree = null;
					counts = null;
					codes = null;
					view.repaint();					
				} else {
					counts = getCharacterFrequency(text);
					tree = getHuffmanTree(counts);
					codes = getCode(tree.root);
					StringBuilder result = new StringBuilder(text + " is encoded to ");
					for (int i = 0; i < text.length(); i++) {
						result.append(codes[text.charAt(i)]);
					}
					view.repaint();
					JOptionPane.showMessageDialog(null, result, "Encode Text to Bits", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
	}

	private String decode() {
		String text = jtfBitString.getText();
		StringBuilder result = new StringBuilder(text + " is encoded to ");
		while(text.length()!= 0) {
			boolean isOk = false;
			for (int i = 0; i < codes.length; i++) {
				if((codes[i] != null)&&(text.indexOf(codes[i]) == 0)) {
					result.append((char)i);
					text = text.substring(codes[i].length(), text.length());
					isOk = true;
					break;
				}
			}
			if(!isOk) {
				return "Error while decoding";
			}
		}
		return result.toString();
	}
	
	// Inner class TreeView for displaying a tree on a panel
	class TreeView extends JPanel {
		private static final long serialVersionUID = 1L;
		private int radius = 20; // Tree node radius
		private int vGap = 50; // Gap between two levels in a tree

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			if (((tree != null)) && (tree.root != null)) {
				displayTree(g, tree.root, getWidth() / 2, 30, getWidth() / 4, 0);
			}
		}

		/** Display a subtree rooted at position (x, y) */
		private void displayTree(Graphics g, Tree.Node root, int x, int y, int hGap, int depth) {

			g.drawOval(x - radius, y - radius, 2 * radius, 2 * radius);
			g.drawString(root.element + "", x - 6, y + radius + 15);
			g.drawString(root.weight + "", x - 6, y + 4);

			if (root.left != null) {
				// Draw a line to the left node
				connectTwoCircles(g, x - hGap, y + vGap, x, y);
				printCode(g, root.left.code.charAt(depth), x - hGap, y + vGap, x, y);
				// Draw the left subtree recursively
				displayTree(g, root.left, x - hGap, y + vGap, hGap / 2, depth + 1);
			}

			if (root.right != null) {
				// Draw a line to the right node
				connectTwoCircles(g, x + hGap, y + vGap, x, y);
				printCode(g, root.right.code.charAt(depth), x + hGap, y + vGap, x, y);
				// Draw the right subtree recursively
				displayTree(g, root.right, x + hGap, y + vGap, hGap / 2, depth + 1);
			}
			
		}

		/** Connect two circles centered at (x1, y1) and (x2, y2) */
		private void connectTwoCircles(Graphics g, int x1, int y1, int x2,	int y2) {
			double d = Math.sqrt(vGap * vGap + (x2 - x1) * (x2 - x1));
			int x11 = (int) (x1 - radius * (x1 - x2) / d);
			int y11 = (int) (y1 - radius * (y1 - y2) / d);
			int x21 = (int) (x2 + radius * (x1 - x2) / d);
			int y21 = (int) (y2 + radius * (y1 - y2) / d);
			g.drawLine(x11, y11, x21, y21);
		}
		
		/** Connect two circles centered at (x1, y1) and (x2, y2) */
		private void printCode(Graphics g, char code, int x1, int y1, int x2,	int y2) {
			double d = Math.sqrt(vGap * vGap + (x2 - x1) * (x2 - x1));
			int x11 = (int) (x1 - radius * (x1 - x2) / d);
			int y11 = (int) (y1 - radius * (y1 - y2) / d);
			int x21 = (int) (x2 + radius * (x1 - x2) / d);
			int y21 = (int) (y2 + radius * (y1 - y2) / d);
			g.drawString("" + code, (x21 + x11) / 2, (y21 + y11) / 2 - 5);
		}
	}
	
	
	/**
	 * Get Huffman codes for the characters This method is called once after a
	 * Huffman tree is built
	 */
	public static String[] getCode(Tree.Node root) {
		if (root == null)
			return null;
		String[] codes = new String[2 * 128];
		assignCode(root, codes);
		return codes;
	}

	/* Recursively get codes to the leaf node */
	private static void assignCode(Tree.Node root, String[] codes) {
		if (root.left != null) {
			root.left.code = root.code + "0";
			assignCode(root.left, codes);

			root.right.code = root.code + "1";
			assignCode(root.right, codes);
		} else {
			codes[(int) root.element] = root.code;
		}
	}

	/** Get a Huffman tree from the codes */
	public static Tree getHuffmanTree(int[] counts) {
		// Create a heap to hold trees
		Heap<Tree> heap = new Heap<Tree>(); // Defined in Listing 24.10
		for (int i = 0; i < counts.length; i++) {
			if (counts[i] > 0)
				heap.add(new Tree(counts[i], (char) i)); // A leaf node tree
		}

		while (heap.getSize() > 1) {
			Tree t1 = heap.remove(); // Remove the smallest weight tree
			Tree t2 = heap.remove(); // Remove the next smallest weight
			heap.add(new Tree(t1, t2)); // Combine two trees
		}

		return heap.remove(); // The final tree
	}

	/** Get the frequency of the characters */
	public static int[] getCharacterFrequency(String text) {
		int[] counts = new int[256]; // 256 ASCII characters

		for (int i = 0; i < text.length(); i++)
			counts[(int) text.charAt(i)]++; // Count the character in text

		return counts;
	}

	/** Define a Huffman coding tree */
	public static class Tree implements Comparable<Tree> {
		Node root; // The root of the tree

		/** Create a tree with two subtrees */
		public Tree(Tree t1, Tree t2) {
			root = new Node();
			root.left = t1.root;
			root.right = t2.root;
			root.weight = t1.root.weight + t2.root.weight;
		}

		/** Create a tree containing a leaf node */
		public Tree(int weight, char element) {
			root = new Node(weight, element);
		}

		@Override
		/** Compare trees based on their weights */
		public int compareTo(Tree t) {
			if (root.weight < t.root.weight) // Purposely reverse the order
				return 1;
			else if (root.weight == t.root.weight)
				return 0;
			else
				return -1;
		}

		public class Node {
			char element; // Stores the character for a leaf node
			int weight; // weight of the subtree rooted at this node
			Node left; // Reference to the left subtree
			Node right; // Reference to the right subtree
			String code = ""; // The code of this node from the root

			/** Create an empty node */
			public Node() {
			}

			/** Create a node with the specified weight and character */
			public Node(int weight, char element) {
				this.weight = weight;
				this.element = element;
			}
		}
	}

	static class Heap<E extends Comparable<E>> {
		private java.util.ArrayList<E> list = new java.util.ArrayList<E>();

		/** Create a default heap */
		public Heap() {
		}

		/** Create a heap from an array of objects */
		public Heap(E[] objects) {
			for (int i = 0; i < objects.length; i++)
				add(objects[i]);
		}

		/** Add a new object into the heap */
		public void add(E newObject) {
			list.add(newObject); // Append to the heap
			int currentIndex = list.size() - 1; // The index of the last node

			while (currentIndex > 0) {
				int parentIndex = (currentIndex - 1) / 2;
				// Swap if the current object is greater than its parent
				if (list.get(currentIndex).compareTo(list.get(parentIndex)) > 0) {
					E temp = list.get(currentIndex);
					list.set(currentIndex, list.get(parentIndex));
					list.set(parentIndex, temp);
				} else
					break; // the tree is a heap now

				currentIndex = parentIndex;
			}
		}

		/** Remove the root from the heap */
		public E remove() {
			if (list.size() == 0)
				return null;

			E removedObject = list.get(0);
			list.set(0, list.get(list.size() - 1));
			list.remove(list.size() - 1);

			int currentIndex = 0;
			while (currentIndex < list.size()) {
				int leftChildIndex = 2 * currentIndex + 1;
				int rightChildIndex = 2 * currentIndex + 2;

				// Find the maximum between two children
				if (leftChildIndex >= list.size())
					break; // The tree is a heap
				int maxIndex = leftChildIndex;
				if (rightChildIndex < list.size()) {
					if (list.get(maxIndex).compareTo(list.get(rightChildIndex)) < 0) {
						maxIndex = rightChildIndex;
					}
				}

				// Swap if the current node is less than the maximum
				if (list.get(currentIndex).compareTo(list.get(maxIndex)) < 0) {
					E temp = list.get(maxIndex);
					list.set(maxIndex, list.get(currentIndex));
					list.set(currentIndex, temp);
					currentIndex = maxIndex;
				} else
					break; // The tree is a heap
			}

			return removedObject;
		}

		/** Get the number of nodes in the tree */
		public int getSize() {
			return list.size();
		}
	}
}