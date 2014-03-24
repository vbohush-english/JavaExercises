package net.bohush.exercises.chapter27;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Exercise17 extends JApplet {
	private ArrayList<Integer> sortedList = new ArrayList<>();
	private ArrayList<Integer> heapList = new ArrayList<>();
	private static final long serialVersionUID = 1L;
	private JTextField jTextField1 = new JTextField(10);
	private HeapClass heapClass = new HeapClass();

	public Exercise17() {
		setLayout(new BorderLayout());
		JPanel jPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		jPanel1.add(new JLabel("Enter a key: "));
		jPanel1.add(jTextField1);
		JButton jButton1 = new JButton("Insert");
		jPanel1.add(jButton1);
		JButton jButton2 = new JButton("Remove the root");
		jPanel1.add(jButton2);
		add(jPanel1, BorderLayout.SOUTH);
		jButton1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					sortedList.add(Integer.parseInt(jTextField1.getText()));
					heapSort(sortedList);
					heapClass.repaint();
					jTextField1.setText("");
					jTextField1.setFocusable(true);
				} catch (NumberFormatException e2) {
				}

			}
		});
		
		jButton2.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(sortedList.size() > 0) {
					sortedList.remove(sortedList.size() - 1);			
					heapSort(sortedList);
					heapClass.repaint();
				}
			}
		});
		
		add(heapClass, BorderLayout.CENTER);
	}
	
	class HeapClass extends JPanel {
		private static final long serialVersionUID = 1L;

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if(heapList.size() > 0) {
				drawNode(g, 0, getWidth() / 2, 60, 1);	
			}			
		}
		
		void drawNode(Graphics g, int i, int x, int y, int step) {
			if(2 * i + 1 < heapList.size()) {
				g.drawLine(x, y, x - (200 / step), y + 60);
				drawNode(g, 2 * i + 1, x - (200 / step), y + 60, step * 2);
			}
			if(2 * i + 2 < heapList.size()) {
				g.drawLine(x, y, x + (200 / step), y + 60);
				drawNode(g, 2 * i + 2, x + (200 / step), y + 60, step * 2);
			}
			g.setColor(getBackground());
			g.fillOval(x - 20, y - 20, 40, 40);
			g.setColor(Color.BLACK);
			g.drawOval(x - 20, y - 20, 40, 40);
			g.drawString(heapList.get(i) + "", x - 10, y);
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Exercise17");
		Exercise17 applet = new Exercise17();
		frame.add(applet);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(960, 500);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	@SuppressWarnings("unchecked")
	public void heapSort(ArrayList<Integer> list) {
		Heap<Integer> heap = new Heap<>();

		for (int i = 0; i < list.size(); i++) {
			heap.add(list.get(i));
		}
		
		heapList = 	(ArrayList<Integer>) heap.getHeapList().clone();
		
		for (int i = list.size() - 1; i >= 0; i--) {
			list.set(i, heap.remove());
		}
	}

	static public class Heap<E extends Comparable<E>> {
		private java.util.ArrayList<E> list = new java.util.ArrayList<E>();

		/** Create a default heap */
		public Heap() {
		}
		
		public java.util.ArrayList<E> getHeapList() {
			return list;
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