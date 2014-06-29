package net.bohush.exercises.chapter25;

import java.util.Comparator;

public class Exercise05 {
	
	static class IntegerComparator implements Comparator<Integer> {
		@Override
		public int compare(Integer o1, Integer o2) {
			return o1.intValue() - o2.intValue();
		}		
	}

	/** Heap sort method */
	public static <E> void heapSort(E[] list, Comparator<? super E> comparator) {
		// Create a Heap of integers
		Heap<E> heap = new Heap<E>(comparator);

		// Add elements to the heap
		for (int i = 0; i < list.length; i++)
			heap.add(list[i]);

		// Remove elements from the heap
		for (int i = list.length - 1; i >= 0; i--)
			list[i] = heap.remove();
	}

	/** A test method */
	public static void main(String[] args) {
		Integer[] list = { -44, -5, -3, 3, 3, 1, -4, 0, 1, 2, 4, 5, 53 };
		heapSort(list, new IntegerComparator());
		for (int i = 0; i < list.length; i++)
			System.out.print(list[i] + " ");
	}
	
	
	static class Heap  <E>  {
		private Comparator<? super E> comparator;
		
		private java.util.ArrayList<E> list = new java.util.ArrayList<E>();

		/** Create a default heap */
		public Heap(Comparator<? super E> comparator) {
			this.comparator = comparator;
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
				if (comparator.compare(list.get(currentIndex), (list.get(parentIndex))) > 0) {
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
					if (comparator.compare(list.get(maxIndex), (list.get(rightChildIndex))) < 0) {
						maxIndex = rightChildIndex;
					}
				}

				// Swap if the current node is less than the maximum
				if (comparator.compare(list.get(currentIndex), (list.get(maxIndex))) < 0) {
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