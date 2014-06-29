package net.bohush.exercises.chapter25;

import java.util.ArrayList;

public class Exercise11 {

	public static void main(String[] args) throws CloneNotSupportedException {
		Heap<Integer> heap1 = new Heap<>();
		heap1.add(1);
		heap1.add(2);
		heap1.add(3);
		
		Heap<Integer> heap2 = new Heap<>();
		heap2.add(3);
		heap2.add(1);
		heap2.add(2);
		
		
		Heap<Integer> heap3 = new Heap<>();
		heap3.add(2);
		heap3.add(2);
		heap3.add(3);
		
		System.out.println(heap1.equals(heap2));
		System.out.println(heap1.equals(heap3));
		
		@SuppressWarnings("unchecked")
		Heap<Integer> heap4 = (Heap<Integer>) heap1.clone();
		System.out.println(heap1.equals(heap4));
		System.out.println(heap1 == heap4);
	}

	static public class Heap<E extends Comparable<E>> implements Cloneable {
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
		
		@SuppressWarnings("unchecked")
		@Override
		protected Object clone() throws CloneNotSupportedException {
			Heap<E> newHeap = new Heap<>();
			newHeap.list = (ArrayList<E>) list.clone();
			return newHeap;
		}
		
		
		@SuppressWarnings("unchecked")
		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Heap) {
				return list.equals(((Heap<E>)(obj)).list);
			} else {
				return false;
			}
			
		}
	}

}
